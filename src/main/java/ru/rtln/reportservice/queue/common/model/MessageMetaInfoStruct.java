package ru.rtln.reportservice.queue.common.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class MessageMetaInfoStruct {

    @Value("${spring.application.name}")
    private String additionalInfoAppId;

    public <PM extends PayloadModel> KafkaMessage<PM> mapMetaInfo(KafkaMessage<PM> message) {
        message.setAdditionalInfo(new AdditionalInfo(additionalInfoAppId));
        message.setEventTimestamp(ZonedDateTime.now());
        return message;
    }
}
