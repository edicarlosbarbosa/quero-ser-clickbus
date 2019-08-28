package br.com.edicarlosbarbosa.clickbusapi.service;

import br.com.edicarlosbarbosa.clickbusapi.handler.exception.NotFoundException;
import br.com.edicarlosbarbosa.clickbusapi.model.dto.PlaceDTO;
import br.com.edicarlosbarbosa.clickbusapi.model.entity.PlaceEntity;
import br.com.edicarlosbarbosa.clickbusapi.repository.PlaceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository repository;

    public PlaceDTO save(PlaceDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        PlaceEntity entity = modelMapper.map(dto, PlaceEntity.class);
        entity.setCreatedAt(LocalDateTime.now());
        entity = repository.save(entity);
        return modelMapper.map(entity, PlaceDTO.class);
    }

    @Override
    public List<PlaceDTO> findAll(String name) {
        List<PlaceEntity> entities;
        if (name != null && !name.isEmpty()) {
            entities = repository.findAllByName(name);
        } else {
            entities = repository.findAll();
        }
        ModelMapper modelMapper = new ModelMapper();
        return entities.stream()
                .map(source -> modelMapper.map(source, PlaceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PlaceDTO findOne(Long id) {
        PlaceEntity place = repository.findById(id).orElseThrow(NotFoundException::new);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(place, PlaceDTO.class);
    }

    @Override
    public PlaceDTO update(Long id, PlaceDTO dto) {
        PlaceEntity place = repository.findById(id).orElseThrow(NotFoundException::new);
        ModelMapper modelMapper = new ModelMapper();
        place.update(place);
        PlaceEntity mergedPlace = repository.save(place);
        return modelMapper.map(mergedPlace, PlaceDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException();
        }
        repository.deleteById(id);
    }
}
