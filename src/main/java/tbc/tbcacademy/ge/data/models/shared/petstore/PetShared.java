package tbc.tbcacademy.ge.data.models.shared.petstore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@JsonPropertyOrder({
        "photoUrls",
        "name",
        "id",
        "category",
        "tags",
        "status"
})
@Accessors(chain = true, fluent = true)
public class PetShared {
    @JsonProperty("id")
    private long id;
    @JsonProperty("photoUrls")
    private String[] photoUrls;
    @JsonProperty("name")
    private String name;
    @JsonProperty("status")
    private String status;
    @JsonProperty("category")
    private CategoryShared category;
    @JsonProperty("tags")
    private TagShared[] tags;
}