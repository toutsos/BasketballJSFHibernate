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
import javax.validation.constraints.NotNull;

/**
 *
 * @author atoutsios
 */
@Entity
@Table(name = "player_training")
@NamedQueries({
    @NamedQuery(name = "PlayerTraining.findAll", query = "SELECT p FROM PlayerTraining p"),
    @NamedQuery(name = "PlayerTraining.findById", query = "SELECT p FROM PlayerTraining p WHERE p.id = :id"),
    @NamedQuery(name = "PlayerTraining.findByPlayerRank", query = "SELECT p FROM PlayerTraining p WHERE p.playerRank = :playerRank")})
public class PlayerTraining implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "playerRank")
    private int playerRank;
    @JoinColumn(name = "playerId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Player playerId;
    @JoinColumn(name = "trainingId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Training trainingId;

    public PlayerTraining() {
    }

    public PlayerTraining(Integer id) {
        this.id = id;
    }

    public PlayerTraining(Integer id, int playerRank) {
        this.id = id;
        this.playerRank = playerRank;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPlayerRank() {
        return playerRank;
    }

    public void setPlayerRank(int playerRank) {
        this.playerRank = playerRank;
    }

    public Player getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Player playerId) {
        this.playerId = playerId;
    }

    public Training getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Training trainingId) {
        this.trainingId = trainingId;
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
        if (!(object instanceof PlayerTraining)) {
            return false;
        }
        PlayerTraining other = (PlayerTraining) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PlayerTraining[ id=" + id + " ]";
    }
    
}
