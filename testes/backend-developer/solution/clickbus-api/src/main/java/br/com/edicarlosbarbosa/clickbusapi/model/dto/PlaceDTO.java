package br.com.edicarlosbarbosa.clickbusapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDTO implements Serializable {

    private static final long serialVersionUID = -7939896121518028866L;

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
