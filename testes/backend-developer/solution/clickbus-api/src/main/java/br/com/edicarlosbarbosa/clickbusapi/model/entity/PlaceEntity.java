package br.com.edicarlosbarbosa.clickbusapi.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class PlaceEntity implements Serializable {

    private static final long serialVersionUID = -8579804412021134009L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String slug;

    @NotNull
    private String city;

    @NotNull
    private String state;

    private LocalDate createdAt;
    private LocalDate updatedAt;
}
