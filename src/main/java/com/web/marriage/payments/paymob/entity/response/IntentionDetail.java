package com.web.marriage.payments.paymob.entity.response;

import java.util.List;
import lombok.Data;

@Data
public class IntentionDetail {
    private int amount;
    private List<Item> items;
    private String currency;
}
