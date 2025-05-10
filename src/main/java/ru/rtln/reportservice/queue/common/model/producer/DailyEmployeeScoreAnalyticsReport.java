package ru.rtln.reportservice.queue.common.model.producer;

import lombok.Builder;
import lombok.Value;
import ru.rtln.reportservice.dto.StatisticReportDto;
import ru.rtln.reportservice.queue.common.model.PayloadModel;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Value
@Builder
public class DailyEmployeeScoreAnalyticsReport implements PayloadModel {

    UUID uuid;

    Map<Long, Double> efficiencyByUsers;
}
