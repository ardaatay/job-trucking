package com.ardaatay.mesai.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ardaatay.mesai.entity.Is;

import java.util.Date;
import java.util.List;

public interface IsRepository extends JpaRepository<Is, Long> {
    List<Is> findAllByUserIdAndTarihBetweenOrderByTarihAsc(Long userId, Date baslangicTarih, Date bitisTarih);

    Page<Is> findAllByUserId(Pageable pageable, Long userId);
}
