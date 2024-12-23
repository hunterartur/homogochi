package com.myhuholi.homogochi.facade;

import com.myhuholi.homogochi.domain.HomoPicture;
import com.myhuholi.homogochi.domain.HomoState;
import com.myhuholi.homogochi.domain.User;
import com.myhuholi.homogochi.dto.ext.request.StepEstimator;
import com.myhuholi.homogochi.dto.request.ChangeStateReq;
import com.myhuholi.homogochi.dto.request.RegisterReq;
import com.myhuholi.homogochi.dto.request.StepStatisticReq;
import com.myhuholi.homogochi.dto.response.StepsForNextDayRes;
import com.myhuholi.homogochi.dto.response.UserRes;
import com.myhuholi.homogochi.mapping.UserMapper;
import com.myhuholi.homogochi.service.HomoPictureService;
import com.myhuholi.homogochi.service.HomoStateService;
import com.myhuholi.homogochi.service.MLService;
import com.myhuholi.homogochi.service.UserService;
import com.myhuholi.homogochi.storage.FileStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;
    private final HomoStateService stateService;
    private final UserMapper userMapper;
    private final HomoPictureService pictureService;
    private final FileStorage fileStorage;
    private final MLService mlService;

    public UserRes createUser(RegisterReq req) {
        User userToCreate = userMapper.registerReqIntoUser(req);
        HomoState normalState = stateService.findDefaultHomoState();
        userToCreate.setState(normalState);
        User createdUser = userService.saveUser(userToCreate);
        int stepsForUser = getStepsForUser(createdUser);
        byte[] pictureBytes = getPictureForUser(createdUser);

        return userMapper.userIntoUserRes(createdUser, pictureBytes, stepsForUser);
    }

    public UserRes changeState(ChangeStateReq req) {
        User user = userService.findUserByUUID(req.uuid());
        HomoState targetState = stateService.findStateBySysName(req.stateSysName());
        user.setState(targetState);
        user = userService.saveUser(user);
        int stepsForUser = getStepsForUser(user);
        byte[] pictureBytes = getPictureForUser(user);
        return userMapper.userIntoUserRes(user, pictureBytes, stepsForUser);
    }

    public StepsForNextDayRes acceptStatistic(StepStatisticReq req) {
        return new StepsForNextDayRes(req.uuid(), req.stepsCount());
    }

    public byte[] getPictureForUser(User user) {
        HomoPicture pictureBySysName = pictureService.getPictureByUser(user);
        return fileStorage.loadFileBytes(pictureBySysName.getPictureFileName());
    }

    public int getStepsForUser(User user) {
        StepEstimator stepEstimator = userMapper.userIntoStepEstimator(user);
        return mlService.getStepsCount(stepEstimator);
    }
}

