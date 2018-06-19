package com.pgt.util;

/**
 * Created by maps on 2016/10/10.
 */
public class ResultMessage {
    private String status;
    private String message;
    private Object data;
    public ResultMessage(Object data){
        this.data = data;
        this.message = "success";
        this.status = ResultFlagEnum.SUCCESS.getValue();
    }
    public ResultMessage(ResultFlagEnum status, String message, Object data){
        this.status = status.getValue();
        this.message = message;
        this.data = data;
    }
    public ResultMessage(ResultFlagEnum status, String message){
        this.status = status.getValue();
        this.message = message;
        this.data = "{}";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResultMessage sucess(Object result){
        return new ResultMessage(result);
    }

    public static ResultMessage fail(String code){
        return new ResultMessage(ResultFlagEnum.getEnum(code),"请求失败");
    }
    public static ResultMessage fail(String error,Object object){
        return new ResultMessage(ResultFlagEnum.FAILED,error,object);
    }
    public static ResultMessage sucess(){
        return new ResultMessage(ResultFlagEnum.SUCCESS,"success");
    }

    public static ResultMessage fail(){
        return new ResultMessage(ResultFlagEnum.FAILED,"false");
    }
}
