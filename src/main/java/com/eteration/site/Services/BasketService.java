package com.eteration.site.Services;

import com.eteration.site.Model.Basket;
import com.eteration.site.Repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    public Optional<Basket> findById(Long id){
        return basketRepository.findById(id);
    }

    public void save(Basket basket){
        basketRepository.save(basket);
    }
}
