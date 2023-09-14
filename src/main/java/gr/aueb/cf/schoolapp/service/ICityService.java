package gr.aueb.cf.schoolapp.service;


import gr.aueb.cf.schoolapp.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapp.dto.CityInsertDTO;
import gr.aueb.cf.schoolapp.dto.CityUpdateDTO;
import gr.aueb.cf.schoolapp.model.City;
import gr.aueb.cf.schoolapp.service.exceptions.CityNotFoundException;

import java.util.List;

public interface ICityService {
    City insertCity(CityInsertDTO dto) throws CityDAOException;
    City updateCity(CityUpdateDTO dto) throws CityDAOException, CityNotFoundException;
    void deleteCity(int id) throws CityDAOException, CityNotFoundException;
    List<City> getCitiesByCityName(String name) throws CityDAOException, CityNotFoundException;
    City getCityById(int id) throws CityDAOException, CityNotFoundException;
    List<City> getAllCities() throws CityDAOException, CityNotFoundException;
}
