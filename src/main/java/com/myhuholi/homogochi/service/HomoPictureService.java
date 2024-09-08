package com.myhuholi.homogochi.service;

import com.myhuholi.homogochi.domain.HomoPicture;
import com.myhuholi.homogochi.domain.User;
import com.myhuholi.homogochi.domain.enums.EntityName;
import com.myhuholi.homogochi.exception.EntityNotFoundException;
import com.myhuholi.homogochi.repository.HomoPictureRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HomoPictureService {
    private final HomoPictureRepository pictureRepository;

    public HomoPicture getPictureByUser(User user) {
        return getDefaultPictureBySysName(user.getState().getSysName());
    }

    public HomoPicture getDefaultPictureBySysName(String sysName) {
        return pictureRepository.findDefaultHomoPictureByStateSysName(sysName)
                .orElseThrow(() -> new EntityNotFoundException(EntityName.HOMO_PICTURE));
    }

    public List<HomoPicture> getDefaultPictures() {
        return pictureRepository.findHomoPictureByDefaultFlagIsTrue();
    }
}
