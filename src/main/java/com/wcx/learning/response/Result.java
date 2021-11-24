package com.wcx.learning.response;


import lombok.Data;

@Data
public class Result<T> {
    private String success;
    private String code;
    private String msg;
    private T data;

}
