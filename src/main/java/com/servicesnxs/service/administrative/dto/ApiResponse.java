package com.servicesnxs.service.administrative.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponse<T> {
    @JsonProperty("code")
    private int code;
    
    @JsonProperty("description")
    private String description;
    
    @JsonProperty("data")
    private T data;
    
    // Constructores
    public ApiResponse() {}
    
    public ApiResponse(int code, String description, T data) {
        this.code = code;
        this.description = description;
        this.data = data;
    }
    
    // Métodos estáticos para crear respuestas comunes
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "OPERACION EXITOSA", data);
    }
    
    public static <T> ApiResponse<T> success(String description, T data) {
        return new ApiResponse<>(200, description, data);
    }
    
    public static <T> ApiResponse<T> error(int code, String description) {
        return new ApiResponse<>(code, description, null);
    }
    
    public static <T> ApiResponse<T> unauthorized() {
        return new ApiResponse<>(401, "CREDENCIALES INVALIDAS", null);
    }
    
    public static <T> ApiResponse<T> internalError() {
        return new ApiResponse<>(500, "ERROR INTERNO DEL SERVIDOR", null);
    }
    
    // Getters y Setters
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
}