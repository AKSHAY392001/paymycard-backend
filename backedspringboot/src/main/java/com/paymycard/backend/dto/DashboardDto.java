package com.paymycard.backend.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
public class DashboardDto {
    private List<ContentDto> offers;
    private List<ContentDto> slogans;

    @Getter
    @Setter
    @Builder
    public static class ContentDto {
        private String id;
        private String title;
        private String description;
        private String text;
        private String subText;
        private String icon;
        private String bgColor;
        private String accentColor;
    }
}
