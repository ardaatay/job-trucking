package com.ardaatay.mesai.service.impl;

import com.ardaatay.mesai.dto.IsDto;
import com.ardaatay.mesai.entity.Izin;
import com.ardaatay.mesai.repository.IsRepository;
import com.ardaatay.mesai.repository.IzinRepository;
import com.ardaatay.mesai.util.TPage;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ardaatay.mesai.entity.Is;
import com.ardaatay.mesai.service.IsService;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class IsServiceImpl implements IsService {

    private final IsRepository isRepository;
    private final IzinRepository izinRepository;
    private final ModelMapper modelMapper;

    public IsServiceImpl(IsRepository isRepository, IzinRepository izinRepository, ModelMapper modelMapper) {
        this.isRepository = isRepository;
        this.izinRepository = izinRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public IsDto save(IsDto isDto) {
        if (isDto.getYapilan() == null) {
            throw new IllegalArgumentException("Yapılan iş dolu olmalıdır");
        }

        Is is = modelMapper.map(isDto, Is.class);
        is.setDurum(true);
        is = isRepository.save(is);
        return modelMapper.map(is, IsDto.class);
    }

    @Override
    public IsDto getById(Long id) {
        Is is = isRepository.getOne(id);
        return modelMapper.map(is, IsDto.class);
    }

    @Override
    public List<IsDto> getAllByUserIdAndMonthAndYear(Long userId, int ay, int yil) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, yil);
        cal.set(Calendar.MONTH, ay - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date baslangicTarihi = cal.getTime();

        cal.set(Calendar.YEAR, yil);
        cal.set(Calendar.MONTH, ay);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DATE, -1);
        Date bitisTarihi = cal.getTime();

        List<Is> isData = isRepository.findAllByUserIdAndTarihBetweenOrderByTarihAsc(userId, baslangicTarihi, bitisTarihi);
        List<Izin> izinData = izinRepository.findByUserIdAndBaslangicTarihiBetweenOrderByBaslangicTarihiAsc(userId, baslangicTarihi, bitisTarihi);

        if (izinData.size() != 0) {
            for (Izin izin : izinData) {

                cal.setTime(izin.getBaslangicTarihi());
                int izinBaslangicGunu = cal.get(Calendar.DAY_OF_MONTH);
                cal.setTime(izin.getBitisTarihi());
                int izinBitisGunu = cal.get(Calendar.DAY_OF_MONTH);

                if (izinBitisGunu > izinBaslangicGunu) {
                    for (int i = izinBaslangicGunu; i <= izinBitisGunu; i++) {
                        cal.set(Calendar.YEAR, yil);
                        cal.set(Calendar.MONTH, ay);
                        cal.set(Calendar.DAY_OF_MONTH, i);
                        Date izinGunu = cal.getTime();

                        Is is = new Is();
                        is.setUserId(izin.getUserId());
                        is.setTarih(izinGunu);
                        is.setYapilan(izin.getIzinTip().getAd());
                        is.setDurum(true);

                        isData.add(is);
                    }
                } else {
                    Is is = new Is();
                    is.setUserId(izin.getUserId());
                    is.setTarih(izin.getBaslangicTarihi());
                    is.setYapilan(izin.getIzinTip().getAd());
                    is.setDurum(true);

                    isData.add(is);
                }
            }
        }

        return Arrays.asList(modelMapper.map(isData, IsDto[].class));
    }

    @Override
    public TPage<IsDto> getAllPageable(Pageable pageable, Long userId) {
        Page<Is> data = isRepository.findAllByUserId(pageable, userId);
        TPage<IsDto> response = new TPage<>();
        response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), IsDto[].class)));
        return response;
    }

    @Override
    public Boolean update(Long id, IsDto isDto) {
        Is is = isRepository.getOne(id);
        is.setYapilan(isDto.getYapilan());
        isRepository.save(is);

        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteById(Long id) {
        Is is = isRepository.getOne(id);
        is.setDurum(false);
        isRepository.save(is);

        return Boolean.TRUE;
    }
}
