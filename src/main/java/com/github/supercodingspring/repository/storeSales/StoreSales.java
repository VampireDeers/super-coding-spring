package com.github.supercodingspring.repository.storeSales;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Builder
@Entity
@Table(name = "store_sales")
public class StoreSales {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "store_name", length = 30)
    private String storeName;

    @Column(name = "amount", nullable = false, columnDefinition = "DEFAULT 0 CHECK(amount) >= 0")
    private Integer amount;
}
