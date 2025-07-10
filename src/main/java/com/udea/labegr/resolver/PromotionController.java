package com.udea.labegr.resolver;

import com.udea.labegr.entity.Promotion;
import com.udea.labegr.service.PromotionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService){
        this.promotionService = promotionService;
    }

    @QueryMapping
    public List<Promotion> allPromotions(){
        return promotionService.getAllPromotions();
    }

    @QueryMapping
    public Promotion promotionById(@Argument Long id){
        return promotionService.getPromotionById(id);
    }

    @MutationMapping
    public Promotion addPromotion(@Argument String promotionNumber,
                                  @Argument String title,
                                  @Argument String description,
                                  @Argument Double discount,
                                  @Argument String startDate,
                                  @Argument String endDate){
        //Convertir a LocalDateTime
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);

        return promotionService.addPromotion(promotionNumber, title, description, discount, start, end);

    }
}
