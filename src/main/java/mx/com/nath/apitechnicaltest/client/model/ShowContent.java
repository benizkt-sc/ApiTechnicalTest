package mx.com.nath.apitechnicaltest.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record ShowContent(
        @JsonProperty("id")
        int id,

        @JsonProperty("name")
        String name,

        @JsonProperty("webChannel")
        WebChannelContent webChannel,

        @JsonProperty("summary")
        String summary,

        @JsonProperty("genres")
        Set<String> genres
) {

}