package br.com.edicarlosbarbosa.clickbusapi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDTO extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = -7939896121518028866L;

    @JsonProperty("id")
    private Long resourceId;

    @NotNull
    private String name;

    @NotNull
    private String slug;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @ApiModelProperty(hidden = true)
    private LocalDateTime createdAt;

    @ApiModelProperty(hidden = true)
    private LocalDateTime updatedAt;

}
