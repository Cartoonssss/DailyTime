package com.song.dailytime.dailytime.common;

public class ResultData {
    private int status;
    private String msg;
    private String data;

    public static ResultData errorExection(String e) {
        return new ResultData(555, e, null);
    }

    public static ResultData ok(String data) {
        return new ResultData(200, "OK", data);
    }

    public ResultData(int status, String msg, String data) {
        super();
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultData() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
