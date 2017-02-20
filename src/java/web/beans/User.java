package web.beans;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
public class User {

    private String username;
    private String password;

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    
    public String login(){
    
        try {
            System.out.println(username);
            System.out.println(password);
            ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).login(username, password);
            
            return "items";
        } catch (ServletException ex) {
            ResourceBundle bundle = ResourceBundle.getBundle("web.nls.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(bundle.getString("username_and_password_not_true"));
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage("login_form", message);
        }
        
        return "index";
        
    }

}
