package com.github.supercodingspring.service.mapper;

import com.github.supercodingspring.repository.airlineTicket.AirlineTicket;
import com.github.supercodingspring.web.dto.airline.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper
public interface TicketMapper {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    // 메소드
    @Mapping(target = "depart", source = "departureLocation")
    @Mapping(target = "arrival", source = "arrivalLocation")
    @Mapping(target = "departureTime", source = "departureAt", qualifiedByName = "convert")
    @Mapping(target = "returnTime", source = "returnAt", qualifiedByName = "convert")
    Ticket airlineTicketToTicket(AirlineTicket airlineTicket);

    @Named("convert")
    static String localDateTimeToString(LocalDateTime localDateTime){
        if(localDateTime != null) return localDateTime.format(formatter);
        else return null;
    }
}
