package cn.qisee.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import cn.qisee.jpa.model.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(value = "Post.details")
    Post findByContent(String content);

    @Query(value = "select * from tbl_post limit ?1", nativeQuery = true)
    List<Post> findAllUseNativeWithLimit(int limit);

    @Query(value = "select p from Post p")
    List<Post> findAllWithLimit(Pageable pageable);

}
