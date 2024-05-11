package tbc.tbcacademy.ge.data.models.responses.petstorev3;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true, chain = true)
@JsonPropertyOrder({
        "id",
        "petId",
        "quantity",
        "shipDate",
        "status",
        "complete",
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {
    @JsonProperty("id")
    private long id;
    @JsonProperty("petId")
    private long petId;
    @JsonProperty("quantity")
    private int petQuantity;
    @JsonProperty("shipDate")
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING)
    private String shippingDate;
    @JsonProperty("status")
    private String petStatus;
    @JsonProperty("complete")
    private boolean complete;
}