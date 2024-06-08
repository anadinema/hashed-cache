package dev.anadinema.projects.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public record CreateDto(
        Long id,
        String name,
        @JsonProperty(required = true)
        String profession,
        String gender
) {
}
