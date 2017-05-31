package com.dan.mathexamapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
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
            score=savedInstanceState.getInt(MESSAGE);}}

//    method check the score, give the message about the user performance based on previous method such as checkOne...
private String scoreMessage(){
//        find the EditText which require users to type name
    EditText nametyping= (EditText)findViewById(R.id.name_typing);
//        get the name typed by the users
    String name=nametyping.getText().toString();
    scoremessage= getString(R.string.name);
    scoremessage+=name +"\n";
    scoremessage+= getString(R.string.scoreMessage);
//        checking the score base on checkOne, checkTwo, chechThree, checkFour methods
//        create score variables if the users answer wrong, the score is decresed by 10
    score=40;
    if (checkOne()==false){
        score-=10;}
    if (checkTwo()==false){
        score-=10;}
    if (checkThree()==false){
        score-=10;}
    if (checkFour()==false){
        score-=10;}
    scoremessage += String.valueOf(score);
//      score message will show the name of the users, the score they gain
    return scoremessage;}

//   method to check whether user answer the 1st question correctly; the right answer is 10.
//    method return true if users answer the question correctly
private boolean checkOne() {
    EditText answerone = (EditText) findViewById(R.id.answer_one);
    String answer = answerone.getText().toString();
    if (answer.equals("10")) {
        return true;}
    else {
        return false;}
}

// method to check whether user answer the 2nd question correctly, the right answerTwo = 20
//    method return true if users answer the question correctly
private boolean checkTwo() {
    RadioButton answerTwo = (RadioButton) findViewById(R.id.answer_two_b);
    if (answerTwo.isChecked()) {
        return true;}
    else {
        return false;}
}

// method to check whether user answer the 3rd question correcly; the right answer is 25
//    method return true if users answer the question correctly
private boolean checkThree() {
    EditText answerThree = (EditText) findViewById(R.id.answer_three);
    String three= answerThree.getText().toString();
    if (three.equals("25")){
        return true;}
    else {
        return false;}
}

//    method to check whether users answer the 4th question correctly: the right answer is both a and b
//    method return true if users answer the question correctly
private boolean checkFour(){
    CheckBox answerfoura = (CheckBox) findViewById(R.id.answer_four_a);
    boolean checkfoura =answerfoura.isChecked();
    CheckBox answerfourb =(CheckBox) findViewById(R.id.answer_four_b);
    boolean checkfourb =answerfourb.isChecked();
    if (checkfoura & checkfourb){
        return true;}
    else{
        return false;}
}

    // user click submit button to receive the score
    public void submit(View view){
        Context not_enuf = getApplicationContext();
        int notEnufDuration= Toast.LENGTH_SHORT;
//      the second parameter of makeText is scoreMessage which is defined above
//      when the users click button, toast message will appear showing the number of correct answer and their name
        Toast.makeText(not_enuf, scoreMessage() ,notEnufDuration).show();}

    /// /Using Indent to send the detail result by email
    public void sendResult(View view){
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