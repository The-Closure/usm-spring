package org.closure.app.profsModule.mapper;

import org.closure.app.entities.ProfsEntity;
import org.closure.app.profsModule.dto.ProfRequest;
import org.closure.app.profsModule.dto.ProfResponse;
import org.closure.app.profsModule.models.ProfModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProfMapper {

    public static ProfMapper INSTANCE = Mappers.getMapper( ProfMapper.class );

    public abstract ProfResponse profToResponse(ProfsEntity profs);

    @Mapping(target = "created_at", expression = "java(new java.util.Date())")
    public abstract ProfsEntity requestToNewProf(ProfRequest request);

    public abstract ProfsEntity requestToProf(ProfRequest request);

    public abstract ProfModel profToModel(ProfsEntity profs);

    public abstract ProfsEntity modelToProfs(ProfModel model);
    
}
