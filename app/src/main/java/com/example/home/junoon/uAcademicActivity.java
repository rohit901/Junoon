package com.example.home.junoon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

public class uAcademicActivity extends AppCompatActivity {

    Intent intent;
    String result;
    int pos;
    JSONObject jsonPart;
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

    Button nextBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_academic);
        sharedPreferences = this.getSharedPreferences("com.example.home.junoon", Context.MODE_PRIVATE);
        intent = getIntent();
        result = intent.getStringExtra("result6");
        pos = intent.getIntExtra("posact",0);
        nextBtn = (Button)findViewById(R.id.updatenextBtn3);

        percentlast = (EditText) findViewById(R.id.updatepercentlastexamEditText);
        schoolAttendance = (EditText) findViewById(R.id.updateschoolattdEditText);
        sv1 = (EditText) findViewById(R.id.updatesv1testEditText);
        hv1 = (EditText) findViewById(R.id.updatehv1testEditText);
        sv2 = (EditText) findViewById(R.id.updatesv2testEditText);
        hv2 = (EditText) findViewById(R.id.updatehv2testEditText);
        sv3 = (EditText) findViewById(R.id.updatesv3testEditText);
        hv3 = (EditText) findViewById(R.id.updatehv3testEditText);
        vfeedbak1 = (EditText) findViewById(R.id.updatevoluntfeedbakEditText);
        vfeedbak2 = (EditText) findViewById(R.id.updatevoluntfeedbakEditText2);
        vfeedbak3 = (EditText) findViewById(R.id.updatevoluntfeedbakEditText3);
        drivepic = (EditText) findViewById(R.id.updatedrivepicEditText);
        final1 = (EditText) findViewById(R.id.updatefinal1EditText);
        final2 = (EditText) findViewById(R.id.updatefinal2EditText);
        final3 = (EditText) findViewById(R.id.updatefinal3EditText);
        comments = (EditText)findViewById(R.id.updatecommentsEditText);
        attendNss = (EditText)findViewById(R.id.updateattendsnssEditText);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),uIncomeActivity.class);
                sharedPreferences.edit().putString("upercentlast", percentlast.getText().toString()).apply();
                sharedPreferences.edit().putString("uschoolattd", schoolAttendance.getText().toString()).apply();
                sharedPreferences.edit().putString("usv1", sv1.getText().toString()).apply();
                sharedPreferences.edit().putString("usv2", sv2.getText().toString()).apply();
                sharedPreferences.edit().putString("usv3", sv3.getText().toString()).apply();
                sharedPreferences.edit().putString("uhv1", hv1.getText().toString()).apply();
                sharedPreferences.edit().putString("uhv2", hv2.getText().toString()).apply();
                sharedPreferences.edit().putString("uhv3", hv3.getText().toString()).apply();

                sharedPreferences.edit().putString("uvfeed1", vfeedbak1.getText().toString()).apply();
                sharedPreferences.edit().putString("uvfeed2", vfeedbak2.getText().toString()).apply();
                sharedPreferences.edit().putString("uvfeed3", vfeedbak3.getText().toString()).apply();
                sharedPreferences.edit().putString("udrivepic", drivepic.getText().toString()).apply();
                sharedPreferences.edit().putString("ufinal1", final1.getText().toString()).apply();

                sharedPreferences.edit().putString("ufinal2", final2.getText().toString()).apply();
                sharedPreferences.edit().putString("ufinal3", final3.getText().toString()).apply();
                sharedPreferences.edit().putString("ucomments", comments.getText().toString()).apply();
                sharedPreferences.edit().putString("uattendnss", attendNss.getText().toString()).apply();
                intent.putExtra("result7",result);
                intent.putExtra("posact2",pos);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });




        try {
            JSONObject jsonObject = new JSONObject(result);

            String compCodes2 = jsonObject.getString("Sheet1");
            //minfo = jsonObject.getString("message");

            JSONArray arr = new JSONArray(compCodes2);

            for (int i = 0; i <=pos ; i++) {
                jsonPart = arr.getJSONObject(i);
                //compCodes.add(jsonPart.getString("Company_name"));
                // resultText.setText(resultText.getText() + jsonPart.getString("main") + ": " + jsonPart.getString("description") + "\n");
                //   Log.i("JsonRegToken", jsonPart.getString("Reg_Token"));
                //Log.i("description", jsonPart.toString());
            }
            percentlast.setText(jsonPart.getString("%_in_Last_Exam"));
            schoolAttendance.setText(jsonPart.getString("School_Attendance"));
            attendNss.setText(jsonPart.getString("Attends_NSS_School"));
            sv1.setText(jsonPart.getString("Tests-_School_Visit1"));
            sv2.setText(jsonPart.getString("Test_SV2"));
            sv3.setText(jsonPart.getString("Test_SV3"));
            hv1.setText(jsonPart.getString("Test-_Home_Visit_1"));
            hv2.setText(jsonPart.getString("Test_HV2"));
            hv3.setText(jsonPart.getString("Test_HV3"));

            vfeedbak1.setText(jsonPart.getString("Volunt_Feedback_1"));
            vfeedbak2.setText(jsonPart.getString("V_Feedback_2"));
            vfeedbak3.setText(jsonPart.getString("V_Feedback_3"));

            drivepic.setText(jsonPart.getString("Drive_pic"));
            final1.setText(jsonPart.getString("Final_1"));
            final2.setText(jsonPart.getString("Final_2"));
            final3.setText(jsonPart.getString("Final_3"));
            comments.setText(jsonPart.getString("Comments"));






        }
        catch(Exception e){
            e.printStackTrace();

        }



    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
