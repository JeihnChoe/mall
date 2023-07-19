package shop.mtcoding.mall.model;

import lombok.Getter;

import javax.persistence.*;

@Table(name="product_tb")
@Entity
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer price;
    private Integer qty;
}
