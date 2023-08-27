package com.kh.myproject.store.flight.model.dto;

import com.kh.myproject.store.flight.model.entity.FlightTicket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightTicketDto {
    private Long ticTicketId;
    private String ticFlightDepartureDate;
    private String ticFlightArrivalDate;
    private String ticSeatGrade;
    private String ticAirlineName;
    private String ticFee;
    private String ticFromLocation;
    private String ticToLocation;


    public FlightTicket toEntity(){
        return FlightTicket.builder()
                .ticTicketId(ticTicketId)
                .ticFlightDepartureDate(ticFlightDepartureDate)
                .ticFlightArrivalDate(ticFlightArrivalDate)
                .ticSeatGrade(ticSeatGrade)
                .ticAirlineName(ticAirlineName)
                .ticFee(ticFee)
                .ticFromLocation(ticFromLocation)
                .ticToLocation(ticToLocation)
                .build();
    }
}
