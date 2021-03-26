package org.closure.app.boardModule.mapper;

import org.closure.app.boardModule.dto.BoardResponse;
import org.closure.app.boardModule.models.BoardModel;
import org.closure.app.entities.BoardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BoardMapper {
    
    public static BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    public BoardResponse boardToResponse(BoardEntity entity);

    public BoardEntity responseToBoard(BoardResponse response);

    public BoardEntity modelToBoard(BoardModel model);
}
