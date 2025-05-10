package ru.rtln.reportservice.queue.producer.mapper.impl;

import org.springframework.stereotype.Service;
import ru.rtln.reportservice.queue.common.model.MessageMetaInfoStruct;
import ru.rtln.reportservice.queue.common.model.producer.DailyProductReport;
import ru.rtln.reportservice.queue.producer.ProducerTargetObjectType;
import ru.rtln.reportservice.queue.producer.mapper.AbstractKafkaMessageStruct;

import static ru.rtln.reportservice.queue.producer.service.PublisherService.KEY_TEMPLATE;

@Service
public class DailyProductReportKafkaMessageStructImpl extends AbstractKafkaMessageStruct<DailyProductReport> {

    private static final ProducerTargetObjectType PRODUCER_TARGET_OBJECT_TYPE = ProducerTargetObjectType.DAILY_PRODUCT_REPORT;

    protected DailyProductReportKafkaMessageStructImpl(MessageMetaInfoStruct metaInfoStruct) {
        super(metaInfoStruct);
    }

    @Override
    protected ProducerTargetObjectType defineTargetObjectType() {
        return PRODUCER_TARGET_OBJECT_TYPE;
    }

    @Override
    protected String defineTargetObjectId(DailyProductReport model) {
        return model.getUuid().toString();
    }

    @Override
    protected Class<DailyProductReport> definePayloadType() {
        return DailyProductReport.class;
    }

    @Override
    public String defineKey(DailyProductReport model) {
        return KEY_TEMPLATE.formatted(PRODUCER_TARGET_OBJECT_TYPE, model.getUuid());
    }
}
