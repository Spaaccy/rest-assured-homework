package tbc.tbcacademy.ge.data.models.responses.restfulbooker;
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
		"token",
})
public class BookerAuthResponse {
	@JsonProperty("token")
	private String token;
}