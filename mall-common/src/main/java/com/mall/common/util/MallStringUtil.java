package com.mall.common.util;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MallStringUtil {

    public static List<String> convertStringToListBySeparator(String str,String separator){

        if (StringUtils.isEmpty(str)) {
            return new ArrayList<>();
        }

        String[] arr = str.split(separator);
        return Arrays.asList(arr);
    }

}
