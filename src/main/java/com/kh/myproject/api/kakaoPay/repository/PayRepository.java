package com.kh.myproject.api.kakaoPay.repository;

import com.kh.myproject.api.kakaoPay.model.entity.PaymentBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRepository extends JpaRepository<PaymentBill, Long> {

}
