package org.closure.app.boardModule.services;

import java.util.List;

import org.closure.app.UserModule.dto.UserResponse;
import org.closure.app.UserModule.exceptions.UserErrorException;
import org.closure.app.UserModule.mapper.UserMapper;
import org.closure.app.UserModule.repositories.UserRepo;
import org.closure.app.boardModule.dto.BoardResponse;
import org.closure.app.boardModule.exceptions.BoardErrorException;
import org.closure.app.boardModule.mapper.BoardMapper;
import org.closure.app.boardModule.models.BoardModel;
import org.closure.app.boardModule.repositories.BoardRepository;
import org.closure.app.entities.BoardEntity;
import org.closure.app.entities.UserEntity;
import org.closure.app.profsModule.dto.ProfResponse;
import org.closure.app.profsModule.mapper.ProfMapper;
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
        if(boardRepository.findByName(boarderModel.getName()).isEmpty())
            return BoardMapper.INSTANCE.boardToResponse(
                boardRepository.save(
                    BoardMapper.INSTANCE.modelToBoard(boarderModel)
                )
            );   
        else 
            throw new BoardErrorException("this board is already exist");
       
    }
    public BoardResponse getBoard(Long id)
    {
        return BoardMapper.INSTANCE.boardToResponse(boardRepository.findById(id).orElseThrow(
                () -> new BoardErrorException("no board with this id")
            )
        );
    }
    public BoardResponse editBoard(Long id, BoardModel boardModel)
    {
        return BoardMapper.INSTANCE.boardToResponse(boardRepository.save(
            boardRepository.findById(id).orElseThrow(
                () -> new BoardErrorException("no board with this id"))
                    .description(boardModel.getDescription()) 
                    .image(boardModel.getImage())
                    .name(boardModel.getName())
            )
        );
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
        userRepo.save(user.boards(boardEntities));
        return true;
    }
    public List<UserResponse> getUsers(Long boardID)
    {
        return boardRepository.findById(boardID).orElseThrow(
            ()-> new BoardErrorException("no board with this id"))
            .getUsers()
            .stream()
            .map(
                UserMapper.INSTANCE::userToResponse)
            .toList();
  
    }

    public List<ProfResponse> getProfs(Long boardID)
    {
        return boardRepository.findById(boardID).orElseThrow(
            ()-> new BoardErrorException("no board with this id"))
            .getProfs()
            .stream()
            .map(ProfMapper.INSTANCE::profToResponse)
            .toList();
    }


}
