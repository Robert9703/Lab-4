package com.udea.labegr.service;

import com.udea.labegr.entity.Promotion;
import com.udea.labegr.repository.PromotionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class PromotionService {

    private PromotionRepository promotionRepository;
    public PromotionService(PromotionRepository promotionRepository){
        this.promotionRepository = promotionRepository;
    }

    public List<Promotion> getAllPromotions(){
        return Optional.ofNullable(promotionRepository.findAll())
                .orElseThrow(()-> new RuntimeException("No promotion available"));
    }

    public Promotion getPromotionById(Long id){
        return promotionRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Promotion not found for id:" + id));
    }

    public Promotion addPromotion(String promotionNumber, String title, String description, Double discount, LocalDateTime startDate, LocalDateTime endDate){
        return Optional.of(new Promotion())
                .map(promotion ->  {
                    setIfNotNull(promotion::setPromotionNumber, promotionNumber);
                    setIfNotNull(promotion::setTitle, title);
                    setIfNotNull(promotion::setDescription, description);
                    setIfNotNull(promotion::setDiscount, discount);
                    setIfNotNull(promotion::setStartDate, startDate);
                    setIfNotNull(promotion::setEndDate, endDate);
                    return promotionRepository.save(promotion);
                })
                .orElseThrow(()-> new RuntimeException("Error creando promocion"));
    }
    //Metodo helper
    private <T> void setIfNotNull(Consumer<T> setter, T value){
        Optional.ofNullable(value).ifPresent(setter);
    }
}
