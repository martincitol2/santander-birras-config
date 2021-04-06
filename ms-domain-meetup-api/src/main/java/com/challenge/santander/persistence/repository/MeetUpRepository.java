package com.challenge.santander.persistence.repository;

import com.challenge.santander.persistence.entity.MeetUpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MeetUpRepository extends JpaRepository<MeetUpEntity,Long> {

    MeetUpEntity findMeetUpEntityByDirectionAndMeetUpDate(String direction, LocalDate meetUpDate);
}

