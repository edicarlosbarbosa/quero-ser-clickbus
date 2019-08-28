package br.com.edicarlosbarbosa.clickbusapi.mapper;

import br.com.edicarlosbarbosa.clickbusapi.model.dto.PlaceDTO;
import br.com.edicarlosbarbosa.clickbusapi.model.entity.PlaceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaceMapper {

    private static final String UPDATE = "update";
    private static final String DELETE = "delete";

    @Autowired
    private EntityLinks entityLinks;

    public PlaceEntity toEntity(PlaceDTO dto) {
        return PlaceEntity.builder()
                .id(dto.getResourceId())
                .name(dto.getName())
                .slug(dto.getSlug())
                .city(dto.getCity())
                .state(dto.getState())
                .build();
    }

    public PlaceDTO toDTO(PlaceEntity entity) {
        PlaceDTO dto = PlaceDTO.builder()
                .resourceId(entity.getId())
                .name(entity.getName())
                .slug(entity.getSlug())
                .city(entity.getCity())
                .state(entity.getState())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();

        Link selfLink = entityLinks.linkToSingleResource(PlaceEntity.class, entity.getId());
        dto.add(selfLink.withSelfRel());
        dto.add(selfLink.withRel(UPDATE));
        dto.add(selfLink.withRel(DELETE));
        return dto;
    }

    public List<PlaceDTO> toDTO(List<PlaceEntity> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
