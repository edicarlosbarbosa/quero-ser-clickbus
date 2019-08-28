package br.com.edicarlosbarbosa.clickbusapi.repository;

import br.com.edicarlosbarbosa.clickbusapi.model.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceEntity, Long> {
    List<PlaceEntity> findAllByName(String name);
}
