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
        "photoUrls",
        "name",
        "id",
        "category",
        "tags",
        "status"
})
public class PetShared {
    @JsonProperty("photoUrls")
    private String[] photoUrls;
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private long id;
    @JsonProperty("category")
    private CategoryShared category;
    @JsonProperty("tags")
    private TagShared[] tags;
    @JsonProperty("status")
    private String status;
}