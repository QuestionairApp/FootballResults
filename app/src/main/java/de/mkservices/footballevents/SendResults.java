package de.mkservices.footballevents;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendResults extends AppCompatActivity {
    private String homeTeam;
    private String guestTeam;
    private String hashTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_results);

        //get references of all buttons
        Button homeBtn=(Button)findViewById(R.id.homeTeamBtn);
        Button guestBtn=(Button)findViewById(R.id.guestTeamBtn);
        Button kickoffBtn=(Button)findViewById(R.id.kickoffBtn);
        //get shared preferences and team names and hashtag
        SharedPreferences prefs=this.getSharedPreferences(getString(R.string.SHARED_PREFS_FILE), Context.MODE_PRIVATE);
        homeTeam=prefs.getString(getString(R.string.PREFS_HOME_NAME),"Heim");
        guestTeam=prefs.getString(getString(R.string.PREFS_GUEST_NAME), "Gast");
        hashTag=prefs.getString(getString(R.string.PREFS_HASHTAG), "#heimvsgast");

        //set button labels
        homeBtn.setText(homeTeam);
        guestBtn.setText(guestTeam);
        Toast.makeText(this, homeTeam+":"+guestTeam+":"+hashTag, Toast.LENGTH_LONG).show();

        //add click handler to buttons
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet +=" "+homeTeam;
                setTweetText(tweet);
            }
        });
    }

    private String getTweetText(){
        EditText editText=(EditText)findViewById(R.id.editTweet);
        String tweetTxt=editText.getText().toString();
        return tweetTxt;
    }

    protected void setTweetText(String tweet){
        EditText editText=(EditText)findViewById(R.id.editTweet);
        editText.setText(tweet);
    }
}
