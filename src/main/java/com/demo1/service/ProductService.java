package com.demo1.service;

import com.demo1.history.HistoryDetail;
import com.demo1.history.HistoryType;
import com.demo1.history.Product;
import com.demo1.history.ProductHistory;
import com.demo1.mapper.ProductMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductMapper productMapper;

    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public int register (Product product) {
        int result  = productMapper.create(product);
        ProductHistory productHistory = new ProductHistory();
        productHistory.setProductId(product.getProductId());
        productHistory.setHistoryType(HistoryType.INSERT);

        productHistory.setHistoryDetail((HistoryDetail) new HistoryDetail().getChanges());

        result += productMapper.historyBuilder(productHistory);
        return result;
    }
}
