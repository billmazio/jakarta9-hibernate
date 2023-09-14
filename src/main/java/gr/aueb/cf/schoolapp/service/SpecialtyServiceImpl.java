package gr.aueb.cf.schoolapp.service;


import gr.aueb.cf.schoolapp.dao.SpecialtyDAOHibernateImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.SpecialtyDAOException;
import gr.aueb.cf.schoolapp.dto.SpecialtyInsertDTO;
import gr.aueb.cf.schoolapp.dto.SpecialtyUpdateDTO;
import gr.aueb.cf.schoolapp.model.Specialty;
import gr.aueb.cf.schoolapp.service.exceptions.SpecialtyNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


import java.util.List;
import java.util.Optional;
@ApplicationScoped
public class SpecialtyServiceImpl implements ISpecialtyService {

    @Inject
    private SpecialtyDAOHibernateImpl specialtyDAO;



    @Override
    public Specialty insertSpecialty(SpecialtyInsertDTO dto) throws SpecialtyDAOException {
        if (dto == null) return null;
        Specialty specialty;
        try {
            specialty = map(dto);
            System.out.println("Service returned specialty: " + specialty.getName());
            return specialtyDAO.insert(specialty);
        }  catch (SpecialtyDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    @Transactional
    public Specialty updateSpecialty(SpecialtyUpdateDTO dto) throws SpecialtyDAOException, SpecialtyNotFoundException {
        if (dto == null) return null;
        Specialty specialty;
        try {
            specialty = mapUpdate(dto);

            if (specialtyDAO.getById(specialty.getId()) == null) {
                throw new SpecialtyNotFoundException(specialty);
            }
            return specialtyDAO.update(specialty);
        }catch (SpecialtyDAOException | SpecialtyNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteSpecialty(int id) throws SpecialtyDAOException, SpecialtyNotFoundException {
        Specialty specialty;
        try {
            specialty = specialtyDAO.getById(id);

            if (specialty == null) {
                throw new SpecialtyNotFoundException("Specialty with id: " + id + " was not found");
            }
            specialtyDAO.delete(id);
        } catch (SpecialtyDAOException | SpecialtyNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Specialty> getSpecialtiesBySpecialtyName(String name) throws SpecialtyDAOException, SpecialtyNotFoundException {
        if (name == null) {
            throw new IllegalArgumentException("Specialty name cannot be null");
        }
        try {
            Optional<List<Specialty>> specialtiesOptional = specialtyDAO.getSpecialtyByName(name);

            if (!specialtiesOptional.isPresent()) {
                throw new SpecialtyNotFoundException("No specialties found with name: " + name);
            }

            return specialtiesOptional.get();

        } catch (SpecialtyDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Specialty getSpecialtyById(int id) throws SpecialtyDAOException, SpecialtyNotFoundException {
        Specialty specialty;
        try {
            specialty = specialtyDAO.getById(id);

            if (specialty == null) {
                throw new SpecialtyNotFoundException("Search Error: Specialty with id: " + id + " was not found");
            }
            return specialty;
        } catch (SpecialtyDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Specialty> getAllSpecialties() {
        List<Specialty> specialties = null;
        try {
            specialties = specialtyDAO.getAllSpecialties();
        } catch (SpecialtyDAOException e) {
            e.printStackTrace();
            // handle exception
        }
        return specialties;
    }

    private Specialty map(SpecialtyInsertDTO dto) {
        return new Specialty(null, dto.getName());
    }

    private Specialty mapUpdate(SpecialtyUpdateDTO dto) {
        return new Specialty(dto.getId(), dto.getName());
    }
}
