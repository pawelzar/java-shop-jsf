package shop.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class CardBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String number;
    private String pin;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void clearCard() {
        this.number = "";
        this.pin = "";
    }
}