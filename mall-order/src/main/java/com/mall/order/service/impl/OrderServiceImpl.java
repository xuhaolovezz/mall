package com.mall.order.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mall.common.enums.OrderStatus;
import com.mall.common.enums.ShippingStatus;
import com.mall.common.exception.BusinessException;
import com.mall.common.vo.MallResult;
import com.mall.common.vo.Oauth2User;
import com.mall.common.vo.PageResult;
import com.mall.model.dto.Goods;
import com.mall.model.dto.Order;
import com.mall.model.dto.OrderGoods;
import com.mall.model.dto.UserAddress;
import com.mall.model.vo.AddOrderVo;
import com.mall.model.vo.GoodsVo;
import com.mall.model.vo.OrderAndGoodsVo;
import com.mall.model.vo.UserFindOrderVo;
import com.mall.order.feign.IGoodsServiceClient;
import com.mall.order.feign.IUserAddressServiceClient;
import com.mall.order.mapper.OrderGoodsMapper;
import com.mall.order.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    @Autowired
    private IGoodsServiceClient goodsServiceClient;

    @Autowired
    private IUserAddressServiceClient userAddressServiceClient;

/*    @Autowired
    private DefaultUidGenerator defaultUidGenerator;*/

    public PageResult<Order> findListByUserId(UserFindOrderVo vo) {
        /*try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Page<Object> page = PageHelper.startPage(vo.getPage(), vo.getPageSize());

        List<Order> list = orderMapper.findListByUserId(vo.getUserId());
        return new PageResult<>(vo.getPage(),vo.getPageSize(),page.getTotal(),list);
    }

    @Transactional
    public void create(AddOrderVo orderAndGoodsVo) {

        Integer userId = orderAndGoodsVo.getUserId();

        Order order = new Order();
        order.setUserId(userId);

        // 设置订单的收货地址
        setOrderAddress(orderAndGoodsVo,order);


        // 要购买的商品ID列表，通过商品ID去购物车查询购买的数量和商品信息
        List<Integer> list = orderAndGoodsVo.getGoodsList();

        BigDecimal goodsPrice = new BigDecimal("0");
        BigDecimal shippingPrice = new BigDecimal("10");


        List<OrderGoods> orderGoodsList = new ArrayList<>(list.size());
        for (Integer goodsId : list) {

            MallResult<GoodsVo> result = goodsServiceClient.findById(goodsId);
            if (!result.isSuccess()) {
                log.error("查询商品信息失败,商品ID为：{},异常消息：{}",goodsId,result.getMsg());
                throw new BusinessException("未查询到商品信息");
            }

            Goods goods = result.getData();
            if (goods == null) {
                throw new BusinessException("未查询到商品信息");
            }
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setGoodsId(goodsId);
            orderGoods.setGoodsName(goods.getName());
            orderGoods.setGoodsSn(goods.getNum());
            orderGoods.setIsSend(0);
            orderGoods.setIsComment(0);
            orderGoods.setMarketPrice(goods.getMarketPrice());
            orderGoods.setGoodsPrice(goods.getSalePrice());
            orderGoods.setGoodsNum((short)1);
            orderGoods.setOriginalImg(goods.getCoverImg());


            // 累加商品金额和实际金额
            goodsPrice = goodsPrice.add(goods.getMarketPrice());

            orderGoodsList.add(orderGoods);

            // 商品库存-1
            MallResult<Void> mallResult = goodsServiceClient.reduceGoodsStock(goodsId, 1);
            if (!mallResult.isSuccess()) {
                throw new RuntimeException("扣减库存失败");
            }
        }

        // TODO
        //order.setOrderId(defaultUidGenerator.getUID());
        order.setStatus(OrderStatus.CONFIRMED.getCode());
        order.setShippingStatus(ShippingStatus.UN_SHIPPED.getCode());
        order.setAddTime(new Date());
        order.setOrderAmount(goodsPrice);
        order.setShippingPrice(shippingPrice);
        order.setTotalAmount(goodsPrice);
        order.setGoodsPrice(goodsPrice);
        order.setUserNote(orderAndGoodsVo.getUserNote());
        orderMapper.insert(order);

        for (OrderGoods orderGoods : orderGoodsList) {
            orderGoods.setOrderId(order.getOrderId());
            orderGoodsMapper.insert(orderGoods);
        }
        log.debug("插入订单数据======》");
    }

    private void setOrderAddress(AddOrderVo orderAndGoodsVo, Order order) {
        // 跟据addressId查询收货地址信息
        Integer addressId = orderAndGoodsVo.getAddressId();
        MallResult<UserAddress> result = userAddressServiceClient.findById(addressId);
        if (!result.isSuccess()) {
            log.error("查询收货地址失败，地址ID：{}，失败消息：{}",addressId,result.getCode());
            throw new BusinessException("未查询到收货地址");
        }
        UserAddress userAddress = result.getData();
        order.setMobile(userAddress.getMobile());
        order.setAddress(userAddress.getAddress());
        order.setConsignee(userAddress.getConsignee());
        order.setZipcode(userAddress.getZipcode());
    }

    public PageResult<OrderAndGoodsVo> findByCurrentUser() {
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        Oauth2User oauth2User = (Oauth2User) authentication.getPrincipal();

        UserFindOrderVo vo = new UserFindOrderVo();
        vo.setUserId(oauth2User.getUserId());
        vo.setPage(1);
        vo.setPageSize(10);
        PageResult<Order> result = findListByUserId(vo);
        List<Order> data = result.getData();

        List<OrderAndGoodsVo> list = new ArrayList<>(data.size());
        for (Order order : data) {
            List<OrderGoods> orderGoodsList = findGoodsByOrderId(order.getOrderId());
            OrderAndGoodsVo orderAndGoodsVo = new OrderAndGoodsVo();
            orderAndGoodsVo.setOrder(order);
            orderAndGoodsVo.setGoodsList(orderGoodsList);
            list.add(orderAndGoodsVo);
        }

        return new PageResult<>(result.getPageNum(),result.getPageSize(),result.getTotal(),list);
    }

    public List<OrderGoods> findGoodsByOrderId(Integer orderId) {
        return orderGoodsMapper.findByOrderId(orderId);
    }
}
