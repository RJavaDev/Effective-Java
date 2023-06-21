package com.example.uplode_file.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.uplode_file.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT du.* FROM d_user du WHERE du.id=:userId",nativeQuery = true)
    Optional<UserEntity> findByUserId(Long userId);
}
