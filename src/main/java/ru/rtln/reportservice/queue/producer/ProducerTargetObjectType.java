package ru.rtln.reportservice.queue.producer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.rtln.reportservice.queue.common.model.producer.DailyEmployeeAnalyticsReport;
import ru.rtln.reportservice.queue.common.model.producer.DailyEmployeeScoreAnalyticsReport;
import ru.rtln.reportservice.queue.common.model.producer.DailyEmployeeScoreReport;
import ru.rtln.reportservice.queue.common.model.producer.DailyProductReport;

@Getter
@AllArgsConstructor
public enum ProducerTargetObjectType {

    DAILY_PRODUCT_REPORT("DAILY_PRODUCT_REPORT", DailyProductReport.class),
    DAILY_EMPLOYEE_ANALYTICS_REPORT("DAILY_EMPLOYEE_ANALYTICS_REPORT", DailyEmployeeAnalyticsReport.class),
    DAILY_EMPLOYEE_SCORE_ANALYTICS_REPORT("DAILY_EMPLOYEE_SCORE_ANALYTICS_REPORT", DailyEmployeeScoreAnalyticsReport.class),
    DAILY_EMPLOYEE_SCORE_REPORT("DAILY_EMPLOYEE_SCORE_REPORT", DailyEmployeeScoreReport.class);

    private final String name;
    private final Class<?> payloadType;
}
