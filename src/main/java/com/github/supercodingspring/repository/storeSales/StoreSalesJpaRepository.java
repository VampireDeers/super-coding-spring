package com.github.supercodingspring.repository.storeSales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreSalesJpaRepository extends JpaRepository<StoreSales, Integer> {

    @Query("SELECT s FROM StoreSales s JOIN FETCH s.itemEntities")
    List<StoreSales> findAllFetchJoin();
}
