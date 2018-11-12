package abdelkrim.ouajjit.com.sda_assign2_abdelkrim_ouajjit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class explicitActivity extends AppCompatActivity {
    //Declaring variables of the type TextView.

    private EditText editTextTo;
    private EditText editTextSubject;
    private EditText editTextCompose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);
    }

        // Reference : https://developer.android.com/reference/java/util/regex/Pattern.
    public void sendMessage(View view) {

        /*
         * Calling the sendMessage()method when the user taps the Send button
         * We can start adding data into the Intent object, we use the method defined in the Intent class putExtra()
         * Or putExtras() to store certain data as a key value pair or Bundle data object.
         * These key-value pairs are known as Extras in the sense we are talking about Intents.
         *
         */

        Intent explicitIntent = new Intent(this, MainActivity.class);
        // Retrieve a reference to the EditText by finding it by its id - in the form of R.id.idname
        editTextTo = (EditText) findViewById(R.id.enterEmailID);
        editTextSubject = (EditText) findViewById(R.id.subjectID);
        editTextCompose = (EditText) findViewById(R.id.composeID);
        String messageTo = editTextTo.getText().toString();
        if (messageTo.isEmpty()){
            editTextTo.setError("Field can't be empty");
         /*
          * The resulting pattern can then be used to create a Matcher object that can match arbitrary
          * character sequences against the regular expression.
          */

        }else if (!Patterns.EMAIL_ADDRESS.matcher(messageTo).matches()) {
            editTextTo.setError("Please enter a valid email address");
        }else {

         /*
          * We can start adding data into the Intent object, we use the method defined in the Intent class putExtra()
          * Or putExtras() to store certain data as a key value pair or Bundle data object.
          * These key-value pairs are known as Extras in the sense we are talking about Intents.
          * Attaching the key value pair using putExtra to this intent
          */

            explicitIntent.putExtra("TO", messageTo);
        }
        String messageSubject = editTextSubject.getText().toString();
        // Attaching the key value pair using putExtra to this intent
        explicitIntent.putExtra("SUBJECT",messageSubject);
        String messageCompose = editTextCompose.getText().toString();
        // Attaching the key value pair using putExtra to this intent
        explicitIntent.putExtra("COMPOSE", messageCompose);
        if(messageTo.length()!= 0 &&  messageSubject.length()!= 0 && messageCompose.length()!= 0 ) {
            startActivity(explicitIntent);
        }else{
            Toast.makeText(getApplicationContext(), "Please Complete all the fields!", Toast.LENGTH_SHORT).show();
        }
    }

}
