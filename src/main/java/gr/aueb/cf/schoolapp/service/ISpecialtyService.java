package gr.aueb.cf.schoolapp.service;


import gr.aueb.cf.schoolapp.dao.exceptions.SpecialtyDAOException;
import gr.aueb.cf.schoolapp.dto.SpecialtyInsertDTO;
import gr.aueb.cf.schoolapp.dto.SpecialtyUpdateDTO;
import gr.aueb.cf.schoolapp.model.Specialty;
import gr.aueb.cf.schoolapp.service.exceptions.SpecialtyNotFoundException;

import java.util.List;

public interface ISpecialtyService {
    Specialty insertSpecialty(SpecialtyInsertDTO dto) throws SpecialtyDAOException;
    Specialty updateSpecialty(SpecialtyUpdateDTO dto) throws SpecialtyDAOException, SpecialtyNotFoundException;
    void deleteSpecialty(int id) throws SpecialtyDAOException, SpecialtyNotFoundException;
    List<Specialty> getSpecialtiesBySpecialtyName(String name) throws SpecialtyDAOException, SpecialtyNotFoundException;
    Specialty getSpecialtyById(int id) throws SpecialtyDAOException, SpecialtyNotFoundException;
    List<Specialty> getAllSpecialties() throws SpecialtyDAOException, SpecialtyNotFoundException;
}
