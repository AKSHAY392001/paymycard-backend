package com.paymycard.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantDto {
    private String id;
    private String name;
    private String address;
    private String owner;
    private String mobile;
    private String email;
}
