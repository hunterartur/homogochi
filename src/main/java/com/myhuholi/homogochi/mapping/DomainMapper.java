package com.myhuholi.homogochi.mapping;

import com.myhuholi.homogochi.domain.HomoState;
import com.myhuholi.homogochi.domain.enums.ActivityRate;
import com.myhuholi.homogochi.domain.enums.Sex;
import com.myhuholi.homogochi.dto.response.ActivityRateDto;
import com.myhuholi.homogochi.dto.response.ActivityRateRes;
import com.myhuholi.homogochi.dto.response.HomoStateDto;
import com.myhuholi.homogochi.dto.response.HomoStateRes;
import com.myhuholi.homogochi.dto.response.SexDto;
import com.myhuholi.homogochi.dto.response.SexRes;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface DomainMapper {
    SexDto sexIntoDto(Sex sex);

    SexRes sexListIntoSexRes(Sex[] sexList);

    ActivityRateDto activityRateIntoDto(ActivityRate activityRate);

    ActivityRateRes activityListIntoActivityRes(ActivityRate[] activityRateList);

    HomoStateDto stateIntoDto(HomoState state);

    default HomoStateRes stateListIntoStateRes(List<HomoState> homoStateList) {
        return stateListWrapIntoStateRes(new HomoStateWrapper(homoStateList));
    }

    HomoStateRes stateListWrapIntoStateRes(HomoStateWrapper stateWrapper);

    record HomoStateWrapper(List<HomoState> homoStateList){};
}
