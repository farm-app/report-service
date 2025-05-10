package ru.rtln.reportservice.queue.producer.mapper.impl;

import org.springframework.stereotype.Service;
import ru.rtln.reportservice.queue.common.model.MessageMetaInfoStruct;
import ru.rtln.reportservice.queue.common.model.producer.DailyEmployeeAnalyticsReport;
import ru.rtln.reportservice.queue.producer.ProducerTargetObjectType;
import ru.rtln.reportservice.queue.producer.mapper.AbstractKafkaMessageStruct;

import static ru.rtln.reportservice.queue.producer.service.PublisherService.KEY_TEMPLATE;

@Service
public class DailyEmployeeAnalyticsReportKafkaMessageStructImpl extends AbstractKafkaMessageStruct<DailyEmployeeAnalyticsReport> {

    private static final ProducerTargetObjectType PRODUCER_TARGET_OBJECT_TYPE = ProducerTargetObjectType.DAILY_EMPLOYEE_ANALYTICS_REPORT;

    protected DailyEmployeeAnalyticsReportKafkaMessageStructImpl(MessageMetaInfoStruct metaInfoStruct) {
        super(metaInfoStruct);
    }

    @Override
    protected ProducerTargetObjectType defineTargetObjectType() {
        return PRODUCER_TARGET_OBJECT_TYPE;
    }

    @Override
    protected String defineTargetObjectId(DailyEmployeeAnalyticsReport model) {
        return model.getUuid().toString();
    }

    @Override
    protected Class<DailyEmployeeAnalyticsReport> definePayloadType() {
        return DailyEmployeeAnalyticsReport.class;
    }

    @Override
    public String defineKey(DailyEmployeeAnalyticsReport model) {
        return KEY_TEMPLATE.formatted(PRODUCER_TARGET_OBJECT_TYPE, model.getUuid());
    }
}
