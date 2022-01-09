package Controller;
import java.util.HashMap;

public class IDandPasswords {

    private final HashMap<String,String> loginInfo = new HashMap<>();

    public IDandPasswords() {

        loginInfo.put("admin", "admin");
        loginInfo.put("sathya","myPassword");

    }

    protected HashMap<String,String> getLoginInfo() {
        return loginInfo;
    }
}
