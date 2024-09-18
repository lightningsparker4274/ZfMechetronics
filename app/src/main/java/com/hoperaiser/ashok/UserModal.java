package com.hoperaiser.ashok;


public class UserModal  {


String id , error_code,error_description,explanation,remedy,message_cancel;
    public UserModal(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getRemedy() {
        return remedy;
    }

    public void setRemedy(String remedy) {
        this.remedy = remedy;
    }

    public String getMessage_cancel() {
        return message_cancel;
    }

    public void setMessage_cancel(String message_cancel) {
        this.message_cancel = message_cancel;
    }

    public UserModal(String id, String error_code, String error_description, String explanation, String remedy, String message_cancel) {
        this.id = id;
        this.error_code = error_code;
        this.error_description = error_description;
        this.explanation = explanation;
        this.remedy = remedy;
        this.message_cancel = message_cancel;
    }
}

