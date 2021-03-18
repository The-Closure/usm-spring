package org.closure.app.boardModule.controllers;

import java.util.List;

import org.closure.app.UserModule.dto.UserResponse;
import org.closure.app.boardModule.dto.BoardResponse;
import org.closure.app.boardModule.models.BoardModel;
import org.closure.app.boardModule.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping(path = "/api/v2/boards")
public class BoardController {
    
    @Autowired
    private BoardService boardService;

    @PostMapping(value="/addboard")
    public BoardResponse addBoard(@RequestBody BoardModel entity) {
        return boardService.addBoarder(entity);
    }

    @GetMapping(value="/getboard")
    public BoardResponse getBoard(@RequestParam(name = "id") Long id) {
        return boardService.getBoard(id);
    }

    @PutMapping(value="editboard/{id}")
    public BoardResponse editBoard(@PathVariable String id, @RequestBody BoardModel model) {
        return boardService.editBoard(Long.parseLong(id), model);
    }

    @DeleteMapping(value = "/delete")
    public boolean deleteBoard(@RequestParam(name = "id") Long id)
    {
        return boardService.deleteBoard(id);
    }

    @PutMapping(value="joinboard/{userID}/{boardID}")
    public boolean joinBoard
        (
            @PathVariable(name = "userID") String userID,
            @PathVariable(name = "boardID") String boardID
        ) 
    {
        return boardService.joinBoard(Long.parseLong(userID), Long.parseLong(boardID));
    }
    
    @PutMapping(value="joinboard/{userID}/{boardID}")
    public boolean leaveBoard(@PathVariable(name = "userID") String userID,@PathVariable(name = "boardID") String boardID) {
        return boardService.leaveBoard(Long.parseLong(userID), Long.parseLong(boardID));
    }
    
    @GetMapping(value="/getusers")
    public List<UserResponse> getUserForBoard(@RequestParam(name = "boardID") String boardID) {
        return boardService.getUsers(Long.parseLong(boardID));
    }



}
