package aim.helmi.bootcamp.login;

public interface LoginView {

    void onLoginSuccess(String username);

    void onError(String message);
}
