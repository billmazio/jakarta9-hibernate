//package gr.aueb.cf.jakarta8hibernate.util;
//
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.EntityTransaction;
//import jakarta.persistence.Persistence;
//
//public class HibernateUtil {
//
//    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
//
//    private HibernateUtil() {
//    }
//
//    public static EntityManager getEntityManager() {
//        return emf.createEntityManager();
//    }
//
//    public static void shutdown() {
//        emf.close();
//    }
//
//    public static void performJpaOperation(Runnable operation) {
//        EntityManager entityManager = getEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//
//        try {
//            transaction.begin();
//            operation.run();
//            transaction.commit();
//        } catch (RuntimeException e) {
//            if (transaction != null && transaction.isActive()) {
//                transaction.rollback();
//            }
//            throw e;
//        } finally {
//            entityManager.close();
//        }
//    }
//}