package com.mall.goods.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mall.common.util.MallStringUtil;
import com.mall.common.vo.MallResult;
import com.mall.common.vo.PageResult;
import com.mall.goods.mapper.GoodsMapper;
import com.mall.model.dto.Goods;
import com.mall.model.vo.GoodsFindVo;
import com.mall.model.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.mall.common.CommonConst.REGEX_COMMA;

@Service
@Slf4j
public class GoodsServiceImpl {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private CatServiceImpl catService;

    @Autowired
    private BrandServiceImpl brandService;

    public GoodsVo findById(Integer goodsId) {
        Goods goods = goodsMapper.findById(goodsId);

        GoodsVo goodsVo = new GoodsVo();
        BeanUtils.copyProperties(goods,goodsVo);

        String simpleImg = goods.getSimpleImg();
        String descImg = goods.getDescImg();

        goodsVo.setSimpleImgList(MallStringUtil.convertStringToListBySeparator(simpleImg,REGEX_COMMA));
        goodsVo.setDescImgList(MallStringUtil.convertStringToListBySeparator(descImg,REGEX_COMMA));

        goodsVo.setBrand(brandService.findById(goods.getBrandId()));
        goodsVo.setCat(catService.findByIdDeep(goods.getCatId()));


        return goodsVo;
    }

    public PageResult<Goods> findList(GoodsFindVo vo) {
        Page<Object> page = PageHelper.startPage(vo.getPage(), vo.getPageSize());

        List<Goods> list = goodsMapper.findList(vo);

        PageResult<Goods> result = new PageResult<>();
        result.setData(list);
        result.setPageNum(vo.getPage());
        result.setPageSize(vo.getPageSize());
        result.setTotal(page.getTotal());

        return result;
    }

    @Transactional
    public MallResult<Void> reduceGoodsStock(Integer goodsId,Integer count) {
        int res = goodsMapper.updateStock(goodsId,count);
        if (res == 0) {
            return MallResult.fail("300","库存不足");
        }
        return MallResult.success();
    }
}
