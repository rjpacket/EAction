package com.rjp.eaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        findViewById(R.id.tv_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, TestActivity.class));
            }
        });
    }
}
