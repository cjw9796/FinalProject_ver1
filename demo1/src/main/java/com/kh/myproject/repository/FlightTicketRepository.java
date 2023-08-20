package com.kh.myproject.repository;

import com.kh.myproject.entity.FlightTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightTicketRepository extends JpaRepository<FlightTicket, Long> {

}
