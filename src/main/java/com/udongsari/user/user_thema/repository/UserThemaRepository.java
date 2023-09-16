package com.udongsari.user.user_thema.repository;

import com.udongsari.user.user_thema.entity.UserThema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserThemaRepository extends JpaRepository<UserThema, Long> {

    @Query(
            value = "select * from user_thema where user_id = :userPostId"
            , nativeQuery = true
    )
    List<UserThema> findAllByUserPost(@Param("userPostId") Long userPostId);

}
