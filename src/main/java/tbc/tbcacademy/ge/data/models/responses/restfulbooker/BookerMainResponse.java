package tbc.tbcacademy.ge.data.models.responses.restfulbooker;
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
@AllArgsConstructor
@JsonPropertyOrder({
        "bookingid",
        "booking",
})
@Accessors(chain = true, fluent = true)
public class BookerMainResponse {
    @JsonProperty("bookingid")
    private int bookingid;
    @JsonProperty("booking")
    private BookerResponse booking;
}
