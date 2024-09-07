package com.myhuholi.homogochi.mapping;

import com.myhuholi.homogochi.domain.User;
import com.myhuholi.homogochi.dto.request.RegisterReq;
import com.myhuholi.homogochi.dto.response.UserRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
    @Mapping(target = "sex", expression = "java(com.myhuholi.homogochi.domain.enums.Sex.getBySysName(dto.sexSysName()))")
    @Mapping(target = "activityRate", expression = "java(com.myhuholi.homogochi.domain.enums.ActivityRate.getBySysName(dto.activitySysName()))")
    User registerReqIntoUser(RegisterReq dto);

    @Mapping(target = "sexSysName", expression = "java(entity.getSex() == null ? null : entity.getSex().getSysName())")
    @Mapping(target = "sexDescription", expression = "java(entity.getSex() == null ? null : entity.getSex().getDescription())")
    @Mapping(target = "activityRate", expression = "java(entity.getActivityRate() == null ? null : entity.getActivityRate().getValue())")
    @Mapping(target = "activityRateDescription", expression = "java(entity.getActivityRate() == null ? null : entity.getActivityRate().getDescription())")
    @Mapping(target = "stateSysName", source = "state.sysName")
    @Mapping(target = "stateBrief", source = "state.brief")
    @Mapping(target = "stateDescription", source = "state.description")
    UserRes userIntoUserRes(User entity);
}
