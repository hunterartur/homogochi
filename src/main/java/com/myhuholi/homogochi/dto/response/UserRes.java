package com.myhuholi.homogochi.dto.response;

import java.util.UUID;

public record UserRes(UUID uuid, String name, Integer age, String sexSysName, String sexDescription,
                      Integer weight, Integer height, Integer activityRate, String activityRateDescription,
                      String stateSysName, String stateBrief, String stateDescription, Integer stepsCount, byte[] pictureBytes) {

}
