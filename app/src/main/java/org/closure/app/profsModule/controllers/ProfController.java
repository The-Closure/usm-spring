package org.closure.app.profsModule.controllers;

import java.util.List;

import org.closure.app.boardModule.dto.BoardResponse;
import org.closure.app.profsModule.dto.ProfRequest;
import org.closure.app.profsModule.dto.ProfResponse;
import org.closure.app.profsModule.services.ProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping(path = "/v2/api/profs")
public class ProfController {
    

    @Autowired
    ProfService profService;

    @PostMapping(value="/addprof")
    public ProfResponse addPfrof(@RequestBody ProfRequest entity) {
        return profService.addProf(entity);
    }
    
    @GetMapping(value="/getprof")
    public ProfResponse getProf(@RequestParam(name = "id") String id) {
        return profService.getProf(Long.parseLong(id));
    }

    @PutMapping(value="updateprof/{id}")
    public ProfResponse updatProf(@PathVariable(name = "id") String id, @RequestBody ProfRequest entity) {        
        return profService.updateProf(Long.parseLong(id), entity);
    }

    @DeleteMapping(value = "deleteprof")
    public boolean deleteProf(@RequestParam(name = "id") String id)
    {
        return profService.deleteProf(Long.parseLong(id));
    }

    @PutMapping(value="joinboard/{profID}/{boardID}")
    public boolean joinBoard(@PathVariable(name = "profID") String profID, @PathVariable(name = "boardID") String boardID) 
    {
        return profService.joinBoard(Long.parseLong(profID), Long.parseLong(boardID));
    }

    @PutMapping(value="leaveboard/{profID}/{boardID}")
    public boolean leaveBoard(@PathVariable(name = "profID") String profID, @PathVariable(name = "boardID") String boardID) 
    {
        return profService.leaveBoard(Long.parseLong(profID), Long.parseLong(boardID));
    }
    
    @GetMapping(value="/getboards")
    public List<BoardResponse> getBoardsForProf(@RequestParam(name = "id") String id) {
        return profService.getBoardsForProf(Long.parseLong(id));
    }
    
    
}
