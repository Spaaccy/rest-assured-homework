package tbc.tbcacademy.ge.data.models.requests.restfulbooker;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.experimental.Accessors;
import tbc.tbcacademy.ge.data.models.responses.restfulbooker.BookerResponse;

@Data
@Builder
public class BookerMainRequest {
    @JsonProperty("bookingid")
    private int bookingid;
    @JsonProperty("booking")
    private BookerResponse booking;
}
