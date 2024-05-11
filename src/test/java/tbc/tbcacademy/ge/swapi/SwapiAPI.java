package tbc.tbcacademy.ge.swapi;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tbc.tbcacademy.ge.data.steps.swapisteps.GetPlanetSteps;
import tbc.tbcacademy.ge.data.steps.swapisteps.GetResidentSteps;

public class SwapiAPI {
    GetPlanetSteps getPlanetSteps;
    GetResidentSteps getResidentSteps;
    @BeforeClass
    public void initializeSteps() {
        getPlanetSteps = new GetPlanetSteps();
        getResidentSteps = new GetResidentSteps();
    }
    @Test(priority = 1)
    public void testPlanet() {
        getPlanetSteps
                .getPlanets()
                .getValidatableResponse()
                .checkStatusCode()
                .extractPlanetsResponseAsClass()
                .extractRecentCreatedPlanets(3)
                .validateExtractedPlanetAmount()
                .validateDiameter()
                .validateEdited()
                .validateClimate()
                .validateSurfaceWater()
                .validateRotationPeriod()
                .extractTopRotationPeriodPlanet();
    }
    @Test(dependsOnMethods = "testPlanet", priority = 2)
    public void testResident() {
        String residentURL = getResidentSteps.extractFirstResidentURLFromTopRotationPlanet();
        getResidentSteps
                .getResident(residentURL)
                .getValidatableResponse()
                .checkStatusCode()
                .extractResidentResponseAsClass()
                .buildResidentResponse()
                .validateSwapiResidentResponse();
    }
}
