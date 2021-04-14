package com.example.alqoriny_midt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(5000, 10) {

            TextView time = (TextView) findViewById(R.id.time);

            public void onTick(long millisUntilFinished) {
                time.setText("seconds remaining: " +new SimpleDateFormat("ss").format(new Date( millisUntilFinished)));
            }

            public void onFinish() {
                time.setText("done!");
            }
        }.start();

        Button act2 = (Button)findViewById(R.id.button2);
        Button act3 = (Button)findViewById(R.id.button3);

        act2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
        act3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ThirdActivity.class));
            }
        });

        EditText PName, PQuantity, PID; DatabaseHelper db;

        Button add = (Button)findViewById(R.id.add);
        Button find = (Button)findViewById(R.id.find);
        Button delete = (Button)findViewById(R.id.delete);
        Button view = (Button)findViewById(R.id.view);
        PName = (EditText)findViewById(R.id.name);
        PQuantity = (EditText)findViewById(R.id.quantity);
        PID = (EditText)findViewById(R.id.productID);
        db = new DatabaseHelper(this);

            String name = PName.getText().toString();
            String quantity = PQuantity.getText().toString();
            if(name.isEmpty()||quantity.isEmpty()){ Toast.makeText(MainActivity.this, "Please fill all boxes",
                    Toast.LENGTH_LONG).show(); }
            else{
                if(!db.addData(name,quantity)){
                    Toast.makeText(MainActivity.this, "Insertion failed", Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(MainActivity.this, "Insertion Successful", Toast.LENGTH_LONG).show();
                } }
            find.setOnClickListener(new View.OnClickListener() { @Override
            public void onClick(View v) {
                String id = PID.getText().toString();
                if(id.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please insert the ID",
                            Toast.LENGTH_LONG).show(); }
                else{
                    Cursor cursor = db.getSpecificResult(id); if(cursor.getCount() <= 0){
                    } });
                Toast.makeText(MainActivity.this, "Item is not exist", Toast.LENGTH_LONG).show();
            }
else {
                    String CID = cursor.getString(0); String CName = cursor.getString(1); String CQuan = cursor.getString(2);
                    Toast.makeText(MainActivity.this, CID + ", " + CName + ", " + CQuan, Toast.LENGTH_LONG).show();
                } }
        });
delete.setOnClickListener(new View.OnClickListener() { @Override
                public void onClick(View v) {
                    String id = PID.getText().toString();
                    db.Delete(id);
                    Toast.makeText(MainActivity.this, "Successful Delete", Toast.LENGTH_LONG).show();
                } });
view.setOnClickListener(new View.OnClickListener() { @Override
                public void onClick(View v) {
                    Cursor cur = db.getListContents();
                    StringBuffer buffer = new StringBuffer();
                    while(cur.moveToNext()) {
                        buffer.append("id: " + cur.getString(0)+ "\n"); buffer.append("Name: " + cur.getString(1)+ "\n"); buffer.append("Quantity: " + cur.getString(2)+ "\n\n");
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true); // a dialog box that can be closed builder.setTitle("All Employees"); builder.setMessage(buffer.toString());
                    builder.show();
                    Toast.makeText(MainActivity.this, "Successful View", Toast.LENGTH_LONG).show();
                } });
}
    }
