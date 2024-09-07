package com.myhuholi.homogochi.dto.request;

import java.util.UUID;

public record RegisterReq(UUID uuid, String name, Integer age, String sexSysName,
                          Integer weight, Integer height, String activitySysName) {
}
