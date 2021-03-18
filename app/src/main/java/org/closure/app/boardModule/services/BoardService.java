package org.closure.app.boardModule.services;

import org.closure.app.boardModule.dto.BoardRequest;
import org.closure.app.boardModule.dto.BoardResponse;
import org.closure.app.boardModule.exceptions.BoardErrorException;
import org.closure.app.boardModule.models.BoardModel;
import org.closure.app.boardModule.repositories.BoardRepository;
import org.closure.app.entities.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    BoardRepository boarderRepository;

    public BoardResponse addBoarder(BoardModel boarderModel)
    {
        BoardEntity entity = boarderRepository.save(
           new BoardEntity()
            .withDescription(boarderModel.getDescription())
            .withImage(boarderModel.getImage()))
            .withName(boarderModel.getName());
        BoardResponse boarderResponse = new BoardResponse()
            .withDescription(entity.getDescription())
            .withImage(entity.getImage())
            .withName(entity.getName());
        return boarderResponse;   
    }
    public BoardResponse getBoarder(Long id)
    {
        BoardEntity boardEntity = boarderRepository.findById(id).orElseThrow(() -> new BoardErrorException("no board with this id"));
        BoardResponse boardResponse = new BoardResponse()
            .withDescription(boardEntity.getDescription())
            .withId(boardEntity.getId())
            .withImage(boardEntity.getImage())
            .withName(boardEntity.getName());
            return boardResponse;
    }
}
