package com.pursuit.StatesSearch;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.supercharge.shimmerlayout.ShimmerLayout;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_LENGTH = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ShimmerLayout shimmerTitle = findViewById(R.id.shimmer_title);
        shimmerTitle.startShimmerAnimation();
        ShimmerLayout shimmerText = findViewById(R.id.shimmer_text);
        shimmerText.startShimmerAnimation();

        new Handler().postDelayed(() -> {

            Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(mainIntent);
            SplashActivity.this.finish();
        }, SPLASH_LENGTH);
    }
}

