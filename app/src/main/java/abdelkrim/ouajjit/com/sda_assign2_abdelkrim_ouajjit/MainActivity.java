package abdelkrim.ouajjit.com.sda_assign2_abdelkrim_ouajjit;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView openCamera;
    private TextView viewPicture;
    private TextView explicitIntent;
    private TextView showMessage;
    private String messageTo;
    private String messageSubject;
    private String messageCompose;

    private static final int CAMERA_IMAGE_REQUEST_IMAGE_CODE=1;
    private static final int REQUEST_CODE=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openCamera =   findViewById(R.id.openCameraID);
        viewPicture=   findViewById(R.id.viewPictureID);
        explicitIntent=findViewById(R.id.callAnActivityID);
        showMessage =  findViewById(R.id.showMessageID);

        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        viewPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewThePicture();
            }
        });
        explicitIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTheSecondActivity();
            }
        });


        /*
        * Extract the data from explicitActivity.
        * Reference : https://developer.android.com/training/basics/firstapp/starting-activity#java.
        * Reference : https://developer.android.com/reference/android/app/Activity.
        */
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            messageTo = extras.getString("TO");
            messageSubject = extras.getString("SUBJECT");
            messageCompose = extras.getString("COMPOSE");
            if (messageTo!=null && messageSubject!=null && messageCompose!=null) {
                // Capture the layout's TextView and set the string as its text
                showMessage.setText("To : " + messageTo + "\nSubject: \t" + messageSubject + "\nCompose: \t" + messageCompose);
            }
            }


    }
    // invoking the dispatchTakePictureIntent() method when the user clicks a TextView openCamera.
    // TODO: try only startActivity() method .
    private void dispatchTakePictureIntent() {
        //Start a camera app in still image mode
        //Reference : https://developer.android.com/guide/components/intents-common.
        Intent takePictureIntent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent,CAMERA_IMAGE_REQUEST_IMAGE_CODE);
            Toast.makeText(MainActivity.this, "camera is launched", Toast.LENGTH_LONG).show();
        }
    }
    // invoking the viewThePicture() method when the user clicks a TextView viewPicture.
    private void viewThePicture() {
        //Reference : https://stackoverflow.com/questions/16928727/open-gallery-app-from-android-intent.
        /*
         * Open the Android gallery application from an intent :
         * Invoke the image gallery using an implicit intent.
         * Creating an intent object called viewPictureIntent by using the key word new .
         */
        Intent viewPictureIntent = new Intent();
        // creating an Intent with the action, ACTION_VIEW because we want to VIEW the contents ..
        viewPictureIntent.setAction(Intent.ACTION_VIEW);
        // Set the data and type ,Get all images types
        viewPictureIntent.setType("image/*");
        //This particular flag (FLAG_ACTIVITY_NEW_TASK) is useful when the notification has to start the application by finishing the existing Activities.
        viewPictureIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Using the startActivity() method by passing the intent to launch the Gallery app.
        startActivity(viewPictureIntent);
        // Creating Toast message when the Gallery activity is launched .
        Toast.makeText(MainActivity.this, "Gallery is launched", Toast.LENGTH_LONG).show();

    }

    private void createTheSecondActivity() {
        //Create the second activity.
        //Reference : https://developer.android.com/training/basics/firstapp/starting-activity#java.
        Intent secondActivityIntent = new Intent(this,explicitActivity.class);

        startActivity(secondActivityIntent);
    }

    public void sendEmail(View view){
        /** Called when the user taps the Send button */

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("plain/text");
            sendIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{messageTo});
            sendIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, messageSubject);
            sendIntent.putExtra(android.content.Intent.EXTRA_TEXT, messageCompose);
        if (messageTo!=null && messageSubject!=null && messageCompose!=null){
            startActivity(sendIntent);
        }else{
            Toast.makeText(getApplicationContext(), "No Data present " +
                    "to send!", Toast.LENGTH_SHORT).show();
        }
    }


}
