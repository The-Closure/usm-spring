package org.closure.app.postModule.repositories;

import org.closure.app.entities.CommunityEntity;
import org.closure.app.entities.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepo extends PagingAndSortingRepository<PostEntity,Long> {
    Page<PostEntity> findAllByPcommuninty(CommunityEntity communityEntity, Pageable pageable);
}
