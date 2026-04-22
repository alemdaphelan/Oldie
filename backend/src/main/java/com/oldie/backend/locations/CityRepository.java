package com.oldie.backend.locations;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    public List<City> findAll();

    public City findByCityGsoId(Integer cityGsoId);
}
