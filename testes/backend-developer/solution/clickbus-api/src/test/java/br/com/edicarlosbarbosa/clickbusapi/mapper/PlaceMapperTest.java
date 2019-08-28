package br.com.edicarlosbarbosa.clickbusapi.mapper;

import br.com.edicarlosbarbosa.clickbusapi.helper.TemplateHelper;
import br.com.edicarlosbarbosa.clickbusapi.model.dto.PlaceDTO;
import br.com.edicarlosbarbosa.clickbusapi.model.entity.PlaceEntity;
import br.com.edicarlosbarbosa.clickbusapi.template.BaseTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.doReturn;

@RunWith(PowerMockRunner.class)
public class PlaceMapperTest {

    @Mock
    private EntityLinks entityLinks;

    @InjectMocks
    private PlaceMapper mapper;

    @BeforeClass
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates(TemplateHelper.TEMPLATE_PACKAGE);
    }

    @Test
    public void toEntity() {
        PlaceDTO dto = Fixture.from(PlaceDTO.class).gimme(BaseTemplate.VALID);

        PlaceEntity expected = PlaceEntity.builder()
                .city(dto.getCity())
                .id(dto.getResourceId())
                .name(dto.getName())
                .slug(dto.getSlug())
                .state(dto.getState())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();

        PlaceEntity actual = mapper.toEntity(dto);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void toDTO() {
        PlaceEntity entity = Fixture.from(PlaceEntity.class).gimme(BaseTemplate.VALID);

        PlaceDTO expected = PlaceDTO.builder()
                .resourceId(entity.getId())
                .name(entity.getName())
                .slug(entity.getSlug())
                .city(entity.getCity())
                .state(entity.getState())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();

        Link link = new Link("http://localhost:8080/v1/place/");
        doReturn(link).when(entityLinks).linkToSingleResource(PlaceEntity.class, entity.getId());

        PlaceDTO actual = mapper.toDTO(entity);

        Assert.assertEquals(expected.getResourceId(), actual.getResourceId());
    }

    @Test
    public void toCollectionDTO() {
        List<PlaceEntity> entities = Fixture.from(PlaceEntity.class).gimme(4, BaseTemplate.VALID);

        List<PlaceDTO> expected = entities.stream().map(entity -> PlaceDTO.builder()
                .resourceId(entity.getId())
                .name(entity.getName())
                .slug(entity.getSlug())
                .city(entity.getCity())
                .state(entity.getState())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build()).collect(Collectors.toList());

        Link link = new Link("http://localhost:8080/v1/place/");
        entities.forEach(entity -> {
            doReturn(link).when(entityLinks).linkToSingleResource(PlaceEntity.class, entity.getId());
        });

        List<PlaceDTO> actual = mapper.toDTO(entities);

        Assert.assertEquals(expected.size(), actual.size());
    }
}
