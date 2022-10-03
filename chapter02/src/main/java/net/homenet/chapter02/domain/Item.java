package net.homenet.chapter02.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Item {

    @Id
    private String id;
    private String name;
    private Double price;
    private String description;

    public Item(String name, Double price) {
        this(name, price, null);
    }

    public Item(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

}
