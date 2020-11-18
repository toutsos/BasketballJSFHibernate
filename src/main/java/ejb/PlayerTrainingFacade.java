/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Player;
import entities.PlayerTraining;
import entities.Training;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author atoutsios
 */
@Stateless
public class PlayerTrainingFacade extends AbstractFacade<PlayerTraining> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlayerTrainingFacade() {
        super(PlayerTraining.class);
    }

    public List<Player> getPlayerFromTraining(Training training) {
        List<Player> players = null;
        try {
            em = getEntityManager();
            TypedQuery query = em.createQuery("SELECT pt.player FROM PlayerTraining pt WHERE pt.training = :training", Player.class);
            query.setParameter("training", training);
            players = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return players;
    }

    public int getRankForPlayer(Training training, Player player) {
        em = getEntityManager();
        int rank = 0;
        PlayerTraining pt;
        try {
            TypedQuery query = em.createQuery("SELECT pt FROM PlayerTraining pt WHERE pt.training = :training AND pt.player=:player", PlayerTraining.class);
            query.setParameter("training", training);
            query.setParameter("player", player);
            pt = (PlayerTraining) query.getSingleResult();
            rank = pt.getPlayerRank();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rank;
    }

}
