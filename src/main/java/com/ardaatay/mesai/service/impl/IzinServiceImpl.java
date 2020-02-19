package com.ardaatay.mesai.service.impl;

import com.ardaatay.mesai.dto.IzinDto;
import com.ardaatay.mesai.entity.Izin;
import com.ardaatay.mesai.repository.IzinRepository;
import com.ardaatay.mesai.service.IzinService;
import com.ardaatay.mesai.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;

public class IzinServiceImpl implements IzinService {

    private final IzinRepository izinRepository;
    private final ModelMapper modelMapper;

    public IzinServiceImpl(IzinRepository izinRepository, ModelMapper modelMapper) {
        this.izinRepository = izinRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public IzinDto save(IzinDto izinDto) {
        if (izinDto.getIzinTip() == null) {
            throw new IllegalArgumentException("İzin türü dolu olmalıdır");
        }

        Izin izin = modelMapper.map(izinDto, Izin.class);
        izin = izinRepository.save(izin);
        return modelMapper.map(izin, IzinDto.class);
    }

    @Override
    public IzinDto getById(Long id) {
        Izin izin = izinRepository.getOne(id);
        return modelMapper.map(izin, IzinDto.class);
    }

    @Override
    public TPage<IzinDto> getAllPageable(Pageable pageable, Long userId) {
        Page<Izin> data = izinRepository.findAllByUserId(pageable, userId);
        TPage<IzinDto> response = new TPage<>();
        response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), IzinDto[].class)));
        return response;
    }

    @Override
    public Boolean deleteById(Long id) {
        Izin izin = izinRepository.getOne(id);
        izin.setDurum(false);
        izinRepository.save(izin);

        return Boolean.TRUE;
    }
}
