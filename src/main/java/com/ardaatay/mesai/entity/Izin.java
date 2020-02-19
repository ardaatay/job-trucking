package com.ardaatay.mesai.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "izin")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Izin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "baslangic_tarihi")
    private Date baslangicTarihi;

    @Column(name = "bitis_tarihi")
    private Date bitisTarihi;

    @Column(name = "kayit_tarihi")
    private Date kayitTarihi;

    @Column(name = "durum")
    private Boolean durum;

    @JoinColumn(name = "izin_tip_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private IzinTip izinTip;
}
