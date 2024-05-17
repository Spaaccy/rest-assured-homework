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
        "additionalneeds",
        "lastname",
        "totalprice",
        "depositpaid",
        "firstname",
        "bookingdates",
})
public class BookerResponse {
    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("totalprice")
    private int totalBookerprice;
    @JsonProperty("depositpaid")
    private boolean depositpaid;
    @JsonProperty("bookingdates")
    private BookerResponseDates bookingdates;
    @JsonProperty("additionalneeds")
    private String additionalneeds;
}