package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepositroy;
import org.springframework.stereotype.Service;

@Service
public class OptimisticLockStockService {

    private final StockRepositroy stockRepositroy;

    public OptimisticLockStockService(StockRepositroy stockRepositroy) {
        this.stockRepositroy = stockRepositroy;
    }

    public void decrease(Long id, Long quantity) {
        Stock stock = stockRepositroy.findByIdWithOptimisticLock(id);

        stock.decrease(quantity);

        stockRepositroy.save(stock);
    }
}
