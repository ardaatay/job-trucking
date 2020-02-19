package com.ardaatay.mesai.service;

import com.ardaatay.mesai.dto.IsDto;
import com.ardaatay.mesai.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IsService {
    IsDto save(IsDto isDto);

    IsDto getById(Long id);

    List<IsDto> getAllByUserIdAndMonthAndYear(Long userId, int ay, int yil);

    TPage<IsDto> getAllPageable(Pageable pageable, Long userId);

    Boolean update(Long id, IsDto isDto);

    Boolean deleteById(Long id);
}
