package com.cheeseocean.jpa.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cheeseocean.jpa.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"posts"}, type = EntityGraph.EntityGraphType.FETCH)
    User findByNickname(String nickname);

    @EntityGraph("User.postsFetchAll")
    @Query("select u from User u where u.nickname = :nickname")
    User fetchAllByNickname(@Param("nickname") String nickname);

    @EntityGraph("User.postsFetchImages")
    User getByNickname(String nickname);

    @Modifying
    @Query("update User u set u.nickname = :nickname")
    public void updateNickname(String nickname);
}
