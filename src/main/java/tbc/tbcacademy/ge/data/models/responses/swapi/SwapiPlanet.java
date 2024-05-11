package tbc.tbcacademy.ge.data.models.responses.swapi;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@JsonPropertyOrder({
        "name",
        "rotation_period",
        "orbital_period",
        "diameter",
        "climate",
        "gravity",
        "terrain",
        "surface_water",
        "population",
        "residents",
        "films",
        "created",
        "edited",
        "url"})
public record SwapiPlanet(
        @NonNull
        @JsonProperty("films")
        List<String> films,
        @JsonProperty("edited")
        String edited,
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", shape = JsonFormat.Shape.STRING)
        @JsonProperty("created")
        LocalDateTime created,
        @JsonProperty("climate")
        String climate,
        @JsonProperty("rotation_period")
        String rotationPeriod,
        @JsonProperty("url")
        String url,
        @JsonProperty("population")
        String population,
        @JsonProperty("orbital_period")
        String orbitalPeriod,
        @JsonProperty("surface_water")
        String surfaceWater,
        @JsonProperty("diameter")
        String diameter,
        @JsonProperty("gravity")
        String gravity,
        @JsonProperty("name")
        String name,
        @JsonProperty("residents")
        List<String> residents,
        @JsonProperty("terrain")
        String terrain
) {
}