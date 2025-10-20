package com.s5.finance.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetailDonDTO {
    private Long idDetailDon;
    private Long idRecette;
    private String typeDon;
    private BigDecimal montant;
}
