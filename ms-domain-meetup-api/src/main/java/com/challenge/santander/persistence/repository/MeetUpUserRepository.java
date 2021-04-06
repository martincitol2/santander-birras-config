package com.challenge.santander.persistence.repository;

import com.challenge.santander.persistence.entity.MeetUpEntity;
import com.challenge.santander.persistence.entity.MeetUpUserEntity;
import com.challenge.santander.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeetUpUserRepository extends JpaRepository<MeetUpUserEntity,Long> {

    Optional<MeetUpUserEntity> findMeetUpUserEntityByUserAndMeetUp(UserEntity user, MeetUpEntity meetUp);
}
