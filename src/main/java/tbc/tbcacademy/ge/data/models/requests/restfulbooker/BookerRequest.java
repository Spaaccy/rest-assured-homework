package tbc.tbcacademy.ge.data.models.requests.restfulbooker;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookerRequest {
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
    private Integer totalprice;
    private Boolean depositpaid;
    @JsonIgnore
    private int saleprice;
    @JsonProperty("bookingdates")
    private BookerRequestDates bookingdates;
    private String additionalneeds;
    @JsonProperty("passportNo")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String passportNo;
}

