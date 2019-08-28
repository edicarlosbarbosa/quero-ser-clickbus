package br.com.edicarlosbarbosa.clickbusapi.bdd.scenario;

import java.util.List;

public interface Scenario<ENTITY, DTO, REPO> {
    REPO getRepository();

    ENTITY gimmeEntity();

    List<ENTITY> gimmeEntity(Integer quantity);

    ENTITY gimmeEntitySaved();

    List<ENTITY> gimmeEntitySaved(Integer quantity);

    DTO gimmeDTO();

    List<DTO> gimmeDTO(Integer quantity);

    void deleteAll();
}
