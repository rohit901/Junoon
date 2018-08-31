package com.example.home.junoon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SpecificStudentActivity extends AppCompatActivity {

    Intent intent;
    int position;
    String result;
    ArrayList<Integer> studentPosition;
    TextView studname;
    TextView studclass;
    TextView studfathername;

    TextView studschool;
    TextView studfee1;
    TextView studfee2;

    TextView studfee3;
    TextView studphone;
    TextView studaddress;


    TextView studfathoccup;
    TextView studmothname;
    TextView studmothoccup;
    TextView studincome;

    TextView studpercentlast;
    TextView studschoolattd;
    TextView studtotfamily;

    TextView studearningfamily;
    TextView studattdsnssschool;
    TextView studapplianceprsnt;

    TextView studnumstudysibling;
    TextView studsiblingtakenscholar;
    TextView studnonumangsibling;

    TextView studextraexpense;

    TextView studtestsv1;
    TextView studtesthv1;
    TextView studtestsv2;

    TextView studtesthv2;
    TextView studtestsv3;
    TextView studtesthv3;

    TextView studvfeed1;
    TextView studvfeed2;
    TextView studvfeed3;

    TextView studdrivepic;

    TextView studei;
    TextView studpas;
    TextView studlas;

    TextView studas;

    TextView studschlr1;
    TextView studlimit1;
    TextView studfinal1;

    TextView studschlr2;
    TextView studlimit2;
    TextView studfinal2;

    TextView studschlr3;
    TextView studlimit3;
    TextView studfinal3;
    TextView studcomments;




    JSONObject jsonPart;


    ArrayList<String> studentData;
    ArrayList<String> studentspname;
    ArrayList<String> schoolData;
    ArrayList<String> studClass;
    ArrayList<String> fees1;
    ArrayList<String> fees2;
    ArrayList<String> fees3;

    ArrayList<String> phonenum;
    ArrayList<String> addrs;
    ArrayList<String> fathname;
    ArrayList<String> fathoccup;

    ArrayList<String> mothname;
    ArrayList<String> mothoccup;
    ArrayList<String> income;
    ArrayList<String> percentlast;

    ArrayList<String> schoolattd;
    ArrayList<String> totfamily;
    ArrayList<String> earningfamily;
    ArrayList<String> attdsnssschool;

    ArrayList<String> applianceprsnt;
    ArrayList<String> studyingsibling;
    ArrayList<String> siblingscholar;
    ArrayList<String> nonumangsibling;

    ArrayList<String> extraexpense;
    ArrayList<String> testsv1;
    ArrayList<String> testhv1;
    ArrayList<String> testsv2;

    ArrayList<String> testhv2;
    ArrayList<String> testsv3;
    ArrayList<String> testhv3;
    ArrayList<String> vfeed1;

    ArrayList<String> vfeed2;
    ArrayList<String> vfeed3;
    ArrayList<String> drivepic;
    ArrayList<String> ei;
    ArrayList<String> pas;
    ArrayList<String> las;
    ArrayList<String> asform;

    ArrayList<String> schlr1;
    ArrayList<String> limit1;

    ArrayList<String> schlr2;
    ArrayList<String> limit2;

    ArrayList<String> schlr3;
    ArrayList<String> limit3;

    ArrayList<String> final1;

    ArrayList<String> final2;
    ArrayList<String> final3;
    final Context c = this;
    ArrayList<String> comments;

    Button updateBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_student);
        intent = getIntent();
        result = intent.getStringExtra("result4");
        studentPosition = new ArrayList<Integer>();
        studentPosition = intent.getIntegerArrayListExtra("trackstud");
        position = intent.getIntExtra("position",0);
        studname = (TextView)findViewById(R.id.spstudName2);
        studclass = (TextView)findViewById(R.id.spclassdataTv);
        studfathername = (TextView)findViewById(R.id.spfathernamedatatv);
        studschool = (TextView)findViewById(R.id.spschooldatatv);
        studfee1 = (TextView)findViewById(R.id.spfeinstl1datatv);
        studfee2 = (TextView)findViewById(R.id.spfeinst2datatv);
        studfee3 = (TextView)findViewById(R.id.spfeinst3datatv);
        studphone = (TextView)findViewById(R.id.spphonedatatv);
        studaddress = (TextView)findViewById(R.id.spaddrsdatatv);
        studfathoccup = (TextView)findViewById(R.id.spfatheroccupdatatv);
        studmothname = (TextView)findViewById(R.id.spmothnamedatatv);
        studmothoccup = (TextView)findViewById(R.id.spmothoccupdatatv);
        studincome = (TextView)findViewById(R.id.spincomedatatv);
        studpercentlast = (TextView)findViewById(R.id.percentlastdatatv);
        studschoolattd = (TextView)findViewById(R.id.spschoolattddatatv);
        studtotfamily = (TextView)findViewById(R.id.sptotfamilydatatv);
        studearningfamily = (TextView)findViewById(R.id.spearningfamilydatatv);
        studattdsnssschool = (TextView)findViewById(R.id.spattdsnssschooldatatv);
        studapplianceprsnt = (TextView)findViewById(R.id.spappliancedatatv);
        studnumstudysibling = (TextView)findViewById(R.id.spstudysiblingdatatv);
        studsiblingtakenscholar = (TextView)findViewById(R.id.spsiblingtakenscholdatatv);
        studnonumangsibling = (TextView)findViewById(R.id.spnonumangdatatv);
        studextraexpense = (TextView)findViewById(R.id.spextraexpensedatatv);
        studtestsv1 = (TextView)findViewById(R.id.spsv1datatv);
        studtesthv1 = (TextView)findViewById(R.id.sphv1datatv);
        updateBtn = (Button)findViewById(R.id.spupdatebtnId);

        studtestsv2 = (TextView)findViewById(R.id.spsv2datatv);
        studtesthv2 = (TextView)findViewById(R.id.sphv2datatv);

        studtestsv3 = (TextView)findViewById(R.id.spsv3datatv);
        studtesthv3 = (TextView)findViewById(R.id.sphv3datatv);

        studvfeed1 = (TextView)findViewById(R.id.spvfeed1datatv);
        studvfeed2 = (TextView)findViewById(R.id.spvfeed2datatv);
        studvfeed3 = (TextView)findViewById(R.id.spvfeed3datatv);
        studdrivepic = (TextView)findViewById(R.id.spdrivepicdatatv);
        studei = (TextView)findViewById(R.id.speidatatv);
        studpas = (TextView)findViewById(R.id.sppasdatatv);
        studlas = (TextView)findViewById(R.id.splasdatatv);
        studas = (TextView)findViewById(R.id.spasdatatv);

        studschlr1 = (TextView)findViewById(R.id.spsch1datatv);
        studlimit1 = (TextView)findViewById(R.id.splim1datatv);
        studfinal1 = (TextView)findViewById(R.id.spfinal1datatv);

        studschlr2 = (TextView)findViewById(R.id.spsch2datatv);
        studlimit2 = (TextView)findViewById(R.id.splim2datatv);
        studfinal2 = (TextView)findViewById(R.id.spfinal2datatv);

        studschlr3 = (TextView)findViewById(R.id.spsch3datatv);
        studlimit3 = (TextView)findViewById(R.id.splim3datatv);
        studfinal3 = (TextView)findViewById(R.id.spfinal3datatv);
        studcomments = (TextView)findViewById(R.id.spcommentsdatatv);




        studentData = new ArrayList<String>();
        schoolData = new ArrayList<String>();
        studClass = new ArrayList<String>();
        fees1 = new ArrayList<String>();
        fees2 = new ArrayList<String>();
        fees3 = new ArrayList<String>();
        studentspname = new ArrayList<String>();
        phonenum = new ArrayList<String>();
        addrs = new ArrayList<String>();
        fathname = new ArrayList<String>();
        fathoccup = new ArrayList<String>();
        mothname = new ArrayList<String>();
        mothoccup = new ArrayList<String>();
        income = new ArrayList<String>();
        percentlast = new ArrayList<String>();
        schoolattd = new ArrayList<String>();
        totfamily = new ArrayList<String>();
        earningfamily = new ArrayList<String>();
        attdsnssschool = new ArrayList<String>();
        applianceprsnt = new ArrayList<String>();
        studyingsibling = new ArrayList<String>();
        siblingscholar = new ArrayList<String>();
        nonumangsibling = new ArrayList<String>();
        extraexpense = new ArrayList<String>();

        testsv1 = new ArrayList<String>();
        testsv2 = new ArrayList<String>();
        testsv3 = new ArrayList<String>();
        testhv1 = new ArrayList<String>();
        testhv2 = new ArrayList<String>();

        testhv3 = new ArrayList<String>();
        vfeed1 = new ArrayList<String>();
        vfeed2 = new ArrayList<String>();
        vfeed3 = new ArrayList<String>();
        drivepic = new ArrayList<String>();
        ei = new ArrayList<String>();
        pas = new ArrayList<String>();
        las = new ArrayList<String>();
        asform = new ArrayList<String>();

        schlr1 = new ArrayList<String>();
        schlr2 = new ArrayList<String>();
        schlr3 = new ArrayList<String>();

        limit1 = new ArrayList<String>();
        limit2 = new ArrayList<String>();
        limit3 = new ArrayList<String>();

        final1 = new ArrayList<String>();
        final2 = new ArrayList<String>();
        final3 = new ArrayList<String>();
        comments = new ArrayList<String>();



        try {
            JSONObject jsonObject = new JSONObject(result);

            String compCodes2 = jsonObject.getString("Sheet1");
            //minfo = jsonObject.getString("message");

            JSONArray arr = new JSONArray(compCodes2);

            for (int i = 0; i < arr.length(); i++) {
                jsonPart = arr.getJSONObject(i);
                //Log.d("books",jsonPart.getString("Results"));
                String splitstr = jsonPart.getString("School"); //school name
                schoolData.add(splitstr);
                studentData.add(jsonPart.getString("Student_Name")); //student name
                studClass.add(jsonPart.getString("Class"));
                fees1.add(jsonPart.getString("Fees-Install_1"));
                fees2.add(jsonPart.getString("Fees-Install_2"));
                fees3.add(jsonPart.getString("Fees-Install_3"));
                phonenum.add(jsonPart.getString("Phone_No"));
                addrs.add(jsonPart.getString("Address"));
                fathname.add(jsonPart.getString("Father_Name"));
                fathoccup.add(jsonPart.getString("Father_Occupation"));
                mothname.add(jsonPart.getString("Mother's_Name"));
                mothoccup.add(jsonPart.getString("Mother's_Occup"));
                income.add(jsonPart.getString("Income_PerMonth"));
                percentlast.add(jsonPart.getString("%_in_Last_Exam"));
                schoolattd.add(jsonPart.getString("School_Attendance"));
                totfamily.add(jsonPart.getString("Total_Family_Members"));
                earningfamily.add(jsonPart.getString("Earning_Family_Member"));
                attdsnssschool.add(jsonPart.getString("Attends_NSS_School"));
                applianceprsnt.add(jsonPart.getString("Appliance_Present"));

                studyingsibling.add(jsonPart.getString("No_of_Studying_Sibling"));
                siblingscholar.add(jsonPart.getString("Sibling_taken_in_scholarship"));
                nonumangsibling.add(jsonPart.getString("Non_Umang_sibling_fee_details"));
                extraexpense.add(jsonPart.getString("Extra_Expenses"));
                testsv1.add(jsonPart.getString("Tests-_School_Visit1"));


                testsv2.add(jsonPart.getString("Test_SV2"));
                testhv1.add(jsonPart.getString("Test-_Home_Visit_1"));
                testhv2.add(jsonPart.getString("Test_HV2"));
                testsv3.add(jsonPart.getString("Test_SV3"));
                testhv3.add(jsonPart.getString("Test_HV3"));


                vfeed1.add(jsonPart.getString("Volunt_Feedback_1"));
                vfeed2.add(jsonPart.getString("V_Feedback_2"));
                vfeed3.add(jsonPart.getString("V_Feedback_3"));
                drivepic.add(jsonPart.getString("Drive_pic"));
                ei.add(jsonPart.getString("E/I"));
                pas.add(jsonPart.getString("PAS"));
                las.add(jsonPart.getString("LAS"));
                asform.add(jsonPart.getString("AS"));

                schlr1.add(jsonPart.getString("Scholarship_1"));
                limit1.add(jsonPart.getString("Limit_1"));

                schlr2.add(jsonPart.getString("Scholarship_2"));
                limit2.add(jsonPart.getString("Limit_2"));

                schlr3.add(jsonPart.getString("Scholarship_3"));
                limit3.add(jsonPart.getString("Limit_3"));


                final1.add(jsonPart.getString("Final_1"));
                final2.add(jsonPart.getString("Final_2"));
                final3.add(jsonPart.getString("Final_3"));
                comments.add(jsonPart.getString("Comments"));


                // resultText.setText(resultText.getText() + jsonPart.getString("main") + ": " + jsonPart.getString("description") + "\n");
                //   Log.i("JsonRegToken", jsonPart.getString("Reg_Token"));
                //Log.i("description", jsonPart.toString());
            }







        }
        catch(Exception e){
            e.printStackTrace();
            Toast.makeText(SpecificStudentActivity.this, "Error! Something's wrong!", Toast.LENGTH_LONG).show();

        }


        studname.setText(studentData.get(studentPosition.get(position)));
        studclass.setText(studClass.get(studentPosition.get(position)));
        studschool.setText(schoolData.get(studentPosition.get(position)));
        studfee1.setText(fees1.get(studentPosition.get(position)));
        studfee2.setText(fees2.get(studentPosition.get(position)));
        studfee3.setText(fees3.get(studentPosition.get(position)));
        studphone.setText(phonenum.get(studentPosition.get(position)));
        studaddress.setText(addrs.get(studentPosition.get(position)));
        studfathername.setText(fathname.get(studentPosition.get(position)));
        studfathoccup.setText(fathoccup.get(studentPosition.get(position)));


        studmothname.setText(mothname.get(studentPosition.get(position)));
        studmothoccup.setText(mothoccup.get(studentPosition.get(position)));
        studincome.setText(income.get(studentPosition.get(position)));
        studpercentlast.setText(percentlast.get(studentPosition.get(position)));

        studschoolattd.setText(schoolattd.get(studentPosition.get(position)));
        studtotfamily.setText(totfamily.get(studentPosition.get(position)));
        studearningfamily.setText(earningfamily.get(studentPosition.get(position)));
        studattdsnssschool.setText(attdsnssschool.get(studentPosition.get(position)));

        studapplianceprsnt.setText(applianceprsnt.get(studentPosition.get(position)));
        studnumstudysibling.setText(studyingsibling.get(studentPosition.get(position)));
        studsiblingtakenscholar.setText(siblingscholar.get(studentPosition.get(position)));
        studnonumangsibling.setText(nonumangsibling.get(studentPosition.get(position)));

        studextraexpense.setText(extraexpense.get(studentPosition.get(position)));

        studtestsv1.setText(testsv1.get(studentPosition.get(position)));
        studtesthv1.setText(testhv1.get(studentPosition.get(position)));

        studtestsv2.setText(testsv2.get(studentPosition.get(position)));
        studtesthv2.setText(testhv2.get(studentPosition.get(position)));

        studtestsv3.setText(testsv3.get(studentPosition.get(position)));
        studtesthv3.setText(testhv3.get(studentPosition.get(position)));

        studvfeed1.setText(vfeed1.get(studentPosition.get(position)));
        studvfeed2.setText(vfeed2.get(studentPosition.get(position)));
        studvfeed3.setText(vfeed3.get(studentPosition.get(position)));

        studdrivepic.setText(drivepic.get(studentPosition.get(position)));

        studei.setText(ei.get(studentPosition.get(position)));
        studpas.setText(pas.get(studentPosition.get(position)));
        studlas.setText(las.get(studentPosition.get(position)));
        studas.setText(asform.get(studentPosition.get(position)));

        studschlr1.setText(schlr1.get(studentPosition.get(position)));
        studlimit1.setText(limit1.get(studentPosition.get(position)));

        studschlr2.setText(schlr2.get(studentPosition.get(position)));
        studlimit2.setText(limit2.get(studentPosition.get(position)));

        studschlr3.setText(schlr3.get(studentPosition.get(position)));
        studlimit3.setText(limit3.get(studentPosition.get(position)));

        studfinal1.setText(final1.get(studentPosition.get(position)));
        studfinal2.setText(final2.get(studentPosition.get(position)));
        studfinal3.setText(final3.get(studentPosition.get(position)));
        studcomments.setText(comments.get(studentPosition.get(position)));



        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
                View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
                alertDialogBuilderUserInput.setView(mView);
                final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
                alertDialogBuilderUserInput
                        .setCancelable(false)
                        .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {

                                //Toast.makeText(c, "Entered: "+userInputDialogEditText.getText().toString(), Toast.LENGTH_SHORT).show();
                                // ToDo get user input here
                                if(userInputDialogEditText.getText().toString().equals("gnamu")){

                                    Intent intent = new Intent(getApplicationContext(),uGeneralDetailsActivity.class);
                                    intent.putExtra("result5",result);
                                    intent.putExtra("studpos",studentPosition);
                                    intent.putExtra("pos",position);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                                }
                                else{
                                    Toast.makeText(SpecificStudentActivity.this, "You Do not have the access to this feature!", Toast.LENGTH_SHORT).show();
                                }



                            }
                        });





                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                alertDialogAndroid.show();







            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
