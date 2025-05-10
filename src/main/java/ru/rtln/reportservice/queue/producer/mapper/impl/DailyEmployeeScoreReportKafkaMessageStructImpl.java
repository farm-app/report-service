package ru.rtln.reportservice.queue.producer.mapper.impl;

import org.springframework.stereotype.Service;
import ru.rtln.reportservice.queue.common.model.MessageMetaInfoStruct;
import ru.rtln.reportservice.queue.common.model.producer.DailyEmployeeScoreReport;
import ru.rtln.reportservice.queue.producer.ProducerTargetObjectType;
import ru.rtln.reportservice.queue.producer.mapper.AbstractKafkaMessageStruct;

import static ru.rtln.reportservice.queue.producer.service.PublisherService.KEY_TEMPLATE;

@Service
public class DailyEmployeeScoreReportKafkaMessageStructImpl extends AbstractKafkaMessageStruct<DailyEmployeeScoreReport> {

    private static final ProducerTargetObjectType PRODUCER_TARGET_OBJECT_TYPE = ProducerTargetObjectType.DAILY_EMPLOYEE_SCORE_REPORT;

    protected DailyEmployeeScoreReportKafkaMessageStructImpl(MessageMetaInfoStruct metaInfoStruct) {
        super(metaInfoStruct);
    }

    @Override
    protected ProducerTargetObjectType defineTargetObjectType() {
        return PRODUCER_TARGET_OBJECT_TYPE;
    }

    @Override
    protected String defineTargetObjectId(DailyEmployeeScoreReport model) {
        return model.getUuid().toString();
    }

    @Override
    protected Class<DailyEmployeeScoreReport> definePayloadType() {
        return DailyEmployeeScoreReport.class;
    }

    @Override
    public String defineKey(DailyEmployeeScoreReport model) {
        return KEY_TEMPLATE.formatted(PRODUCER_TARGET_OBJECT_TYPE, model.getUuid());
    }
}
