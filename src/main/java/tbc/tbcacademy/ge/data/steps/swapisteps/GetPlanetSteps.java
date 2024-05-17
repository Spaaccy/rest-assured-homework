package tbc.tbcacademy.ge.data.steps.swapisteps;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import tbc.tbcacademy.ge.data.models.responses.swapi.PlanetsModel;
import tbc.tbcacademy.ge.data.models.responses.swapi.SwapiPlanet;
import tbc.tbcacademy.ge.data.steps.CommonSteps;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static tbc.tbcacademy.ge.data.constants.SwapiData.SWAPI_ENDPOINT;
import static tbc.tbcacademy.ge.data.specbuilder.RequestSpecs.getBaseRequestSpecForSwapi;

public class GetPlanetSteps extends CommonSteps<GetPlanetSteps> {
    private int planetAmount;
    private final RequestSpecification requestSpecification;
    private PlanetsModel planetsModel;
    private List<SwapiPlanet> filteredRecentPlanets;

    public GetPlanetSteps() {
        requestSpecification = getBaseRequestSpecForSwapi();
    }
    @Step("get planets")
    public GetPlanetSteps getPlanets() {
        response = given(requestSpecification)
                .when()
                .get(SWAPI_ENDPOINT);
        return this;
    }
    @Step("extract planets response as class")
    public GetPlanetSteps extractPlanetsResponseAsClass() {
        planetsModel = validatableResponse
                .extract()
                .body()
                .as(PlanetsModel.class);
        return this;
    }
    @Step("extract recent created planets")
    public GetPlanetSteps extractRecentCreatedPlanets(int amountToExtract) {
        planetAmount = amountToExtract;
        filteredRecentPlanets = planetsModel.results().stream()
                .sorted((planet1, planet2) -> planet2.created().compareTo(planet1.created()))
                .limit(amountToExtract)
                .toList();
        return this;
    }
    @Step("validate extracted planet amount")
    public GetPlanetSteps validateExtractedPlanetAmount() {
        assertThat(filteredRecentPlanets, hasSize(planetAmount));
        return this;
    }
    @Step("validate planet Diameter")
    public GetPlanetSteps validateDiameter() {
        System.out.println(filteredRecentPlanets);
        assertThat(filteredRecentPlanets, everyItem(
                hasToString(matchesPattern("SwapiPlanet\\[.*diameter=.*"))
        ));
        return this;
    }
    @Step("validate planet edited")
    public GetPlanetSteps validateEdited() {
        assertThat(filteredRecentPlanets, everyItem(
                hasToString(matchesPattern("SwapiPlanet\\[.*edited=.*"))
        ));
        return this;
    }
    @Step("validate planet climate")
    public GetPlanetSteps validateClimate() {
        assertThat(filteredRecentPlanets, everyItem(
                hasToString(matchesPattern("SwapiPlanet\\[.*climate=.*"))
        ));
        return this;
    }
    @Step("validate planet rotation period")
    public GetPlanetSteps validateRotationPeriod() {
        assertThat(filteredRecentPlanets, everyItem(
                hasToString(matchesPattern("SwapiPlanet\\[.*rotationPeriod=.*"))
        ));
        return this;
    }
    @Step("validate planet surface water")
    public GetPlanetSteps validateSurfaceWater() {
        assertThat(filteredRecentPlanets, everyItem(
                hasToString(matchesPattern("SwapiPlanet\\[.*surfaceWater=.*"))
        ));
        return this;
    }
    @Step("extract top rotation period planet")
    public void extractTopRotationPeriodPlanet() {
        filteredTopRotationPlanet = planetsModel.results().stream().min((planet1, planet2) -> planet2.rotationPeriod().compareTo(planet1.rotationPeriod())).get();
    }
}
