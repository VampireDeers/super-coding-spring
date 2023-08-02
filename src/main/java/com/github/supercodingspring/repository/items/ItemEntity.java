package com.github.supercodingspring.repository.items;

import com.github.supercodingspring.repository.storeSales.StoreSales;
import com.github.supercodingspring.web.dto.items.ItemBody;
import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Builder
@Entity
@Table(name = "item")
public class ItemEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;
    @Column(name = "type", length = 20, nullable = false)
    private String type;

    @Column(name ="price")
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = true)
    private StoreSales storeSales;

    @Column(name = "stock", columnDefinition = "DEFAULT 0 CHECK(stock) >= 0")
    private Integer stock;

    @Column(name = "cpu", length = 30)
    private String cpu;
    @Column(name = "capacity", length = 30)
    private String capacity;

    public ItemEntity(Integer id, String name, String type, Integer price, String cpu, String capacity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.storeSales = null;
        this.stock = 0;
        this.cpu = cpu;
        this.capacity = capacity;
    }

    public Optional<StoreSales> getStoreSales() {
        return Optional.ofNullable(storeSales);
    }

    public void setItemBody(ItemBody itemBody) {
        this.name = itemBody.getName();
        this.type = itemBody.getType();
        this.price = itemBody.getPrice();
        this.cpu = itemBody.getSpec().getCpu();
        this.capacity = itemBody.getSpec().getCapacity();
    }
}
