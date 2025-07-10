package com.udea.labegr.service;

import com.udea.labegr.entity.Promotion;
import com.udea.labegr.entity.Purchase;
import com.udea.labegr.repository.PromotionRepository;
import com.udea.labegr.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PromotionRepository promotionRepository;
    public PurchaseService(PurchaseRepository purchaseRepository, PromotionRepository promotionRepository){
        this.purchaseRepository = purchaseRepository;
        this.promotionRepository = promotionRepository;
    }

    public Purchase createPurchase(Long promotionId, String customerName, int quantity, LocalDateTime purchaseDate) {
        return promotionRepository.findById(promotionId)
                .map(promotion -> {
                    Purchase purchase = new Purchase();
                    purchase.setCustomerName(customerName);
                    purchase.setQuantity(quantity);
                    purchase.setPurchaseDate(purchaseDate);
                    purchase.setPromotion(promotion);
                    return purchaseRepository.save(purchase);
                })

                .orElseThrow(()-> new RuntimeException("Promotion not found"));
    }


}
