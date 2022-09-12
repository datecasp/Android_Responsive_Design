package com.datecasp.androidresponsivedesign;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    float dX, dY;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fab.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent event)
            {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        dX = view.getX() - event.getRawX();
                        dY = view.getY() - event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:

                        // Variables for visible boundaries set
                        // For the height of the textView above buttons
                        TextView txt = findViewById(R.id.textView);
                        // To get the limits of the holding view
                        ConstraintLayout viewport = findViewById(R.id.viewport);
                        // Height of the FAB
                        // Reference system is 0,0 up and left
                        float fabHeight = fab.getHeight();

                        float posX = (event.getRawX()+dX);
                        float posY = (event.getRawY()+dX);

                        /*
                            Check visible area bounds
                         */
                        if ((posX <= 0 || posX >= viewport.getWidth() - fabHeight) ||
                                (posY <= viewport.getTop() + txt.getHeight() || posY >= viewport.getBottom()))
                        {

                            return true;
                        }

                        view.animate()
                                .x(event.getRawX() + dX)
                                .y(event.getRawY() + dY)
                                .setDuration(0)
                                .start();

                        break;
                    default:
                        return false;
                }
                return true;            }
        });
    }


}