package com.miaotech.common.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 源自： https://blog.csdn.net/u010024991/article/details/97258984
 * 提供Bean对象的相互转化
 */
public class BeanUtils {
    /**
     * bean对象之间转换
     * @param src
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T transform(Object src, Class<T> clazz) {
        if (src == null) {
            return null;
        } else {
            Object instance = null;

            try {
                instance = clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            org.springframework.beans.BeanUtils.copyProperties(src, instance, getNullPropertyNames(src));
            return (T) instance;
        }
    }

    private static String[] getNullPropertyNames(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = (Set) Arrays.stream(pds).filter((pd) -> {
            return src.getPropertyValue(pd.getName()) == null;
        }).map(FeatureDescriptor::getName).distinct().collect(Collectors.toSet());
        String[] result = new String[emptyNames.size()];
        return (String[]) emptyNames.toArray(result);
    }

}
