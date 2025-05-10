package ru.rtln.reportservice.queue.producer.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.rtln.reportservice.queue.common.model.producer.DailyEmployeeAnalyticsReport;
import ru.rtln.reportservice.queue.common.model.producer.DailyEmployeeScoreAnalyticsReport;
import ru.rtln.reportservice.queue.common.model.producer.DailyEmployeeScoreReport;
import ru.rtln.reportservice.queue.common.model.producer.DailyProductReport;
import ru.rtln.reportservice.queue.common.model.producer.event.CreateProducerEvent;
import ru.rtln.reportservice.queue.producer.mapper.impl.DailyEmployeeAnalyticsReportKafkaMessageStructImpl;
import ru.rtln.reportservice.queue.producer.mapper.impl.DailyEmployeeScoreAnalyticsReportKafkaMessageStructImpl;
import ru.rtln.reportservice.queue.producer.mapper.impl.DailyEmployeeScoreReportKafkaMessageStructImpl;
import ru.rtln.reportservice.queue.producer.mapper.impl.DailyProductReportKafkaMessageStructImpl;
import ru.rtln.reportservice.queue.producer.service.PublisherService;

import static ru.rtln.reportservice.queue.common.model.EventDeterminant.CREATE;

@ConditionalOnProperty(name = "report-service.kafka.enabled", havingValue = "true")
@RequiredArgsConstructor
@Service
public class ReportListener {

    private final PublisherService publisherService;

    private final DailyProductReportKafkaMessageStructImpl dailyProductReportKafkaMessageStruct;
    private final DailyEmployeeScoreReportKafkaMessageStructImpl dailyEmployeeScoreReportKafkaMessageStruct;
    private final DailyEmployeeAnalyticsReportKafkaMessageStructImpl dailyEmployeeAnalyticsReportKafkaMessageStruct;
    private final DailyEmployeeScoreAnalyticsReportKafkaMessageStructImpl dailyEmployeeScoreAnalyticsReportKafkaMessageStruct;

    @Value("${report-service.kafka.producer.topics.system-event-topic}")
    private String systemEventTopic;

    @Async
    @EventListener
    public void sendDailyProductReportNotification(CreateProducerEvent<DailyProductReport> event) {
        var payload = event.getPayload();
        var message = dailyProductReportKafkaMessageStruct.toMessage(payload, CREATE);
        publisherService.sendMessage(systemEventTopic, message, dailyProductReportKafkaMessageStruct.defineKey(payload));
    }

    @Async
    @EventListener
    public void sendDailyEmployeeScoreAnalyticsReportNotification(CreateProducerEvent<DailyEmployeeScoreAnalyticsReport> event) {
        var payload = event.getPayload();
        var message = dailyEmployeeScoreAnalyticsReportKafkaMessageStruct.toMessage(payload, CREATE);
        publisherService.sendMessage(systemEventTopic, message, dailyEmployeeScoreAnalyticsReportKafkaMessageStruct.defineKey(payload));
    }

    @Async
    @EventListener
    public void sendDailyEmployeeAnalyticsReportNotification(CreateProducerEvent<DailyEmployeeAnalyticsReport> event) {
        var payload = event.getPayload();
        var message = dailyEmployeeAnalyticsReportKafkaMessageStruct.toMessage(payload, CREATE);
        publisherService.sendMessage(systemEventTopic, message, dailyEmployeeAnalyticsReportKafkaMessageStruct.defineKey(payload));
    }

    @Async
    @EventListener
    public void sendDailyEmployeeScoreReportNotification(CreateProducerEvent<DailyEmployeeScoreReport> event) {
        var payload = event.getPayload();
        var message = dailyEmployeeScoreReportKafkaMessageStruct.toMessage(payload, CREATE);
        publisherService.sendMessage(systemEventTopic, message, dailyEmployeeScoreReportKafkaMessageStruct.defineKey(payload));
    }

}
