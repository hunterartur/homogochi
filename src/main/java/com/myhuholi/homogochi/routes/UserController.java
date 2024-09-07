package com.myhuholi.homogochi.routes;

import com.myhuholi.homogochi.dto.request.ChangeStateReq;
import com.myhuholi.homogochi.dto.request.RegisterReq;
import com.myhuholi.homogochi.dto.response.UserRes;
import com.myhuholi.homogochi.facade.UserFacade;
import com.myhuholi.homogochi.mapping.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserFacade userFacade;

    @PostMapping(path = "/register")
    public ResponseEntity<UserRes> singUp(@RequestBody RegisterReq req) {
        return ResponseEntity.ok(userFacade.createUser(req));
    }

    @PostMapping(path = "/update_state")
    public ResponseEntity<UserRes> changeState(@RequestBody ChangeStateReq req) {
        return ResponseEntity.ok(userFacade.changeState(req));
    }
}
