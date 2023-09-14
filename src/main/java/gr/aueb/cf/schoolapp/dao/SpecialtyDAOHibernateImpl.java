package gr.aueb.cf.schoolapp.dao;


import gr.aueb.cf.schoolapp.dao.dbutil.HibernateHelper;
import gr.aueb.cf.schoolapp.dao.exceptions.SpecialtyDAOException;
import gr.aueb.cf.schoolapp.model.Specialty;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

@Dependent
public class SpecialtyDAOHibernateImpl implements ISpecialtyDAO {





    @Override
    public Optional<List<Specialty>> getSpecialtyByName(String name) throws SpecialtyDAOException {
        EntityManager entityManager = HibernateHelper.getEntityManager();
        TypedQuery<Specialty> query = entityManager.createQuery("SELECT s FROM Specialty s WHERE s.name LIKE :name", Specialty.class);
        query.setParameter("name", name + "%");
        List<Specialty> specialties = query.getResultList();
        return specialties.isEmpty() ? Optional.empty() : Optional.of(specialties);
    }

    @Override
    public Specialty getById(int id) throws SpecialtyDAOException {
        EntityManager entityManager = HibernateHelper.getEntityManager();
        return entityManager.find(Specialty.class, id);
    }

    @Override
    public Specialty insert(Specialty specialty) throws SpecialtyDAOException {
        EntityManager entityManager = HibernateHelper.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(specialty);
            entityManager.getTransaction().commit();
            return specialty;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new SpecialtyDAOException("Error inserting specialty", e);
        }
    }

    @Override
    public Specialty update(Specialty specialty) throws SpecialtyDAOException {
        EntityManager entityManager = HibernateHelper.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Specialty updatedSpecialty = entityManager.merge(specialty);
            entityManager.getTransaction().commit();
            return updatedSpecialty;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new SpecialtyDAOException("Error updating specialty", e);
        }
    }

    @Override
    public void delete(int id) throws SpecialtyDAOException {
        EntityManager entityManager = HibernateHelper.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Specialty specialty = getById(id);
            if (specialty != null) {
                entityManager.remove(specialty);
            } else {
                throw new SpecialtyDAOException("Specialty with ID: " + id + " not found");
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new SpecialtyDAOException("Error deleting specialty", e);
        }
    }

    @Override
    public List<Specialty> getAllSpecialties() throws SpecialtyDAOException {
        EntityManager entityManager = HibernateHelper.getEntityManager();
        TypedQuery<Specialty> query = entityManager.createQuery("FROM Specialty", Specialty.class);
        return query.getResultList();
    }
}
