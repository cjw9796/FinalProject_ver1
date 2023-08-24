package com.kh.myproject.sungsick.kakaoPay.payVO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AmountVO {

    private Integer total, tax_free, vat, point, discount;
}