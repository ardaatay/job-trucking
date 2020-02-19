package com.ardaatay.mesai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IzinDto {
    private UserDto user;
    private IzinTipDto izinTip;
    private Date baslangicTarihi;
    private Date bitisTarihi;
}
