package io.github.lucasiferreira.produtosapi.exception;

import java.time.Instant;

public record ErrorResponse(String message,
                            Instant timestamp
) {
}
