/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author atoutsios
 */
@Entity
@Table(name = "training")
@NamedQueries({
    @NamedQuery(name = "Training.findAll", query = "SELECT t FROM Training t"),
    @NamedQuery(name = "Training.findById", query = "SELECT t FROM Training t WHERE t.id = :id"),
    @NamedQuery(name = "Training.findByDateTimeTraining", query = "SELECT t FROM Training t WHERE t.dateTimeTraining = :dateTimeTraining")})
public class Training implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "DateTimeTraining")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTimeTraining;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "training")
    private List<PlayerTraining> playerTrainingList;
    
    @JoinColumn(name = "trainingStadium_id")
    @ManyToOne
    private Stadium trainingStadium;

    public Training() {
    }

    public Training(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateTimeTraining() {
        return dateTimeTraining;
    }

    public void setDateTimeTraining(Date dateTimeTraining) {
        this.dateTimeTraining = dateTimeTraining;
    }

    public List<PlayerTraining> getPlayerTrainingList() {
        return playerTrainingList;
    }

    public void setPlayerTrainingList(List<PlayerTraining> playerTrainingList) {
        this.playerTrainingList = playerTrainingList;
    }

    public Stadium getTrainingStadium() {
        return trainingStadium;
    }

    public void setTrainingStadium (Stadium trainingStadium) {
        this.trainingStadium = trainingStadium;
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
        if (!(object instanceof Training)) {
            return false;
        }
        Training other = (Training) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Training[ id=" + id + " ]";
    }
    
}
