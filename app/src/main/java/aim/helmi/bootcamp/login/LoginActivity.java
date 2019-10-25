package aim.helmi.bootcamp.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import aim.helmi.bootcamp.dialog.DialogActivity;
import aim.helmi.bootcamp.utils.Preference;
import aim.helmi.bootcamp.R;
import aim.helmi.bootcamp.ui.RegisterActivity;
import aim.helmi.bootcamp.ui.MainActivity;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginView {

    EditText EtUser;
    EditText EtPass;
    Button BtnCancel;
    Preference preference;
    Button BtnLogin;
    LoginPresenter presenter;
    TextView Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preference = new Preference(this);
        presenter= new LoginPresenter(this, this);

        EtUser = findViewById(R.id.EtUser);
        EtPass = findViewById(R.id.etPass);
        BtnLogin = findViewById(R.id.BtnLogin);
        BtnCancel= findViewById(R.id.BtnCancel);
        Register = findViewById(R.id.tvRegister);

        BtnLogin.setOnClickListener(this);
        BtnCancel.setOnClickListener(this);
        Register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.BtnLogin:
//                if(EtUser.getText().toString().equals("admin") && EtPass.getText().toString().equals("password")){
//                    preference.setIsLogin(true);
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }else{
//                    Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show();
//                }
                presenter.doLogin(EtUser.getText().toString(), EtPass.getText().toString());
                break;
            case R.id.BtnCancel:
                finish();
                break;
            case R.id.tvRegister:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            default:
                Toast.makeText(this, "No Action", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void onLoginSuccess(String username) {
        preference.setIsLogin(true);
        preference.setUsername(username);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onError(String message) {
        DialogActivity dialog = new DialogActivity(this);
        dialog.show();
        dialog.setMesssage(message);
        dialog.setTitle("LOGIN ERROR");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (preference.getIsLogin()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
