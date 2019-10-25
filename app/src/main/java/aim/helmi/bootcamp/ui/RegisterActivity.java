package aim.helmi.bootcamp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import aim.helmi.bootcamp.R;
import aim.helmi.bootcamp.room.BcDatabase;
import aim.helmi.bootcamp.room.table.User;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    BcDatabase dbRoom;
    Button btnRegister;
    EditText EtUser, EtPass, EtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbRoom = BcDatabase.getDatabase(this);
//        db = new SQLiteDB(this);

        EtUser = findViewById(R.id.etUsername);
        EtPass = findViewById(R.id.etPassword);
        EtEmail = findViewById(R.id.etEmail);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                User user = new User();
                user.username = EtUser.getText().toString();
                user.password = EtPass.getText().toString();
                user.email = EtEmail.getText().toString();
                long insert = dbRoom.userDao().register(user);
                if (insert > 0) {
                    Toast.makeText(this, "Register Success.", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(this, "Register Failed.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}