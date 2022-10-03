package net.homenet.chapter01.domain;

import org.springframework.lang.NonNull;

public class Dish {

    private String description;
    private boolean delivered = false;

    public Dish(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @SuppressWarnings("unused")
    public void setDescription(String description) {
        this.description = description;
    }

    @SuppressWarnings("unused")
    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    @NonNull
    public static Dish deliver(@NonNull Dish dish) {
        Dish deliveredDish = new Dish(dish.getDescription());
        deliveredDish.setDelivered(true);
        return deliveredDish;
    }

    @Override
    public String toString() {
        return "Dish{" +
            "description='" + description + '\'' +
            ", delivered=" + delivered +
            '}';
    }
}
