package com.example.padcc.innovertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText fname,lname,email;
    Button register,display;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fname=(EditText)findViewById(R.id.first);
        lname=(EditText)findViewById(R.id.last);
        email=(EditText)findViewById(R.id.emai);

        register=(Button) findViewById(R.id.register);
        display=(Button) findViewById(R.id.view);
        db = new DatabaseHandler(MainActivity.this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people visitor = new people();
                String fn,ln,em;

                fn = fname.getText().toString();
                ln = lname.getText().toString();
                em = email.getText().toString().trim();

                visitor.setFname(fn);
                visitor.setLname(ln);
                visitor.setEmail(em);

                Long result = db.insertVisitor(visitor);
                Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisteredDataActivity.class);
                startActivity(intent);
            }
        });

    }
}
