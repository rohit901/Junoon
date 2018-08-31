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

import java.util.ArrayList;

public class uGeneralDetailsActivity extends AppCompatActivity {

    Intent intent;
    String result;
    int position;
    ArrayList<Integer> studentPosition;
    JSONObject jsonPart;
    int actualPos;


    EditText nameEditText;
    EditText classEditText;

    EditText schoolEditText;
    EditText phoneEditText;

    EditText feeinstal2;
    EditText feeinstal3;
    EditText phonenum;
    EditText address;

    Button updateNextBtn;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_general_details);
        intent = getIntent();
        studentPosition = new ArrayList<Integer>();
        result = intent.getStringExtra("result5");
        studentPosition = intent.getIntegerArrayListExtra("studpos");
        position = intent.getIntExtra("pos",0);
        actualPos = studentPosition.get(position);

        nameEditText = (EditText)findViewById(R.id.updatenameEditText);  //student name
        classEditText = (EditText)findViewById(R.id.updateclassEditText); //class
        schoolEditText = (EditText)findViewById(R.id.updateschoolEditText); //school
        phoneEditText = (EditText)findViewById(R.id.updatephoneEditText); //fee install 1
        feeinstal2 = (EditText)findViewById(R.id.updatefeeinst2EditText);
        feeinstal3 = (EditText)findViewById(R.id.updatefeeinst3EditText);
        phonenum = (EditText)findViewById(R.id.updatephonenumEditText);
        address = (EditText)findViewById(R.id.updateaddressEditText);
        updateNextBtn = (Button)findViewById(R.id.updatenextBtn1);
        sharedPreferences = this.getSharedPreferences("com.example.home.junoon", Context.MODE_PRIVATE);

        updateNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), uAcademicActivity.class);
                sharedPreferences.edit().putString("uName", nameEditText.getText().toString()).apply();
                sharedPreferences.edit().putString("uClass", classEditText.getText().toString()).apply();
                sharedPreferences.edit().putString("uSchool", schoolEditText.getText().toString()).apply();
                sharedPreferences.edit().putString("uFeeone", phoneEditText.getText().toString()).apply();
                sharedPreferences.edit().putString("uFeetwo", feeinstal2.getText().toString()).apply();
                sharedPreferences.edit().putString("uFeethree", feeinstal3.getText().toString()).apply();
                sharedPreferences.edit().putString("uPhone", phonenum.getText().toString()).apply();
                sharedPreferences.edit().putString("uAddrs", address.getText().toString()).apply();
                intent.putExtra("result6",result);
                intent.putExtra("posact",actualPos);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });




        try {
            JSONObject jsonObject = new JSONObject(result);

            String compCodes2 = jsonObject.getString("Sheet1");
            //minfo = jsonObject.getString("message");

            JSONArray arr = new JSONArray(compCodes2);

            for (int i = 0; i <=actualPos ; i++) {
                jsonPart = arr.getJSONObject(i);
                //compCodes.add(jsonPart.getString("Company_name"));
                // resultText.setText(resultText.getText() + jsonPart.getString("main") + ": " + jsonPart.getString("description") + "\n");
                //   Log.i("JsonRegToken", jsonPart.getString("Reg_Token"));
                //Log.i("description", jsonPart.toString());
            }
            nameEditText.setText(jsonPart.getString("Student_Name"));
            classEditText.setText(jsonPart.getString("Class"));
            schoolEditText.setText(jsonPart.getString("School"));
            phoneEditText.setText(jsonPart.getString("Fees-Install_1"));
            feeinstal2.setText(jsonPart.getString("Fees-Install_2"));
            feeinstal3.setText(jsonPart.getString("Fees-Install_3"));
            phonenum.setText(jsonPart.getString("Phone_No"));
            address.setText(jsonPart.getString("Address"));






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
