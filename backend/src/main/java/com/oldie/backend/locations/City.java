package com.oldie.backend.locations;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", nullable = false, updatable = false)
    private Long cityId;

    @Column(name="city_gso_id", nullable = false, unique = true)
    private Integer cityGsoId;

    @Column(name = "name", nullable = false)
    private String name;
}