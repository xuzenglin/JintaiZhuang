package com.chris.lanfeng.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>JSON工具类，提供对象和字符串互相转换等功能
 * <p>
 * Author: allen
 * Date: 15-12-21
 */
public final class JsonUtils {
    private final static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * 禁止实例化
     */
    private JsonUtils() {
    }

    /**
     * 将对象转换成String
     *
     * @param o
     * @return
     * @throws JsonProcessingException
     */
    public static String toString(Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(o);
    }

    /**
     * 将对象转换成String
     *
     * @param o
     * @return
     */
    public static String toStringNoEx(Object o) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            logger.error("toStringNoEx excetion", e);
        }

        return null;
    }

    /**
     * 从文件读取json对象
     *
     * @param file
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T toJson(File file, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, clazz);
    }

    public static <T> List<T> fileToJSONObjectLists(String file, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        return mapper.readValue(new File(file), typeFactory.constructCollectionType(List.class, clazz));
    }

    /**
     * 从流读取json
     *
     * @param inputStream
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T toJson(InputStream inputStream, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(inputStream, clazz);
    }

    /**
     * 从流读取json
     *
     * @param inputStream
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> List<T> toList(InputStream inputStream, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        return mapper.readValue(inputStream, typeFactory.constructCollectionType(List.class, clazz));
    }


    /**
     * 将JSON string转换成JSON对象
     *
     * @param target
     * @param clazz
     * @return
     * @throws IOException
     */
    public static <T> T toJson(String target, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(target, clazz);
    }

    public static <T> T toJsonNoEx(String target, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(target, clazz);
        } catch (IOException e) {
            logger.error("toJsonNoEx exception, target:{}", target, e);
        }
        return null;
    }

    public static <T> List<T> stringToObjectLists(String target, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        return mapper.readValue(target, typeFactory.constructCollectionType(List.class, clazz));
    }

    public static <T> List<T> stringToObjectListsNoEx(String target, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        try {
            return mapper.readValue(target, typeFactory.constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            logger.error("stringToObjectListsNoEx exception, target:{}", target, e);
        }
        return null;
    }

}
