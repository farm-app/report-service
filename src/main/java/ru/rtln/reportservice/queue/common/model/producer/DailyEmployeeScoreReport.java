package ru.rtln.reportservice.queue.common.model.producer;

import lombok.Builder;
import lombok.Value;
import ru.rtln.reportservice.queue.common.model.PayloadModel;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class DailyEmployeeScoreReport implements PayloadModel {

    UUID uuid;

    Long userId;

    Double efficiency;
}
