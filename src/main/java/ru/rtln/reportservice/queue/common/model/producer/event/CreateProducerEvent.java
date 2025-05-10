package ru.rtln.reportservice.queue.common.model.producer.event;

import ru.rtln.reportservice.queue.common.model.PayloadModel;

public class CreateProducerEvent<PM extends PayloadModel> extends ProducerAbstractEvent<PM> {

    public CreateProducerEvent(PM payload) {
        setPayload(payload);
    }
}
