package com.alvaroquispe.ejerciciostema5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ArosOlimpicos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(new ArosOlimpicosCanvas(this));

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    public class ArosOlimpicosCanvas extends View{
        public ArosOlimpicosCanvas (Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas){
            Paint pinzell = new Paint();
            pinzell.setColor(Color.BLUE);
            pinzell.setStrokeWidth(18);

            pinzell.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(350, 250, 100, pinzell);

            pinzell.setColor(getResources().getColor(R.color.black));
            canvas.drawCircle(570, 250, 100, pinzell);

            pinzell.setColor(getResources().getColor(R.color.rojo));
            canvas.drawCircle(790, 250, 100, pinzell);

            pinzell.setColor(getResources().getColor(R.color.amarillo));
            canvas.drawCircle(450, 350, 100, pinzell);

            pinzell.setColor(getResources().getColor(R.color.verder));
            canvas.drawCircle(650, 350, 100, pinzell);
        }
    }
}