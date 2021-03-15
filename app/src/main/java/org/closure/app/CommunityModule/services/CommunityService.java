package org.closure.app.CommunityModule.services;

import org.closure.app.CommunityModule.exceptions.CommunityErrorException;
import org.closure.app.CommunityModule.repositories.CommunityRepo;
import org.closure.app.entities.CommunityEntity;
import org.closure.app.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityService {

    @Autowired
    CommunityRepo communityRepo;

    

    public CommunityEntity findByUsers(UserEntity userEntity)
    {
       return communityRepo.findByUsers(userEntity).orElseThrow(()-> new CommunityErrorException("can not find community for this user"));
    }

    public CommunityEntity findById(Long id)
    {
        return communityRepo.findById(id).orElseThrow(()-> new CommunityErrorException("no community for this id"));
    }

    public CommunityEntity findByName(String name)
    {
        return communityRepo.findByName(name).orElseThrow(()-> new CommunityErrorException("no community with this name"));
    }

    public boolean addUser(Long userId, Long communityId)
    {
        boolean state = false;
        
        CommunityEntity communintyEntity = communityRepo.findById(communityId).orElseThrow(()-> new CommunityErrorException("no community for this id"));
        
        // communintyEntity.getUsers().stream().anyMatch(predicate)
        return false;
    }
}

