package org.web.library.service.impl;

import org.web.library.model.City;
import org.web.library.repository.CityRepository;
import org.web.library.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }
}
