package com.ardaatay.mesai.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "is_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Is {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "tarih")
    private Date tarih;

    @Column(name = "yapilan", length = 1000)
    private String yapilan;

    @Column(name = "durum")
    private Boolean durum;
}
