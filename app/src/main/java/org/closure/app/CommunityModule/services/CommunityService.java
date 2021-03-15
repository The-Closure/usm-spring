package org.closure.app.CommunityModule.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.closure.app.CommunityModule.dto.CommunityRequest;
import org.closure.app.CommunityModule.dto.CommunityResponse;
import org.closure.app.CommunityModule.exceptions.CommunityErrorException;
import org.closure.app.CommunityModule.models.CommunityModel;
import org.closure.app.CommunityModule.repositories.CommunityRepo;
import org.closure.app.UserModule.exceptions.UserErrorException;
import org.closure.app.UserModule.repositories.UserRepo;
import org.closure.app.entities.CommunityEntity;
import org.closure.app.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.models.Response;

@Service
public class CommunityService {

    @Autowired
    CommunityRepo communityRepo;

    @Autowired
    UserRepo userRepo;

    public CommunityResponse findByUsers(UserEntity userEntity)
    {
        CommunityEntity communityEntity = communityRepo.findByUsers(userEntity).orElseThrow(()-> new CommunityErrorException("can not find community for this user"));
        CommunityResponse communityResponse = new CommunityResponse()
            .withName(communityEntity.getName())
            .withDescription(communityEntity.getDescription())
            .withImg(communityEntity.getImg());
            return communityResponse;
    }

    public CommunityResponse findById(Long id)
    {
         CommunityEntity communityEntity = communityRepo.findById(id).orElseThrow(()-> new CommunityErrorException("no community for this id"));
        CommunityResponse communityResponse = new CommunityResponse()
            .withName(communityEntity.getName())
            .withDescription(communityEntity.getDescription())
            .withImg(communityEntity.getImg());
            return communityResponse;
    }

    public CommunityResponse findByName(String name)
    {
         CommunityEntity communityEntity = communityRepo.findByName(name).orElseThrow(()-> new CommunityErrorException("no community with this name"));
        CommunityResponse communityResponse = new CommunityResponse()
            .withName(communityEntity.getName())
            .withDescription(communityEntity.getDescription())
            .withImg(communityEntity.getImg());
            return communityResponse;
    }

    public boolean addUser(Long userId, Long communityId)
    {
        boolean exist = false;
        
        CommunityEntity communintyEntity = communityRepo.findById(communityId).orElseThrow(
            ()-> new CommunityErrorException("no community for this id"));

            exist = communintyEntity.getUsers().stream().anyMatch(u -> u.getId().equals(userId));

        if(!exist)
        {
            communintyEntity.getUsers().add(
                userRepo.findById(userId).orElseThrow(()->new UserErrorException("no suer with this id")));
            communityRepo.save(communintyEntity);
            exist = true;
        }
        return exist;
    }

    public CommunityResponse addCommunity(CommunityModel communityModel)
    {
        if(communityRepo.findByName(communityModel.getName()).isEmpty())
        {

            CommunityEntity communityEntity = new CommunityEntity();
            communityEntity
                .withDescription(communityModel.getDescription())
                .withImg(communityModel.getImg())
                .withName(communityModel.getName());
            communityRepo.save(communityEntity);
            return new CommunityResponse()
                .withDescription(communityEntity.getDescription())
                .withImg(communityEntity.getImg())
                .withName(communityEntity.getName());

        }else throw new CommunityErrorException("this community is already exist");
    }

    public boolean delete(Long id)
    {
        CommunityEntity community = communityRepo.findById(id).orElseThrow(
            () -> new UserErrorException("error in id"));
        communityRepo.delete(community);
        return communityRepo.findById(id).isEmpty();
    }

    public List<CommunityResponse> search(String value)
    {
        List<CommunityResponse> responses = new ArrayList<CommunityResponse>();
        communityRepo.findByNameLike(value).stream().forEach(e->{
            CommunityResponse response = new CommunityResponse()
                .withImg(e.getImg())
                .withDescription(e.getDescription())
                .withName(e.getName());
            responses.add(response);
        });
        return responses;
    }

    public CommunityModel edit(CommunityModel communityModel)
    {
        CommunityEntity communityEntity = communityRepo.findByName(communityModel.getName()).orElseThrow(
            ()-> new CommunityErrorException("no community with this name"));
        communityEntity
            .withDescription(communityModel.getDescription())
            .withImg(communityModel.getImg())
            .withName(communityModel.getName());
        communityRepo.save(communityEntity);
        return communityModel;
    }

}

