package br.com.edicarlosbarbosa.clickbusapi.controller;

import br.com.edicarlosbarbosa.clickbusapi.model.dto.PlaceDTO;
import br.com.edicarlosbarbosa.clickbusapi.service.PlaceService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/place")
@ExposesResourceFor(PlaceDTO.class)
@Slf4j
public class PlaceController {

    private final String RESOURCE = "place";

    @Autowired
    private PlaceService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create a place")
    public PlaceDTO save(@Validated @RequestBody PlaceDTO dto) {
        log.info("Save register for {}", RESOURCE);
        return service.save(dto);
    }

    @GetMapping()
    @ApiOperation("Get all places")
    public List<PlaceDTO> findAll(String name) {
        log.info("Find All {}", RESOURCE);
        return service.findAll(name);
    }

    @GetMapping()
    @ResponseBody
    @ApiOperation("Get a specific place")
    public PlaceDTO findOne(@PathVariable Long id) {
        log.info("Find One By Id for {}", RESOURCE);
        return service.findOne(id);
    }

    @PutMapping("{id}")
    @ApiOperation("Edit a place")
    public PlaceDTO update(@PathVariable Long id, @Validated @RequestBody PlaceDTO dto) {
        log.info("Edit register for {}", RESOURCE);
        return service.update(id, dto);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Remove a place")
    public void delete(@PathVariable Long id) {
        log.info("Delete register for {}", RESOURCE);
        service.delete(id);
    }

}
