/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author atoutsios
 */
@Entity
@Table(name = "player_game")
@NamedQueries({
    @NamedQuery(name = "PlayerGame.findAll", query = "SELECT p FROM PlayerGame p"),
    @NamedQuery(name = "PlayerGame.findById", query = "SELECT p FROM PlayerGame p WHERE p.id = :id"),
    @NamedQuery(name = "PlayerGame.findByPoints", query = "SELECT p FROM PlayerGame p WHERE p.points = :points")})

public class PlayerGame implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "points")
    private Integer points;
    
    @JoinColumn(name = "idgame")
    @ManyToOne
    private Game game;
    
    @JoinColumn(name = "idplayer")
    @ManyToOne
    private Player player;

    public PlayerGame() {
    }
    
    public PlayerGame(Game game, Player player){
        this.player = player;
        this.game = game;
    }

    public PlayerGame(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlayerGame)) {
            return false;
        }
        PlayerGame other = (PlayerGame) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PlayerGame[ id=" + id + " ]";
    }
    
}
