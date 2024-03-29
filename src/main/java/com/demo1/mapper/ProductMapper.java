package com.demo1.mapper;

import com.demo1.history.Product;
import com.demo1.history.ProductHistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

    //등록 페이지
    int register (Product product);

    //히스토리
    int historyBuilder (ProductHistory productHistory);

    int create(Product product);
}
