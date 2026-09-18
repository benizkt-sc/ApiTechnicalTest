package mx.com.nath.apitechnicaltest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorInformation(
        @JsonProperty("message")
        String message
) {
}