package muzoo.io.sscsw.webappbackend.security;

import java.util.HashMap;
import java.util.Map;

public class CredentialCheck {

    private Map<String, String> userCredentials = new HashMap<String, String>() {{
        put("admin", "password");
        put("test", "123");
    }}; //Dont forget to change to DB


    //Don't forget to change to DB
    public boolean checkUser(String username, String password){
        if(userCredentials.containsKey(username)){
            String databasePassword = userCredentials.get(username);
            return databasePassword.equals(password);
        }
        else{
            return false;
        }
    }

}
