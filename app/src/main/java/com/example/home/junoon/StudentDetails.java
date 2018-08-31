package com.example.home.junoon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class StudentDetails extends AppCompatActivity {

    Intent intent;
    String result;
    String result2;
    String sname;
    ListView studentListView;
    ArrayList<String> studentData;
    ArrayList<String> studentspname;
    ArrayList<String> schoolData;
    ArrayList<String> studClass;
    ArrayList<Integer> trackSpStudent;


    ArrayAdapter arrayAdapter;
    private ProgressBar spinner;
    JSONObject jsonPart;
    EditText searchStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        intent = getIntent();
        //position = intent.getIntExtra("position",0);
        result = intent.getStringExtra("result");
        sname = intent.getStringExtra("sname");
        studentListView = (ListView)findViewById(R.id.studentListView);
        studentData = new ArrayList<String>();
        schoolData = new ArrayList<String>();
        studentspname = new ArrayList<String>();
        trackSpStudent = new ArrayList<Integer>();
        searchStudent = (EditText)findViewById(R.id.searchStudentEditText);



        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,studentspname);
        studentListView.setAdapter(arrayAdapter);
        spinner = (ProgressBar)findViewById(R.id.progressBar2);
        spinner.setVisibility(View.GONE);
        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),SpecificStudentActivity.class);
                intent.putExtra("trackstud",trackSpStudent);
                intent.putExtra("result4",result);
                intent.putExtra("position",studentspname.indexOf(adapterView.getItemAtPosition(i)));
                //Toast.makeText(StudentDetails.this, "Position "+studentspname.indexOf(adapterView.getItemAtPosition(i)), Toast.LENGTH_SHORT).show();
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });



        try {
            JSONObject jsonObject = new JSONObject(result);

            String compCodes2 = jsonObject.getString("Sheet1");
            //minfo = jsonObject.getString("message");

            JSONArray arr = new JSONArray(compCodes2);

            for (int i = 0; i < arr.length(); i++) {
                jsonPart = arr.getJSONObject(i);
                //Log.d("books",jsonPart.getString("Results"));
                String splitstr = jsonPart.getString("School");
                schoolData.add(splitstr);
                studentData.add(jsonPart.getString("Student_Name"));
                arrayAdapter.notifyDataSetChanged();
                // resultText.setText(resultText.getText() + jsonPart.getString("main") + ": " + jsonPart.getString("description") + "\n");
                //   Log.i("JsonRegToken", jsonPart.getString("Reg_Token"));
                //Log.i("description", jsonPart.toString());
            }

            for(int j = 0; j<schoolData.size();j++){
                if(schoolData.get(j).equals(sname)){

                    studentspname.add(studentData.get(j));
                    trackSpStudent.add(j);
                    arrayAdapter.notifyDataSetChanged();

                }
            }






        }
        catch(Exception e){
            e.printStackTrace();
            Toast.makeText(StudentDetails.this, "Error! Something's wrong!", Toast.LENGTH_LONG).show();

        }



        searchStudent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                StudentDetails.this.arrayAdapter.getFilter().filter(charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //new SendRequest().execute();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public class SendRequest extends AsyncTask<String, Void, String> {


        protected void onPreExecute(){
            spinner.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... arg0) {

            try{

                //Enter script URL Here
                URL url = new URL("https://script.google.com/macros/s/AKfycby6xOJeW0T-Qw0nHXP5ZtfyjiSpFXolsNxtnsZyCgNyXpuB5xDD/exec?");

                JSONObject postDataParams = new JSONObject();

                //Passing scanned code as parameter

                postDataParams.put("sdata",sname);



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
            DownloadTask task = new DownloadTask();
            try {
                // asyncT.execute();
                result2 = new DownloadTask().execute("https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1nO6Vf5HuV3a9yWzf1C2JTiBRomPA29s-F4mGtfJxTXE&sheet=Sheet2").get();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(StudentDetails.this, "Error! Something's wrong!", Toast.LENGTH_LONG).show();

            }
            try {
                JSONObject jsonObject = new JSONObject(result2);

                String compCodes2 = jsonObject.getString("Sheet2");
                //minfo = jsonObject.getString("message");

                JSONArray arr = new JSONArray(compCodes2);

                for (int i = 0; i < arr.length(); i++) {
                    jsonPart = arr.getJSONObject(i);
                    //Log.d("books",jsonPart.getString("Results"));
                    String[] splitstr = jsonPart.getString("Results").split(",");
                    studentData.add(splitstr[0]);
                    arrayAdapter.notifyDataSetChanged();
                    // resultText.setText(resultText.getText() + jsonPart.getString("main") + ": " + jsonPart.getString("description") + "\n");
                    //   Log.i("JsonRegToken", jsonPart.getString("Reg_Token"));
                    //Log.i("description", jsonPart.toString());
                }



                if(studentData.size()==0){
                    Toast.makeText(StudentDetails.this, "Error! Something's wrong!", Toast.LENGTH_LONG).show();
                }


            }
            catch(Exception e){
                e.printStackTrace();
                Toast.makeText(StudentDetails.this, "Error! Something's wrong!", Toast.LENGTH_LONG).show();

            }
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
    public class DownloadTask extends AsyncTask<String,Void,String> {
        private ProgressDialog progressDialog;



        @Override
        protected void onPreExecute() {
            // SHOW THE SPINNER WHILE LOADING FEEDS
            spinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //lv.setAdapter(adapter);

            // CHANGE THE LOADINGMORE STATUS TO PERMIT FETCHING MORE DAT

            // HIDE THE SPINNER AFTER LOADING FEEDS
            spinner.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... urls) {
            String result ="";
            URL url;
            HttpURLConnection urlConnection = null;
            try{

                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection)url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while(data!=-1){
                    char current = (char) data;
                    result+=current;
                    data = reader.read();
                }
                return result;
            }
            catch (Exception e){
                e.printStackTrace();
                return "failed";
            }

        }
    }
}
