package com.dan.mathexamapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // create the global variable scoremessage
    String scoremessage;
    int score;

//  define a static variable for later saveinstance method
    static final String SCORE = "SCORE";
    static final String MESSAGE = "MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Hide action Bar
        getSupportActionBar().hide();

//        Restart method in case the phone is rotating
        if (savedInstanceState != null) {
            scoremessage = savedInstanceState.getString(SCORE);
            score=savedInstanceState.getInt(MESSAGE);
            TextView Score = (TextView)findViewById(R.id.score);
            Score.setText(scoreMessage());}}


//method to check whether users answer all the required question
    private boolean countingSubmit() {

        RadioGroup answerTwo= (RadioGroup)findViewById(R.id.answer_two);
        EditText answerone = (EditText) findViewById(R.id.answer_one);
        String two =answerone.getText().toString();
        EditText answerThree = (EditText) findViewById(R.id.answer_three);
        String three =answerone.getText().toString();
//        if statement check whether each questions has been answer
        if ( two.isEmpty()
                ||answerTwo.getCheckedRadioButtonId()==-1
                || three.isEmpty()){
            return false;}
        else{return true;}

    }
//    method check the score, give the message about the user performance based on previous method such as checkOne...
    private String scoreMessage(){
        EditText nametyping= (EditText)findViewById(R.id.name_typing);
        String name=nametyping.getText().toString();
        scoremessage= getString(R.string.name);
        scoremessage+=name +"\n";
        scoremessage+= getString(R.string.scoreMessage);
//        checking the score base on checkOne... method
        score=30;
        if (checkOne()==false){score-=10;}
        if (checkTwo()==false){score-=10;}
        if (checkThree()==false){score-=10;}
        scoremessage += String.valueOf(score);
        return scoremessage;}

//   method to check whether user answer the 1st question correctly
    private boolean checkOne() {
        EditText answerone = (EditText) findViewById(R.id.answer_one);
        String answer = answerone.getText().toString();
        if (answer.equals("10")) {return true;}
        else {return false;}}
// method to check whether user answer the 2th question correctly
    private boolean checkTwo() {
        RadioButton answerTwo = (RadioButton) findViewById(R.id.answer_two_b);
        if (answerTwo.isChecked()) {return true;}
        else {return false;}}

// method to check whether user answer the 3th question correcly
    private boolean checkThree() {
        EditText answerThree = (EditText) findViewById(R.id.answer_three);
        String three= answerThree.getText().toString();
        Log.i("MainActivity.java", three);
        if (three.equals("25")){return true;}
        else {return false;}}

    // method associated with submit button
    public void submit(View view){
//        if provide the toast message requiring user to answer all the questions
        if (countingSubmit()==false){
            Context not_enuf = getApplicationContext();
            CharSequence please = "You have to answer all 3 questions to submit";
            int notEnufDuration= Toast.LENGTH_SHORT;
            Toast.makeText(not_enuf, please ,notEnufDuration).show();}
// else provide the name of the user and the score
        else{
            TextView Score = (TextView)findViewById(R.id.score);
            Score.setText(scoreMessage());}}
    private void display(){}

    /// /Using Indent to send the detail result by email
    public void send_result(View view){
        EditText nametyping= (EditText)findViewById(R.id.name_typing);
        String name=nametyping.getText().toString();
        EditText emailtyping= (EditText) findViewById(R.id.email_typing);
        String email=emailtyping.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.ExtraSubject) + name);
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.ExtraText));
        intent.putExtra(Intent.EXTRA_EMAIL, email);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);}}
}