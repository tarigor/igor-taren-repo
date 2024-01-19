package com.senla.adsservice.service.impl;

import com.senla.adsdatabase.repository.SaleRepository;
import com.senla.adsservice.dto.SaleDto;
import com.senla.adsservice.service.ISalesService;
import com.senla.adsservice.util.EntityDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SalesServiceImpl implements ISalesService {
    public static final String PATTERN = "MMM d, yyyy, hh:mm:ss a";
    private final SaleRepository saleRepository;
    private final EntityDtoMapper entityDtoMapper;

    public SalesServiceImpl(SaleRepository saleRepository, EntityDtoMapper entityDtoMapper) {
        this.saleRepository = saleRepository;
        this.entityDtoMapper = entityDtoMapper;
    }

    @Override
    public List<SaleDto> getSaleHistoryByDate(String dateFrom, String dateTo) {
        return saleRepository.findSaleByDateIsBetween(stringToDate(dateFrom), stringToDate(dateTo)).stream()
                .map(s -> entityDtoMapper.convertFromEntityToDto(s, SaleDto.class))
                .collect(Collectors.toList());
    }

    private Date stringToDate(String stringDate) {
        DateFormat dateFormat = new SimpleDateFormat(PATTERN, Locale.ENGLISH);
        Date date = null;
        try {
            date = dateFormat.parse(stringDate);
            System.out.println("Parsed Date: " + date);
        } catch (ParseException e) {
            log.error("an error occurred during a parse operation -> {}", e.getMessage());
        }
        return date;
    }
}
