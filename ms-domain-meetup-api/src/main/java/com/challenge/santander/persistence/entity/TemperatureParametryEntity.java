package com.challenge.santander.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TEMPERATURE_PARAMETRY")
public class TemperatureParametryEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="minimum_temperature", nullable = false)
    private Double minimumTemperature;

    @Column(name="maximum_temperature", nullable = false)
    private Double maximumTemperature;

    @ElementCollection
    @Column(name="beers_by_person", nullable = false)
    private List<Double> beersByPerson;

    @Column(name="beers_per_box", nullable = false)
    private Integer beersPerBox;
}
