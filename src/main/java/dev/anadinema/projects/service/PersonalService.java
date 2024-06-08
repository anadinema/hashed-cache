package dev.anadinema.projects.service;

import dev.anadinema.projects.dao.PersonEntity;
import dev.anadinema.projects.dto.CreateDto;
import dev.anadinema.projects.dto.SearchDto;
import dev.anadinema.projects.mapper.PersonalMapper;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class PersonalService {

    public Uni<Response> searchForPerson(final String hashKey, final SearchDto searchDto) {
        return this.searchTransaction(hashKey, searchDto)
                .onItem()
                .transform(it -> Response.ok(it).build())
                .onFailure()
                .recoverWithItem(Response.status(Response.Status.NO_CONTENT).build());
    }

    @WithTransaction
    public Uni<Response> createNewPerson(final CreateDto createDto) {
        return PersonalMapper.mapCreateToEntity(createDto).persist()
                .onItem()
                .transform(it -> Response.ok(it).build())
                .onFailure()
                .recoverWithItem(Response.status(Response.Status.BAD_REQUEST).build());
    }

    @WithTransaction
    @CacheResult(cacheName = "match_cache")
    public Uni<SearchDto> searchTransaction(@CacheKey final String hashKey, SearchDto searchDto) {
        log.info("Inside the database interaction layer!!");
        return PersonEntity.findById(searchDto.id())
                .onItem()
                .transform(it -> PersonalMapper
                                .mapEntityToSearch(searchDto.queryId(), searchDto.id(), (PersonEntity) it))
                .onFailure().invoke(it -> log.error("Something went wrong in the database transaction!"));
    }


}
