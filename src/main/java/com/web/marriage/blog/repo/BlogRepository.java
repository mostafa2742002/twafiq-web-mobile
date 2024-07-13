package com.web.marriage.blog.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.web.marriage.blog.entity.Blog;


@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {

    Optional<Blog> findByTitle(String title);

}
