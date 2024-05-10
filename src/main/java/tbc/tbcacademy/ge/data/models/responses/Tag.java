package tbc.tbcacademy.ge.data.models.responses;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "name",
        "id",
})
public class Tag {
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private long id;
}