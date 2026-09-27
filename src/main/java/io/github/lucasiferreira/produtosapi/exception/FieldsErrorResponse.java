package io.github.lucasiferreira.produtosapi.exception;

import java.util.List;

public record FieldsErrorResponse(
        int status,
        String title,
        List<ErrorField> erros

) {
}
