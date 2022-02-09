package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Cart")
public class Cart {

    private final String currency = " UAH";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @NotNull
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "carts_products",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @Column(name = "sum")
    private BigDecimal sum = BigDecimal.valueOf(0);

    public String getSum() {
        return sum + currency;
    }

    public BigDecimal calculateSum() {
        BigDecimal sumPrice = BigDecimal.valueOf(0);
        for (Product p : products) {
            sumPrice = sumPrice.add(p.getPrice());
        }
        sum = sumPrice;
        return sum;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", person=" + person +
                ", shop=" + shop +
                ", products=" + products +
                ", sum=" + sum + currency +
                '}';
    }

}
