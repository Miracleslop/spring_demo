package com.miracleslop.base.returnType;

public class BaseResponse<T> {

    private DicReturnCode code;
    private String msg;
    private T data;


    public BaseResponse() {
        this.code = DicReturnCode.SUCCESS;
    }

    public BaseResponse(DicReturnCode code, T data) {
        this.code = code;
        this.data = data;
    }

    public BaseResponse(DicReturnCode code) {
        this.code = code;
    }

    public BaseResponse(BaseResponse<T> baseResponse) {
        this.code = baseResponse.code;
        this.msg = baseResponse.msg;
        this.data = baseResponse.data;
    }

    public DicReturnCode getCode() {
        return code;
    }

    public BaseResponse<T> setCode(DicReturnCode code) {
        this.code = code;
        return this;
    }

    public OptReturn<String> getMsg() {
        return OptReturn.of(msg);
    }

    public BaseResponse<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public OptReturn<T> getData() {
        return OptReturn.of(data);
    }

    public BaseResponse<T> setData(T data) {
        this.data = data;
        return this;
    }
}
