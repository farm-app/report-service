package ru.rtln.reportservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.rtln.reportservice.config.ClientConfiguration;
import ru.rtln.reportservice.dto.AnalyticDetailsDto;
import ru.rtln.reportservice.dto.ReportFilter;
import ru.rtln.reportservice.dto.StatisticReportDto;

import java.util.List;

@FeignClient(name = "workingNormApiClient", url = "${internal.service.working-norm.url}", configuration = ClientConfiguration.class)
public interface WorkingNormApiClient {

    @GetMapping("/reports/statistic")
    List<StatisticReportDto> getStatisticByFilter(@SpringQueryMap ReportFilter filter);

    @GetMapping("/reports/analytics")
    List<AnalyticDetailsDto> getAnalyticsByFilter(@SpringQueryMap ReportFilter reportFilter);
}
