package com.myhuholi.homogochi.facade;

import com.myhuholi.homogochi.domain.HomoState;
import com.myhuholi.homogochi.domain.User;
import com.myhuholi.homogochi.dto.request.ChangeStateReq;
import com.myhuholi.homogochi.dto.request.RegisterReq;
import com.myhuholi.homogochi.dto.response.UserRes;
import com.myhuholi.homogochi.mapping.UserMapper;
import com.myhuholi.homogochi.service.HomoPictureService;
import com.myhuholi.homogochi.service.HomoStateService;
import com.myhuholi.homogochi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;
    private final HomoStateService stateService;
    private final UserMapper userMapper;
    private final HomoPictureService pictureService;

    public UserRes createUser(RegisterReq req) {
        User userToCreate = userMapper.registerReqIntoUser(req);
        HomoState normalState = stateService.findStateBySysName("normal");
        userToCreate.setState(normalState);
        User createdUser = userService.saveUser(userToCreate);
        pictureService.getPictureBySysName(createdUser);

        return userMapper.userIntoUserRes(createdUser);
    }

    public UserRes changeState(ChangeStateReq req) {
        User user = userService.findUserByUUID(req.uuid());
        HomoState targetState = stateService.findStateBySysName(req.stateSysName());
        user.setState(targetState);
        user = userService.saveUser(user);
        return userMapper.userIntoUserRes(user);
    }
}
