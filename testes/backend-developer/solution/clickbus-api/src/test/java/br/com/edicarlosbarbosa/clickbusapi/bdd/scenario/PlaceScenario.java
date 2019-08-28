package br.com.edicarlosbarbosa.clickbusapi.bdd.scenario;

import br.com.edicarlosbarbosa.clickbusapi.mapper.PlaceMapper;
import br.com.edicarlosbarbosa.clickbusapi.model.dto.PlaceDTO;
import br.com.edicarlosbarbosa.clickbusapi.model.entity.PlaceEntity;
import br.com.edicarlosbarbosa.clickbusapi.repository.PlaceRepository;
import br.com.edicarlosbarbosa.clickbusapi.template.BaseTemplate;
import br.com.six2six.fixturefactory.Fixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaceScenario implements Scenario<PlaceEntity, PlaceDTO, PlaceRepository> {

    @Autowired
    private PlaceRepository repository;

    @Autowired
    private PlaceMapper mapper;

    private List<String> erros = new ArrayList<>();

    @Override
    public PlaceRepository getRepository() {
        return repository;
    }

    @Override
    public PlaceEntity gimmeEntity() {
        return Fixture.from(PlaceEntity.class).gimme(BaseTemplate.VALID_WITHOUT_FK);
    }

    @Override
    public List<PlaceEntity> gimmeEntity(Integer quantity) {
        List<PlaceEntity> entities = new ArrayList<>();
        while (entities.size() < quantity) {
            entities.add(gimmeEntity());
        }
        return entities;
    }

    @Override
    public PlaceEntity gimmeEntitySaved() {
        return repository.save(gimmeEntity());
    }

    @Override
    public List<PlaceEntity> gimmeEntitySaved(Integer quantity) {
        return gimmeEntity(quantity).stream()
                .map(entity -> repository.save(entity))
                .collect(Collectors.toList());
    }

    @Override
    public PlaceDTO gimmeDTO() {
        return mapper.toDTO(gimmeEntity());
    }

    @Override
    public List<PlaceDTO> gimmeDTO(Integer quantity) {
        return mapper.toDTO(gimmeEntity(quantity));
    }

    @Override
    public void deleteAll() {
        PlaceRepository repository = getRepository();
        if (repository.count() > 0) {
            repository.deleteAll();
        }
        erros.clear();
    }
}
