package com.oldie.backend.locations;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WardRepository extends JpaRepository<Ward, Long> {

    public List<Ward> findByDistrict_DistrictGsoId(Integer districtGsoId);

    public Ward findByWardGsoId(Integer wardGsoId);
}
