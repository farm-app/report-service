package ru.rtln.reportservice.queue.common.model.producer;

import lombok.Builder;
import lombok.Value;
import ru.rtln.reportservice.dto.AnalyticDetailsDto;
import ru.rtln.reportservice.dto.StatisticReportDto;
import ru.rtln.reportservice.queue.common.model.PayloadModel;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class DailyEmployeeAnalyticsReport implements PayloadModel {

    UUID uuid;

    List<AnalyticDetailsDto> report;
}
