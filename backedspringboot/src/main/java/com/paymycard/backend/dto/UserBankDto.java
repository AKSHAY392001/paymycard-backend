package com.paymycard.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBankDto {
    private String id;
    private String bankName;
    private String accountNo;
    private String ifsc;
    private String holderName;
    private boolean isPrimary;
}
