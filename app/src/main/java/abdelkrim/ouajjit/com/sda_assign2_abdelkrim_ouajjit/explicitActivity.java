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

    private EditText editTextTo;
    private EditText editTextSubject;
    private EditText editTextCompose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);
    }
    //TODO all the input must be valid ..done
    //TODO Valid email input ...
    public void sendMessage(View view) {
        /*
         * Called when the user taps the Send button
         * The resulting pattern can then be used to create a Matcher object that can match arbitrary
         * character sequences against the regular expression.
         * Reference : https://developer.android.com/reference/java/util/regex/Pattern.
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
        }else if (!Patterns.EMAIL_ADDRESS.matcher(messageTo).matches()) {
            editTextTo.setError("Please enter a valid email address");
        }else {
            explicitIntent.putExtra("TO", messageTo);
        }
        String messageSubject = editTextSubject.getText().toString();
        explicitIntent.putExtra("SUBJECT",messageSubject);
        String messageCompose = editTextCompose.getText().toString();
        explicitIntent.putExtra("COMPOSE", messageCompose);
        if(messageTo.length()!= 0 &&  messageSubject.length()!= 0 && messageCompose.length()!= 0 ) {
            startActivity(explicitIntent);
        }else{
            Toast.makeText(getApplicationContext(), "Please Complete all the fields!", Toast.LENGTH_SHORT).show();
        }
    }
}
