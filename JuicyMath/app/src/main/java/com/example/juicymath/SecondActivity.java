package com.example.juicymath;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private MediaPlayer backgroundMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        backgroundMusic = MediaPlayer.create(this, R.raw.m2);
        backgroundMusic.setLooping(true);
        backgroundMusic.start();

        ImageView imageView = findViewById(R.id.imageView);
        Animation bubbleAnimation = AnimationUtils.loadAnimation(this, R.anim.bubble_animation);
        imageView.startAnimation(bubbleAnimation);

        Button additionButton = findViewById(R.id.button_addition);
        additionButton.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, AdditionActivity.class);
            startActivity(intent);
        });

        Button subtractionButton = findViewById(R.id.button_subtraction);
        subtractionButton.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, SubtractionActivity.class);
            startActivity(intent);
        });

        Button multiplicationButton = findViewById(R.id.button_multiplication);
        multiplicationButton.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, MultiplicationActivity.class);
            startActivity(intent);
        });

        Button divisionButton = findViewById(R.id.button_division);
        divisionButton.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, DivisionActivity.class);
            startActivity(intent);
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (backgroundMusic != null && backgroundMusic.isPlaying()) {
            backgroundMusic.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (backgroundMusic != null) {
            backgroundMusic.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (backgroundMusic != null) {
            backgroundMusic.release();
            backgroundMusic = null;
        }
    }
}

/*
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ImageView imageView = findViewById(R.id.imageView);

        Animation bubbleAnimation = AnimationUtils.loadAnimation(this, R.anim.bubble_animation);

        imageView.startAnimation(bubbleAnimation);

        Button additionButton = findViewById(R.id.button_addition);
        additionButton.setOnClickListener(v -> {

            Intent intent = new Intent(SecondActivity.this, AdditionActivity.class);
            startActivity(intent);
        });

        Button subtractionButton = findViewById(R.id.button_subtraction);
        subtractionButton.setOnClickListener(v -> {

            Intent intent = new Intent(SecondActivity.this, SubtractionActivity.class);
            startActivity(intent);
        });

        Button multiplicationButton = findViewById(R.id.button_multiplication);
        multiplicationButton.setOnClickListener(v -> {

            Intent intent = new Intent(SecondActivity.this, MultiplicationActivity.class);
            startActivity(intent);
        });

        Button divisionButton = findViewById(R.id.button_division);
        divisionButton.setOnClickListener(v -> {

            Intent intent = new Intent(SecondActivity.this, DivisionActivity.class);
            startActivity(intent);
        });
    }
}
*/