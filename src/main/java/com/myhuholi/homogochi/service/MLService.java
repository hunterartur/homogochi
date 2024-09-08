package com.myhuholi.homogochi.service;

import com.myhuholi.homogochi.dto.ext.request.StepEstimator;
import com.myhuholi.homogochi.dto.ext.response.BaseLineSteps;
import java.net.URI;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class MLService {
    private final RestTemplate restTemplate;

    public int getStepsCount(StepEstimator stepEstimator) {
        log.info(String.format("Getting steps count for parameters: %s", stepEstimator));
        HttpEntity<StepEstimator> request = new HttpEntity<>(stepEstimator);
        ResponseEntity<BaseLineSteps> response = restTemplate.exchange("/predict", HttpMethod.POST,
                request, BaseLineSteps.class);
        BaseLineSteps responseBody = response.getBody();
        log.info(String.format("Got ML response: %s", responseBody));
        return Optional.ofNullable(responseBody)
                .map(BaseLineSteps::baseline_steps)
                .orElse(1000);
    }
}
