package com.example.home.junoon;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import javax.net.ssl.HttpsURLConnection;

public class DetailsActivity extends AppCompatActivity {

    Intent intent;
    String jsonResult = "";
    JSONObject jsonPart;
    int position;

    Button confUpdate;

    EditText nameEditText;
    EditText classEditText;

    EditText schoolEditText;
    EditText phoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        nameEditText = (EditText)findViewById(R.id.unameEditText);
        classEditText = (EditText)findViewById(R.id.uclassEditText);
        schoolEditText = (EditText)findViewById(R.id.uschoolEditText);
        phoneEditText = (EditText)findViewById(R.id.uphoneEditText);
        confUpdate = (Button)findViewById(R.id.confUpdateBtn);
        intent = getIntent();
        jsonResult = intent.getStringExtra("result");
        position = intent.getIntExtra("position",0);


        try {
            JSONObject jsonObject = new JSONObject(jsonResult);

            String compCodes2 = jsonObject.getString("Sheet1");
            //minfo = jsonObject.getString("message");

            JSONArray arr = new JSONArray(compCodes2);

            for (int i = 0; i <=position ; i++) {
                jsonPart = arr.getJSONObject(i);
                //compCodes.add(jsonPart.getString("Company_name"));
                // resultText.setText(resultText.getText() + jsonPart.getString("main") + ": " + jsonPart.getString("description") + "\n");
                //   Log.i("JsonRegToken", jsonPart.getString("Reg_Token"));
                //Log.i("description", jsonPart.toString());
            }
            nameEditText.setText(jsonPart.getString("ID"));
            classEditText.setText(jsonPart.getString("Name"));
            schoolEditText.setText(jsonPart.getString("Class"));
            phoneEditText.setText(jsonPart.getString("School"));






        }
        catch(Exception e){
            e.printStackTrace();

        }

        confUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SendRequest().execute();
            }
        });
    }

    public class SendRequest extends AsyncTask<String, Void, String> {


        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{

                //Enter script URL Here
                URL url = new URL("https://script.google.com/macros/s/AKfycbyKSJD87JgMdcM1qI827JtEa7SmU_0mZA0_L020xLtXGvq0BalG/exec?");

                JSONObject postDataParams = new JSONObject();

                //Passing scanned code as parameter

                postDataParams.put("name",nameEditText.getText().toString());
                postDataParams.put("class",classEditText.getText().toString());
                postDataParams.put("school",schoolEditText.getText().toString());
                postDataParams.put("phone",phoneEditText.getText().toString());
                postDataParams.put("pos",position);


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
                    //Toast.LENGTH_LONG).show();

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
