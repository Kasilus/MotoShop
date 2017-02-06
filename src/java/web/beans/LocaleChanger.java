
package web.beans;

import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LocaleChanger {
    
    Locale currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    
    public void changeLocale(String localeCode){
        currentLocale = new Locale(localeCode);
    }
    
    public Locale getCurrentLocale(){
        return currentLocale;
    }
    
}
