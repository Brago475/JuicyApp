package com.example.juicymath;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SubtractionActivity extends AppCompatActivity {

    private TextView display;
    private TextView operationTextView;
    private int firstNumber;
    private int secondNumber;
    private int correctAnswer;

    private MediaPlayer bsound3;
    private MediaPlayer button;
    private MediaPlayer right;
    private MediaPlayer wrong;
    private MediaPlayer wrong2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtraction);

        bsound3 = MediaPlayer.create(this, R.raw.bsound3);
        button = MediaPlayer.create(this, R.raw.button);
        right = MediaPlayer.create(this, R.raw.right);
        wrong = MediaPlayer.create(this, R.raw.wrong);
        wrong2 = MediaPlayer.create(this, R.raw.wrong2);

        button.setVolume(2.0f, 2.0f);

        bsound3.setLooping(true);
        bsound3.start();

        Button buttonZero = findViewById(R.id.zero);
        Button buttonOne = findViewById(R.id.one);
        Button buttonTwo = findViewById(R.id.two);
        Button buttonThree = findViewById(R.id.three);
        Button buttonFour = findViewById(R.id.four);
        Button buttonFive = findViewById(R.id.five);
        Button buttonSix = findViewById(R.id.six);
        Button buttonSeven = findViewById(R.id.seven);
        Button buttonEight = findViewById(R.id.eigth);
        Button buttonNine = findViewById(R.id.nine);
        Button buttonCheck = findViewById(R.id.check_mark);
        Button buttonBack = findViewById(R.id.back_button);
        Button buttonDelete = findViewById(R.id.delete4);

        display = findViewById(R.id.display);
        operationTextView = findViewById(R.id.operation);

        generateNewOperation();

        buttonZero.setOnClickListener(v -> {
            playSound(button);
            appendToDisplay("0");
        });
        buttonOne.setOnClickListener(v -> {
            playSound(button);
            appendToDisplay("1");
        });
        buttonTwo.setOnClickListener(v -> {
            playSound(button);
            appendToDisplay("2");
        });
        buttonThree.setOnClickListener(v -> {
            playSound(button);
            appendToDisplay("3");
        });
        buttonFour.setOnClickListener(v -> {
            playSound(button);
            appendToDisplay("4");
        });
        buttonFive.setOnClickListener(v -> {
            playSound(button);
            appendToDisplay("5");
        });
        buttonSix.setOnClickListener(v -> {
            playSound(button);
            appendToDisplay("6");
        });
        buttonSeven.setOnClickListener(v -> {
            playSound(button);
            appendToDisplay("7");
        });
        buttonEight.setOnClickListener(v -> {
            playSound(button);
            appendToDisplay("8");
        });
        buttonNine.setOnClickListener(v -> {
            playSound(button);
            appendToDisplay("9");
        });

        buttonCheck.setOnClickListener(v -> {
            playSound(button);
            checkAnswer();
        });

        buttonDelete.setOnClickListener(v -> {
            playSound(button);
            deleteLastCharacter();
        });

        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(SubtractionActivity.this, SecondActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void generateNewOperation() {
        firstNumber = (int) (Math.random() * 10);
        secondNumber = (int) (Math.random() * 10);

        if (firstNumber < secondNumber) {
            int temp = firstNumber;
            firstNumber = secondNumber;
            secondNumber = temp;
        }

        correctAnswer = firstNumber - secondNumber;
        operationTextView.setText(firstNumber + " - " + secondNumber);
    }

    private void appendToDisplay(String number) {
        String currentText = display.getText().toString();
        display.setText(currentText + number);
    }

    private void deleteLastCharacter() {
        String currentText = display.getText().toString();
        if (!currentText.isEmpty()) {
            display.setText(currentText.substring(0, currentText.length() - 1));
        }
    }

    private void checkAnswer() {
        String userAnswerString = display.getText().toString().replaceAll("[^0-9]", "");
        int userAnswer;

        try {
            if (userAnswerString.isEmpty()) {
                playSound(wrong2);
                showImageFeedback("enter_number");
                return;
            }

            userAnswer = Integer.parseInt(userAnswerString);
            if (userAnswer == correctAnswer) {
                playSound(right);
                showImageFeedback("correct");
                generateNewOperation();
                display.setText("");
            } else {
                playSound(wrong);
                showImageFeedback("try_again");
                display.setText("");
            }
        } catch (NumberFormatException e) {
            playSound(wrong2);
            showImageFeedback("enter_number");
        }
    }

    private void showImageFeedback(String feedbackType) {
        ImageView feedbackImage = findViewById(R.id.imageView4);

        switch (feedbackType) {
            case "correct":
                feedbackImage.setImageResource(R.drawable.gb);
                break;
            case "try_again":
                feedbackImage.setImageResource(R.drawable.incorrect);
                break;
            case "enter_number":
                feedbackImage.setImageResource(R.drawable.enter_number);
                break;
        }

        feedbackImage.setVisibility(View.VISIBLE);

        new android.os.Handler().postDelayed(() -> feedbackImage.setVisibility(View.GONE), 1500);
    }

    private void playSound(MediaPlayer sound) {
        if (sound != null) {
            sound.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (bsound3 != null && bsound3.isPlaying()) {
            bsound3.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bsound3 != null) {
            bsound3.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bsound3 != null) {
            bsound3.release();
            bsound3 = null;
        }
        if (button != null) {
            button.release();
            button = null;
        }
        if (right != null) {
            right.release();
            right = null;
        }
        if (wrong != null) {
            wrong.release();
            wrong = null;
        }
        if (wrong2 != null) {
            wrong2.release();
            wrong2 = null;
        }
    }
}

/*
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SubtractionActivity extends AppCompatActivity {

    private TextView display;
    private TextView operationTextView;
    private int firstNumber;
    private int secondNumber;
    private int correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtraction);

        Button buttonZero = findViewById(R.id.zero);
        Button buttonOne = findViewById(R.id.one);
        Button buttonTwo = findViewById(R.id.two);
        Button buttonThree = findViewById(R.id.three);
        Button buttonFour = findViewById(R.id.four);
        Button buttonFive = findViewById(R.id.five);
        Button buttonSix = findViewById(R.id.six);
        Button buttonSeven = findViewById(R.id.seven);
        Button buttonEight = findViewById(R.id.eigth);
        Button buttonNine = findViewById(R.id.nine);
        Button buttonCheck = findViewById(R.id.check_mark);
        Button buttonBack = findViewById(R.id.back_button);

        display = findViewById(R.id.display);
        operationTextView = findViewById(R.id.operation);

        generateNewOperation();

        buttonZero.setOnClickListener(v -> appendToDisplay("0"));
        buttonOne.setOnClickListener(v -> appendToDisplay("1"));
        buttonTwo.setOnClickListener(v -> appendToDisplay("2"));
        buttonThree.setOnClickListener(v -> appendToDisplay("3"));
        buttonFour.setOnClickListener(v -> appendToDisplay("4"));
        buttonFive.setOnClickListener(v -> appendToDisplay("5"));
        buttonSix.setOnClickListener(v -> appendToDisplay("6"));
        buttonSeven.setOnClickListener(v -> appendToDisplay("7"));
        buttonEight.setOnClickListener(v -> appendToDisplay("8"));
        buttonNine.setOnClickListener(v -> appendToDisplay("9"));

        buttonCheck.setOnClickListener(v -> checkAnswer());

        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(SubtractionActivity.this, SecondActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void generateNewOperation() {
        firstNumber = (int) (Math.random() * 10);
        secondNumber = (int) (Math.random() * 10);

        if (firstNumber < secondNumber) {
            int temp = firstNumber;
            firstNumber = secondNumber;
            secondNumber = temp;
        }

        correctAnswer = firstNumber - secondNumber;

        operationTextView.setText(firstNumber + " - " + secondNumber);
    }

    private void appendToDisplay(String number) {
        String currentText = display.getText().toString();
        if (currentText.contains("=")) {
            display.setText(number);
        } else {
            display.setText(currentText + number);
        }
    }

    private void checkAnswer() {
        String userAnswerString = display.getText().toString().replaceAll("[^0-9]", "");
        int userAnswer;

        try {
            userAnswer = Integer.parseInt(userAnswerString);
            if (userAnswer == correctAnswer) {
                showToast("Good Job!");
                generateNewOperation();
                display.setText("");
            } else {
                showToast("Try Again!");
                display.setText("");
            }
        } catch (NumberFormatException e) {
            showToast("Please enter a number!");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

 */
