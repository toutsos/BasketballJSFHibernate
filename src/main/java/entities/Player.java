/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author atoutsios
 */
@Entity
@Table(name = "player")
@NamedQueries({
    @NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p"),
    @NamedQuery(name = "Player.findById", query = "SELECT p FROM Player p WHERE p.id = :id"),
    @NamedQuery(name = "Player.findByName", query = "SELECT p FROM Player p WHERE p.name = :name"),
    @NamedQuery(name = "Player.findByWeight", query = "SELECT p FROM Player p WHERE p.weight = :weight"),
    @NamedQuery(name = "Player.findByHeight", query = "SELECT p FROM Player p WHERE p.height = :height"),
    @NamedQuery(name = "Player.findByAge", query = "SELECT p FROM Player p WHERE p.age = :age"),
    @NamedQuery(name = "Player.findByPhone", query = "SELECT p FROM Player p WHERE p.phone = :phone"),
    @NamedQuery(name = "Player.findByTrainingsNumber", query = "SELECT p FROM Player p WHERE p.trainingsNumber = :trainingsNumber")})
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "name")
    private String name;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "height")
    private Integer height;
    @Column(name = "age")
    private Integer age;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 42)
    @Column(name = "phone")
    private String phone;
    @Column(name = "trainings_number")
    private Integer trainingsNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "playerId")
    private List<PlayerTraining> playerTrainingList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idplayer")
    private List<PlayerGame> playerGameList;

    public Player() {
    }

    public Player(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getTrainingsNumber() {
        return trainingsNumber;
    }

    public void setTrainingsNumber(Integer trainingsNumber) {
        this.trainingsNumber = trainingsNumber;
    }

    public List<PlayerTraining> getPlayerTrainingList() {
        return playerTrainingList;
    }

    public void setPlayerTrainingList(List<PlayerTraining> playerTrainingList) {
        this.playerTrainingList = playerTrainingList;
    }

    public List<PlayerGame> getPlayerGameList() {
        return playerGameList;
    }

    public void setPlayerGameList(List<PlayerGame> playerGameList) {
        this.playerGameList = playerGameList;
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
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Player[ id=" + id + " ]";
    }
    
}
