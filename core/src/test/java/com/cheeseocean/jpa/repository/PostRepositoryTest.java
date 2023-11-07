package com.cheeseocean.jpa.repository;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import com.cheeseocean.jpa.model.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import lombok.extern.apachecommons.CommonsLog;

@ActiveProfiles("test")
@DataJpaTest
@EnableJpaRepositories(basePackages = "com.cheeseocean.jpa.repository")
@CommonsLog
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void batchInsert() {
        Set<Post> posts = new HashSet<>();
        IntStream.range(1, 10).forEach(i -> {
            Post post = new Post();
            post.setContent("hello im " + i);
            posts.add(post);
        });
        postRepository.saveAll(posts);
        postRepository.findAllUseNativeWithLimit(3).forEach(System.out::println);
        log.info(" -------- ");
        postRepository.findAllWithLimit(PageRequest.of(0, 5)).forEach(System.out::println);
    }
}
