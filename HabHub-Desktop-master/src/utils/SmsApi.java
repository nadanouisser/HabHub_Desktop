/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


/**
 *
 * @author Kalai
 */
public class SmsApi implements Initializable {
    
     public static final String ACCOUNT_SID =
             "ACe6a7496474a350b50330f24e8215abbe";
    public static final String AUTH_TOKEN =
            "638fd577147e0d41c4dade415a8db903";

        @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    
    public void send(int numTel,String messageContent){
       
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

     
        try {

        Message message = Message 
                .creator(new PhoneNumber("+216"+Integer.toString(numTel)), // to
                        new PhoneNumber("+14699330223"), // from
                       messageContent)
                .create();
        System.out.println(message.getSid());
        }catch(final ApiException ex){
            System.out.println(ex.getMessage());
            
        }
    
  
    }

}
