package mx.com.nath.apitechnicaltest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record AddCommentRequest(

        @NotEmpty(message = "The comment can't be empty")
        @JsonProperty("comment")
        String comment,

        @Min(value = 0, message = "Rating must be greater than or equal to 0")
        @Max(value = 5, message = "Rating must be less than or equal to 5")
        @JsonProperty("rating")
        Integer rating
) {
}