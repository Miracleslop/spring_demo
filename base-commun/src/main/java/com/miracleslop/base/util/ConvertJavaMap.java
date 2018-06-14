package com.miracleslop.base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConvertJavaMap {

    public static class BeanMap extends AbstractMap {
        public static BeanMap create(Object bean) {
            return new BeanMap();
        }


        @Override
        public Set<Entry> entrySet() {
            return null;
        }

        public Class getPropertyType(String key) {
            return Object.class;
        }
    }

    /**
     * 将对象装换为map
     *
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将对象装换为map
     *
     * @param bean
     * @return
     */
    public static <T> Map<String, String> beanToMapString(T bean) {
        Map<String, String> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                if (beanMap.get(key) != null) {
                    map.put(key + "", beanMap.get(key).toString());
                }
            }
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     *
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * 将map装换为javabean对象,包含类型处理
     *
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBeanInType(Map<String, Object> map, T bean) throws ParseException {
        BeanMap beanMap = BeanMap.create(bean);
        for (String key : map.keySet()) {
            Class propertyType = beanMap.getPropertyType(key);
            switch (propertyType.getSimpleName()) {
                case "Long":
                    beanMap.put(key, Long.valueOf(map.get(key).toString()));
                    break;
                case "Integer":
                    beanMap.put(key, Integer.valueOf(map.get(key).toString()));
                    break;
                case "Date":
                    DateFormat df = new SimpleDateFormat();
                    Date parse = df.parse(map.get(key).toString());
                    beanMap.put(key, parse);
                    break;
                default:
                    beanMap.put(key, map.get(key));
            }
        }
        return bean;
    }

    /**
     * 将List<T>转换为List<Map<String, Object>>
     *
     * @param objList
     * @return
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0, size = objList.size(); i < size; i++) {
                bean = objList.get(i);
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     *
     * @param maps
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList<>();
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0, size = maps.size(); i < size; i++) {
                map = maps.get(i);
                bean = clazz.newInstance();
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
    }
}
