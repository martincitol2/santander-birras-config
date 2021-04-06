package com.challenge.santander.persistence.repository;

import com.challenge.santander.persistence.entity.TemperatureParametryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureParametryRepository extends JpaRepository<TemperatureParametryEntity,Long> {
}
