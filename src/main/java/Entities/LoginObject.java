package Entities;

public class LoginObject {
    private final String Login;
    private final String Password;

    public LoginObject(String Login, String Password) {
        this.Login = Login;
        this.Password = Password;
    }

    public String getLogin() {

        return Login;
    }

    public String getPassword() {

        return Password;
    }


}
