package com.example.home.junoon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class uIncomeActivity extends AppCompatActivity {
    Intent intent2;
    String result;
    int pos;
    JSONObject jsonPart;

    EditText fathername;
    EditText fatheroccup;
    EditText mothername;
    EditText motheroccup;
    EditText earningfamily;
    EditText income;
    EditText totfamily;

    EditText numsiblings;
    EditText siblingscholar;
    EditText nonumangsibling;
    EditText extraexpense;

    CheckBox appTV;
    CheckBox appFridge;
    CheckBox appMotor;
    SharedPreferences sharedPreferences;
    String appliance;
    String tvChecked;
    String fridgeChecked;
    String motorChecked;

    Button unextBtn2;

    final Context c = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_income);
        sharedPreferences = this.getSharedPreferences("com.example.home.junoon", Context.MODE_PRIVATE);
        intent2 = getIntent();
        result = intent2.getStringExtra("result7");
        pos = intent2.getIntExtra("posact2",0);

        fathername = (EditText)findViewById(R.id.updatefathernameEditText);
        fatheroccup = (EditText)findViewById(R.id.updatefatheroccupEditText);
        mothername = (EditText)findViewById(R.id.updatemothernameEditText);
        motheroccup = (EditText)findViewById(R.id.updatemotheroccupEditText);
        earningfamily = (EditText)findViewById(R.id.updateearningfamilyEditText);
        income = (EditText)findViewById(R.id.updateincomeEditText);
        totfamily = (EditText)findViewById(R.id.updatetotfamilyEditText);
        numsiblings = (EditText)findViewById(R.id.updatenumsiblingsEditText);
        siblingscholar = (EditText)findViewById(R.id.updatesiblingsscholarEditText);
        nonumangsibling = (EditText)findViewById(R.id.updatenonumangsiblingEditText);
        extraexpense = (EditText)findViewById(R.id.updateextraexpensesEditText);

        appTV = (CheckBox)findViewById(R.id.updateapplianceCheckBox1);
        appFridge = (CheckBox)findViewById(R.id.updateapplianceCheckBox2);
        appMotor = (CheckBox)findViewById(R.id.updateapplianceCheckBox3);
        unextBtn2 = (Button)findViewById(R.id.updatenextBtn2);
        unextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appliance="TV: "+appTV.isChecked()+", Fridge: "+appFridge.isChecked()+", Motor Vehicle: "+appMotor.isChecked();
                new SendRequest().execute();

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
            fathername.setText(jsonPart.getString("Father_Name"));
            fatheroccup.setText(jsonPart.getString("Father_Occupation"));
            mothername.setText(jsonPart.getString("Mother's_Name"));
            motheroccup.setText(jsonPart.getString("Mother's_Occup"));
            income.setText(jsonPart.getString("Income_PerMonth"));
            totfamily.setText(jsonPart.getString("Total_Family_Members"));
            earningfamily.setText(jsonPart.getString("Earning_Family_Member"));
            numsiblings.setText(jsonPart.getString("No_of_Studying_Sibling"));
            siblingscholar.setText(jsonPart.getString("Sibling_taken_in_scholarship"));

            nonumangsibling.setText(jsonPart.getString("Non_Umang_sibling_fee_details"));
            extraexpense.setText(jsonPart.getString("Extra_Expenses"));


            String[] appliancearr = jsonPart.getString("Appliance_Present").split(",");
            Pattern p = Pattern.compile(":(.*)");
            Matcher m = p.matcher(appliancearr[0]);
            while(m.find()){

                tvChecked = m.group(1).replaceAll("\\s+","");
            }

            Pattern p2 = Pattern.compile(":(.*)");
            Matcher m2 = p2.matcher(appliancearr[1]);
            while(m2.find()){

                fridgeChecked = m2.group(1).replaceAll("\\s+","");
            }

            Pattern p3 = Pattern.compile(":(.*)");
            Matcher m3 = p3.matcher(appliancearr[2]);
            while(m3.find()){

                motorChecked = m3.group(1).replaceAll("\\s+","");
            }

            if(tvChecked.equals("true")){
                appTV.setChecked(true);
            }
            else if(tvChecked.equals("false")){
                appTV.setChecked(false);
            }

            if(fridgeChecked.equals("true")){
                appFridge.setChecked(true);
            }
            else if(fridgeChecked.equals("false")){
                appFridge.setChecked(false);
            }

            if(motorChecked.equals("true")){
                appMotor.setChecked(true);
            }
            else if(motorChecked.equals("false")){
                appMotor.setChecked(false);
            }







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
    public class SendRequest extends AsyncTask<String, Void, String> {


        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{

                //Enter script URL Here
                URL url = new URL("https://script.google.com/macros/s/AKfycbxFpUuTrcB2SyT0PCj817vp8c7yzFfnUSA6l7TPWcGSUlkkoee7/exec?");

                JSONObject postDataParams = new JSONObject();

                //Passing scanned code as parameter

                postDataParams.put("name",sharedPreferences.getString("uName",""));
                postDataParams.put("class",sharedPreferences.getString("uClass",""));
                postDataParams.put("school",sharedPreferences.getString("uSchool",""));
                postDataParams.put("phone",sharedPreferences.getString("uFeeone",""));

                postDataParams.put("feeinstl2",sharedPreferences.getString("uFeetwo",""));
                postDataParams.put("feeinstl3",sharedPreferences.getString("uFeethree",""));
                postDataParams.put("phnume",sharedPreferences.getString("uPhone",""));
                postDataParams.put("addrs",sharedPreferences.getString("uAddrs",""));

                postDataParams.put("fathername",fathername.getText().toString());
                postDataParams.put("fatheroccup",fatheroccup.getText().toString());
                postDataParams.put("mothername",mothername.getText().toString());
                postDataParams.put("motheroccup",motheroccup.getText().toString());

                postDataParams.put("income",income.getText().toString());
                postDataParams.put("percentlast",sharedPreferences.getString("upercentlast",""));
                postDataParams.put("schoolattd",sharedPreferences.getString("uschoolattd",""));
                postDataParams.put("totfamily",totfamily.getText().toString());

                postDataParams.put("earningfamily",earningfamily.getText().toString());
                postDataParams.put("attdnssschool",sharedPreferences.getString("uattendnss",""));
                postDataParams.put("applianpresent",appliance);
                postDataParams.put("numstudysibling",numsiblings.getText().toString());

                postDataParams.put("siblingtakenscholar",siblingscholar.getText().toString());
                postDataParams.put("nonumang",nonumangsibling.getText().toString());
                postDataParams.put("extraexpense",extraexpense.getText().toString());
                postDataParams.put("Testsv1",sharedPreferences.getString("usv1",""));
                postDataParams.put("Testhv1",sharedPreferences.getString("uhv1",""));
                postDataParams.put("Testsv2",sharedPreferences.getString("usv2",""));
                postDataParams.put("Testhv2",sharedPreferences.getString("uhv2",""));
                postDataParams.put("Testsv3",sharedPreferences.getString("usv3",""));
                postDataParams.put("Testhv3",sharedPreferences.getString("uhv3",""));
                postDataParams.put("vfeed1",sharedPreferences.getString("uvfeed1",""));
                postDataParams.put("vfeed2",sharedPreferences.getString("uvfeed2",""));
                postDataParams.put("vfeed3",sharedPreferences.getString("uvfeed3",""));
                postDataParams.put("drivepic",sharedPreferences.getString("udrivepic",""));
                postDataParams.put("final1",sharedPreferences.getString("ufinal1",""));
                postDataParams.put("final2",sharedPreferences.getString("ufinal2",""));
                postDataParams.put("final3",sharedPreferences.getString("ufinal3",""));
                postDataParams.put("comments",sharedPreferences.getString("ucomments",""));
                postDataParams.put("pos",pos);


                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            sharedPreferences.edit().clear().apply();
            Toast.makeText(uIncomeActivity.this, "Updated!", Toast.LENGTH_SHORT).show();
            Intent intent3 = new Intent(getApplicationContext(),MainMenuActivity.class);
            startActivity(intent3);

        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}
