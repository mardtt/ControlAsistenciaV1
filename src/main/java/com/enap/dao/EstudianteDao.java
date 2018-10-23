package com.enap.dao;

import com.enap.modelo.Estudiante;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marco.ENAP
 */
@Stateful
public class EstudianteDao extends FabricaDao<Estudiante> {

    @PersistenceContext(unitName = "ControlAsistenciaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteDao() {
        super(Estudiante.class);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
}
