package com.example.api.common;

public enum StatusCode {

    FAIL(0 , "失败了~"),
    SUCCESS(1 , "成功！"),
    PARAM_EMPTY(2 , "参数为空"),
    PARAM_ERROR(3 , "参数不合法");

    private int code;
    private String msg;

    StatusCode(int code , String msg){
        this.code = code;
        this.msg = msg;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

}
