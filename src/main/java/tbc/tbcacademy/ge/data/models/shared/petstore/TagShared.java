package tbc.tbcacademy.ge.data.models.shared.petstore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@JsonPropertyOrder({
        "name",
        "id",
})
public class TagShared {
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private long id;
}