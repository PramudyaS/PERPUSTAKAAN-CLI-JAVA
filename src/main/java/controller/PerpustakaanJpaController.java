/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import model.Perpustakaan;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class PerpustakaanJpaController implements Serializable {

    public PerpustakaanJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Perpustakaan perpustakaan) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(perpustakaan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Perpustakaan perpustakaan) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            perpustakaan = em.merge(perpustakaan);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = perpustakaan.getIdBuku();
                if (findPerpustakaan(id) == null) {
                    throw new NonexistentEntityException("The perpustakaan with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perpustakaan perpustakaan;
            try {
                perpustakaan = em.getReference(Perpustakaan.class, id);
                perpustakaan.getIdBuku();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perpustakaan with id " + id + " no longer exists.", enfe);
            }
            em.remove(perpustakaan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Perpustakaan> findPerpustakaanEntities() {
        return findPerpustakaanEntities(true, -1, -1);
    }

    public List<Perpustakaan> findPerpustakaanEntities(int maxResults, int firstResult) {
        return findPerpustakaanEntities(false, maxResults, firstResult);
    }

    private List<Perpustakaan> findPerpustakaanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Perpustakaan.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Perpustakaan findPerpustakaan(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Perpustakaan.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerpustakaanCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Perpustakaan> rt = cq.from(Perpustakaan.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
