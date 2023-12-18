package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepositroy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessimisticLockStockService {

    private final StockRepositroy stockRepositroy;

    public PessimisticLockStockService(StockRepositroy stockRepositroy) {
        this.stockRepositroy = stockRepositroy;
    }

    @Transactional
    public void decrease(Long id, Long quantity) {
        Stock stock = stockRepositroy.findByIdWithPessimisticLock(id);

        stock.decrease(quantity);

        stockRepositroy.save(stock);
    }
}
