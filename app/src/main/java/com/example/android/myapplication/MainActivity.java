package com.example.android.myapplication;

import android.graphics.Point;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView score,timer;
    private ImageButton ball;
    private Button start;
    private Handler mHandler=new Handler();
    private Timer mTimer=new Timer();
    private boolean pause_flg=false;
    private int width;
    private int height;
    private float arrowUpY;
    private float arrowUpX;
    private int val;
    private int sum=0;
    private CountDownTimer count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = (TextView) findViewById(R.id.score);
        ball = (ImageButton) findViewById(R.id.ball);
        timer=(TextView) findViewById(R.id.timer);
        start=(Button) findViewById(R.id.start);
        Point size=new Point();
        width=size.x;
        height=size.y;

        ball.setY(-80.0f);
        ball.setX(-80.0f);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
                mTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                changePos();
                            }
                        });
                    }
                },0,20);
            }
        });
    }

    private Point getPointOfView(View view) {
        int[] location=new int[2];
        view.getLocationInWindow(location);
        return new Point(location[0],location[1]);
    }

    public void changePos() {

            arrowUpY-=10;
            if(ball.getY() + ball.getHeight()<0){
                //arrowUpX=(float)Math.floor(Math.random()*(width-ball.getWidth()));
                arrowUpY=height+1100.0f;
            }
            ball.setY(arrowUpY);

    }
    public void pauseClick(View view){
        if(pause_flg==false){
            pause_flg=true;
            mTimer.cancel();
            mTimer= null;
            if (mTimer == null) {
               add();
;           }
        }else{
            pause_flg=false;
            mTimer=new Timer();
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            },0,20);
        }
    }
    public void stop(){
        if(pause_flg==false) {
            pause_flg = true;
            mTimer.cancel();
        }
    }
    public void add(){
        Point points=getPointOfView(ball);
        Toast toast=new Toast(this);
        //toast.makeText(this,"view point x,y (" + points.x + ", " + (points.y+10) + ")",Toast.LENGTH_SHORT).show();
        val=points.y+10;
        if(val>=0 && val<=200){
            sum += 10;
            score.setText("Score: "+sum);
        }else if(val>=201 && val<=300){
            sum += 20;
            score.setText("Score: "+sum);
        } else if(val>=301 && val<=400){
            sum += 30;
            score.setText("Score: "+sum);
        }else if(val>=401 && val<=500){
            sum += 40;
            score.setText("Score: "+sum);
        }else if(val>=501 && val<=600){
            sum += 50;
            score.setText("Score: "+sum);
        }else if(val>=601 && val<=700){
            sum += 60;
            score.setText("Score: "+sum);
        }else if(val>=701 && val<=800){
            sum += 70;
            score.setText("Score: "+sum);
        }else if(val>=801 && val<=900){
            sum += 80;
            score.setText("Score: "+sum);
        }else if(val>=901 && val<=999){
            sum += 90;
            score.setText("Score: "+sum);
        }else if(val>=1000 && val<=1500){
            sum += 100;
            score.setText("Score: "+sum);
        }
    }
    private void start(){
        timer.setText("Timer: 10");
        count = new CountDownTimer(10 * 1000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText("Timer: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                stop();
                if(sum>=350){
                    timer.setText("You Won");
                    score.setVisibility(View.INVISIBLE);
                }else{
                    timer.setText("You Failed");
                    score.setVisibility(View.INVISIBLE);
                }
            }
        };
        count.start();
    }
    /*private void stop(){
        if(count !=null){
            count.cancel();
            count=null;
        }
    }*/
}
