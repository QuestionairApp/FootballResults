package de.mkservices.footballevents;

import android.content.Context;
import android.content.Intent;
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
    private Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_results);

        //set context property
        ctx=this;
        //get references of all buttons
        Button homeBtn=(Button)findViewById(R.id.homeTeamBtn);
        Button guestBtn=(Button)findViewById(R.id.guestTeamBtn);
        Button kickoffBtn=(Button)findViewById(R.id.kickoffBtn);
        Button endofgameBtn=(Button)findViewById(R.id.endofGameBtn);
        Button endof1stBtn=(Button)findViewById(R.id.endof1stbtn);
        Button endof2ndBtn=(Button)findViewById(R.id.endof2ndbtn);
        Button endof3rdBtn=(Button)findViewById(R.id.endof3rdbtn);
        Button firstDownBtn=(Button)findViewById(R.id.firstdownbtn);
        Button touchDownBtn=(Button)findViewById(R.id.tdbtn);
        Button patBtn=(Button)findViewById(R.id.patbtn);
        Button twoPtBtn=(Button)findViewById(R.id.twoptconfbtn);
        Button safetyBtn=(Button)findViewById(R.id.safetybtn);
        Button sendBtn=(Button)findViewById(R.id.sendBtn);
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
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=homeTeam+" vs. "+guestTeam+": "+getTweetText()+" "+hashTag;
                setTweetText("");
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, tweet);
                startActivity(Intent.createChooser(intent, "Share event"));
            }
        });

        safetyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet=" "+"Safety!!!";
                setTweetText(tweet);
            }
        });

        twoPtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet+=" "+"2Pt. conversion good";
                setTweetText(tweet);
            }
        });

        patBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet+=" "+"PAT good";
                setTweetText(tweet);
            }
        });

        touchDownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet+=" "+"Touchdown!!!";
                setTweetText(tweet);
            }
        });

        firstDownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet+=" "+"first down";
                setTweetText(tweet);
            }
        });

        endof3rdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet+=" "+"End of 3rd quarter";
                setTweetText(tweet);
            }
        });

        endof2ndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet+=" "+"End of 2nd quarter";
                setTweetText(tweet);
            }
        });

        endof1stBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet=" "+"End of 1st Quarter";
                setTweetText(tweet);
            }
        });

        endofgameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet+=" "+"End of game";
                setTweetText(tweet);
            }
        });

        kickoffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet+=" "+"Kick off";
                setTweetText(tweet);
            }
        });

        guestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet+=" "+guestTeam;
                setTweetText(tweet);
            }
        });

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
