package com.alvaroquispe.repasoexamen2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VectorActivity extends AppCompatActivity {
    Button btnVolverAMainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vector);

        btnVolverAMainActivity = findViewById(R.id.btnVolverAMainActivity);

        btnVolverAMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int contadorVector = getIntent().getIntExtra("contadorVector", 0);
                contadorVector++;
                Intent i = new Intent();
                i.putExtra("contadorVectorAumentado", contadorVector);
                setResult(RESULT_OK, i);
                finish();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}