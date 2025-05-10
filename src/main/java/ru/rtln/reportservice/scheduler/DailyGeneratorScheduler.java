package ru.rtln.reportservice.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.rtln.reportservice.aop.annotation.Loggable;
import ru.rtln.reportservice.client.WorkingNormApiClient;
import ru.rtln.reportservice.dto.AnalyticDetailsDto;
import ru.rtln.reportservice.dto.ReportFilter;
import ru.rtln.reportservice.dto.StatisticReportDto;
import ru.rtln.reportservice.queue.common.model.producer.DailyEmployeeAnalyticsReport;
import ru.rtln.reportservice.queue.common.model.producer.DailyEmployeeScoreAnalyticsReport;
import ru.rtln.reportservice.queue.common.model.producer.DailyEmployeeScoreReport;
import ru.rtln.reportservice.queue.common.model.producer.DailyProductReport;
import ru.rtln.reportservice.queue.common.model.producer.event.CreateProducerEvent;
import ru.rtln.reportservice.service.AnalyticService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DailyGeneratorScheduler {

    @Value("${owner.email}")
    private String emailOwner;

    private final WorkingNormApiClient workingNormApiClient;

    private final AnalyticService analyticsService;

    private final ApplicationEventPublisher eventPublisher;

    @Scheduled(cron = "${spring.scheduled.cron.expression.daily}")
    @Loggable
    public void generateDailyProductReport() {
        ReportFilter filterForToday = getReportFilterForToday();
        List<StatisticReportDto> report = workingNormApiClient
                .getStatisticByFilter(filterForToday);

        var payload = DailyProductReport.builder()
                .uuid(UUID.randomUUID())
                .report(report)
                .build();
        eventPublisher.publishEvent(new CreateProducerEvent<>(payload));

        System.out.println(report);
    }

    @Scheduled(cron = "${spring.scheduled.cron.expression.daily}")
    @Loggable
    public void generateDailyAnalyticReport() {
        ReportFilter filterForToday = getReportFilterForToday();
        List<AnalyticDetailsDto> report = workingNormApiClient
                .getAnalyticsByFilter(filterForToday);

        var payload = DailyEmployeeAnalyticsReport.builder()
                .uuid(UUID.randomUUID())
                .report(report)
                .build();
        eventPublisher.publishEvent(new CreateProducerEvent<>(payload));

        System.out.println(report);
    }

    @Scheduled(cron = "${spring.scheduled.cron.expression.daily}")
    @Loggable
    public void generateDailyEmployeeScoreAnalyticsReport() {
        ReportFilter filterForToday = getReportFilterForToday();
        List<AnalyticDetailsDto> analytics = workingNormApiClient
                .getAnalyticsByFilter(filterForToday);
        Map<Long, Double> efficiencyByEmployees = analyticsService
                .calculatingEfficiencyByUsers(analytics);

        var payload = DailyEmployeeScoreAnalyticsReport.builder()
                .uuid(UUID.randomUUID())
                .efficiencyByUsers(efficiencyByEmployees)
                .build();
        eventPublisher.publishEvent(new CreateProducerEvent<>(payload));

        System.out.println(efficiencyByEmployees);
    }

    @Scheduled(cron = "${spring.scheduled.cron.expression.daily}")
    @Loggable
    public void generateDailyEmployeeScoreReport() {
        ReportFilter filterForToday = getReportFilterForToday();
        List<AnalyticDetailsDto> analytics = workingNormApiClient
                .getAnalyticsByFilter(filterForToday);

        analyticsService.calculatingEfficiencyByUsers(analytics)
                .forEach((email, efficiency) -> {
                    var payload = DailyEmployeeScoreReport.builder()
                            .uuid(UUID.randomUUID())
                            .userId(email)
                            .efficiency(efficiency)
                            .build();
                    eventPublisher.publishEvent(new CreateProducerEvent<>(payload));
                });
    }

    private ReportFilter getReportFilterForToday() {
        return ReportFilter.builder()
                .startTime(LocalDate.now().atStartOfDay())
                .endTime(LocalDateTime.now().plusHours(24))
                .build();
    }
}
