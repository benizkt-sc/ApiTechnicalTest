package mx.com.nath.apitechnicaltest.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ShowWrapperResponse(
        @JsonProperty("score")
        BigDecimal score,

        @JsonProperty("show")
        ShowContent show
) {
}