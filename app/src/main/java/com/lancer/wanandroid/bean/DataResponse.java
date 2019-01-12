package com.lancer.wanandroid.bean;

/**
 * author: Lancer
 * date：2018/10/30
 * des:
 * email:tyk790406977@126.com
 */
public class DataResponse<T> {
    private int errorCode;
    private T data;
    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
