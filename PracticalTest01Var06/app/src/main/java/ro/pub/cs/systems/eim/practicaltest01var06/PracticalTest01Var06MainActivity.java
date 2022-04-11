package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {

    private EditText leftEditText;
    private EditText centerEditText;
    private EditText rightEditText;

    private CheckBox leftCheckBox;
    private CheckBox centerCheckBox;
    private CheckBox rightCheckBox;

    private Button playButton;

    private Random rand = new Random();

    private int totalScore = 0;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.play_button) {
                String leftValue;
                String centerValue;
                String rightValue;
                int numberOfCheckBoxes = 0;

                leftValue = Constants.randomValues[rand.nextInt(Constants.randomValues.length)];
                centerValue = Constants.randomValues[rand.nextInt(Constants.randomValues.length)];
                rightValue = Constants.randomValues[rand.nextInt(Constants.randomValues.length)];

                if (!leftCheckBox.isChecked()) {
                    leftEditText.setText(leftValue);
                }
                else {
                    numberOfCheckBoxes++;
                    leftValue = leftEditText.getText().toString();
                }

                if (!centerCheckBox.isChecked()) {
                    centerEditText.setText(centerValue);
                }
                else {
                    numberOfCheckBoxes++;
                    centerValue = centerEditText.getText().toString();
                }

                if (!rightCheckBox.isChecked()) {
                    rightEditText.setText(rightValue);
                }
                else {
                    numberOfCheckBoxes++;
                    rightValue = rightEditText.getText().toString();
                }

                if (leftValue.equals(centerValue) && rightValue.equals(centerValue)) {
                    // Generating the intent
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var06SecondaryActivity.class);
                    intent.putExtra(Constants.LEFT_NUMBER, leftValue);
                    intent.putExtra(Constants.CENTER_NUMBER, centerValue);
                    intent.putExtra(Constants.RIGHT_NUMBER, rightValue);
                    intent.putExtra(Constants.NUMBER_OF_CHECKED_BOXES, numberOfCheckBoxes);
                    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
                }
                else {
                    Toast.makeText(PracticalTest01Var06MainActivity.this, "Total score is " + totalScore,
                            Toast.LENGTH_SHORT).show();
                }

            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);

        leftEditText = (EditText) findViewById(R.id.left_edit_text);
        centerEditText = (EditText) findViewById(R.id.center_edit_text);
        rightEditText = (EditText) findViewById(R.id.right_edit_text);

        leftCheckBox = (CheckBox) findViewById(R.id.left_checkbox);
        centerCheckBox = (CheckBox) findViewById(R.id.center_checkbox);
        rightCheckBox = (CheckBox) findViewById(R.id.right_checkbox);

        playButton = (Button) findViewById(R.id.play_button);
        playButton.setOnClickListener(buttonClickListener);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        totalScore += resultCode;
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "Total score is " + totalScore,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(Constants.SCORE, totalScore);
    }

    // La restaurarea starii aplicatiei
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.SCORE)) {
            totalScore = savedInstanceState.getInt(Constants.SCORE);
        }
    }
}