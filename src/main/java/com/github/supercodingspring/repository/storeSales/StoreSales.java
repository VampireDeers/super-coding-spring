package com.github.supercodingspring.repository.storeSales;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Builder
public class StoreSales {
    private Integer id;
    private String storeName;
    private Integer amount;
}
