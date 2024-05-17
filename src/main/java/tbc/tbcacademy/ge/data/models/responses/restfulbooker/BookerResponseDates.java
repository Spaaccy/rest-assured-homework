package tbc.tbcacademy.ge.data.models.responses.restfulbooker;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.experimental.Accessors;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
		"checkin",
		"checkout",
})
@Accessors(chain = true, fluent = true)
public class BookerResponseDates {
	@JsonProperty("checkin")
	private String checkin;

	@JsonProperty("checkout")
	private String checkout;

}