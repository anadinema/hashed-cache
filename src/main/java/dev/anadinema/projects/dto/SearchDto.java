package dev.anadinema.projects.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SearchDto(

        @JsonProperty(value = "query_id", required = true)
        String queryId,

        @JsonProperty(value = "id", required = true)
        Long id,

        @JsonProperty(value = "name")
        String name,

        @JsonProperty(value = "profession")
        String profession,

        @JsonProperty(value = "gender")
        String gender) {
}
