package com.beerhouse.services;

import com.beerhouse.domain.Beer;
import com.beerhouse.dto.BeerDTO;
import com.beerhouse.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
public class BeerService {

    @Autowired
    private BeerRepository beerRepository;


    public Beer create(BeerDTO beerDTO) {

        Beer beer = new Beer();
        copyProperties(beerDTO, beer);
        return beerRepository.save(beer);
    }

    public List<Beer> findAll() {

        List<Beer> listBeer = beerRepository.findAll();
        if (listBeer.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);

        return listBeer;
    }

    public Beer findById(Long id) {

        return beerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Beer not found! ID: " + id + ", Type: " + Beer.class.getName()
        ));
    }

    public void delete(Long id) {

        Beer beer = findById(id);
        beerRepository.delete(beer);
    }

    public Beer update(Long id, BeerDTO beerDTO) {

        Beer beer = findById(id);
        copyProperties(beerDTO, beer);
        return beerRepository.save(beer);
    }

    public Beer patch(Long id, BeerDTO beerDTO) {

        Beer beer = findById(id);

        beer.setName(Optional.ofNullable(beerDTO.getName()).orElse(beer.getName()));
        beer.setIngredients(Optional.ofNullable(beerDTO.getIngredients()).orElse(beer.getIngredients()));
        beer.setAlcoholContent(Optional.ofNullable(beerDTO.getAlcoholContent()).orElse(beer.getAlcoholContent()));
        beer.setPrice(Optional.ofNullable(beerDTO.getPrice()).orElse(beer.getPrice()));
        beer.setCategory(Optional.ofNullable(beerDTO.getCategory()).orElse(beer.getCategory()));

        return beerRepository.save(beer);
    }
}
