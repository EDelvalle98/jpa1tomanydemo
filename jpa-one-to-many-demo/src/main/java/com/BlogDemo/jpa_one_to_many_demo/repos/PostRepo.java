package com.BlogDemo.jpa_one_to_many_demo.repos;

import com.BlogDemo.jpa_one_to_many_demo.entities.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends CrudRepository<Post, Long> {

}
