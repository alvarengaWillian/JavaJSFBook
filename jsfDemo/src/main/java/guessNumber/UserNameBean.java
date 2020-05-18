/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessNumber;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Random;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author willi
 */
@Named(value = "UserNameBean")
@SessionScoped
public class UserNameBean implements Serializable {

    Integer randomInt;
    Integer userNumber;
    String response;
    
    public UserNameBean() {
        Random randomGR = new Random();
        randomInt = randomGR.nextInt(10);
        System.out.println("Duke's number: " + randomInt);
    }
    
     public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }

    public String getResponse() {
        if ((userNumber != null) && (userNumber.compareTo(randomInt) == 0)) {
            // invalidate user session
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();
            
            return "Você acertou";
        } else {
            return "<p> Desculpe, O numero " + userNumber + " não é o sorteado, Tente novamente!";
        }
    }
}

