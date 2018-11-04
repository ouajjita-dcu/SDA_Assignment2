package abdelkrim.ouajjit.com.sda_assign2_abdelkrim_ouajjit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
    //TODO validation
    public void sendMessage(View view) {
        /** Called when the user taps the Send button */
        Intent explicitIntent = new Intent(this, MainActivity.class);
        editTextTo = (EditText) findViewById(R.id.enterEmailID);
        editTextSubject = (EditText) findViewById(R.id.subjectID);
        editTextCompose = (EditText) findViewById(R.id.composeID);
        String messageTo = editTextTo.getText().toString();
        explicitIntent.putExtra("TO",messageTo);
        String messageSubject = editTextSubject.getText().toString();
        explicitIntent.putExtra("SUBJECT",messageSubject);
        String messageCompose = editTextCompose.getText().toString();
        explicitIntent.putExtra("COMPOSE", messageCompose);
        startActivity(explicitIntent);

    }
}
