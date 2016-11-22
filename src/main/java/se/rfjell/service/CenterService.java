package se.rfjell.service;

import java.util.List;

import se.rfjell.model.Center;

public interface CenterService {
    Center save(Center center);
    Center findById(Long id);
    List<Center> findAllCenters();
}
