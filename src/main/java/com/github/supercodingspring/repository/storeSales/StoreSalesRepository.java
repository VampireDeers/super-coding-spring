package com.github.supercodingspring.repository.storeSales;

import java.util.Optional;

public interface StoreSalesRepository {
    Optional<StoreSales> findStoreSalesById(Integer storeId);

    void updateSalesAmount(Integer storeId, Integer stock);
}
