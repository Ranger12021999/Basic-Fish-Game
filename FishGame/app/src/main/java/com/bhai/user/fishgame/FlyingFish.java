package com.bhai.user.fishgame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class FlyingFish extends View {
    private Bitmap fish[] = new Bitmap[2];
    private Bitmap background;
    private Paint scorepaint=new Paint();
    private Bitmap life[] =new Bitmap[2];

    private int fishX=20;
    private int fishY,fishspeed;
    private int canvasWidth,canvasHeight;

    private int yellowX,yellowY,yellowSpeed=16;
    Paint yellowPaint=new Paint();

    private int greenX,greenY,greenSpeed=20;
    Paint greenPaint=new Paint();

    private int redX,redY,redSpeed=15;
    Paint redPaint=new Paint();

    private int score,lifeCounter;

    private boolean touch=false;

    public FlyingFish(Context context) {
        super(context);
	 fish[0]= BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish[1]= BitmapFactory.decodeResource(getResources(),R.drawable.fish2);

        background= BitmapFactory.decodeResource(getResources(),R.drawable.background);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenPaint.setAntiAlias(false);
        greenPaint.setColor(Color.GREEN);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        scorepaint.setColor(Color.WHITE);
        scorepaint.setTextSize(40);
        scorepaint.setAntiAlias(true);
        scorepaint.setTypeface(Typeface.DEFAULT_BOLD);

        life[0]=BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1]=BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);

        fishY=550;
        score=0;
        lifeCounter=3;
       
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasWidth=canvas.getWidth();
        canvasHeight=canvas.getHeight();

        canvas.drawBitmap(background,0,0,null);

        int minfishY=fish[0].getHeight();
        int maxfishY=canvasHeight-fish[0].getHeight()*3;
        fishY=fishY+fishspeed;

        if(fishY<minfishY)
        {
            fishY=minfishY;
        }
        if(fishY>maxfishY)
        {
            fishY=maxfishY;
        }
        fishspeed=fishspeed+2;

        if(touch)
        {
            canvas.drawBitmap(fish[1],fishX,fishY,null);
            touch=false;
        }

        else{
            canvas.drawBitmap(fish[0],fishX,fishY,null);
        }



        yellowX=yellowX-yellowSpeed;

        if(hitBallChecker(yellowX,yellowY))
        {
            score+= 10;
            yellowX= -100;
        }

        if(yellowX<0){

            yellowX=canvasWidth+21;
            yellowY=(int)Math.floor(Math .random()*(maxfishY-minfishY))+minfishY;
        }
        canvas.drawCircle(yellowX, yellowY,25, yellowPaint);

        greenX=greenX-greenSpeed;

        if(hitBallChecker(greenX,greenY))
        {
            score+= 20;
            greenX= -100;
        }

        if(greenX<0){

            greenX=canvasWidth+21;
            greenY=(int)Math.floor(Math .random()*(maxfishY-minfishY))+minfishY;
        }
        canvas.drawCircle(greenX, greenY,20, greenPaint);

        redX=redX-redSpeed;

        if(hitBallChecker(redX,redY))
        {
            redX= -100;
            lifeCounter--;
            if(lifeCounter==0)
            {
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), GameOverActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("abc",score);
                getContext().startActivity(intent);
            }
        }

        if(redX<0){

            redX=canvasWidth+21;
            redY=(int)Math.floor(Math .random()*(maxfishY-minfishY))+minfishY;
        }
        canvas.drawCircle(redX, redY,25, redPaint);

        canvas.drawText("Score: "+score,20,60,scorepaint);
        if(lifeCounter==3)
        {
            canvas.drawBitmap(life[0],400,10,null);
            canvas.drawBitmap(life[0],480,10,null);
            canvas.drawBitmap(life[0],560,10,null);
        }
        if(lifeCounter==2)
        {
            canvas.drawBitmap(life[0],400,10,null);
            canvas.drawBitmap(life[0],480,10,null);
            canvas.drawBitmap(life[1],560,10,null);
        }
        if(lifeCounter==1)
        {
            canvas.drawBitmap(life[0],400,10,null);
            canvas.drawBitmap(life[1],480,10,null);
            canvas.drawBitmap(life[1],560,10,null);
        }
        if(lifeCounter==0)
        {
            canvas.drawBitmap(life[1],400,10,null);
            canvas.drawBitmap(life[1],480,10,null);
            canvas.drawBitmap(life[1],560,10,null);
        }
    }

    public boolean hitBallChecker(int x ,int y)
    {
        if(fishX<x && x< (fishX+fish[0].getWidth()) && fishY<y && y<(fishY+fish[0].getHeight()))
        {
            return  true;
        }
        else
        {
             return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            touch=true;
            fishspeed=-22;
        }
        return true;
    }
}
