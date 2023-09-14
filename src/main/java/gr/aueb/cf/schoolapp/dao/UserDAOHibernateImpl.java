package gr.aueb.cf.schoolapp.dao;
import gr.aueb.cf.schoolapp.dao.dbutil.HibernateHelper;

import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.security.SecUtil;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


@Dependent
public class UserDAOHibernateImpl implements UserDAO {





    @Override
    @Transactional
    public boolean isUserValid(String username, String password) throws UserDAOException {
        EntityManager entityManager = HibernateHelper.getEntityManager();
        TypedQuery<User> query = entityManager.createQuery("FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);

        try {
            User user = query.getSingleResult();

            if (user != null) {
                String storedHashedPassword = user.getPassword();
                return SecUtil.checkPassword(password, storedHashedPassword);
            }

            return false;
        } catch (NoResultException nre) {
            return false;
        } catch (Exception e) {
            throw new UserDAOException("Error validating user", e);
        }
    }

    @Override

    public boolean registerUser(User user) throws UserDAOException {
        if (isUserExists(user.getUsername())) {
            return false;
        }
        EntityManager entityManager = HibernateHelper.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new UserDAOException("Error registering user", e);
        }
    }



    @Override
    public boolean isUserExists(String username) throws UserDAOException {
        EntityManager entityManager = HibernateHelper.getEntityManager();
        TypedQuery<User> query = entityManager.createQuery("FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);

        try {
            User user = query.getSingleResult();
            return user != null;
        } catch (NoResultException nre) {
            return false;
        } catch (Exception e) {
            throw new UserDAOException("Error checking user existence", e);
        }
    }
}