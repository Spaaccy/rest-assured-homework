package tbc.tbcacademy.ge.data.models.responses.swapi;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.NonNull;

import java.util.List;

@JsonPropertyOrder({
        "count",
        "next",
        "previous",
        "results"})
public record PlanetsModel(
        @NonNull
        @JsonProperty("next")
        String next,
        @JsonProperty("previous")
        Object previous,
        @JsonProperty("count")
        int count,
        @JsonProperty("results")
        List<SwapiPlanet> results
) {
}
