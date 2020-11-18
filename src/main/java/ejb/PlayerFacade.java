/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Player;
import entities.Training;
import java.util.List;
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
public class PlayerFacade extends AbstractFacade<Player> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlayerFacade() {
        super(Player.class);
    }
 
    public List<Player> playersNotInTraining(Training training){
        List<Player> players = null;
        em=getEntityManager();
        try {
            
            TypedQuery query = em.createQuery("SELECT p FROM Player p WHERE p NOT IN (SELECT pt.player from PlayerTraining pt WHERE pt.training = :training)", Player.class);
            query.setParameter("training", training);
            players = query.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }
    
     public List<Player> sortedPlayersFromTotalTrainings(){
        List<Player> sortedPlayers = null;
        em=getEntityManager();
        try {
            TypedQuery query = em.createQuery("SELECT p FROM Player p order by p.trainingsNumber DESC",Player.class);
            sortedPlayers = query.setMaxResults(10).getResultList();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sortedPlayers;
    }
}
