package com.backend.sartbux.model;

import com.backend.sartbux.model.enums.PromotionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {
    String id;
    BigDecimal promotionAmount;
    PromotionType promotionType;

    public Promotion(PromotionType promotionType) {
        this.promotionType = promotionType;
    }
}
