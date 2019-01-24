package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {



    // TODO: Declare member variables here:
    Button mFalseButton;
    Button mTrueButton;
    TextView mQuestionTextView;
    int mIndex;
    int mQuestion;
    ProgressBar mScoreProgressBar;
    TextView mScoreTextView;
    int mScore;

    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };
    // TODO: Declare constants here
    final int PROGRESS_BAR_INCRIMENT =(int) Math.ceil(100.0/ mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFalseButton  = (Button) findViewById(R.id.false_button);
        mTrueButton  = (Button) findViewById(R.id.true_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mScoreProgressBar = findViewById(R.id.progress_bar);
        mScoreTextView = findViewById(R.id.score);



        mQuestion = mQuestionBank[mIndex].getQuestionId();
        mQuestionTextView.setText(mQuestion);

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
                updateQuestion();
            }
        });
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
                updateQuestion();
            }
        });

    }
    private void updateQuestion()
    {
        mIndex = (mIndex+1)% mQuestionBank.length;
        if(mIndex ==0)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            // AlertDialog.Builder alert = new AlertDialog.Builder(this); the same as above
            alert.setTitle("Game over");
            alert.setCancelable(false);
            alert.setMessage("You Score "+mScore+ " Points");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.show();
        }
        mQuestion = mQuestionBank[mIndex].getQuestionId();
        mQuestionTextView.setText(mQuestion);
        mScoreProgressBar.incrementProgressBy(PROGRESS_BAR_INCRIMENT);
        mScoreTextView.setText("Score "+mScore+"/" +mQuestionBank.length);
    }
    private void checkAnswer(boolean userAnswer)
    {
        boolean correctAnswer = mQuestionBank[mIndex].isAnswer();
        if(userAnswer == correctAnswer)
        {
            Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();
            mScore++;
        }else{
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
        }
    }
}
