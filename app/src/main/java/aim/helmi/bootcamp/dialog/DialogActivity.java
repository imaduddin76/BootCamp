package aim.helmi.bootcamp.dialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import aim.helmi.bootcamp.R;

public class DialogActivity extends Dialog {

    private TextView tvTitle, tvMessage;
    private Button btnOk;

    public DialogActivity(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.activity_dialog);

        tvMessage = findViewById(R.id.tvMessage);
        tvTitle = findViewById(R.id.tvTitle);
        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void setMesssage(String message){
        tvMessage.setText(message);
    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }
}
