package com.myhuholi.homogochi.service;

import com.myhuholi.homogochi.domain.HomoState;
import com.myhuholi.homogochi.domain.enums.EntityName;
import com.myhuholi.homogochi.exception.EntityNotFoundException;
import com.myhuholi.homogochi.repository.HomoStateRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HomoStateService {
    private final HomoStateRepository stateRepository;

    public List<HomoState> getAllStates() {
        return stateRepository.findAll();
    }

    public HomoState findStateBySysName(String sysName) {
        return stateRepository.findHomoStateBySysName(sysName)
                .orElseThrow(() -> new EntityNotFoundException(EntityName.HOMO_STATE));
    }

    public HomoState findDefaultHomoState() {
        return stateRepository.findHomoStateByDefaultFlagIsTrue()
                .orElseThrow(() -> new EntityNotFoundException(EntityName.HOMO_STATE));
    }
}
