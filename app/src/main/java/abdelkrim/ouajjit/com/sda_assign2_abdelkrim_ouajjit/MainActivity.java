package abdelkrim.ouajjit.com.sda_assign2_abdelkrim_ouajjit;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.Reference;

public class MainActivity extends AppCompatActivity {
    //Declaring variables of the type TextView.
    private TextView openCamera;
    private TextView viewPicture;
    private TextView explicitIntent;
    private TextView showMessage;
    //Declaring variables of the type string.
    private String messageTo;
    private String messageSubject;
    private String messageCompose;
    //Declaring a static final variable of the type integer.
    private static final int CAMERA_IMAGE_REQUEST_IMAGE_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
         * Required call through to Activity onCreate().
         * Restore any saved instance state.
         */
        super.onCreate(savedInstanceState);
        // Set up the application's user interface (Content View).
        setContentView(R.layout.activity_main);
        // Get a reference to TextView in the content view,
        openCamera = findViewById(R.id.openCameraID);
        viewPicture= findViewById(R.id.viewPictureID);
        explicitIntent=findViewById(R.id.callAnActivityID);
        showMessage = findViewById(R.id.showMessageID);
        // set OnClickListener for TextView
        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        // Called the dispatchTakePictureIntent() method when the user taps TextView openCamera.
                dispatchTakePictureIntent();
                Log.i("tag'","dispatchTakePictureIntent() called");
            }
        });
        viewPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        // Called the viewThePicture() method when the user taps TextView viewPicture.
                viewThePicture();
                Log.i("tag'","viewThePicture() called");
            }
        });
        explicitIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        // Called the createTheSecondActivity() method when the user taps TextView explicitIntent.
                createTheSecondActivity();
                Log.i("tag'","createTheSecondActivity() called");
            }
        });


        /*
         * Reference : https://developer.android.com/training/basics/firstapp/starting-activity#java.
         * Reference : https://developer.android.com/reference/android/app/Activity.
         */

        /*
         * Get the current intent.
         * Get the attached extras from the intent through the Bundle.
         */
        Bundle extras = getIntent().getExtras();
        if (extras!=null) { // If there is some data
         // We should use the same key as we used to attach the data to retrieve the data.
            messageTo = extras.getString("TO");
            messageSubject = extras.getString("SUBJECT");
            messageCompose = extras.getString("COMPOSE");
            // If all the fields are completed then we can pass all the information to set the Text View with our message.
            if (messageTo!=null && messageSubject!=null && messageCompose!=null) {
                // Capture the layout's TextView and set the string as its text
                showMessage.setText("To : " + messageTo + "\nSubject: \t" + messageSubject + "\nCompose: \t" + messageCompose);
            }
            }
    }

    /*
     * Reference : https://developer.android.com/guide/components/intents-common.
     * Invoking the dispatchTakePictureIntent() method when the user clicks a TextView openCamera.
     */

    private void dispatchTakePictureIntent() {
        // Starting a camera app using intent filter STILL_IMAGE_CAMERA
        Intent takePictureIntent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

        /*
         * Starting the startActivityForResult() method to receive a result back.
         * The integer argument is a "CAMERA_IMAGE_REQUEST_IMAGE_CODE" that identifies your request.
         */

            startActivityForResult(takePictureIntent,CAMERA_IMAGE_REQUEST_IMAGE_CODE);
        // Toast message when the camera is launched.
            Toast.makeText(MainActivity.this, "camera is launched", Toast.LENGTH_LONG).show();
        }
    }
    // invoking the viewThePicture() method when the user clicks a TextView viewPicture.
    private void viewThePicture() {

        /*
         * Reference : https://stackoverflow.com/questions/16928727/open-gallery-app-from-android-intent.
         * Open the Android gallery application from an intent :
         * Invoke the image gallery using an implicit intent.
         * Creating an intent object called viewPictureIntent by using the key word new .
         */

        Intent viewPictureIntent = new Intent();
        // Creating an Intent with the ACTION_VIEW because we want to VIEW the contents ..
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

        /*
         * Reference : https://developer.android.com/training/basics/firstapp/starting-activity#java.
         * Invoking the createTheSecondActivity() to create the second activity.
         */

    private void createTheSecondActivity() {

        //Reference : https://developer.android.com/training/basics/firstapp/starting-activity#java.
        Intent secondActivityIntent = new Intent(this,explicitActivity.class);
        //starting the activity
        startActivity(secondActivityIntent);
    }

    public void sendEmail(View view){
        // Called when the user taps the Send button
        // Get the Intent that started this activity and extract the string
            Intent sendIntent = new Intent();
        //Set the intent action send
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("plain/text");
            sendIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{messageTo});
            sendIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, messageSubject);
            sendIntent.putExtra(android.content.Intent.EXTRA_TEXT, messageCompose);
        if (messageTo!=null && messageSubject!=null && messageCompose!=null){
            //starting the activity
            startActivity(sendIntent);
        }else{
            Toast.makeText(getApplicationContext(), "No Data present " +
                    "to send!", Toast.LENGTH_SHORT).show();
        }
    }


}
