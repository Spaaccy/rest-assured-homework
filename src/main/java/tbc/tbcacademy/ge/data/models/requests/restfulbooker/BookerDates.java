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
		"checkin",
		"checkout",
})
public class BookerDates {
	@JsonProperty("checkin")
	private String checkin;

	@JsonProperty("checkout")
	private String checkout;

}