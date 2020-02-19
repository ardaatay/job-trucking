package com.ardaatay.mesai.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "izin_tip")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class IzinTip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ad", length = 50)
    private String ad;

    @JoinColumn(name = "izin_tip_id")
    @OneToMany(fetch = FetchType.LAZY)
    private List<Izin> izinList;
}
