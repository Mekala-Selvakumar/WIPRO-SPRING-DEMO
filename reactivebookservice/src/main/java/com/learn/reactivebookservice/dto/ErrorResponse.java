package com.learn.reactivebookservice.dto;

import java.time.LocalDateTime;

public record ErrorResponse(String errorMessage, LocalDateTime   timestamp) {

}
