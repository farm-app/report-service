package ru.rtln.reportservice.queue.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

/**
 * Represents the Kafka message.
 */
@JsonPropertyOrder({
        "eventType",
        "targetObjectType",
        "targetObjectId",
        "eventTimestamp",
        "targetObjectName",
        "changedAttributes",
        "additionalInfo"
})
@Getter
@Setter
@ToString
public class KafkaMessage<PM extends PayloadModel> {

    private PM attributes;
    private String eventType;
    private String targetObjectType;
    private String targetObjectId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime eventTimestamp;

    private AdditionalInfo additionalInfo;
}
