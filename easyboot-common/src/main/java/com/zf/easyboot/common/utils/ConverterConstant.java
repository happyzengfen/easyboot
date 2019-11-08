package com.zf.easyboot.common.utils;

import com.google.common.base.Joiner;
import com.zf.easyboot.common.constant.CommonConstant;
import com.zf.easyboot.common.enums.HttpStatus;
import com.zf.easyboot.common.exception.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;


import java.util.List;
import java.util.stream.Collectors;

/**
 * object 中转换类
 *
 * @author 疯信子
 * @version 1.0
 * @date 2019/9/21.
 */
public class ConverterConstant {

    @FunctionalInterface
    public static interface Converter<F, T> {
        T convert(F from);
    }

    //转换为object为String 的方法
    public static Converter<Object, String> converterStr = (from) -> {
        if (from == null) {
            return "";
        }
        return String.valueOf(from);
    };


    public static Converter<Object, Integer> converterInt = (from) -> {
        if (from == null) {
            return null;
        }
        return Integer.valueOf(converterStr.convert(from));
    };


    public static Converter<List<Long>, String> converterListToStr = (from) -> {
        if (CollectionUtils.isEmpty(from)) {
            return "";
        }

        List<String> list = from.parallelStream().filter(item -> StringUtils.isNotBlank(String.valueOf(item)))
                .map(item -> "'" + item + "'").collect(Collectors.toList());

        return Joiner.on(",").join(list);
    };

    //分页信息
    public  static  Converter<Object, Integer>  converterPageInfo=(from) ->{
        if (from == null) {
            return null;
        }

        Integer page=Integer.valueOf(converterStr.convert(from));;

        //小于0直接抛出异常
        if(page<0){
            throw  new BaseException(HttpStatus.PAGE_ERROR);
        }
        // 大于10000也直接抛出异常
        if(page> CommonConstant.PAGESIZE){
            throw  new BaseException(HttpStatus.PAGE_ERROR);
        }

        return  page;
    };
    public static Converter<String, Integer> converterInts = Integer::valueOf;


    public static Converter<Long, String> converterLong = String::valueOf;


}
