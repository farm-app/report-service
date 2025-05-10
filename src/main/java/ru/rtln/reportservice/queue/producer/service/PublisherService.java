package ru.rtln.reportservice.queue.producer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.rtln.reportservice.queue.common.model.KafkaMessage;
import ru.rtln.reportservice.queue.common.model.PayloadModel;

import java.util.Locale;

@Slf4j
@Service
public class PublisherService {

    private final KafkaTemplate<String, Object> template;

    public static final String KEY_TEMPLATE = "%s_%s";

    public PublisherService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.template = kafkaTemplate;
    }

    public <PM extends PayloadModel> void sendMessage(String topic, KafkaMessage<PM> data, String key) {
        template.send(topic, key.toLowerCase(Locale.ROOT), data)
                .handle((result, throwable) -> {
                    if (throwable == null) {
                        log.info("Message successfully sent: {}", result.getProducerRecord().value());
                        log.info("Offset changed to {}", result.getRecordMetadata().offset());
                    } else {
                        log.error("Failed to send message: {}", data, throwable);
                    }
                    return null;
                });
    }
}
