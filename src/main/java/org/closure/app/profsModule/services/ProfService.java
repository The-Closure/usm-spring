package org.closure.app.profsModule.services;

import java.util.List;

import org.closure.app.boardModule.dto.BoardResponse;
import org.closure.app.boardModule.exceptions.BoardErrorException;
import org.closure.app.boardModule.mapper.BoardMapper;
import org.closure.app.boardModule.repositories.BoardRepository;
import org.closure.app.entities.BoardEntity;
import org.closure.app.entities.ProfsEntity;
import org.closure.app.profsModule.dto.ProfRequest;
import org.closure.app.profsModule.dto.ProfResponse;
import org.closure.app.profsModule.exceptions.ProfsErrorException;
import org.closure.app.profsModule.mapper.ProfMapper;
import org.closure.app.profsModule.repositories.ProfRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfService {
    
    @Autowired
    ProfRepo profRepo;

    @Autowired 
    BoardRepository boardRepo;

    public ProfResponse addProf(ProfRequest profRequest)
    {
        return ProfMapper.INSTANCE.profToResponse(profRepo.save(ProfMapper.INSTANCE.requestToProf(profRequest)));
    }

    public ProfResponse getProf(Long id)
    {
        return ProfMapper.INSTANCE.profToResponse(profRepo.findById(id).orElseThrow(
                () -> new ProfsErrorException("no prof with this id")
            )
        );
    }
    public ProfResponse updateProf(Long id, ProfRequest model)
    {
        profRepo.findById(id).orElseThrow(
            () -> new ProfsErrorException("no prof with this id"));
        return ProfMapper.INSTANCE.profToResponse(profRepo.save(ProfMapper.INSTANCE.requestToProf(model)));
    }

    public boolean deleteProf(Long id)
    {
        profRepo.deleteById(id);
        return profRepo.findById(id).isEmpty();
    }

    public boolean joinBoard(Long profID, Long boardID)
    {
        BoardEntity bEntity= boardRepo.findById(boardID).orElseThrow(
            ()-> new BoardErrorException("no board with this id"));
        ProfsEntity pentity = profRepo.findById(profID).orElseThrow(
            ()-> new ProfsErrorException("no prof with this id"));
        List<ProfsEntity> pentities = bEntity.getProfs();
        if(pentities.stream().anyMatch((p) -> p.getId().equals(profID))) return false;
        pentities.add(pentity);
        bEntity = bEntity.withProfs(pentities);
        boardRepo.save(bEntity);
        return true;
    }
    public boolean leaveBoard(Long profID, Long boardID)
    {
        BoardEntity bEntity= boardRepo.findById(boardID).orElseThrow(
            ()-> new BoardErrorException("no board with this id"));
        ProfsEntity pentity = profRepo.findById(profID).orElseThrow(
            ()-> new ProfsErrorException("no prof with this id"));
        List<ProfsEntity> pentities = bEntity.getProfs();
        if(pentities.stream().allMatch((p) -> !p.getId().equals(profID))) return false;
        pentities.remove(pentity);
        bEntity = bEntity.withProfs(pentities);
        boardRepo.save(bEntity);
        return true;
    }

    public List<BoardResponse> getBoardsForProf(Long profID)
    {
        return profRepo.findById(profID).orElseThrow(
            ()-> new ProfsErrorException("no prof for this id"))
            .getBoards()
            .stream()
            .map(BoardMapper.INSTANCE::boardToResponse)
            .toList();
    }



}
