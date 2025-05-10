package ru.rtln.reportservice.queue.producer.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import ru.rtln.reportservice.queue.common.model.EventDeterminant;
import ru.rtln.reportservice.queue.common.model.KafkaMessage;
import ru.rtln.reportservice.queue.common.model.MessageMetaInfoStruct;
import ru.rtln.reportservice.queue.common.model.PayloadModel;
import ru.rtln.reportservice.queue.producer.ProducerTargetObjectType;

@Slf4j
public abstract class AbstractKafkaMessageStruct<PM extends PayloadModel> {

    private final MessageMetaInfoStruct metaInfoStruct;
    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    protected AbstractKafkaMessageStruct(MessageMetaInfoStruct metaInfoStruct) {
        this.metaInfoStruct = metaInfoStruct;
    }

    public KafkaMessage<PM> toMessage(PM model, EventDeterminant eventType) {
        if (model == null) {
            return null;
        }

        final var result = new KafkaMessage<PM>();
        result.setEventType(eventType.getEventName());
        result.setTargetObjectType(defineTargetObjectType().getName());
        result.setTargetObjectId(defineTargetObjectId(model));
        result.setAttributes(model);
        return metaInfoStruct.mapMetaInfo(result);
    }

    public PM getPayloadFromString(String payload) {
        try {
            return mapper.readValue(payload, definePayloadType());
        } catch (JsonProcessingException e) {
            log.error("Could not read {} object!", definePayloadType().getName(), e);
            throw new IllegalArgumentException("Could not read " + definePayloadType().getName() + " object!", e);
        }
    }

    protected abstract ProducerTargetObjectType defineTargetObjectType();

    protected abstract String defineTargetObjectId(PM model);

    protected abstract Class<PM> definePayloadType();

    public abstract String defineKey(PM model);
}
