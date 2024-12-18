package com.BlogDemo.jpa_one_to_many_demo.repos;

import com.BlogDemo.jpa_one_to_many_demo.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {
    Comment findByIdAndPostId(Long id, Long postId);
}
