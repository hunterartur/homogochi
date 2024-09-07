package com.myhuholi.homogochi.routes;

import com.myhuholi.homogochi.domain.enums.ActivityRate;
import com.myhuholi.homogochi.domain.enums.Sex;
import com.myhuholi.homogochi.dto.response.ActivityRateRes;
import com.myhuholi.homogochi.dto.response.HomoStateRes;
import com.myhuholi.homogochi.dto.response.SexRes;
import com.myhuholi.homogochi.mapping.DomainMapper;
import com.myhuholi.homogochi.service.HomoStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/domain")
@RequiredArgsConstructor
public class DomainController {
    private final DomainMapper domainMapper;
    private final HomoStateService stateService;

    @GetMapping(path = "/sex")
    public ResponseEntity<SexRes> getSupportedSexList() {
        SexRes sexRes = domainMapper.sexListIntoSexRes(Sex.values());
        return ResponseEntity.ok(sexRes);
    }

    @GetMapping(path = "/activity")
    public ResponseEntity<ActivityRateRes> getSupportedActivityRateList() {
        ActivityRateRes activityRateRes = domainMapper.activityListIntoActivityRes(ActivityRate.values());
        return ResponseEntity.ok(activityRateRes);
    }

    @GetMapping(path = "/state")
    public ResponseEntity<HomoStateRes> getSupportedStateList() {
        HomoStateRes stateRes = domainMapper.stateListIntoStateRes(stateService.getAllStates());
        return ResponseEntity.ok(stateRes);
    }
}
