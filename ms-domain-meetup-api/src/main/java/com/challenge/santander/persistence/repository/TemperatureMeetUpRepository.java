package com.challenge.santander.persistence.repository;

import com.challenge.santander.persistence.entity.MeetUpEntity;
import com.challenge.santander.persistence.entity.TemperatureMeetUpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureMeetUpRepository extends JpaRepository<TemperatureMeetUpEntity,Long> {

    TemperatureMeetUpEntity findTemperatureMeetUpEntityByMeetUp(MeetUpEntity meetUpEntity);
}
