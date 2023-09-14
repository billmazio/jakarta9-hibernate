package gr.aueb.cf.schoolapp.dao;




import gr.aueb.cf.schoolapp.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapp.model.City;

import java.util.List;
import java.util.Optional;

public interface ICityDAO  {
    Optional<List<City>> getByCityName(String name) throws CityDAOException;
    City getById(int id) throws CityDAOException;
    City insert(City city) throws CityDAOException;
    City update(City city) throws CityDAOException;
    void delete(int id) throws CityDAOException;
    List<City> getAllCities() throws CityDAOException;
}
