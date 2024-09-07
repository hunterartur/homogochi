package com.myhuholi.homogochi.service;

import com.myhuholi.homogochi.domain.User;
import com.myhuholi.homogochi.domain.enums.EntityName;
import com.myhuholi.homogochi.exception.EntityNotFoundException;
import com.myhuholi.homogochi.repository.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUserByUUID(UUID uuid) {
        return userRepository.findUserByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException(EntityName.USER));
    }

    public User saveUser(User userToSave) {
        return userRepository.save(userToSave);
    }
}
