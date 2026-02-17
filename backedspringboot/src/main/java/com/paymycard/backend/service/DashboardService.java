package com.paymycard.backend.service;

import com.paymycard.backend.dto.DashboardDto;
import com.paymycard.backend.model.DashboardContent;
import com.paymycard.backend.repository.DashboardContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardContentRepository dashboardContentRepository;

    public DashboardDto getDashboardData() {
        List<DashboardContent> offers = dashboardContentRepository.findAllByTypeAndActiveOrderByDisplayOrderAsc("offer",
                true);
        List<DashboardContent> slogans = dashboardContentRepository
                .findAllByTypeAndActiveOrderByDisplayOrderAsc("slogan", true);

        return DashboardDto.builder()
                .offers(offers.stream().map(this::mapToDto).collect(Collectors.toList()))
                .slogans(slogans.stream().map(this::mapToDto).collect(Collectors.toList()))
                .build();
    }

    private DashboardDto.ContentDto mapToDto(DashboardContent content) {
        return DashboardDto.ContentDto.builder()
                .id(content.getId().toString())
                .title(content.getTitle())
                .description(content.getDescription())
                .text(content.getText())
                .subText(content.getSubText())
                .icon(content.getIcon())
                .bgColor(content.getBgColor())
                .accentColor(content.getAccentColor())
                .build();
    }
}
