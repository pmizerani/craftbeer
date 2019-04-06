package com.beerhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeerDTO implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private String ingredients;

    @NotNull
    private String alcoholContent;

    @NotNull
    @Digits(integer=15, fraction=2)
    private BigDecimal price;

    @NotNull
    private String category;
}
