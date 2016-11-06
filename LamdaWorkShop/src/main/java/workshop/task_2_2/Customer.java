package workshop.task_2_2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olenasyrota on 6/28/16.
 */
public class Customer {

    private final String name;
    private final String city;

    private final List<Order> orders = new ArrayList<>();

    public Customer(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }

    public String getName() {
        return this.name;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void addOrder(Order anOrder) {
        this.orders.add(anOrder);
    }

    public double getTotalOrderValue() {
        return orders.stream()
                .flatMap(o -> o.getLineItems().stream())
                .mapToDouble(LineItem::getValue)
                .sum();
    }

    public double getMostExpensiveItemValue() {
        return orders.stream()
                .flatMap(o -> o.getLineItems().stream())
                .mapToDouble(LineItem::getValue)
                .max()
                .orElse(0);
    }
}