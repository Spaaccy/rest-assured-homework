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
        "photoUrls",
        "name",
        "id",
        "category",
        "tags",
        "status"
})
public class Pet {
    @JsonProperty("photoUrls")
    private String[] photoUrls;
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private long id;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("tags")
    private Tag[] tags;
    @JsonProperty("status")
    private String status;
}