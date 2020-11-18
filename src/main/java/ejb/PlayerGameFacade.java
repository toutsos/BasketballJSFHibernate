/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Game;
import entities.Player;
import entities.PlayerGame;
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
public class PlayerGameFacade extends AbstractFacade<PlayerGame> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlayerGameFacade() {
        super(PlayerGame.class);
    }
    
    public List<Player> getPlayersFromGame(Game game){
        em = getEntityManager();
        TypedQuery query = em.createQuery("SELECT pg.player from PlayerGame pg where pg.game = :game",Player.class);
        query.setParameter("game", game);
        List<Player> players = query.getResultList();
        return players;
    }
}
