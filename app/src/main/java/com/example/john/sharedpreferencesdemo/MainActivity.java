package com.example.john.sharedpreferencesdemo;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
//getPreferences(int)
//  -Activity persistent state is managed with the method getPreferences(int),
//      allowing you to retrieve and modify a set of name/value pairs associated with the activity.
//Context.getSharedPreferences()
//  -preferences that are shared across multiple application
//      components (activities, receivers, services, providers) you
//      can use the underlying Context.getSharedPreferences() method
// to retrieve a preferences object stored under a specific name
//-If you don't need to store a lot of data and it doesn't require structure,
//      you should use SharedPreferences.
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivityLog";
    private TextView mTextView;
    private String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        mTextView = findViewById(R.id.textView);
        SharedPreferences mPrefs = getApplicationContext()
                .getSharedPreferences("com.example.john.sharedpreferencesdemo", Context.MODE_PRIVATE);
        message = mPrefs.getString("message",null);
        if(message != null){
            mTextView.setText(message);
        }else{
            mTextView.setText("hello!!");
        }
    }
    //saves the message to "YOU ROCK" which will be saved in shared pref
    //-shared prefs persist even after app is killed
    public void sendMessage(View view){
        message = "YOU ROCK!";
        mTextView.setText(message);
        //saved data into shared preference
        SharedPreferences mPrefs = getApplicationContext()
                .getSharedPreferences("com.example.john.sharedpreferencesdemo", Context.MODE_PRIVATE);
        //Context.MODE_PRIVATE -File creation mode: the default mode, where the created file can only
        //   be accessed by the calling application (or all applications sharing the same user ID).
        //Get a editor object-which can put key/values into SharedPreferences
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("message", message);
        editor.commit();
    }
}
