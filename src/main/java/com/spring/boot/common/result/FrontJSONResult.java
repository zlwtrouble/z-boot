package com.spring.boot.common.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.spring.boot.common.utils.DateUtil;
import javassist.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.Transient;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Ranj on 2017/7/19 0019.
 */
public class FrontJSONResult implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(FrontJSONResult.class);


    /**
     * 该字段描述接口是否成功，必须返回
     */
    private boolean success;
    /**
     * 当不只成功失败两种状态时，该字段补充描述
     */
    private String code;
    /**
     * 接口返回的数据
     */
    private Object data;
    /**
     * 当接口不成功时，该字段必须返回，描述失败原因
     */
    private String message;

    private static final String DEFAULT_SUCCESS_CODE = "success";

    private static final String DEFAULT_SUCCESS_MESSAGE = "操作成功";

    @SuppressWarnings("rawtypes")
    //为了避免加载的class过多 引起方法区溢出  要把加载过的类信息缓存起来
    public static transient final Map<Class, Class> clazMap = Maps.newConcurrentMap();

    private FrontJSONResult() {
    }

    public FrontJSONResult(boolean success, String code, Object data, String message) {
        this.success = success;
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static FrontJSONResult fromExecuteResult(BasePageResult<?> executeResult) {
        FrontJSONResult ret = new FrontJSONResult();
        ret.setCode(executeResult.getCode());
        ret.setMessage(executeResult.getDescription());
        ret.setSuccess(executeResult.isSuccess());
        fillData(ret, executeResult);
        logger.info("返回前端结果:ret={}", ret);
        return ret;
    }

    public static FrontJSONResult fromExecuteResult(BaseResult executeResult) {
        FrontJSONResult ret = new FrontJSONResult();
        if (executeResult != null) {
            ret.setCode(executeResult.getCode());
            ret.setMessage(executeResult.getDescription());
            ret.setSuccess(executeResult.isSuccess());
            fillData(ret, executeResult);
        }
        logger.info("返回前端结果:ret={}", ret);
        return ret;
    }

    public static FrontJSONResult success(BaseResult executeResult) {
        FrontJSONResult ret = new FrontJSONResult();
        ret.setCode(DEFAULT_SUCCESS_CODE);
        ret.setMessage(DEFAULT_SUCCESS_MESSAGE);
        ret.setSuccess(true);
        fillData(ret, executeResult);
        logger.info("返回前端结果:ret={}", ret);
        return ret;
    }

    /*private static void fillData(FrontJSONResult to, BaseResult from) {
        to.setData(from);
    }*/


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    private static void fillData(FrontJSONResult to, BaseResult from) {
        Object object = mkFrontDataInstance(from);
        BeanUtils.copyProperties(from, object);
        if (from instanceof BasePageResult) {
            try {
                buildPageParam((BasePageResult<?>) from, object);
            } catch (Exception e) {
                logger.error("填充数据出错e={}", e);
            }
        }
        to.setData(object);
    }

    /**
     * @param from
     * @param object
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private static void buildPageParam(BasePageResult<?> from,
                                       Object object) throws NoSuchMethodException,
            SecurityException, IllegalAccessException,
            IllegalArgumentException,
            InvocationTargetException {

        Paginator paginator = from.getPaginator();
        Method totalPageCountMethod = object.getClass().getMethod("setTotalPageCount", int.class);
        totalPageCountMethod.invoke(object, paginator.getPages());
        Method currentPageNoMethod = object.getClass().getMethod("setCurrentPageNo", int.class);
        currentPageNoMethod.invoke(object, paginator.getPage());
        Method pageSizeMethod = object.getClass().getMethod("setPageSize", int.class);
        pageSizeMethod.invoke(object, paginator.getItemsPerPage());
        Method totalCountMethod = object.getClass().getMethod("setTotalCount", int.class);
        totalCountMethod.invoke(object, paginator.getItems());
        Method previosPageNoMethod = object.getClass().getMethod("setPreviosPageNo", int.class);
        previosPageNoMethod.invoke(object, paginator.getPreviousPage());
        Method nexPagetNoMethod = object.getClass().getMethod("setNexPagetNo", int.class);
        nexPagetNoMethod.invoke(object, paginator.getNextPage());
        Method resultMethod = object.getClass().getMethod("setResult", List.class);
        resultMethod.invoke(object, from.getList());
    }

    private static Object mkFrontDataInstance(BaseResult from) {
        if (clazMap.containsKey(from.getClass())) {
            @SuppressWarnings("rawtypes")
            Class clazz = clazMap.get(from.getClass());
            logger.info("缓存中找到类型信息{}", clazz);
            try {
                Object instance = clazz.newInstance();
                return instance;
            } catch (InstantiationException | IllegalAccessException e) {
                return null;
            }
        }
        synchronized (from.getClass()) {
            if (clazMap.containsKey(from.getClass())) {
                @SuppressWarnings("rawtypes")
                Class clazz = clazMap.get(from.getClass());
                logger.info("缓存中找到类型信息{}", clazz);
                try {
                    Object instance = clazz.newInstance();
                    return instance;
                } catch (InstantiationException | IllegalAccessException e) {
                    return null;
                }
            }
            logger.info("缓存中没有找到类型信key={}", from.getClass());
            ClassPool pool = ClassPool.getDefault();
            pool.appendSystemPath();
            pool.insertClassPath(new ClassClassPath(FrontJSONResult.class));
            CtClass cc = pool.makeClass(
                    "com.spring.boot.common.result.FrontData"+ System.currentTimeMillis());
            fillClass(cc, from);
            try {
                @SuppressWarnings("rawtypes")
                Class clazz = cc.toClass();
                clazMap.put(from.getClass(), clazz);
                logger.info("向缓存中加入类型key={},value={}", from.getClass(), clazz);
                Object instance = clazz.newInstance();
                return instance;
            } catch (CannotCompileException | InstantiationException | IllegalAccessException e) {
                logger.error("生成类型信息出错e={}", e);
                return null;
            }
        }
    }

    /**
     * @param cc
     * @param from
     */
    private static void fillClass(CtClass cc, BaseResult from) {
        boolean fomPage = false;
        List<Field> fields = Lists.newArrayList();
        fields.addAll(Arrays.asList(from.getClass().getDeclaredFields()));
        if (from instanceof BasePageResult) {
            Class<?> claz = from.getClass().getSuperclass();
            while (BasePageResult.class.isAssignableFrom(claz)) {
                fields.addAll(Arrays.asList(claz.getDeclaredFields()));
                claz = claz.getSuperclass();
            }
        }
        for (Field field : fields) {
            field.setAccessible(true);
            if (isTransient(field)) {
                //不需要虚列化的字端
                continue;
            }
            if (Modifier.isStatic(field.getModifiers())
                    || StringUtils.equalsIgnoreCase("verbose", field.getName())) {
                continue;
            }
            try {
                if (paginator(field)) {
                    fillPaginator(cc);
                    fomPage = true;
                    continue;
                }
                if (fomPage && StringUtils.equals("list", field.getName())) {
                    continue;
                }
                fillGetAndSetMethod(cc, fillField(cc, field));
            } catch (CannotCompileException e) {
                logger.error("填充class发生异常e={}", e);
                continue;
            }

        }
        //		try {
        //			fillToStringMethod(cc);
        //		} catch (CannotCompileException e) {
        //			logger.error("填充 toString 方法发生异常e={}", e.getMessage(), e);
        //		}
    }

    private static void fillToStringMethod(CtClass cc) throws CannotCompileException {
        CtMethod toStringMethord = CtMethod
                .make("public String toString() {"
                                + "return com.yjf.common.util.ToString.toString(this);" + "}",
                        cc);
        cc.addMethod(toStringMethord);
    }


    private static boolean isTransient(Field field) {

        if (Modifier.isTransient(field.getModifiers())) {
            return true;
        }
        if (null != field.getAnnotation(Transient.class)) {
            return true;
        }
        if (null != field.getAnnotation(JSONField.class)) {
            if (!field.getAnnotation(JSONField.class).serialize()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param cc
     * @param field
     */
    private static void fillGetAndSetMethod(CtClass cc, CtField field) {
        try {
            String str = field.getName();
            String Str = str.substring(0, 1).toUpperCase() + str.substring(1);
            CtMethod setMethod = CtNewMethod
                    .setter("set" + Str, field);
            cc.addMethod(setMethod);
            CtMethod getMethod = CtNewMethod
                    .getter("get" + Str, field);

            cc.addMethod(getMethod);

        } catch (CannotCompileException e) {
            logger.error("生成类型信息出错e={}", e);
        }
    }

    /**
     * @param cc
     * @param field
     * @return
     * @throws CannotCompileException
     */
    private static CtField fillField(CtClass cc, Field field) throws CannotCompileException {
        CtField ctField = CtField
                .make("private " + field.getType().getName() + " " + field.getName() + ";", cc);
        cc.addField(ctField);
        return ctField;
    }

    public static CtField fillField(CtClass cc, String filedName,
                                    Class<?> type) throws CannotCompileException {
        CtField field = CtField.make("private " + type.getTypeName() + " " + filedName + ";", cc);
        cc.addField(field);
        return field;
    }

    private static void fillPaginator(CtClass cc) throws CannotCompileException {
        fillGetAndSetMethod(cc, fillField(cc, "totalPageCount", int.class));
        fillGetAndSetMethod(cc, fillField(cc, "currentPageNo", int.class));
        fillGetAndSetMethod(cc, fillField(cc, "pageSize", int.class));
        fillGetAndSetMethod(cc, fillField(cc, "totalCount", int.class));
        fillGetAndSetMethod(cc, fillField(cc, "previosPageNo", int.class));
        fillGetAndSetMethod(cc, fillField(cc, "nexPagetNo", int.class));
        fillGetAndSetMethod(cc, fillField(cc, "result", List.class));
    }

    /**
     * @param field
     * @return
     */
    private static boolean paginator(Field field) {
        return StringUtils.equalsIgnoreCase(field.getType().getSimpleName(), "paginator");
    }
}
