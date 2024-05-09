package tbc.tbcacademy.ge.data.models.responses.petstore;
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
        "code",
        "type",
        "message"
})
public class ImageUploadResponse {
    @JsonProperty("code")
    private int code;
    @JsonProperty("type")
    private String type;
    @JsonProperty("message")
    private String message;
}