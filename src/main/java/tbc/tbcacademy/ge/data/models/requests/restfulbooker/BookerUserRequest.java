package tbc.tbcacademy.ge.data.models.requests.restfulbooker;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class BookerUserRequest {
	@JsonProperty("password")
	private String password;

	@JsonProperty("username")
	private String userName;
}