package com.song.dailytime.dailytime.common;

public enum ResponseStatus {
    Ok(200),Error(500);

    int status;

    ResponseStatus(int status){
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
