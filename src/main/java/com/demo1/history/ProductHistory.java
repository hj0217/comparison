package com.demo1.history;

import lombok.Data;

@Data
public class ProductHistory {

    private int productHistoryId;
    private int productId;
    private HistoryType historyType;
    private HistoryDetail historyDetail;

}
