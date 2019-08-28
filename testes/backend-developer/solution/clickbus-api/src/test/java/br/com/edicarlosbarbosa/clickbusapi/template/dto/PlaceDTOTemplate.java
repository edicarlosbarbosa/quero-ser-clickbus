package br.com.edicarlosbarbosa.clickbusapi.template.dto;

import br.com.edicarlosbarbosa.clickbusapi.model.dto.PlaceDTO;
import br.com.edicarlosbarbosa.clickbusapi.template.BaseTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

import java.time.LocalDateTime;
import java.util.stream.LongStream;

public class PlaceDTOTemplate implements BaseTemplate {

    @Override
    public void load() {
        Fixture.of(PlaceDTO.class).addTemplate(VALID, new Rule() {{
            add("resourceId", uniqueRandom(LongStream.range(1L, 200L).boxed().toArray()));
            add("name", random("Av. Paulista", "Corcovado", "Ouro Preto"));
            add("slug", "Test");
            add("city", random("São Paulo", "Rio de Janeiro", "Belo Horizonte"));
            add("state", random("SP", "RJ", "BH"));
            add("createdAt", random(LocalDateTime.of(2000, 1, 1, 1, 1), LocalDateTime.of(2019, 1, 1, 1, 1)));
            add("updatedAt", random(LocalDateTime.of(2000, 1, 1, 1, 1), LocalDateTime.of(2019, 1, 1, 1, 1)));
        }}).addTemplate(VALID_WITHOUT_FK).inherits(VALID, new Rule() {{
            add("id", null);
        }});
    }
}
