package com.example.javaAT.models.payloads;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotFoundPayload {
    private String erro;
    private LocalDateTime horario;

    public NotFoundPayload(String message) {
        this.erro = message;
        this.horario = LocalDateTime.now();
    }
}
