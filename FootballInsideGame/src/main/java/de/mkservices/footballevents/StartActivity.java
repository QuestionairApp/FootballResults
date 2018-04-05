package de.mkservices.footballevents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.media.MediaBrowserCompatUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button btn=(Button)findViewById(R.id.newGameBtn);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       EditText homeTeam=(EditText) findViewById(R.id.editHometeam);
                                       EditText guestTeam=(EditText)findViewById(R.id.editGuestTeam);
                                       EditText hashTag=(EditText)findViewById(R.id.editHashTag);
                                       EditText homeResultEd=(EditText)findViewById(R.id.editHomeStartResult);
                                       EditText guestResultEd=(EditText)findViewById(R.id.editGuestStartResult);
                                       String homeName=homeTeam.getText().toString();
                                       String guestName=guestTeam.getText().toString();
                                       String hashTagName=hashTag.getText().toString();
                                       int homeResult=Integer.parseInt(homeResultEd.getText().toString());
                                       int guestResult=Integer.parseInt(guestResultEd.getText().toString());

                                       Context ctx=view.getContext();
                                       SharedPreferences prefs=ctx.getSharedPreferences(getString(R.string.SHARED_PREFS_FILE), Context.MODE_PRIVATE);
                                       SharedPreferences.Editor edit=prefs.edit();
                                       edit.putString(getString(R.string.PREFS_HOME_NAME), homeName);
                                       edit.putString(getString(R.string.PREFS_GUEST_NAME), guestName);
                                       edit.putString(getString(R.string.PREFS_HASHTAG), hashTagName);
                                       edit.putInt(getString(R.string.PREFS_HOME_RESULT), homeResult);
                                       edit.putInt(getString(R.string.PREFS_GUEST_RESULT), guestResult);
                                       final boolean commit = edit.commit();
                                       Intent intent=new Intent(view.getContext(), SendResults.class);
                                       startActivity(intent);
                                   }
                               }
        );
    }
}
