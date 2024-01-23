package com.senla.adsservice.service.impl;

import com.senla.adsdatabase.entity.Adv;
import com.senla.adsdatabase.entity.Order;
import com.senla.adsdatabase.entity.User;
import com.senla.adsdatabase.repository.OrderRepository;
import com.senla.adsservice.dto.OrderDto;
import com.senla.adsservice.service.IAdvService;
import com.senla.adsservice.service.IOrderService;
import com.senla.adsservice.service.IUserService;
import com.senla.adsservice.util.EntityDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {
    public static final String PATTERN = "dd.MM.yyyy HH:mm";
    private final OrderRepository orderRepository;
    private final EntityDtoMapper entityDtoMapper;
    private final IAdvService advService;
    private final IUserService userService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            EntityDtoMapper entityDtoMapper,
                            AdvServiceImpl advService,
                            UserServiceImpl userService) {
        this.orderRepository = orderRepository;
        this.entityDtoMapper = entityDtoMapper;
        this.advService = advService;
        this.userService = userService;
    }

    @Override
    public List<OrderDto> getSaleHistoryByDate(String dateFrom, String dateTo) {
        return orderRepository.findSaleByDateIsBetween(stringToDate(dateFrom), stringToDate(dateTo)).stream()
                .map(s -> entityDtoMapper.convertFromEntityToDto(s, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto orderAds(OrderDto orderDto) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User buyer = entityDtoMapper.convertFromDtoToEntity(userService.findUserByEmail(userName), User.class);
        Adv adv = entityDtoMapper.convertFromDtoToEntity(advService.getAdsById(orderDto.getAdvId()), Adv.class);
        Order orderSaved = orderRepository.save(new Order(buyer, adv, LocalDate.now()));
        return new OrderDto(orderSaved.getAdv().getId(), orderSaved.getAdv().getSeller().getId(), orderSaved.getDate());
    }

    @Override
    public List<OrderDto> getOrders() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return orderRepository.findAll().stream()
                .filter(o -> o.getBuyer().getEmail().equals(userName))
                .map(o -> new OrderDto(o.getAdv().getId(), o.getAdv().getSeller().getId(), o.getDate()))
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

    @Override
    public List<OrderDto> getSales() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User seller = entityDtoMapper.convertFromDtoToEntity(userService.findUserByEmail(userName), User.class);
        return orderRepository.findAll().stream()
                .filter(o -> o.getAdv().getSeller().getEmail().equals(seller.getEmail()))
                .map(o -> new OrderDto(o.getAdv().getId(), o.getBuyer().getId(), o.getDate()))
                .collect(Collectors.toList());
    }
}
