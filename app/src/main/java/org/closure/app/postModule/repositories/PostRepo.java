package org.closure.app.postModule.repositories;

import org.closure.app.entities.PostEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepo extends PagingAndSortingRepository<PostEntity,Long> {

}
