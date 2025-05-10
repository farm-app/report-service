package ru.rtln.reportservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rtln.reportservice.aop.annotation.Loggable;
import ru.rtln.reportservice.dto.AnalyticDetailsDto;
import ru.rtln.reportservice.service.AnalyticService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalyticServiceImpl implements AnalyticService {

    @Override
    @Loggable
    public Map<Long, Double> calculatingEfficiencyByUsers(List<AnalyticDetailsDto> analytics) {
        return analytics.stream()
                .collect(Collectors.groupingBy(
                        AnalyticDetailsDto::getUserId,
                        Collectors.averagingDouble(AnalyticDetailsDto::getTotalScore)));
    }
}
