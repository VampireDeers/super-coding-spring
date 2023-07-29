package com.github.supercodingspring.repository.storeSales;

public interface StoreSalesRepository {
    StoreSales findStoreSalesById(Integer storeId);

    void updateSalesAmount(Integer storeId, Integer stock);
}
