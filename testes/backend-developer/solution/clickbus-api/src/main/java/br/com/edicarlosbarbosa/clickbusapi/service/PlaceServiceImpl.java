package br.com.edicarlosbarbosa.clickbusapi.service;

import br.com.edicarlosbarbosa.clickbusapi.model.dto.PlaceDTO;
import br.com.edicarlosbarbosa.clickbusapi.model.entity.PlaceEntity;
import br.com.edicarlosbarbosa.clickbusapi.repository.PlaceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository repository;

    public PlaceDTO save(PlaceDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        PlaceEntity entity = modelMapper.map(dto, PlaceEntity.class);
        entity = repository.save(entity);
        return modelMapper.map(entity, PlaceDTO.class);
    }

    @Override
    public List<PlaceDTO> findAll() {
        Iterable<PlaceEntity> places = repository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        PlaceDTO map = modelMapper.map(places, PlaceDTO.class);
        return null;
    }

    @Override
    public PlaceDTO findOne(Long id) throws Exception {
        return repository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public PlaceDTO update(Long id, PlaceDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
