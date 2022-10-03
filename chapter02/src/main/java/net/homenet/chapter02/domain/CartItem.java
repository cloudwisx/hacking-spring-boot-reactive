package net.homenet.chapter02.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CartItem {

    private Item item;
    private Integer quantity;

    public CartItem(Item item) {
        this.item = item;
        quantity = 1;
    }

    public void increment() {
        quantity++;
    }
}
