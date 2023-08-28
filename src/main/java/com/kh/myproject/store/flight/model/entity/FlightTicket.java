package com.kh.myproject.store.flight.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ticket_info")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticTicketId;

    @Column
    private String ticFlightDepartureDate;
    @Column
    private String ticFlightArrivalDate;
    @Column
    private String ticSeatGrade;
    @Column
    private String ticAirlineName;
    @Column
    private String ticFee;
    @Column
    private String ticFromLocation;
    @Column
    private String ticToLocation;

//    public FlightTicketDto toDto(FlightTicket entity){
//        return FlightTicketDto.builder()
//                .ticTicketId(entity.getTicTicketId())
//                .ticFlightDepartureDate(entity.getTicFlightDepartureDate())
//                .ticFlightArrivalDate(entity.getTicFlightArrivalDate())
//                .ticSeatGrade(entity.getTicSeatGrade())
//                .ticAirlineName(entity.getTicAirlineName())
//                .ticFee(entity.getTicFee())
//                .ticFromLocation(entity.getTicFromLocation())
//                .ticToLocation(entity.getTicToLocation())
//                .build();
//    }
}
