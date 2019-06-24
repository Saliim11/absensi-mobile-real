package com.saliim.absensimobile.model.uploadGambar;

/**
 * Created by Wim on 11/14/16.
 */
public class BaseResponse {

    private boolean success;
    private String message;
    private String path;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
