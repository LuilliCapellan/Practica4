package modelo.servicios.Utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.Field;
import java.util.List;

public class CRUD<T> {

    private static EntityManagerFactory entityManagerFactory;
    private Class<T> tClass;

    public CRUD(Class<T> tClass){
        if (entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("UnidadPersistencia");
        }

        this.tClass = tClass;
    }

    public EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

    private Object getValorCampo(T entidad){
        if(entidad == null){
            return null;
        }
        //aplicando la clase de reflexión.
        for(Field f : entidad.getClass().getDeclaredFields()) {  //tomando todos los campos privados.
            if (f.isAnnotationPresent(Id.class)) {
                try {
                    f.setAccessible(true);
                    Object valorCampo = f.get(entidad);

                    System.out.println("nombre del campo: "+f.getName());
                    System.out.println("Tipo del campo: "+f.getType().getName());
                    System.out.println("Valor del campo: "+valorCampo );

                    return valorCampo;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public void crear(T entidad){

        EntityManager em = getEntityManager();

        try {
            if (em.find(tClass, getValorCampo(entidad)) != null){
                System.out.println("La entidad a guardar existe, no creada.");
            }
        }catch (IllegalArgumentException ie){
            System.out.println("Parametro ilegal.");
        }

        em.getTransaction().begin();

        try {
            em.persist(entidad);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            throw e;
        }finally {
            em.close();
        }
    }

    public void merge(T entidad) {

        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {

            em.merge(entidad);
            em.getTransaction().commit();

        }catch (Exception e){
            em.getTransaction().rollback();
        }finally {
            em.close();
        }

    }

    public void editar(T entidad){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(entidad);
            em.flush();
            em.getTransaction().commit();
        }catch (Exception ex){
            System.out.println("Error actualizando: " + ex);
            em.getTransaction().rollback();
            throw  ex;
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param entidadId
     */
    public void eliminar(Object  entidadId){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            T entidad = em.find(tClass, entidadId);
            em.remove(entidad);
            em.getTransaction().commit();
        }catch (Exception ex){
            em.getTransaction().rollback();
            throw  ex;
        } finally {
            em.close();
        }
    }
    /**
     *
     * @param id
     * @return
     */
    public T find(Object id) {
        EntityManager em = getEntityManager();
        try{
            return em.find(tClass, id);
        } catch (Exception ex){
            throw  ex;
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public List<T> findAll(){
        EntityManager em = getEntityManager();
        try{
            CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(tClass);
            criteriaQuery.select(criteriaQuery.from(tClass));
            return em.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex){
            throw  ex;
        }finally {
            em.close();
        }
    }
}
