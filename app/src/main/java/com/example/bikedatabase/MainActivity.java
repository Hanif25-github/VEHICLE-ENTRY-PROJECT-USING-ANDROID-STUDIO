package com.example.bikedatabase;
//edit

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText bikeName,bikeDate;
    TextView textViewBik;
    Button insertButton, fetchButton;
    Spinner dayTimeSpinner;
    Switch swtch;
    DbConnection dbConnection;

    //STEP1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbConnection = new DbConnection(this);
        textViewBik = findViewById(R.id.txtViewBik);
        bikeName = findViewById(R.id.edtTxtBik);
        bikeDate = findViewById(R.id.edtTxtDate);
        insertButton = findViewById(R.id.btnInsert);
        fetchButton = findViewById(R.id.btnFetch);
        fetchButton.setVisibility(View.INVISIBLE);
        dayTimeSpinner = findViewById(R.id.spinner);
        swtch = findViewById(R.id.switcher);

        //STEP2

        swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {//Checked whether the switch is checked to enable some components
                if(!isChecked)
                {
                    fetchButton.setVisibility(View.INVISIBLE);

                    insertButton.setVisibility(View.VISIBLE);

                    bikeName.setVisibility(View.VISIBLE);

                    textViewBik.setVisibility(View.VISIBLE);
                }
                else
                {
                    //Enable some of the buttons and components (Views) as Visible

                    bikeName.setVisibility(View.INVISIBLE);

                    insertButton.setVisibility(View.INVISIBLE);

                    textViewBik.setVisibility(View.INVISIBLE);

                    fetchButton.setVisibility(View.VISIBLE);
                    //End of STEP 3
                }
            }
        });

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = bikeName.getText().toString();
                String date = bikeDate.getText().toString();
                String time = dayTimeSpinner.getSelectedItem().toString();


                boolean insert = dbConnection.insertValues(name,date,time);
                if(insert==true)
                {

                    Toast.makeText(getApplicationContext(),"Data inserted Successfully", Toast.LENGTH_SHORT).show();
                    bikeName.setText(null);
                    bikeDate.setText(null);
                    //This allows us to insert new data
                }
                else

                    Toast.makeText(getApplicationContext(), "Data insertion unsuccessful", Toast.LENGTH_SHORT).show();
            }
        });

        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = bikeDate.getText().toString();
                String time = dayTimeSpinner.getSelectedItem().toString();
                String bik = "";
                Cursor cur = dbConnection.RetrieveData(date, time);
                cur.moveToFirst();
                do{

                    //med=med+(String.valueOf(cursor.getString(cursor.getColumnIndex("medicineName"))));
                    bik = bik + (String.valueOf(cur.getString(cur.getColumnIndex("bikeName"))));
                    bik+="\n";
                }while (cur.moveToNext());
                Toast.makeText(getApplicationContext(), bik, Toast.LENGTH_LONG).show();
            }
        });
    } }



