package dev.anadinema.projects.controller;

import dev.anadinema.projects.dto.CreateDto;
import dev.anadinema.projects.dto.SearchDto;
import dev.anadinema.projects.service.CacheHelperService;
import dev.anadinema.projects.service.PersonalService;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.Nonnull;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/personal")
public class PersonalController {

    private final PersonalService appService;
    private final CacheHelperService helperService;

    @Inject
    public PersonalController(final PersonalService personalService, CacheHelperService cacheHelperService) {
        this.appService = personalService;
        this.helperService = cacheHelperService;
    }

    @POST
    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> search(@Nonnull SearchDto searchDto) {
        return appService.searchForPerson(helperService.getHashedKey(searchDto), searchDto);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> create(@Nonnull CreateDto createDto) {
        return appService.createNewPerson(createDto);
    }

}
