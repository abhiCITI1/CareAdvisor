package info.androidhive.speechtotext;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import android.app.AlertDialog;

public class Main2Activity extends Activity {

    DatabaseHelper myDb;
    EditText name, registrationNum, time, contact,address, specialization;
    Button registration;
    Button findDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        myDb = new DatabaseHelper(this);

        name = (EditText)findViewById(R.id.editname);
        registrationNum = (EditText)findViewById(R.id.editreg);
        time = (EditText)findViewById(R.id.edittime);
        contact = (EditText)findViewById(R.id.editcontact);
        address = (EditText)findViewById(R.id.editadd);
        specialization = (EditText)findViewById(R.id.editspec);

        registration= (Button)findViewById(R.id.registration);
        findDoctor= (Button)findViewById(R.id.findDoctor);
        AddData();
        //findStudent(address.toString());
    }

    public  void AddData() {
        System.out.println("Add data invoked");
        registration.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("Values :" + registrationNum.getText().toString() +","+ name.getText().toString() +","+ time.getText().toString()+","+contact.getText().toString()+","+ address.getText().toString()+","+specialization.getText().toString());
                        boolean isInserted = myDb.insertData(registrationNum.getText().toString(), name.getText().toString(),
                                time.getText().toString(),contact.getText().toString(),address.getText().toString(),specialization.getText().toString()   );
                        if(isInserted == true)
                            Toast.makeText(Main2Activity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Main2Activity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void findStudent(String address) {
        final String addr = address;
        findDoctor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData(addr);
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");

                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }



}
