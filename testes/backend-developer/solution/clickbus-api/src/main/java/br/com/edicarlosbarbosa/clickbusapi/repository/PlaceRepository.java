package br.com.edicarlosbarbosa.clickbusapi.repository;

import br.com.edicarlosbarbosa.clickbusapi.model.entity.PlaceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends CrudRepository<PlaceEntity, Long> {

}
