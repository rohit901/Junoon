package com.example.home.junoon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

import javax.net.ssl.HttpsURLConnection;

public class IncomeFieldActivity extends AppCompatActivity {

    Button nextBtn2;
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
    String appliance;

    CheckBox appTV;
    CheckBox appFridge;
    CheckBox appMotor;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_field);
        nextBtn2 = (Button) findViewById(R.id.nextBtn2);
        fathername = (EditText)findViewById(R.id.fathernameEditText);
        fatheroccup = (EditText)findViewById(R.id.fatheroccupEditText);
        mothername = (EditText)findViewById(R.id.mothernameEditText);
        motheroccup = (EditText)findViewById(R.id.motheroccupEditText);
        earningfamily = (EditText)findViewById(R.id.earningfamilyEditText);
        income = (EditText)findViewById(R.id.incomeEditText);
        totfamily = (EditText)findViewById(R.id.totfamilyEditText);
        numsiblings = (EditText)findViewById(R.id.numsiblingsEditText);
        siblingscholar = (EditText)findViewById(R.id.siblingsscholarEditText);
        nonumangsibling = (EditText)findViewById(R.id.nonumangsiblingEditText);
        extraexpense = (EditText)findViewById(R.id.extraexpensesEditText);
        appTV = (CheckBox)findViewById(R.id.applianceCheckBox1);
        appFridge = (CheckBox)findViewById(R.id.applianceCheckBox2);
        appMotor = (CheckBox)findViewById(R.id.applianceCheckBox3);

        sharedPreferences = this.getSharedPreferences("com.example.home.junoon", Context.MODE_PRIVATE);

        nextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appliance="TV: "+appTV.isChecked()+", Fridge: "+appFridge.isChecked()+", Motor Vehicle: "+appMotor.isChecked();
                new SendRequest().execute();
                Intent intent = new Intent(getApplicationContext(),MainMenuActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });

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
                URL url = new URL("https://script.google.com/macros/s/AKfycbylo7F3V3W84odLrEHKTtQo_sW6Qz7oDJ3JfgeEZAD3ndTjliU/exec?");

                JSONObject postDataParams = new JSONObject();

                //Passing scanned code as parameter

                postDataParams.put("name",sharedPreferences.getString("Name",""));
                postDataParams.put("class",sharedPreferences.getString("Class",""));
                postDataParams.put("school",sharedPreferences.getString("School",""));
                postDataParams.put("phone",sharedPreferences.getString("Feeone",""));

                postDataParams.put("feeinstl2",sharedPreferences.getString("Feetwo",""));
                postDataParams.put("feeinstl3",sharedPreferences.getString("Feethree",""));
                postDataParams.put("phnume",sharedPreferences.getString("Phone",""));
                postDataParams.put("addrs",sharedPreferences.getString("Addrs",""));

                postDataParams.put("fathername",fathername.getText().toString());
                postDataParams.put("fatheroccup",fatheroccup.getText().toString());
                postDataParams.put("mothername",mothername.getText().toString());
                postDataParams.put("motheroccup",motheroccup.getText().toString());

                postDataParams.put("income",income.getText().toString());
                postDataParams.put("percentlast",sharedPreferences.getString("percentlast",""));
                postDataParams.put("schoolattd",sharedPreferences.getString("schoolattd",""));
                postDataParams.put("totfamily",totfamily.getText().toString());

                postDataParams.put("earningfamily",earningfamily.getText().toString());
                postDataParams.put("attdnssschool",sharedPreferences.getString("attendnss",""));
                postDataParams.put("applianpresent",appliance);
                postDataParams.put("numstudysibling",numsiblings.getText().toString());

                postDataParams.put("siblingtakenscholar",siblingscholar.getText().toString());
                postDataParams.put("nonumang",nonumangsibling.getText().toString());
                postDataParams.put("extraexpense",extraexpense.getText().toString());
                postDataParams.put("Testsv1",sharedPreferences.getString("sv1",""));
                postDataParams.put("Testhv1",sharedPreferences.getString("hv1",""));
                postDataParams.put("Testsv2",sharedPreferences.getString("sv2",""));
                postDataParams.put("Testhv2",sharedPreferences.getString("hv2",""));
                postDataParams.put("Testsv3",sharedPreferences.getString("sv3",""));
                postDataParams.put("Testhv3",sharedPreferences.getString("hv3",""));
                postDataParams.put("vfeed1",sharedPreferences.getString("vfeed1",""));
                postDataParams.put("vfeed2",sharedPreferences.getString("vfeed2",""));
                postDataParams.put("vfeed3",sharedPreferences.getString("vfeed3",""));
                postDataParams.put("drivepic",sharedPreferences.getString("drivepic",""));
                postDataParams.put("final1",sharedPreferences.getString("final1",""));
                postDataParams.put("final2",sharedPreferences.getString("final2",""));
                postDataParams.put("final3",sharedPreferences.getString("final3",""));
                postDataParams.put("comments",sharedPreferences.getString("comments",""));





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
            Toast.makeText(IncomeFieldActivity.this, "Submitted!", Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), result,
            // Toast.LENGTH_LONG).show();

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
