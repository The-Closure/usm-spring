package org.closure.app.boardModule.services;

import java.util.ArrayList;
import java.util.List;

import org.closure.app.UserModule.dto.UserResponse;
import org.closure.app.UserModule.exceptions.UserErrorException;
import org.closure.app.UserModule.repositories.UserRepo;
import org.closure.app.boardModule.dto.BoardResponse;
import org.closure.app.boardModule.exceptions.BoardErrorException;
import org.closure.app.boardModule.models.BoardModel;
import org.closure.app.boardModule.repositories.BoardRepository;
import org.closure.app.entities.BoardEntity;
import org.closure.app.entities.ProfsEntity;
import org.closure.app.entities.UserEntity;
import org.closure.app.profsModule.dto.ProfResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepo userRepo;
    public BoardResponse addBoard(BoardModel boarderModel)
    {
        BoardEntity entity = boardRepository.save(
           new BoardEntity()
            .withDescription(boarderModel.getDescription())
            .withName(boarderModel.getName())
            .withImage(boarderModel.getImage()));
        BoardResponse boarderResponse = new BoardResponse()
            .withDescription(entity.getDescription())
            .withImage(entity.getImage())
            .withName(entity.getName())
            .withId(entity.getId());
        return boarderResponse;   
    }
    public BoardResponse getBoard(Long id)
    {
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow(
            () -> new BoardErrorException("no board with this id"));
            System.out.println(boardEntity.getName());
        BoardResponse boardResponse = new BoardResponse()
            .withDescription(boardEntity.getDescription())
            .withId(boardEntity.getId())
            .withName(boardEntity.getName())
            .withImage(boardEntity.getImage());
            return boardResponse;
    }
    public BoardResponse editBoard(Long id, BoardModel boardModel)
    {
        BoardEntity e = boardRepository.findById(id).orElseThrow(
            () -> new BoardErrorException("no board with this id"));

        e = boardRepository.save(
                e
                .withDescription(boardModel.getDescription()) 
                .withImage(boardModel.getImage())
                .withName(boardModel.getName())
            );
        BoardResponse boardResponse = new BoardResponse()
            .withDescription(e.getDescription())
            .withId(e.getId())
            .withImage(e.getImage())
            .withName(e.getName());
        return boardResponse;
    }

    public boolean deleteBoard(Long id)
    {
        boardRepository.deleteById(id);
        return boardRepository.findById(id).isEmpty();
    }

    public boolean joinBoard(Long userID, Long boardID)
    {
        
        UserEntity user = userRepo.findById(userID).orElseThrow(()-> 
            new UserErrorException("no user with this id"));
        List<BoardEntity> boardEntities = user.getBoards();
        BoardEntity boardEntity = boardRepository.findById(boardID).orElseThrow(() -> 
            new BoardErrorException("no board with this id"));
        boolean exist = boardEntities.stream().anyMatch((e) -> e.getId().equals(boardID));
        if(exist) return false;
        boardEntities.add(boardEntity);
        user.setBoards(boardEntities);
        userRepo.save(user);
        return true;
    }

    public boolean leaveBoard(Long userID, Long boardID)
    {
        UserEntity user = userRepo.findById(userID).orElseThrow(
            ()-> new UserErrorException("no user with this id"));
        List<BoardEntity> boardEntities = user.getBoards();
        boardRepository.findById(boardID).orElseThrow(
            () -> new BoardErrorException("no board with this id"));
        boardEntities = boardEntities.stream().filter((e) -> !e.getId().equals(boardID)).toList();    
        userRepo.save(user.withBoards(boardEntities));
        return true;
    }
    public List<UserResponse> getUsers(Long boardID)
    {
        List<UserEntity> users= boardRepository.findById(boardID).orElseThrow(
            ()-> new BoardErrorException("no board with this id")).getUsers();
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach((e) -> {
            userResponses.add
                (
                    new UserResponse()
                        .withId(e.getId())
                        .withName(e.getName())
                        .withImg(e.getImg())
                );
        });
        return userResponses;
    }

    public List<ProfResponse> getProfs(Long boardID)
    {
        List<ProfsEntity> Profs= boardRepository.findById(boardID).orElseThrow(
            ()-> new BoardErrorException("no board with this id")).getProfs();
        List<ProfResponse> ProfResponses = new ArrayList<>();
        Profs.forEach((e) -> {
            ProfResponses.add
                (
                    new ProfResponse()
                        .withId(e.getId())
                        .withName(e.getName())
                        .withImage(e.getImage())
                        .withCreated_at(e.getCreated_at())
                        .withSpec(e.getSpec())
                        .withEmail(e.getEmail())
                        .withPassword(e.getPassword())
                );
        });
        return ProfResponses;
    }


}
