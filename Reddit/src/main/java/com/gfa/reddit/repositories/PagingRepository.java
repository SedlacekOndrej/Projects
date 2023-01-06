package com.gfa.reddit.repositories;

import com.gfa.reddit.models.Post;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagingRepository extends PagingAndSortingRepository<Post, Long> {
}
