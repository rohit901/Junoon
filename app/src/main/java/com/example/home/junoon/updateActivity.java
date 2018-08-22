package com.example.home.junoon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class updateActivity extends AppCompatActivity {
    ListView updateDetailsLv;
    ArrayList<String> updateData;
    ArrayAdapter arrayAdapter;
    private ProgressBar spinner;
    String result2="";
    JSONObject jsonPart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        updateDetailsLv = (ListView) findViewById(R.id.updateListView);
        updateData = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,updateData);
        updateDetailsLv.setAdapter(arrayAdapter);
        updateDetailsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),DetailsActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("result",result2);
                startActivity(intent);
            }
        });

        DownloadTask task = new DownloadTask();
        try {
            // asyncT.execute();
            result2 = new DownloadTask().execute("https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1nO6Vf5HuV3a9yWzf1C2JTiBRomPA29s-F4mGtfJxTXE&sheet=Sheet1").get();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(updateActivity.this, "Error! Something's wrong!", Toast.LENGTH_LONG).show();

        }
        try {
            JSONObject jsonObject = new JSONObject(result2);

            String compCodes2 = jsonObject.getString("Sheet1");
            //minfo = jsonObject.getString("message");

            JSONArray arr = new JSONArray(compCodes2);

            for (int i = 0; i < arr.length(); i++) {
                jsonPart = arr.getJSONObject(i);
                //Log.d("books",jsonPart.getString("Results"));
                String splitstr = jsonPart.getString("Name");
                updateData.add(splitstr);
                arrayAdapter.notifyDataSetChanged();
                // resultText.setText(resultText.getText() + jsonPart.getString("main") + ": " + jsonPart.getString("description") + "\n");
                //   Log.i("JsonRegToken", jsonPart.getString("Reg_Token"));
                //Log.i("description", jsonPart.toString());
            }



            if(updateData.size()==0){
                Toast.makeText(updateActivity.this, "Error! Something's wrong!", Toast.LENGTH_LONG).show();
            }


        }
        catch(Exception e){
            e.printStackTrace();
            Toast.makeText(updateActivity.this, "Error! Something's wrong!", Toast.LENGTH_LONG).show();

        }


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
