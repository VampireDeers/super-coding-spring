package com.github.supercodingspring.web.dto.airline;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReservationRequest {
    @ApiModelProperty(name = "userId", value = "유저 ID", example = "1") private Integer userId;
    @ApiModelProperty(name = "airlineTicketId", value = "항공편 ID", example = "2") private Integer airlineTicketId;

}

