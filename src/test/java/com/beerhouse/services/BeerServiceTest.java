package com.beerhouse.services;

import com.beerhouse.domain.Beer;
import com.beerhouse.dto.BeerDTO;
import com.beerhouse.repositories.BeerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class BeerServiceTest {

    @Mock
    private BeerRepository beerRepository;

    @InjectMocks
    private BeerService beerService;

    private BeerDTO beerDTO = new BeerDTO();
    private Beer beer = new Beer();

    @Before
    public void setup() {

        beerDTO = BeerDTO.builder()
                .name("Cerveja Puro Malte")
                .ingredients("Malte")
                .alcoholContent("4,5%")
                .price(new BigDecimal(15.25))
                .category("Puro Malte")
                .build();

        beer = Beer.builder()
                .id(1L)
                .name("Cerveja Puro Malte")
                .ingredients("Malte")
                .alcoholContent("4,5%")
                .price(new BigDecimal(15.25))
                .category("Puro Malte")
                .build();

    }

    @Test
    public void create() {

        Mockito.when(beerRepository.save(Mockito.any())).thenReturn(beer);
        Beer beer = this.beerService.create(beerDTO);

        Assert.assertNotNull(beer);
        Assert.assertEquals(beer.getName(), beerDTO.getName());
        Mockito.verify(beerRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void findAll() {

        Mockito.when(beerRepository.findAll()).thenReturn(Arrays.asList(beer));
        List<Beer> listBeer = this.beerService.findAll();

        Assert.assertNotNull(listBeer);
        Mockito.verify(beerRepository, Mockito.times(1)).findAll();
    }

    @Test(expected = ResponseStatusException.class)
    public void findAll_NoContent() {

        Mockito.when(beerRepository.findAll()).thenThrow(new ResponseStatusException(HttpStatus.NO_CONTENT));
        this.beerService.findAll();
    }

    @Test
    public void findById() {

        Mockito.when(beerRepository.findById(1L)).thenReturn(Optional.of(beer));
        Beer beerFound = this.beerService.findById(1L);

        Assert.assertNotNull(beerFound);
        Mockito.verify(beerRepository, Mockito.times(1)).findById(1L);
    }

    @Test(expected = ResponseStatusException.class)
    public void findById_NotFound() {

        this.beerService.findById(2L);
    }

    @Test
    public void delete() {

        Mockito.when(beerRepository.findById(1L)).thenReturn(Optional.of(beer));
        this.beerService.delete(1L);
    }

    @Test(expected = ResponseStatusException.class)
    public void delete_NotFound() {

        Mockito.when(beerRepository.findById(1L)).thenThrow(new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Beer not found! ID: " + 1L + ", Type: " + Beer.class.getName()
        ));
        this.beerService.delete(1L);
    }

    @Test
    public void update() {

        Mockito.when(beerRepository.findById(1L)).thenReturn(Optional.of(beer));

        Mockito.when(beerRepository.save(Mockito.any())).thenReturn(beer);
        Beer beer = this.beerService.update(1L, beerDTO);

        Assert.assertNotNull(beer);
        Assert.assertEquals(beer.getName(), beerDTO.getName());
        Mockito.verify(beerRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(beerRepository, Mockito.times(1)).findById(Mockito.any());
    }

    @Test(expected = ResponseStatusException.class)
    public void update_NotFound() {

        Mockito.when(beerRepository.findById(1L)).thenThrow(new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Beer not found! ID: " + 1L + ", Type: " + Beer.class.getName()
        ));

        this.beerService.update(1L, beerDTO);
    }

    @Test
    public void patch() {

        Mockito.when(beerRepository.findById(1L)).thenReturn(Optional.of(beer));

        Mockito.when(beerRepository.save(Mockito.any())).thenReturn(beer);
        Beer beer = this.beerService.patch(1L, beerDTO);

        Assert.assertNotNull(beer);
        Assert.assertEquals(beer.getName(), beerDTO.getName());
        Mockito.verify(beerRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(beerRepository, Mockito.times(1)).findById(Mockito.any());
    }

    @Test(expected = ResponseStatusException.class)
    public void patch_NotFound() {

        Mockito.when(beerRepository.findById(1L)).thenThrow(new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Beer not found! ID: " + 1L + ", Type: " + Beer.class.getName()
        ));

        this.beerService.patch(1L, beerDTO);
    }
}
