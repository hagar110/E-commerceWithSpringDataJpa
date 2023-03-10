package com.example.EcommerceWithSpringDataJpa.model;

public class Response<T> {
    private String message;
    private int statusCode;
    private boolean errorOccurred;

    private boolean fieldErrorOccurred;
    private T objectToBeReturned;

    public T getObjectToBeReturned() {
        return objectToBeReturned;
    }

    public void setObjectToBeReturned(T objectToBeReturned) {
        this.objectToBeReturned = objectToBeReturned;
    }

    public boolean isErrorOccurred() {
        return errorOccurred;
    }

    public void setErrorOccurred(boolean errorOccurred) {
        this.errorOccurred = errorOccurred;
    }

    public boolean isFieldErrorOccurred() {
        return fieldErrorOccurred;
    }

    public void setFieldErrorOccurred(boolean fieldErrorOccurred) {
        this.fieldErrorOccurred = fieldErrorOccurred;
    }

    public Response(String message, int statusCode, boolean errorOccurred, T objectToBeReturned) {
        this.message = message;
        this.statusCode = statusCode;
        this.errorOccurred = errorOccurred;
        this.objectToBeReturned = objectToBeReturned;
    }

    public Response(String message, int statusCode, boolean errorOccurred, boolean fieldErrorOccurred, T objectToBeReturned) {
        this.message = message;
        this.statusCode = statusCode;
        this.errorOccurred = errorOccurred;
        this.fieldErrorOccurred = fieldErrorOccurred;
        this.objectToBeReturned = objectToBeReturned;
    }

    public Response(String message, int statusCode, boolean errorOccurred) {
        this.message = message;
        this.statusCode = statusCode;
        this.errorOccurred = errorOccurred;
    }

    public Response(String message, int statusCode, boolean errorOccurred, boolean fieldErrorOccurred) {
        this.message = message;
        this.statusCode = statusCode;
        this.fieldErrorOccurred = fieldErrorOccurred;
        this.errorOccurred = errorOccurred;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

}
