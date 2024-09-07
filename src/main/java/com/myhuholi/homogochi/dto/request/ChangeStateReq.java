package com.myhuholi.homogochi.dto.request;

import java.util.UUID;

public record ChangeStateReq(UUID uuid, String stateSysName) {
}
