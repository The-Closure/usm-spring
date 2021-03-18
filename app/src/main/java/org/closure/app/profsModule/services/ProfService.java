package org.closure.app.profsModule.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.closure.app.boardModule.dto.BoardResponse;
import org.closure.app.boardModule.exceptions.BoardErrorException;
import org.closure.app.boardModule.repositories.BoardRepository;
import org.closure.app.entities.BoardEntity;
import org.closure.app.entities.ProfsEntity;
import org.closure.app.profsModule.dto.ProfRequest;
import org.closure.app.profsModule.dto.ProfResponse;
import org.closure.app.profsModule.exceptions.ProfsErrorException;
import org.closure.app.profsModule.models.ProfModel;
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
        ProfsEntity entity = profRepo.save(
            new ProfsEntity()
                .withCreated_at(new Date())
                .withEmail(profRequest.getEmail())
                .withName(profRequest.getName())
                .withPassword(profRequest.getPassword())
                .withSpec(profRequest.getSpec()));
        return new ProfResponse()
            .withCreated_at(entity.getCreated_at())
            .withEmail(entity.getEmail())
            .withId(entity.getId())
            .withImage(entity.getImage())
            .withName(entity.getName())
            .withPassword(entity.getPassword())
            .withSpec(entity.getSpec());

    }

    public ProfResponse getProf(Long id)
    {
        ProfsEntity entity = profRepo.findById(id).orElseThrow(
            () -> new ProfsErrorException("no prof with this id")
        );
        return new ProfResponse()
            .withCreated_at(entity.getCreated_at())
            .withEmail(entity.getEmail())
            .withId(entity.getId())
            .withImage(entity.getImage())
            .withName(entity.getName())
            .withPassword(entity.getPassword())
            .withSpec(entity.getSpec()); 
    }
    public ProfResponse updateProf(Long id, ProfRequest model)
    {
        ProfsEntity e = profRepo.findById(id).orElseThrow(
            () -> new ProfsErrorException("no board with this id"));

        e = profRepo.save(
                e
            .withEmail(model.getEmail())
            .withName(model.getName())
            .withPassword(model.getPassword())
            .withSpec(model.getSpec())
            );
        return new ProfResponse()
            .withCreated_at(e.getCreated_at())
            .withEmail(e.getEmail())
            .withId(e.getId())
            .withImage(e.getImage())
            .withName(e.getName())
            .withPassword(e.getPassword())
            .withSpec(e.getSpec()); 
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
        ProfsEntity pentity = profRepo.findById(profID).orElseThrow(()-> new ProfsErrorException("no prof for this id"));
        List<BoardResponse> boardResponses = new ArrayList<>();
        pentity.getBoards().forEach((b)->{
            boardResponses.add
                (
                    new BoardResponse()
                        .withDescription(b.getDescription())
                        .withId(b.getId())
                        .withName(b.getName())
                        .withImage(b.getImage())
                );
        });
        return boardResponses;
    }



}
