package br.com.edicarlosbarbosa.clickbusapi.service;

import br.com.edicarlosbarbosa.clickbusapi.model.dto.PlaceDTO;

import java.util.List;

public interface PlaceService {

    PlaceDTO save(PlaceDTO dto);

    List<PlaceDTO> findAll(String name);

    PlaceDTO findOne(Long id);

    PlaceDTO update(Long id, PlaceDTO dto);

    void delete(Long id);
}
