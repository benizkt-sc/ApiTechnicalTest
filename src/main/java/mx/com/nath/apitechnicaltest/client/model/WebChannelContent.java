package mx.com.nath.apitechnicaltest.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WebChannelContent(
        @JsonProperty("id")
        int id,

        @JsonProperty("name")
        String name,

        @JsonProperty("country")
        String country,

        @JsonProperty("officialSite")
        String officialSite
) {

}