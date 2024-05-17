package tbc.tbcacademy.ge.data.models.requests.petv3;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@XmlRootElement(name = "Category")
@XmlAccessorType(XmlAccessType.FIELD)
public class Category {

    private long id;
    private String name;

}