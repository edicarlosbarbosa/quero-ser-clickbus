package br.com.edicarlosbarbosa.clickbusapi.service;

import br.com.edicarlosbarbosa.clickbusapi.model.dto.PlaceDTO;

import java.util.List;

public interface PlaceService {

    PlaceDTO save(PlaceDTO dto);

    List<PlaceDTO> findAll();

    PlaceDTO findOne(Long id) throws Exception;

    PlaceDTO update(Long id, PlaceDTO dto);

    void delete(Long id);
}
