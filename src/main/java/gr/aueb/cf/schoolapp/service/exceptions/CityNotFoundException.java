package gr.aueb.cf.schoolapp.service.exceptions;


import gr.aueb.cf.schoolapp.model.City;

public class CityNotFoundException extends Exception{
    public CityNotFoundException(City city) {
        super("City with id " + city.getId() + " does not exist");
    }

    public CityNotFoundException(String s) {
        super(s);
    }

    public CityNotFoundException(Class<City> cityClass, Integer id) {
    }
}
