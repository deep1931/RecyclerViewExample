package stem.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import stem.recyclerview.db.DBAdapter;

public class MainActivity extends AppCompatActivity {


    private Context context;

    private EditText etEmailId;
    private EditText etPassword;

    private Button btnUserDetail;
    private Button btnCreateUser;


    private DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    private void init() {
        context = this;

        db = new DBAdapter(context);
        db.open();


        etEmailId = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnCreateUser = (Button) findViewById(R.id.btnCreateUSer);
        btnUserDetail = (Button) findViewById(R.id.btnUserDetail);


        setListeners();

    }

    private void setListeners() {
        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmailId.getText().toString();
                String password = etPassword.getText().toString();

                if (email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter email id and password", Toast.LENGTH_SHORT).show();

                } else {
                    if (db.insertLogs(email, password)) {
                        Toast.makeText(MainActivity.this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        etEmailId.setText("");
                        etPassword.setText("");
                    }

                }


            }
        });

        btnUserDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(context, UserDetailActivity.class));
            }
        });
    }

}
