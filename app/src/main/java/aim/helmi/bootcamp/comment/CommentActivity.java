package aim.helmi.bootcamp.comment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import aim.helmi.bootcamp.utils.DateUtils;
import aim.helmi.bootcamp.utils.Preference;
import aim.helmi.bootcamp.R;
import aim.helmi.bootcamp.adapter.CommentAdapter;
import aim.helmi.bootcamp.model.CommentModel;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Button btnPost;
    FirebaseDatabase database;
    DatabaseReference comment;
    List<CommentModel> data = new ArrayList<>();
    CommentAdapter adapter;
    RecyclerView rvComment;
    EditText etComment;
    Preference preference;
    String id = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        toolbar = findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preference = new Preference(this);
        setTitle("Comment");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
        }
        
        etComment = findViewById(R.id.etComment);
        rvComment = findViewById(R.id.rvChat);
        rvComment.setLayoutManager(new LinearLayoutManager(this));
        rvComment.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new CommentAdapter(this, data);
        rvComment.setAdapter(adapter);
        
        database = FirebaseDatabase.getInstance();
        comment = database.getReference("comment").child(id);
        comment.limitToLast(100).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.getValue() != null){
                    try {
                        CommentModel cmd = dataSnapshot.getValue(CommentModel.class);
                        data.add(cmd);
                        adapter.notifyDataSetChanged();
                        rvComment.scrollToPosition(data.size() - 1);
//                        setTitle("Comment (" + data.size() + ")");
                    }catch (Exception e){
                        Log.w( "onChildAdded: ", e.getMessage());
                    }
                }
                
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        
        btnPost = findViewById(R.id.BtnPost);
        btnPost.setOnClickListener(this);
    }
    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.BtnPost){
            CommentModel cm = new CommentModel();
            cm.setUsername(preference.getUsername());
            cm.setComment(etComment.getText().toString());
            cm.setDate(new DateUtils().getDateNow());
            comment.push().setValue(cm);
            etComment.setText("");
        }
        
    }
}
