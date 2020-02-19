package com.ardaatay.mesai.controller;

import com.ardaatay.mesai.dto.IsDto;
import com.ardaatay.mesai.service.impl.IsServiceImpl;
import com.ardaatay.mesai.util.ApiPaths;
import com.ardaatay.mesai.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.IsCtrl.CTRL)
@Api(produces = ApiPaths.IsCtrl.CTRL)
@Slf4j
@CrossOrigin("*")
public class IsController {
    private final IsServiceImpl isService;

    public IsController(IsServiceImpl isService) {
        this.isService = isService;
    }

    @GetMapping("/{userId}/{ay}/{yil}")
    @ApiOperation(value = "Aylara göre raporlama", response = IsDto.class)
    public ResponseEntity<List<IsDto>> getAllByUserIdAndMonth(@PathVariable(value = "userId") Long id,
                                                              @PathVariable(value = "ay") int ay,
                                                              @PathVariable(value = "yil") int yil) {
        log.info("IsController -> getAllByUserIdAndMonth");
        List<IsDto> data = isService.getAllByUserIdAndMonthAndYear(id, ay, yil);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/pagination/{userId}")
    @ApiOperation(value = "Get By Pagination Operation", response = IsDto.class)
    public ResponseEntity<TPage<IsDto>> getAllByPagination(Pageable pageable,
                                                           @PathVariable(value = "userId") Long userId) {
        log.info("IsController -> getAllByPagination");
        TPage<IsDto> data = isService.getAllPageable(pageable, userId);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation", response = IsDto.class)
    public ResponseEntity<IsDto> getById(@PathVariable(value = "id") Long id) {
        log.info("IsController -> GetById");
        log.debug("IsController -> GetById->PARAM:" + id);
        IsDto isDto = isService.getById(id);
        return ResponseEntity.ok(isDto);
    }

    @PostMapping
    @ApiOperation(value = "Create Operation", response = IsDto.class)
    public ResponseEntity<IsDto> createProject(@Valid @RequestBody IsDto isDto) {
        return ResponseEntity.ok(isService.save(isDto));
    }
}
