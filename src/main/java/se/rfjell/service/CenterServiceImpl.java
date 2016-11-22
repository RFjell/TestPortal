package se.rfjell.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.rfjell.model.Center;
import se.rfjell.repository.CenterRepository;

@Service("centerService")
public class CenterServiceImpl implements CenterService {

	@Autowired
    private CenterRepository centerRepository;

    public Center save(Center center) {
        return centerRepository.save(center);
    }

    public Center findById(Long id) {
        return centerRepository.findOne(id);
    }

    public List<Center> findAllCenters() {
        return centerRepository.findAll();
    }
}
