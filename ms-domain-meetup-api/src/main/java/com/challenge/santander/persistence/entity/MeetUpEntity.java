package com.challenge.santander.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEETUP")
public class MeetUpEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "meetUp")
    @JsonIgnore
    private Set<MeetUpUserEntity> meetUpUsers;

    private LocalDate meetUpDate;

    private String direction;

    private Double latitude;

    private Double longitude;
}
