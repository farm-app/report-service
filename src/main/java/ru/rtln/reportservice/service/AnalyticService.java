package ru.rtln.reportservice.service;

import ru.rtln.reportservice.dto.AnalyticDetailsDto;

import java.util.List;
import java.util.Map;

public interface AnalyticService {

    Map<Long, Double> calculatingEfficiencyByUsers(List<AnalyticDetailsDto> analytics);

}
