package com.udea.labegr.resolver;

import com.udea.labegr.entity.Purchase;
import com.udea.labegr.service.PurchaseService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class PurchaseController {

    private final PurchaseService purchaseService;
    public PurchaseController(PurchaseService purchaseService){
        this.purchaseService = purchaseService;
    }

    @MutationMapping
    public Purchase createPurchase(@Argument Long promotionId,
                                  @Argument String customerName,
                                  @Argument int quantity,
                                  @Argument String purchaseDate){
        LocalDateTime date = LocalDateTime.parse(purchaseDate);
        return purchaseService.createPurchase(promotionId, customerName, quantity, date);
    }
}
