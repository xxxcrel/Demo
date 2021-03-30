package beer.cheese.jpa.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import beer.cheese.jpa.model.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(value = "Post.details")
    Post findByContent(String content);
}
