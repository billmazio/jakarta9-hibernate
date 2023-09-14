package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.dbutil.HibernateHelper;
import gr.aueb.cf.schoolapp.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapp.model.City;
import gr.aueb.cf.schoolapp.model.Student;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dependent

public class CityDAOHibernateImpl implements ICityDAO {

    @Override
    public Optional<List<City>> getByCityName(String name) throws CityDAOException {
        EntityManager entityManager = HibernateHelper.getEntityManager();

        try {
            TypedQuery<City> query = entityManager.createQuery("SELECT c FROM City c WHERE c.name LIKE :name", City.class);
            query.setParameter("name", name + "%");

            List<City> cities = query.getResultList();

            if (cities != null && !cities.isEmpty()) {
                return Optional.of(cities);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new CityDAOException("Error retrieving cities by name", e);
        } finally {
            entityManager.close();
        }
    }


    @Override
    public City getById(int id) throws CityDAOException {
        try {
            EntityManager entityManager = HibernateHelper.getEntityManager();
            return entityManager.find(City.class, id);
        } catch (Exception e) {
            throw new CityDAOException("Error getting city by ID", e);
        }
    }

    @Override
    public City insert(City city) throws CityDAOException {
        try {
            EntityManager entityManager = HibernateHelper.getEntityManager();
            HibernateHelper.beginTransaction();

            List<Student> students = city.getStudents();
            if (students != null) {
                for (Student student : students) {
                    city.addStudent(student);  // Using convenience method
                }
            }

            entityManager.persist(city);
            HibernateHelper.commitTransaction();

            return city;
        } catch (Exception e) {
            HibernateHelper.rollbackTransaction();
            throw new CityDAOException("Error inserting city", e);
        }
    }

    @Override
    public City update(City city) throws CityDAOException {
        try {
            EntityManager entityManager = HibernateHelper.getEntityManager();
            HibernateHelper.beginTransaction();

            City updatedCity = entityManager.merge(city);
            HibernateHelper.commitTransaction();

            return updatedCity;
        } catch (Exception e) {
            HibernateHelper.rollbackTransaction();
            throw new CityDAOException("Error updating city", e);
        }
    }




    @Override
    public void delete(int id) throws CityDAOException {
        try {
            EntityManager entityManager = HibernateHelper.getEntityManager();
            HibernateHelper.beginTransaction();

            City city = getById(id);
            if (city != null) {
                for (Student student : new ArrayList<>(city.getStudents())) {
                    city.removeStudent(student);
                }
                entityManager.remove(city);
            } else {
                throw new CityDAOException("City with ID: " + id + " not found");
            }
            HibernateHelper.commitTransaction();
        } catch (Exception e) {
            HibernateHelper.rollbackTransaction();
            throw new CityDAOException("Error deleting city", e);
        }
    }

    @Override
    public List<City> getAllCities() throws CityDAOException {
        try {
            EntityManager entityManager = HibernateHelper.getEntityManager();

            TypedQuery<City> query = entityManager.createQuery("FROM City", City.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new CityDAOException("Error getting all cities", e);
        }
    }
}
