package com.beerhouse.resources;

import com.beerhouse.domain.Beer;
import com.beerhouse.dto.BeerDTO;
import com.beerhouse.services.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/beer")
public class BeerController {

    @Autowired
    private BeerService beerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody BeerDTO beerDTO) {

        Beer beer = beerService.create(beerDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(beer.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Beer>> findAll() {

        return ResponseEntity.ok(beerService.findAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<Beer> findById(@PathVariable Long id) {

        return ResponseEntity.ok(beerService.findById(id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        beerService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<Beer> update(@PathVariable Long id, @Valid @RequestBody BeerDTO beerDTO) {

        return ResponseEntity.ok(beerService.update(id, beerDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public ResponseEntity<Beer> patch(@PathVariable Long id, @RequestBody BeerDTO beerDTO) {

        return ResponseEntity.ok(beerService.patch(id, beerDTO));
    }
}
