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
    private int tmpResult;
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
        Button fiveydBtn=(Button)findViewById(R.id.fiveydBtn);
        Button tenydBtn=(Button)findViewById(R.id.tenYardBtn);
        Button fifteenydBtn=(Button)findViewById(R.id.fifteenYardBtn);
        Button fieldGoalBtn=(Button)findViewById(R.id.fieldGoalBtn);
        //get shared preferences and team names and hashtag
        SharedPreferences prefs=this.getSharedPreferences(getString(R.string.SHARED_PREFS_FILE), Context.MODE_PRIVATE);
        homeTeam=prefs.getString(getString(R.string.PREFS_HOME_NAME),"Heim");
        guestTeam=prefs.getString(getString(R.string.PREFS_GUEST_NAME), "Gast");
        hashTag=prefs.getString(getString(R.string.PREFS_HASHTAG), "#heimvsgast");
        int homeResult=prefs.getInt(getString(R.string.PREFS_HOME_RESULT), 0);
        int guestResult=prefs.getInt(getString(R.string.PREFS_GUEST_RESULT), 0);
        setResultFields(homeResult, guestResult);
        //set button labels
        homeBtn.setText(homeTeam);
        guestBtn.setText(guestTeam);
        Toast.makeText(this, homeTeam+":"+guestTeam+":"+hashTag, Toast.LENGTH_LONG).show();

        //add click handler to buttons
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs=ctx.getSharedPreferences(getString(R.string.SHARED_PREFS_FILE), Context.MODE_PRIVATE);
                int homeResult=prefs.getInt(getString(R.string.PREFS_HOME_RESULT),0);
                int guestResult=prefs.getInt(getString(R.string.PREFS_GUEST_RESULT), 0);
                String tweet=homeTeam+" " + String.valueOf(homeResult)+" vs. "+guestTeam+" "+String.valueOf(guestResult)+" : "+getTweetText()+" "+hashTag;
                setTweetText("");
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, tweet);
                startActivity(Intent.createChooser(intent, "Share event"));
            }
        });

        fifteenydBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet+=" "+"15 yd. penalty";
                setTweetText(tweet);
            }
        });

        tenydBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet+=" "+"10 yd. penalty";
                setTweetText(tweet);
            }
        });

        fiveydBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet+=" "+"5 yd. penalty";
                setTweetText(tweet);
            }
        });

        fieldGoalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet+=" "+"Field Goal!";
                setTweetText(tweet);
                tmpResult=3;
            }
        });

        safetyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet+=" "+"Safety!!!";
                setTweetText(tweet);
                tmpResult=2;
            }
        });

        twoPtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet+=" "+"2Pt. conversion good";
                setTweetText(tweet);
                tmpResult=2;
            }
        });

        patBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet+=" "+"PAT good";
                setTweetText(tweet);
                tmpResult=1;
            }
        });

        touchDownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet+=" "+"Touchdown!!!";
                setTweetText(tweet);
                tmpResult=6;
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
                SharedPreferences prefs=ctx.getSharedPreferences(getString(R.string.SHARED_PREFS_FILE), Context.MODE_PRIVATE);
                int homeResult=prefs.getInt(getString(R.string.PREFS_HOME_RESULT), 0);
                int guestResult=prefs.getInt(getString(R.string.PREFS_GUEST_RESULT), 0);
                guestResult+=tmpResult;
                tmpResult=0;
                SharedPreferences.Editor editor=prefs.edit();
                editor.putInt(getString(R.string.PREFS_GUEST_RESULT), guestResult);
                editor.apply();
                setResultFields(homeResult, guestResult);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet=getTweetText();
                tweet +=" "+homeTeam;
                setTweetText(tweet);
                SharedPreferences prefs=ctx.getSharedPreferences(getString(R.string.SHARED_PREFS_FILE), Context.MODE_PRIVATE);
                int homeResult=prefs.getInt(getString(R.string.PREFS_HOME_RESULT), 0);
                int guestResult=prefs.getInt(getString(R.string.PREFS_GUEST_RESULT), 0);
                homeResult+=tmpResult;
                tmpResult=0;
                SharedPreferences.Editor editor=prefs.edit();
                editor.putInt(getString(R.string.PREFS_HOME_RESULT), homeResult);
                editor.apply();
                setResultFields(homeResult, guestResult);
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

    private void setResultFields(int home, int guest){
        EditText homeResultEdit=(EditText)findViewById(R.id.editHomeRes);
        EditText guestResultEdit=(EditText)findViewById(R.id.editGuestRes);
        homeResultEdit.setText(String.valueOf(home));
        guestResultEdit.setText(String.valueOf(guest));
    }
}
