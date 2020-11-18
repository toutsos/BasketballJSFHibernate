/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Game;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author atoutsios
 */
@Stateless
public class GameFacade extends AbstractFacade<Game> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GameFacade() {
        super(Game.class);
    }
    
    public void deleteGame (Game game){
        em = getEntityManager();
        try {
            Query query = em.createQuery("DELETE FROM Game g WHERE g.id = :id");
            query.setParameter("id", game.getId());
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
