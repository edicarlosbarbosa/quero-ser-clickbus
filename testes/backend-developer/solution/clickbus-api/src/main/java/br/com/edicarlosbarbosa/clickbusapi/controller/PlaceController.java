package br.com.edicarlosbarbosa.clickbusapi.controller;

import br.com.edicarlosbarbosa.clickbusapi.model.dto.PlaceDTO;
import br.com.edicarlosbarbosa.clickbusapi.service.PlaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("v1/place")
@Slf4j
public class PlaceController {

    private final String RESOURCE = "/place";

    @Autowired
    private PlaceService service;

    @PostMapping
    @ResponseStatus(HttpStatus.)
    public ResponseEntity<PlaceDTO> save(@Validated @RequestBody PlaceDTO dto) {
        PlaceDTO place = service.save(dto);
        return ResponseEntity
                .created(URI.create(RESOURCE))
                .body(place);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<PlaceDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<PlaceDTO> findOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    @Pat
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PlaceDTO> update(@PathVariable Long id, @Validated @RequestBody PlaceDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
