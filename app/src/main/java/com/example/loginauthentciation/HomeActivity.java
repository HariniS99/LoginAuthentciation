package com.example.loginauthentciation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.icu.text.DateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import utils.DBHelper;

public class HomeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText name,dob,age,contact,mailid,education,address;
    //Spinner nation,gender,martial_state;
    Button insert,update,delete,view;
    //String nation_str,gen_str,mar_str;

    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name=findViewById(R.id.name);
        dob=findViewById(R.id.dob);
        age=findViewById(R.id.age);
        contact=findViewById(R.id.contact);
        mailid=findViewById(R.id.mailid);
        education=findViewById(R.id.education);
        address=findViewById(R.id.address);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePrickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });


        insert=findViewById(R.id.btnInsert);
        update=findViewById(R.id.btnUpdate);
        delete=findViewById(R.id.btnDelete);
        view=findViewById(R.id.btnView);
        DB=new DBHelper(this);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT=name.getText().toString();
                String dobTXT=dob.getText().toString();
                String ageTXT=age.getText().toString();
                String contactTXT=contact.getText().toString();
                String mailidTXT=mailid.getText().toString();
                String educationTXT=education.getText().toString();
                String addressTXT=address.getText().toString();
                //String nationTXT=nation_str.toString();
                //String genTXT=gen_str.toString();
                //String maritalTXT=mar_str.toString();
                Boolean checkinsertdata=DB.insertuserdate(nameTXT,dobTXT,ageTXT,contactTXT,mailidTXT,educationTXT,addressTXT);
                if(nameTXT.equals("")||dobTXT.equals("")||ageTXT.equals("")||contactTXT.equals("")||mailidTXT.equals("")
                        ||educationTXT.equals("")||addressTXT.equals(""))
                {
                    Toast.makeText(HomeActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();

                }
                else{
                    if(checkinsertdata==true)

                        Toast.makeText(HomeActivity.this,"New Entry",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(HomeActivity.this,"New Entry not inserted",Toast.LENGTH_SHORT).show();
                }

            }

        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT=name.getText().toString();
                String dobTXT=dob.getText().toString();
                String ageTXT=age.getText().toString();
                String contactTXT=contact.getText().toString();
                String mailidTXT=mailid.getText().toString();
                String educationTXT=education.getText().toString();
                String addressTXT=address.getText().toString();

                Boolean checkupdatedata=DB.updateuserdate(nameTXT,dobTXT,ageTXT,contactTXT,mailidTXT,educationTXT,addressTXT);
                if(nameTXT.equals("")||dobTXT.equals("")||ageTXT.equals("")||contactTXT.equals("")||mailidTXT.equals("")
                        ||educationTXT.equals("")||addressTXT.equals(""))
                {
                    Toast.makeText(HomeActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();

                }
                else {
                    if (checkupdatedata == true)
                        Toast.makeText(HomeActivity.this, "updated successfull", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(HomeActivity.this, "update failure", Toast.LENGTH_SHORT).show();
                }

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT=name.getText().toString();

                Boolean checkdeletedata=DB.deleteuserdate(nameTXT);
                if(nameTXT.equals(""))
                {
                    Toast.makeText(HomeActivity.this, "Please enter the name to delete", Toast.LENGTH_SHORT).show();

                }
                else{
                    if(checkdeletedata==true)
                        Toast.makeText(HomeActivity.this,"Deleted Successfull",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(HomeActivity.this,"Delete failure",Toast.LENGTH_SHORT).show();
                }

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=DB.getdata();
                if(res.getCount()==0)
                {
                    Toast.makeText(HomeActivity.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
                    return ;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name:"+res.getString(0)+"\n");
                    buffer.append("dob:"+res.getString(1)+"\n");
                    buffer.append("age:"+res.getString(2)+"\n");
                    buffer.append("Contact:"+res.getString(3)+"\n");
                    buffer.append("mailid:"+res.getString(4)+"\n");
                    buffer.append("education:"+res.getString(5)+"\n");
                    buffer.append("address:"+res.getString(6)+"\n\n");

                }
                AlertDialog.Builder builder=new AlertDialog.Builder(HomeActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Enteries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDateSet(DatePicker view, int year, int month, int
            dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String date = DateFormat.getDateInstance().format(c.getTime());
        dob.setText(date);
        //.setAge(c);
        //ageInput.setText(String.format("%s",profile.getAge()));
    }
}

