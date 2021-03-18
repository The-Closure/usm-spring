package org.closure.app.CommunityModule.services;

import java.util.ArrayList;
import java.util.List;

import org.closure.app.CommunityModule.dto.CommunityResponse;
import org.closure.app.CommunityModule.exceptions.CommunityErrorException;
import org.closure.app.CommunityModule.models.CommunityModel;
import org.closure.app.CommunityModule.repositories.CommunityRepo;
import org.closure.app.UserModule.exceptions.UserErrorException;
import org.closure.app.UserModule.models.UserModel;
import org.closure.app.UserModule.repositories.UserRepo;
import org.closure.app.entities.CommunityEntity;
import org.closure.app.entities.UserEntity;
import org.closure.app.postModule.dto.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommunityService {

    @Autowired
    CommunityRepo communityRepo;

    @Autowired
    UserRepo userRepo;

    public CommunityResponse findByUsers(UserEntity userEntity)
    {
        CommunityEntity communityEntity = communityRepo.findByUsers(userEntity).orElseThrow(
            ()-> new CommunityErrorException("can not find community for this user"));
        CommunityResponse communityResponse = new CommunityResponse()
            .withName(communityEntity.getName())
            .withDescription(communityEntity.getDescription())
            .withImg(communityEntity.getImg());
            return communityResponse;
    } 

    public CommunityResponse findById(Long id)
    {
         CommunityEntity communityEntity = communityRepo.findById(id).orElseThrow(
             ()-> new CommunityErrorException("no community for this id"));
        CommunityResponse communityResponse = new CommunityResponse()
            .withName(communityEntity.getName())
            .withDescription(communityEntity.getDescription())
            .withImg(communityEntity.getImg());
            return communityResponse;
    }

    public CommunityResponse findByName(String name)
    {
         CommunityEntity communityEntity = communityRepo.findByName(name).orElseThrow(
             ()-> new CommunityErrorException("no community with this name"));
        CommunityResponse communityResponse = new CommunityResponse()
            .withName(communityEntity.getName())
            .withDescription(communityEntity.getDescription())
            .withImg(communityEntity.getImg());
            return communityResponse;
    }

    public String addUsreToCommunity(Long userId, Long communityId)
    {
        return addUser(userId, communityId) ? "user with id "+userId+" added  to community" : "for some reasons you can't join this community";
    }

    private boolean addUser(Long userId, Long communityId)
    {
        CommunityEntity communityEntity = communityRepo
            .findById(communityId)
            .orElseThrow(() -> new CommunityErrorException(
                    "authorId " + communityId + " not found")
            );

        userRepo.findById(userId)
            .map(user -> {
                user.setCommuninty(communityEntity);
                return userRepo.save(user);
            });
        return userRepo.findAll().stream().filter(user ->
            user.getCommuninty().getId().equals(communityId)
        ).toList().size() > 0;

    }

    public CommunityModel addCommunity(CommunityModel communityModel)
    {
        if(communityRepo.findByName(communityModel.getName()).isEmpty())
        {
            CommunityEntity communityEntity = new CommunityEntity()
                .withDescription(communityModel.getDescription())
                .withImg(communityModel.getImg())
                .withName(communityModel.getName());
            System.out.println(communityEntity.getName());
            communityRepo.save(communityEntity);
            System.out.println(communityEntity.getName());
            return new CommunityModel()
                .withDescription(communityEntity.getDescription())
                .withImg(communityEntity.getImg())
                .withName(communityEntity.getName())
                .withId(communityEntity.getId());

        }else throw new CommunityErrorException("this community is already exist");
    }

    public String deleteCommunity(Long id)
    {
        return delete(id) ? "community deleted" :"for some reason you can't delete this community";
    }

    private boolean delete(Long id)
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

    public List<CommunityModel> getAll()
    {
        List<CommunityModel> models = new ArrayList<>();
        communityRepo.findAll().stream().forEach((e)->{
            CommunityModel communityModel = new CommunityModel()
                .withDescription(e.getDescription())
                .withImg(e.getImg())
                .withName(e.getName())
                .withId(e.getId());
            models.add(communityModel);
        });
        return models;
    }

    public List<UserModel> getUsers(Long communityId)
    {
        List<UserModel> models = new ArrayList<>();
        CommunityEntity communityEntity = communityRepo.findById(communityId)
            .orElseThrow(()->new CommunityErrorException("no community with this di"));
        communityEntity.getUsers().forEach((e)->{
            UserModel model = new UserModel()
                .withAge(e.getAge())
                .withCommunity_name(e.getCommunity_name())
                .withEmail(e.getEmail())
                .withId(e.getId())
                .withImg(e.getImg())
                .withName(e.getName())
                .withStart_year(e.getStart_year())
                .withStudy_year(e.getStudy_year());
                models.add(model);
        });
        return models;
    }

    public List<PostResponse> getPosts(Long communityID)
    {
        CommunityEntity cEntity = communityRepo.findById(communityID).orElseThrow(
            () -> new UserErrorException("no community with this id"));
        List<PostResponse> postResponses = new ArrayList<>();
        cEntity.getPosts().forEach(
            (p) -> {
                PostResponse postResponse = new PostResponse()
                    .withAttach(p.getAttach())
                    .withCommunityID(p.getPcommuninty().getId())
                    .withPostID(p.getId())
                    .withTitle(p.getTitle())
                    .withUserID(p.getUEntity().getId())
                    .withValue(p.getValue());
                postResponses.add(postResponse);
            });
        return postResponses;
    }
    
}

