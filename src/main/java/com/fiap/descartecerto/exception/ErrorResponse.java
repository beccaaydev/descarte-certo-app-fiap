package com.fiap.descartecerto.exception;

public record ErrorResponse(int status, String message, long timestamp) {

}