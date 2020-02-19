package com.ardaatay.mesai.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IsDto {
    private Long userId;
    private Date tarih;
    private String yapilan;
}
