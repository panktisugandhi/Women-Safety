package com.example.womendemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class personal_detail extends AppCompatActivity {

    EditText et[] = new EditText[3];
    int ids[] = {R.id.etname, R.id.etno ,R.id.etemail};
    String values[] = new String[et.length];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_detail);

        for (int i = 0; i < et.length; i++) {
            et[i] = (EditText) findViewById(ids[i]);
        }
    }

    public void per_detail(View v) {
        int i;
        for (i = 0; i < et.length; i++) {
            values[i] = et[i].getText().toString().trim();
            if (values[i].isEmpty()) {
                et[i].requestFocus();
                et[i].setError("Empty");
                Toast.makeText(this, "Enter Data", Toast.LENGTH_SHORT).show();
                break;
            }

            if (!(et[i]==null)) {
                Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(personal_detail.this,Relativedetail.class);
                startActivity(intent);
                break;
            }
        }


    }
}
