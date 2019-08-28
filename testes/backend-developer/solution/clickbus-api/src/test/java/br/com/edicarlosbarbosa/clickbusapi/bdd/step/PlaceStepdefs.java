package br.com.edicarlosbarbosa.clickbusapi.bdd.step;

import br.com.edicarlosbarbosa.clickbusapi.bdd.scenario.PlaceScenario;
import br.com.edicarlosbarbosa.clickbusapi.helper.FeatureHelper;
import br.com.edicarlosbarbosa.clickbusapi.helper.TemplateHelper;
import br.com.edicarlosbarbosa.clickbusapi.mapper.PlaceMapper;
import br.com.edicarlosbarbosa.clickbusapi.model.dto.PlaceDTO;
import br.com.edicarlosbarbosa.clickbusapi.model.entity.PlaceEntity;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.BeforeStep;
import io.cucumber.java8.En;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlaceStepdefs implements En {

    private final String ENDPOINT = "/v1/place/";

    private List<PlaceEntity> entities = new ArrayList<>();
    private List<PlaceDTO> dtos = new ArrayList<>();

    private Response response;
    private PlaceDTO dto;
    private PlaceDTO filterDto;
    private PlaceEntity entity;

    @Autowired
    private PlaceScenario scenario;

    @Autowired
    private PlaceMapper mapper;

    @LocalServerPort
    private int port;

    @BeforeStep
    public void setUp() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;
        FixtureFactoryLoader.loadTemplates(TemplateHelper.TEMPLATE_PACKAGE);
    }

    public PlaceStepdefs() {

        Given("^That there are (\\d+) registered places$", (Integer quantity) -> {
            this.entities = scenario.gimmeEntitySaved(quantity);
            this.dtos = mapper.toDTO(this.entities);
        });

        Given("^That there is a recorded place$", () -> {
            this.entity = scenario.gimmeEntitySaved();
            this.dtos = Collections.singletonList(mapper.toDTO(this.entity));
        });

        And("^That there is a recorded place with name (\\w+)$", (String name) -> {
            PlaceDTO template = scenario.gimmeDTO();
            template.setName(name);
            PlaceEntity save = scenario.getRepository().save(mapper.toEntity(template));
            this.filterDto = mapper.toDTO(save);
        });

        When("^I get the list of places$", () -> this.response = RestAssured.given()
                .queryParam("name", filterDto.getName())
                .expect()
                .statusCode(200)
                .body(FeatureHelper.CONTENT_PATH, Matchers.hasSize(1))
                .when()
                .get(ENDPOINT));

        When("^I get the list of places without filter$", () -> this.response = RestAssured.expect()
                .statusCode(200)
                .body(FeatureHelper.CONTENT_PATH, Matchers.hasSize(entities.size()))
                .when()
                .get(ENDPOINT));

        When("^I try to get the place by id$", () -> {
            this.dto = dtos.get(0);
            Long id = dto.getResourceId();
            this.response = RestAssured.expect()
                    .statusCode(200)
                    .when()
                    .get(ENDPOINT + id);
        });

        When("^I try to create a place$", () -> {
            this.dto = scenario.gimmeDTO();
            this.dto.setCreatedAt(null);
            this.dto.setUpdatedAt(null);

            this.response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(this.dto)
                    .expect()
                    .statusCode(201)
                    .when()
                    .post(ENDPOINT);

            PlaceDTO responseDTO = response.getBody().as(PlaceDTO.class);
            this.dto.setResourceId(responseDTO.getResourceId());
        });

        When("^I try to update the place$", () -> {
            this.dto = scenario.gimmeDTO();
            Long id = this.dtos.get(0).getResourceId();
            this.response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(this.dto)
                    .expect()
                    .statusCode(200)
                    .when()
                    .put(ENDPOINT + id);

            this.dto.setResourceId(id);
        });

        When("^I try to delete the place by id$", () -> {
            Long id = this.dtos.get(0).getResourceId();
            RestAssured.expect()
                    .statusCode(200)
                    .when()
                    .delete(ENDPOINT + id);
        });

        Then("^the service will not find the place$", () -> {
            Long id = this.dtos.get(0).getResourceId();
            RestAssured.given()
                    .expect()
                    .statusCode(404)
                    .when()
                    .get(ENDPOINT + id);
        });

        Then("^the service returns a place list with name (\\w+)$", (String name) -> {
            List<PlaceDTO> dtos = this.response.jsonPath()
                    .getList(FeatureHelper.CONTENT_PATH, PlaceDTO.class);
            assertThat(dtos.get(0).getResourceId(), equalTo(filterDto.getResourceId()));
            assertThat(dtos.get(0).getCity(), equalTo(filterDto.getCity()));
            assertThat(dtos.get(0).getName(), equalTo(filterDto.getName()));
            assertThat(dtos.get(0).getSlug(), equalTo(filterDto.getSlug()));
            assertThat(dtos.get(0).getState(), equalTo(filterDto.getState()));
        });

        Then("^The service returns the place$", () -> {
            PlaceDTO responseDTO = this.response.getBody().as(PlaceDTO.class);
            assertThat(this.dto.getResourceId(), equalTo(responseDTO.getResourceId()));
            assertThat(this.dto.getCity(), equalTo(responseDTO.getCity()));
            assertThat(this.dto.getName(), equalTo(responseDTO.getName()));
            assertThat(this.dto.getSlug(), equalTo(responseDTO.getSlug()));
            assertThat(this.dto.getState(), equalTo(responseDTO.getState()));
        });

        Then("^the service returns a place list without filter$", () -> {
            List<PlaceDTO> dtos = this.response.jsonPath()
                    .getList(FeatureHelper.CONTENT_PATH, PlaceDTO.class);
            assertEquals(dtos.size(), this.dtos.size());
        });

        After("@Place", (Scenario scenario) -> {
            this.scenario.deleteAll();
        });
    }
}
