package com.paymycard.backend.config;

import com.paymycard.backend.model.DashboardContent;
import com.paymycard.backend.repository.DashboardContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final DashboardContentRepository repository;

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            seedDashboardContent();
        }
    }

    private void seedDashboardContent() {
        DashboardContent sub1 = DashboardContent.builder()
                .type("offer")
                .title("Premium Rewards")
                .description("Get 5% cashback on all utility payments this month.")
                .icon("gift-outline")
                .bgColor("#047857")
                .displayOrder(1)
                .active(true)
                .build();

        DashboardContent sub2 = DashboardContent.builder()
                .type("offer")
                .title("Cyber Protection")
                .description("Your transactions are protected by bank-grade AI encryption.")
                .icon("shield-lock-outline")
                .bgColor("#1E3A8A")
                .displayOrder(2)
                .active(true)
                .build();

        DashboardContent sub3 = DashboardContent.builder()
                .type("offer")
                .title("Global Transfer")
                .description("Send money to any bank account instantly with zero fees.")
                .icon("flash-outline")
                .bgColor("#D97706")
                .displayOrder(3)
                .active(true)
                .build();

        DashboardContent slogan1 = DashboardContent.builder()
                .type("slogan")
                .text("Precision in every payment, power in every card.")
                .subText("Experience the future of digital finance.")
                .bgColor("#047857")
                .accentColor("#0EA5E9")
                .displayOrder(1)
                .active(true)
                .build();

        DashboardContent slogan2 = DashboardContent.builder()
                .type("slogan")
                .text("Wealth is the ability to fully experience life.")
                .subText("Your journey to financial freedom starts here.")
                .bgColor("#1E3A8A")
                .accentColor("#F59E0B")
                .displayOrder(2)
                .active(true)
                .build();

        repository.saveAll(List.of(sub1, sub2, sub3, slogan1, slogan2));
    }
}
