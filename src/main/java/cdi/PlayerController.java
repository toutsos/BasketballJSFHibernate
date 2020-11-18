package cdi;

import entities.Player;
import cdi.util.JsfUtil;
import cdi.util.JsfUtil.PersistAction;
import ejb.PlayerFacade;
import entities.Training;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("playerController")
@SessionScoped
public class PlayerController implements Serializable {

    @EJB
    private ejb.PlayerFacade ejbFacade;
    private List<Player> items = null;
    private Player selected;

    public PlayerController() {
    }

    public Player getSelected() {
        return selected;
    }

    public void setSelected(Player selected) {
        this.selected = selected;
    }

    private PlayerFacade getFacade() {
        return ejbFacade;
    }

    public Player prepareCreate() {
        selected = new Player();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PlayerCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PlayerUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PlayerDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Player> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
            }
        }
    }

    public Player getPlayer(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Player> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Player> getPlayerNotInTraining(Training training) {
        List<Player> players;
        players = getFacade().playersNotInTraining(training);
        return players;
    }

    public List<Player> getSortPlayerFromTrainingNumber() {
        return getFacade().sortedPlayersFromTotalTrainings();
    }

    @FacesConverter(forClass = Player.class)
    public static class PlayerControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PlayerController controller = (PlayerController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "playerController");
            return controller.getPlayer(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Player) {
                Player o = (Player) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Player.class.getName()});
                return null;
            }
        }

    }

}
