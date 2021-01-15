package com.mall.goods.service.impl;

import com.mall.goods.mapper.CatMapper;
import com.mall.model.dto.Cat;
import com.mall.model.vo.CatVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CatServiceImpl {

    @Autowired
    private CatMapper catMapper;

    /**
     * 根据分类ID查询分类信息，并且查询到所有上级分类信息
     * @return cat
     */
    public CatVo findByIdDeep(Integer catId) {
        CatVo catVo = new CatVo();

        Cat cat = findById(catId);
        if (cat == null) {
            return catVo;
        }
        BeanUtils.copyProperties(cat,catVo);
        List<Cat> list = new ArrayList<>();
        catVo.setParentCatList(list);
        findDeep(cat, list);

        Collections.reverse(list);
        return catVo;
    }

    private void findDeep(Cat cat, List<Cat> list) {
        Integer parentId = cat.getParentId();
        if (parentId == null) {
            return;
        }

        Cat parentCat = findById(parentId);
        if (parentCat != null) {
            list.add(parentCat);
            findDeep(parentCat,list);
        }
    }


    /**
     * 根据分类ID查询分类信息，并且查询到所有上级分类信息
     * @return cat
     */
    public Cat findById(Integer catId) {
        return catMapper.findById(catId);
    }
}
