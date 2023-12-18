package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepositroy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    private final StockRepositroy stockRepositroy;

    public StockService(StockRepositroy stockRepositroy) {
        this.stockRepositroy = stockRepositroy;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public synchronized void decrease(Long id, Long quantity) {
        // Stock 조회
        // 재고를 감소시킨 뒤
        // 갱신된 값을 저장

        Stock stock = stockRepositroy.findById(id).orElseThrow();
        stock.decrease(quantity);

        stockRepositroy.saveAndFlush(stock);
    }
}
