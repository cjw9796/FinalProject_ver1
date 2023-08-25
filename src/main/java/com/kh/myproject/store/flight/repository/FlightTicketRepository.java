package com.kh.myproject.store.flight.repository;

import com.kh.myproject.store.flight.model.entity.FlightTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightTicketRepository extends JpaRepository<FlightTicket, Long> {

}
