package dev.anadinema.projects.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import dev.anadinema.projects.dto.SearchDto;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;

@ApplicationScoped
public class CacheHelperService {

    private final HashFunction hashFunction = Hashing.sha256();
    private final ObjectMapper mapper = new ObjectMapper();

    public String getHashedKey(final SearchDto searchDto) {
        var search = new SearchDto(null, searchDto.id(), searchDto.name(),
                searchDto.profession(), searchDto.gender());
        return hashFunction.hashString(this.pojoToString(search), StandardCharsets.UTF_8).toString();
    }

    @SneakyThrows
    private String pojoToString(final SearchDto searchDto) {
        return this.mapper.writeValueAsString(searchDto);
    }

}
