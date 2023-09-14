package gr.aueb.cf.schoolapp.dao;



import gr.aueb.cf.schoolapp.dao.exceptions.SpecialtyDAOException;
import gr.aueb.cf.schoolapp.model.Specialty;

import java.util.List;
import java.util.Optional;

public interface ISpecialtyDAO {
    Optional<List<Specialty>> getSpecialtyByName(String name) throws SpecialtyDAOException;
    Specialty getById(int id) throws SpecialtyDAOException;
    Specialty insert(Specialty specialty) throws SpecialtyDAOException;
    Specialty update(Specialty specialty) throws SpecialtyDAOException;
    void delete(int id) throws SpecialtyDAOException;
    List<Specialty> getAllSpecialties() throws SpecialtyDAOException;
}

