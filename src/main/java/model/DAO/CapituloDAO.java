
package model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import model.DataObject.Capitulo;
import model.IDAO.ICapituloDAO;
import utils.ConnectionUtil;

/**
 *
 * @author adryc
 */
public class CapituloDAO implements ICapituloDAO{
	
	public static EntityManager createEM() {
		EntityManagerFactory emf = ConnectionUtil.getInstace();
		return emf.createEntityManager();
	}
	EntityManager em = createEM();
    
    //Queries
    private final String getAll="Select * from Capitulo";

    @Override
    public void crear(Capitulo aux) {
        try {
        	em.getTransaction().begin();
        	em.persist(aux);
        	em.getTransaction().commit();        	
        } catch (EntityExistsException e) {
			throw new EntityExistsException("El usuario ya existe");
		} catch	(IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion"); 
		}
    }

    @Override
    public void editar(Capitulo aux) {
        try {
    	em.getTransaction().begin();
        em.persist(aux);
        em.getTransaction().commit();
        } catch	(IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion"); 
		}
    }

    @Override
    public void borrar(Long id) {
        Capitulo deleted = mostrarPorId(id);
        try {
        em.getTransaction().begin();
        em.remove(deleted);
        em.getTransaction().commit();
        } catch	(IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion"); 
		}
    }

    @Override
    public List<Capitulo> mostrarTodos() {
        List<Capitulo> result = new ArrayList<>();
        try {
        em.getTransaction().begin();
        TypedQuery<Capitulo> q = em.createQuery(getAll, Capitulo.class);
        result = q.getResultList();
        em.getTransaction().commit();
        return result;       
		} catch	(IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion"); 
		}
    }

    @Override
    public Capitulo mostrarPorId(Long id) {
        Capitulo result = new Capitulo();
        try {
        em.getTransaction().begin();
        result = em.find(Capitulo.class, id);
        em.getTransaction().commit();
        return result;
		} catch	(IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion"); 
		}
    }
}
