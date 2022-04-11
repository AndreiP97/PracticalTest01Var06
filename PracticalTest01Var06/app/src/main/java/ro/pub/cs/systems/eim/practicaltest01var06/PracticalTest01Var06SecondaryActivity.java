package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var06SecondaryActivity extends AppCompatActivity  {

    private TextView gainedTextView;
    private TextView scoreTextView;
    private Button okButton;
    private int score = 0;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.ok_button) {
                setResult(score, null);
            }
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_secondary);

        gainedTextView = (TextView) findViewById(R.id.gained_text_view);
        scoreTextView = (TextView) findViewById(R.id.score_text_view);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.NUMBER_OF_CHECKED_BOXES)) {
            String leftValue, centerValue, rightValue;
            int numberOfCheckedBoxes = 0;

            leftValue = intent.getStringExtra(Constants.LEFT_NUMBER);

            centerValue = intent.getStringExtra(Constants.CENTER_NUMBER);

            rightValue = intent.getStringExtra(Constants.RIGHT_NUMBER);

            numberOfCheckedBoxes = intent.getIntExtra(Constants.NUMBER_OF_CHECKED_BOXES, -1);

            if (leftValue.equals(centerValue) && centerValue.equals(rightValue)) {
                if (numberOfCheckedBoxes == 0)
                    score = 100;
                else if (numberOfCheckedBoxes == 1)
                    score = 50;
                else if (numberOfCheckedBoxes == 2)
                    score = 10;
                else
                    score = 0;
            }
        }
        scoreTextView.setText(String.valueOf(score));

        okButton = (Button) findViewById(R.id.ok_button);
        okButton.setOnClickListener(buttonClickListener);
    }

}
