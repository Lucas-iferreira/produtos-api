package io.github.lucasiferreira.produtosapi.exception;

public record ErrorField(
        String field,
        String message
) {
}
