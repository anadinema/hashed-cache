package dev.anadinema.projects.mapper;

import dev.anadinema.projects.dao.PersonEntity;
import dev.anadinema.projects.dto.CreateDto;
import dev.anadinema.projects.dto.SearchDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonalMapper {

    public static PersonEntity mapCreateToEntity(final CreateDto createDto) {
        return PersonEntity.builder()
                .name(createDto.name())
                .profession(createDto.profession())
                .gender(createDto.gender())
                .build();
    }

    public static SearchDto mapEntityToSearch(final String queryId, final Long id, final PersonEntity personEntity) {
        return new SearchDto(queryId, id, personEntity.name, personEntity.profession, personEntity.gender);
    }

}
