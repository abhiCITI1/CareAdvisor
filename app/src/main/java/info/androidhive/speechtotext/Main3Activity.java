package info.androidhive.speechtotext;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main3Activity extends Activity {

    DatabaseHelper myDb;
    Button findDoctor, bookAppointment;
    EditText  location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        myDb = new DatabaseHelper(this);

        location = (EditText)findViewById(R.id.editLoc);
        System.out.println("locationis "+ Main3Activity.this.location.getText().toString());
        bookAppointment= (Button)findViewById(R.id.bookAppointment);
        findDoctor= (Button)findViewById(R.id.findDoctor);
        bookAnAppointment();
        findDoctor(Main3Activity.this.location.getText().toString());
    }


    public void bookAnAppointment() {
        bookAppointment.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        showMessage("Booked","Your appointment is booked you will receive a communication soon!!");
                    }
                }
        );


    }


    public void findDoctor(String location) {
        final String addr = location;
        System.out.println("location received " + location);
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
                            buffer.append("Name :"+ res.getString(0)+"\n");
                            buffer.append("Address :"+ res.getString(1)+"\n");

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
