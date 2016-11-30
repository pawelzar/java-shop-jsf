package shop.jsf.user;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;


@SessionScoped
@ManagedBean
public class User implements Serializable {
    private String login;

    public User() {
        login = "quest";
    }

    public String getLogin() {
        return login;
    }

}
