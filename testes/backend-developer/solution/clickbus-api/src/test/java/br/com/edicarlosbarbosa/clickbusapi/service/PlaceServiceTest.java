package br.com.edicarlosbarbosa.clickbusapi.service;

import br.com.edicarlosbarbosa.clickbusapi.handler.exception.NotFoundException;
import br.com.edicarlosbarbosa.clickbusapi.helper.TemplateHelper;
import br.com.edicarlosbarbosa.clickbusapi.mapper.PlaceMapper;
import br.com.edicarlosbarbosa.clickbusapi.model.dto.PlaceDTO;
import br.com.edicarlosbarbosa.clickbusapi.model.entity.PlaceEntity;
import br.com.edicarlosbarbosa.clickbusapi.repository.PlaceRepository;
import br.com.edicarlosbarbosa.clickbusapi.template.BaseTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@RunWith(PowerMockRunner.class)
public class PlaceServiceTest {

    @Mock
    private PlaceRepository repository;

    @Mock
    private PlaceMapper mapper;

    @InjectMocks
    private PlaceServiceImpl service;

    @BeforeClass
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates(TemplateHelper.TEMPLATE_PACKAGE);
    }

    @Test
    public void create() {
        PlaceDTO dto = Fixture.from(PlaceDTO.class).gimme(BaseTemplate.VALID);

        PlaceEntity savedEntity = Fixture.from(PlaceEntity.class).gimme(BaseTemplate.VALID);

        doReturn(savedEntity).when(mapper).toEntity(dto);

        doReturn(savedEntity).when(repository).save(savedEntity);

        doReturn(dto).when(mapper).toDTO(savedEntity);

        PlaceDTO actual = service.save(dto);

        Assert.assertEquals(dto, actual);
    }

    @Test
    public void get() {
        Long id = 1L;

        Optional<PlaceEntity> place = Optional.of(Fixture.from(PlaceEntity.class).gimme(BaseTemplate.VALID));

        PlaceDTO dto = Fixture.from(PlaceDTO.class).gimme(BaseTemplate.VALID);

        doReturn(place).when(repository).findById(id);

        doReturn(dto).when(mapper).toDTO(place.get());

        PlaceDTO actual = service.findOne(id);

        Assert.assertEquals(dto, actual);
    }

    @Test(expected = NotFoundException.class)
    public void get_NotFound() {
        Long id = 1L;

        Optional<PlaceEntity> place = Optional.empty();

        PlaceDTO dto = Fixture.from(PlaceDTO.class).gimme(BaseTemplate.VALID);

        doReturn(place).when(repository).findById(id);

        PlaceDTO actual = service.findOne(id);

        Assert.assertEquals(dto, actual);
    }

    @Test
    public void list() {
        String name = null;
        Integer page = 0;
        Integer size = 20;

        PageRequest pagination = PageRequest.of(page, size);

        List<PlaceEntity> listEntity = Fixture.from(PlaceEntity.class).gimme(5, BaseTemplate.VALID);
        Page<PlaceEntity> entities = new PageImpl<>(listEntity, pagination, 5L);

        List<PlaceDTO> listDTO = Fixture.from(PlaceDTO.class).gimme(5, BaseTemplate.VALID);

        doReturn(entities).when(repository).findAll(pagination);
        doReturn(listDTO).when(mapper).toDTO(listEntity);
        doReturn(5L).when(repository).count();

        List<PlaceDTO> actual = service.findAll(name);

        Assert.assertEquals(listDTO, actual);
        Assert.assertEquals(5L, actual.size());
    }

    @Test
    public void list_WithFilter() {
        String name = "Avenida";
        Integer page = 0;
        Integer size = 20;

        PageRequest pagination = PageRequest.of(page, size);

        List<PlaceEntity> listEntity = Fixture.from(PlaceEntity.class).gimme(5, BaseTemplate.VALID);
        Page<PlaceEntity> entities = new PageImpl<>(listEntity, pagination, 5L);

        List<PlaceDTO> listDTO = Fixture.from(PlaceDTO.class).gimme(5, BaseTemplate.VALID);

        doReturn(entities).when(repository).findAllByName(name);
        doReturn(listDTO).when(mapper).toDTO(listEntity);
        doReturn(5L).when(repository).count();

        List<PlaceDTO> actual = service.findAll(name);

        Assert.assertEquals(listDTO, actual);
    }

    @Test
    public void edit() {
        Long id = 1L;

        Optional<PlaceEntity> place = Optional.of(Fixture.from(PlaceEntity.class).gimme(BaseTemplate.VALID));

        PlaceDTO dto = Fixture.from(PlaceDTO.class).gimme(BaseTemplate.VALID);

        doReturn(place).when(repository).findById(id);

        doReturn(place.get()).when(mapper).toEntity(dto);

        doReturn(place.get()).when(repository).save(place.get());

        doReturn(dto).when(mapper).toDTO(place.get());

        PlaceDTO actual = service.update(id, dto);

        Assert.assertEquals(dto, actual);
    }

    @Test(expected = NotFoundException.class)
    public void edit_NotFound() {
        Long id = 1L;

        Optional<PlaceEntity> place = Optional.empty();

        PlaceDTO dto = Fixture.from(PlaceDTO.class).gimme(BaseTemplate.VALID);

        doReturn(place).when(repository).findById(id);

        PlaceDTO actual = service.update(id, dto);

        Assert.assertEquals(dto, actual);
    }

    @Test
    public void remove() {
        Long id = 1L;

        doReturn(true).when(repository).existsById(id);

        service.delete(id);

        Mockito.verify(repository, Mockito.times(1)).deleteById(id);
    }

    @Test(expected = NotFoundException.class)
    public void remove_NotFound() {
        Long id = 1L;

        doReturn(false).when(repository).existsById(id);

        service.delete(id);
    }
}
