package com.ardaatay.mesai.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ardaatay.mesai.entity.Izin;

import java.util.Date;
import java.util.List;

public interface IzinRepository extends JpaRepository<Izin, Long> {
    List<Izin> findByUserIdAndBaslangicTarihiBetweenOrderByBaslangicTarihiAsc(Long userId, Date baslangicTarihi, Date bitisTarihi);

    Page<Izin> findAllByUserId(Pageable pageable, Long userId);
}
