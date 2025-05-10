package ru.rtln.reportservice.queue.common.model.producer.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;
import ru.rtln.reportservice.queue.common.model.PayloadModel;

@Getter
@Setter
public abstract class ProducerAbstractEvent<PM extends PayloadModel> implements ResolvableTypeProvider {

    private PM payload;

    @Override
    public ResolvableType getResolvableType() {
        if (payload == null) {
            throw new IllegalArgumentException("Message can not be constructed");
        }
        return ResolvableType.forClassWithGenerics(this.getClass(), payload.getClass());
    }
}

