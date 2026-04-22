package com.oldie.backend.locations;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Long> {
    public List<District> findByCity_CityGsoId(Integer cityGsoId);

    public District findByDistrictGsoId(Integer districtGsoId);
}
