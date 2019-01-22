
package com.spring.boot.common.utils;

import com.google.common.collect.Maps;
import com.spring.boot.annotation.FieldNameAnnotation;
import com.spring.boot.entity.CompareValue;
import com.spring.boot.vo.CompareObjectVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author weitao
 * @version $$Id: FieldValueCompareUtil.java, v 0.1 2019/1/16 16:15 weitao Exp $$
 */
@Slf4j
public class FieldValueCompareUtil {

    final private static String DATE_CLASS = "class java.util.Date";
    final private static String BIGDECIMAL_CLASS = "class java.math.BigDecimal";

    public static List<CompareValue> compareValue(Object obj1, Object obj2) {
        List<CompareValue> list = new ArrayList<>();
        try {
            Field[] fields1 = obj1.getClass().getDeclaredFields();
            List<Field> fields2 = new ArrayList<>();
            Class tempClass = obj2.getClass();
            while (tempClass != null) {
                fields2.addAll(Arrays.asList(tempClass.getDeclaredFields()));
                tempClass = tempClass.getSuperclass();
            }
            Map<String, Field> fieldMap = Maps.newHashMap();
            for (Field field : fields2) {
                field.setAccessible(true);
                fieldMap.put(field.getName(), field);
            }
            for (Field field1 : fields1) {
                field1.setAccessible(true);
                Field field2 = fieldMap.get(field1.getName());
                if (field1.get(obj1) == null && field2.get(obj2) == null) {
                    continue;
                }
                if (field1.getName().equals(field2.getName()) &&
                        !"updateTime".equals(field1.getName()) && !"updateUser".equals(field1.getName()) &&
                        !(field1.get(obj1) == null ? "" : field1.get(obj1)).equals(field2.get(obj2))) {
                    CompareValue compareValue = new CompareValue();
                    compareValue.setField(field1.getName());
                    compareValue.setOldVal(String.valueOf(field1.get(obj1)));
                    compareValue.setNewVal(String.valueOf(field2.get(obj2)));
                    list.add(compareValue);
                }
            }

        } catch (Exception e) {
            log.error("对比属性值失败",e);
        }
        return list;
    }

    public static List<String> compareValueContent(Object obj1, Object obj2, String code) {
        List<String> list = new ArrayList<>();
        try {
            Field[] fields1 = obj1.getClass().getDeclaredFields();
            List<Field> fields2 = new ArrayList<>();
            Class tempClass = obj2.getClass();
            while (tempClass != null) {
                fields2.addAll(Arrays.asList(tempClass.getDeclaredFields()));
                tempClass = tempClass.getSuperclass();
            }
            Map<String, Field> fieldMap = Maps.newHashMap();
            for (Field field : fields2) {
                field.setAccessible(true);
                fieldMap.put(field.getName(), field);
            }
            for (Field field1 : fields1) {
                field1.setAccessible(true);
                Field field2 = fieldMap.get(field1.getName());
                if (field1.get(obj1) == null && field2.get(obj2) == null) {
                    continue;
                }
                if (field1.getName().equals(field2.getName()) &&
                        !"updateTime".equals(field1.getName()) && !"updateUser".equals(field1.getName()) &&
                        !(field1.get(obj1) == null ? "" : field1.get(obj1)).equals(field2.get(obj2))) {
                    CompareValue compareValue = new CompareValue();
                    compareValue.setField(field1.getName());
                    compareValue.setOldVal(String.valueOf(field1.get(obj1)));
                    compareValue.setNewVal(String.valueOf(field2.get(obj2)));
                    if (field1.isAnnotationPresent(FieldNameAnnotation.class)) {
                        String name = field1.getAnnotation(FieldNameAnnotation.class).name();
                        String message;
                        if (StringUtils.isNotBlank(code)) {
                            message = MessageFormat.format("将商品({0})【{1}】由【{2}】改为【{3}】", code, name, compareValue.getOldVal(),
                                    compareValue.getNewVal());
                        } else {
                            message = MessageFormat.format("将【{0}】由【{1}】改为【{2}】", name, compareValue.getOldVal(),
                                    compareValue.getNewVal());
                        }

                        list.add(message);
                    }
                }
            }

        } catch (Exception e) {
            log.error("对比属性值失败",e);
        }
        return list;
    }


    public static List<String> compareValueContentByObject(Object obj1, Object obj2, String code, CompareObjectVo vo) {
        if (StringUtils.isBlank(code)) {
            code = "";
        }
        List<String> list = new ArrayList<>();
        try {
            if (obj1 == null && obj2 != null) {
                String message = MessageFormat.format("新增", code);
                list.add(message);
                return list;
            }

            if (obj1 != null && obj2 == null) {
                String message = MessageFormat.format("删除", code);
                list.add(message);
                return list;
            }

            if (obj1 == null && obj2 == null) {
                throw new RuntimeException("obj1和obj2不能同时为null");
            }

            List<Field> fields1 = new ArrayList<>();
            Class tempClass1 = obj1.getClass();
            while (tempClass1 != null) {
                fields1.addAll(Arrays.asList(tempClass1.getDeclaredFields()));
                tempClass1 = tempClass1.getSuperclass();
            }
            List<Field> fields2 = new ArrayList<>();
            Class tempClass = obj2.getClass();
            while (tempClass != null) {
                fields2.addAll(Arrays.asList(tempClass.getDeclaredFields()));
                tempClass = tempClass.getSuperclass();
            }
            Map<String, Field> fieldMap = Maps.newHashMap();
            for (Field field : fields2) {
                field.setAccessible(true);
                fieldMap.put(field.getName(), field);
            }
            for (Field field1 : fields1) {
                field1.setAccessible(true);
                Field field2 = fieldMap.get(field1.getName());
                if (field1.get(obj1) == null && field2.get(obj2) == null) {
                    continue;
                }
                handleBigDecimal(obj1, obj2, field1, field2);
                if (field1.getName().equals(field2.getName()) &&
                        !"updateTime".equals(field1.getName()) && !"updateUser".equals(field1.getName()) &&
                        !(field1.get(obj1) == null ? "" : field1.get(obj1)).equals(field2.get(obj2))) {
                    CompareValue compareValue = new CompareValue();
                    compareValue.setField(field1.getName());
                    compareValue.setOldVal(field1.get(obj1)==null?" ":String.valueOf(field1.get(obj1)));
                    compareValue.setNewVal(field2.get(obj2)==null?" ":String.valueOf(field2.get(obj2)));
                    if (field1.isAnnotationPresent(FieldNameAnnotation.class)) {
                        String name;
                        try {
                             name = field1.getAnnotation(FieldNameAnnotation.class).name();
                        }catch (Exception e){
                             name = field2.getAnnotation(FieldNameAnnotation.class).name();
                        }
                        String message;
                        if (DATE_CLASS.equals(field1.getType().toString())) {
                            compareValue.setOldVal(field1.get(obj1) == null ? " " : new SimpleDateFormat(field1.getAnnotation(FieldNameAnnotation.class).dateFormat()).format(field1.get(obj1)));
                            compareValue.setNewVal(field2.get(obj2) == null ? " " : new SimpleDateFormat(field2.getAnnotation(FieldNameAnnotation.class).dateFormat()).format(field2.get(obj2)));
                        }
                        message = MessageFormat.format("将【{0}】由【{1}】改为【{2}】", name, compareValue.getOldVal(),
                                compareValue.getNewVal());
                        list.add(message);
                    }
                }
            }

        } catch (
                Exception e) {
            log.error("对比属性值失败",e);
        }
        return list;
    }

    public static List<String> compareValueContentByList(Object obj1, Object obj2, String code,CompareObjectVo vo) {
        if (StringUtils.isBlank(code)) {
            code = "";
        }
        List<String> list = new ArrayList<>();
        try {
            if (obj1 == null && obj2 != null) {
                String message = MessageFormat.format("新增了商品{0}", code);
                list.add(message);
                return list;
            }
            if (obj1 != null && obj2 == null) {
                String message = MessageFormat.format("删除了商品{0}", code);
                list.add(message);
                return list;
            }

            if (obj1 == null && obj2 == null) {
                throw new RuntimeException("obj1和obj2不能同时为null");
            }

            List<Field> fields1 = new ArrayList<>();
            Class tempClass1 = obj1.getClass();
            while (tempClass1 != null) {
                fields1.addAll(Arrays.asList(tempClass1.getDeclaredFields()));
                tempClass1 = tempClass1.getSuperclass();
            }
            List<Field> fields2 = new ArrayList<>();
            Class tempClass = obj2.getClass();
            while (tempClass != null) {
                fields2.addAll(Arrays.asList(tempClass.getDeclaredFields()));
                tempClass = tempClass.getSuperclass();
            }
            Map<String, Field> fieldMap = Maps.newHashMap();
            for (Field field : fields2) {
                field.setAccessible(true);
                fieldMap.put(field.getName(), field);
            }
            for (Field field1 : fields1) {
                field1.setAccessible(true);
                Field field2 = fieldMap.get(field1.getName());
                if (field1.get(obj1) == null && field2.get(obj2) == null) {
                    continue;
                }
                handleBigDecimal(obj1, obj2, field1, field2);
                if (field1.getName().equals(field2.getName()) &&
                        !"updateTime".equals(field1.getName()) && !"updateUser".equals(field1.getName()) &&
                        !(field1.get(obj1) == null ? "" : field1.get(obj1)).equals(field2.get(obj2))) {
                    CompareValue compareValue = new CompareValue();
                    compareValue.setField(field1.getName());
                    compareValue.setOldVal(field1.get(obj1)==null?" ":String.valueOf(field1.get(obj1)));
                    compareValue.setNewVal(field2.get(obj2)==null?" ":String.valueOf(field2.get(obj2)));
                    if (field1.isAnnotationPresent(FieldNameAnnotation.class)||field1.isAnnotationPresent(FieldNameAnnotation.class)) {
                        String name;
                        try {
                            name = field1.getAnnotation(FieldNameAnnotation.class).name();
                        }catch (Exception e){
                            name = field2.getAnnotation(FieldNameAnnotation.class).name();
                        }
                        String message;
                        if (DATE_CLASS.equals(field1.getType().toString())) {
                            compareValue.setOldVal(field1.get(obj1) == null ? " " : new SimpleDateFormat(field1.getAnnotation(FieldNameAnnotation.class).dateFormat()).format(field1.get(obj1)));
                            compareValue.setNewVal(field2.get(obj2) == null ? " " : new SimpleDateFormat(field2.getAnnotation(FieldNameAnnotation.class).dateFormat()).format(field2.get(obj2)));
                        }
                        if (StringUtils.isNotBlank(code)) {
                            message = MessageFormat.format("将商品({0})的【{1}】由【{2}】改为【{3}】", code, name, compareValue.getOldVal(),
                                    compareValue.getNewVal());
                        } else {
                            message = MessageFormat.format("将【{0}】由【{1}】改为【{2}】", name, compareValue.getOldVal(),
                                    compareValue.getNewVal());
                        }

                        list.add(message);
                    }
                }
            }

        } catch (Exception e) {
            log.error("对比属性值失败",e);
            throw new RuntimeException("对比属性值失败");
        }
        return list;
    }

    private static void handleBigDecimal(Object obj1, Object obj2, Field field1, Field field2) throws IllegalAccessException {
        if (BIGDECIMAL_CLASS.equals(field1.getType().toString())) {
            if (field1.get(obj1) != null) {
                BigDecimal bigDecimal1 = (BigDecimal) field1.get(obj1);
                field1.set(obj1, new BigDecimal(bigDecimal1.stripTrailingZeros().toPlainString()));
            }
            if (field2.get(obj2) != null) {
                BigDecimal bigDecimal2 = (BigDecimal) (field2.get(obj2));
                field2.set(obj2, new BigDecimal(bigDecimal2.stripTrailingZeros().toPlainString()));
            }
        }
    }


}
