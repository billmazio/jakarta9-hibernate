package gr.aueb.cf.schoolapp.dao.dbutil;



import jakarta.persistence.*;


public class HibernateHelper {
    private static EntityManagerFactory emf;
    protected static ThreadLocal <EntityManager> threadLocal = new ThreadLocal<>();

    private HibernateHelper() {}

    public static EntityManagerFactory getEntityManagerFactory() {
        if ((emf == null) || ((emf.isOpen()))){
            emf = Persistence.createEntityManagerFactory("myPU");
        }
        return emf;
    }

    public static EntityManager getEntityManager() {
        EntityManager em = threadLocal.get();
        if ((em == null) || (!em.isOpen())) {
            em = getEntityManagerFactory().createEntityManager();
            threadLocal.set(em);
        }
        return em;
    }

    public static void closeEntityManager() { getEntityManager().close(); }
    public static void beginTransaction() { getEntityManager().getTransaction().begin(); }
    public static void commitTransaction() { getEntityManager().getTransaction().commit(); }
    public static void rollbackTransaction() { getEntityManager().getTransaction().rollback(); }

    public static void closeEMF() { emf.close(); }

}