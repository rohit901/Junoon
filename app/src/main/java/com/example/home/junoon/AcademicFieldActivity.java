package com.example.home.junoon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class AcademicFieldActivity extends AppCompatActivity {

    Button nextBtn;
    SharedPreferences sharedPreferences;
    EditText percentlast;
    EditText schoolAttendance;
    EditText sv1;
    EditText hv1;
    EditText sv2;
    EditText hv2;
    EditText sv3;
    EditText hv3;
    EditText vfeedbak1;
    EditText vfeedbak2;
    EditText vfeedbak3;
    EditText drivepic;
    EditText final1;
    EditText final2;
    EditText final3;
    EditText comments;
    EditText attendNss;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_field);
        nextBtn = (Button) findViewById(R.id.nextBtn3);
        sharedPreferences = this.getSharedPreferences("com.example.home.junoon", Context.MODE_PRIVATE);
        percentlast = (EditText) findViewById(R.id.percentlastexamEditText);
        schoolAttendance = (EditText) findViewById(R.id.schoolattdEditText);
        sv1 = (EditText) findViewById(R.id.sv1testEditText);
        hv1 = (EditText) findViewById(R.id.hv1testEditText);
        sv2 = (EditText) findViewById(R.id.sv2testEditText);
        hv2 = (EditText) findViewById(R.id.hv2testEditText);
        sv3 = (EditText) findViewById(R.id.sv3testEditText);
        hv3 = (EditText) findViewById(R.id.hv3testEditText);
        vfeedbak1 = (EditText) findViewById(R.id.voluntfeedbakEditText);
        vfeedbak2 = (EditText) findViewById(R.id.voluntfeedbakEditText2);
        vfeedbak3 = (EditText) findViewById(R.id.voluntfeedbakEditText3);
        drivepic = (EditText) findViewById(R.id.drivepicEditText);
        final1 = (EditText) findViewById(R.id.final1EditText);
        final2 = (EditText) findViewById(R.id.final2EditText);
        final3 = (EditText) findViewById(R.id.final3EditText);
        comments = (EditText)findViewById(R.id.commentsEditText);
        attendNss = (EditText)findViewById(R.id.attendsnssEditText);
        percentlast.setText(sharedPreferences.getString("percentlast",""));
        schoolAttendance.setText(sharedPreferences.getString("schoolattd",""));
        sv1.setText(sharedPreferences.getString("sv1",""));
        sv2.setText(sharedPreferences.getString("sv2",""));
        sv3.setText(sharedPreferences.getString("sv3",""));
        hv1.setText(sharedPreferences.getString("hv1",""));
        hv2.setText(sharedPreferences.getString("hv2",""));
        hv3.setText(sharedPreferences.getString("hv3",""));
        vfeedbak1.setText(sharedPreferences.getString("vfeed1",""));
        vfeedbak2.setText(sharedPreferences.getString("vfeed2",""));
        vfeedbak3.setText(sharedPreferences.getString("vfeed3",""));
        drivepic.setText(sharedPreferences.getString("drivepic",""));
        final1.setText(sharedPreferences.getString("final1",""));
        final2.setText(sharedPreferences.getString("final2",""));
        final3.setText(sharedPreferences.getString("final3",""));
        comments.setText(sharedPreferences.getString("comments",""));
        attendNss.setText(sharedPreferences.getString("attendnss",""));

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),IncomeFieldActivity.class);
                sharedPreferences.edit().putString("percentlast", percentlast.getText().toString()).apply();
                sharedPreferences.edit().putString("schoolattd", schoolAttendance.getText().toString()).apply();
                sharedPreferences.edit().putString("sv1", sv1.getText().toString()).apply();
                sharedPreferences.edit().putString("sv2", sv2.getText().toString()).apply();
                sharedPreferences.edit().putString("sv3", sv3.getText().toString()).apply();
                sharedPreferences.edit().putString("hv1", hv1.getText().toString()).apply();
                sharedPreferences.edit().putString("hv2", hv2.getText().toString()).apply();
                sharedPreferences.edit().putString("hv3", hv3.getText().toString()).apply();

                sharedPreferences.edit().putString("vfeed1", vfeedbak1.getText().toString()).apply();
                sharedPreferences.edit().putString("vfeed2", vfeedbak2.getText().toString()).apply();
                sharedPreferences.edit().putString("vfeed3", vfeedbak3.getText().toString()).apply();
                sharedPreferences.edit().putString("drivepic", drivepic.getText().toString()).apply();
                sharedPreferences.edit().putString("final1", "").apply();

                sharedPreferences.edit().putString("final2", "").apply();
                sharedPreferences.edit().putString("final3", "").apply();
                sharedPreferences.edit().putString("comments", comments.getText().toString()).apply();
                sharedPreferences.edit().putString("attendnss", attendNss.getText().toString()).apply();
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
