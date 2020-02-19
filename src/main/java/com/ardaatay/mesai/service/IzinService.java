package com.ardaatay.mesai.service;

import com.ardaatay.mesai.dto.IzinDto;
import com.ardaatay.mesai.util.TPage;
import org.springframework.data.domain.Pageable;

public interface IzinService {
    IzinDto save(IzinDto izinDto);

    IzinDto getById(Long id);

    TPage<IzinDto> getAllPageable(Pageable pageable, Long userId);

    Boolean deleteById(Long id);
}
