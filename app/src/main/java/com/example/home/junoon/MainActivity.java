package com.example.home.junoon;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {
    EditText nameEditText;
    EditText classEditText;

    EditText schoolEditText;
    EditText phoneEditText;

    Button submitBtn;

    String nameText;
    String classText;
    String schoolText;
    String phoneText;

    Button updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = (EditText)findViewById(R.id.unameEditText);
        classEditText = (EditText)findViewById(R.id.uclassEditText);
        schoolEditText = (EditText)findViewById(R.id.uschoolEditText);
        phoneEditText = (EditText)findViewById(R.id.uphoneEditText);
        submitBtn = (Button)findViewById(R.id.submitBtn);
        updateBtn = (Button) findViewById(R.id.updateBtn);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),updateActivity.class);
                startActivity(intent);
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameText = nameEditText.getText().toString();
                classText = classEditText.getText().toString();
                schoolText = schoolEditText.getText().toString();
                phoneText = phoneEditText.getText().toString();
                if(TextUtils.isEmpty(nameText) || TextUtils.isEmpty(classText) || TextUtils.isEmpty(schoolText) || TextUtils.isEmpty(phoneText)){
                    Toast.makeText(MainActivity.this, "Empty Fields! Try again!", Toast.LENGTH_SHORT).show();
                }
                else {
                    new SendRequest().execute();
                }
            }
        });
    }

    public class SendRequest extends AsyncTask<String, Void, String> {


        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{

                //Enter script URL Here
                URL url = new URL("https://script.google.com/macros/s/AKfycbylo7F3V3W84odLrEHKTtQo_sW6Qz7oDJ3JfgeEZAD3ndTjliU/exec?");

                JSONObject postDataParams = new JSONObject();

                //Passing scanned code as parameter

                postDataParams.put("name",nameText);
                postDataParams.put("class",classText);
                postDataParams.put("school",schoolText);
                postDataParams.put("phone",phoneText);


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
