package com.example.utkarshshukla.darkstar.RetrofitPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Utkarsh Shukla on 04/04/17.
 */

public class ResponsePOJO {
    @SerializedName ("success")
    @Expose
    private Integer success;
    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("message")
    @Expose
    private MessagePOJO message;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public MessagePOJO getMessage() {
        return message;
    }

    public void setMessage(MessagePOJO message) {
        this.message = message;
    }
}
