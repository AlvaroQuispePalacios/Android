package com.alvaroquispe.ejerciciostema5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Actividad5_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(new Acti52(this));
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    public class Acti52 extends View {
        public Acti52(Context context){
            super(context);
        }

        @Override
        public void onDraw(Canvas canvas){
            Path cami = new Path();
            cami.addCircle(450, 450, 300, Path.Direction.CW);
            canvas.drawColor(Color.WHITE);
            Paint pinzell = new Paint();
            pinzell.setColor(Color.BLUE);
            pinzell.setStrokeWidth(8);
            pinzell.setStyle(Paint.Style.STROKE);

            canvas.drawPath(cami, pinzell);
            pinzell.setStrokeWidth(1);
            pinzell.setStyle(Paint.Style.FILL);
            pinzell.setTextSize(40);
            pinzell.setTypeface(Typeface.SANS_SERIF);
            canvas.drawTextOnPath("Desenvolupament d'aplicacions per a mòbils amb Android", cami, 100, -20, pinzell);
        }
    }
}