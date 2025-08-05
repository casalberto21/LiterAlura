package com.alura.literalura.model;

import java.util.List;

public interface IJsonConvert {
    <T> T getData(String json, Class<T> simpleClass);
    <T> List<T> getArrayData(String json, Class<T> arrayClass);
}