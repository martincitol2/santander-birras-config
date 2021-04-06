package com.challenge.santander.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TEMPERATURE_MEET_UP")
public class TemperatureMeetUpEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meet_up_id", nullable = false, unique = true)
    private MeetUpEntity meetUp;

    @Column(name="min_temperature", nullable = false)
    private Double minTemperature;

    @Column(name="max_temperature", nullable = false)
    private Double maxTemperature;

    @Column(name="amount_meet_up_beers", nullable = false)
    private Integer amountBeers;

}
