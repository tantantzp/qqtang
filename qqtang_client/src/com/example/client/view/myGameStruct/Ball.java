package com.example.client.view.myGameStruct;

import com.example.client.view.myThread.BallExplodeThread;
import com.example.client.view.myThread.BallThread;
import com.example.client.view.others.Constant;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
/**
 * ��������Ϸ������,֮����ʹ��
 */
public class Ball {
	public MapPic map;
	public int row, col;
	public float x;//ͼƬ��x����
	public float y;//ͼƬ��y���� 
	Bitmap ball[];//λͼ
	BallThread ballThread;
	BallExplodeThread explodeThread;
	public boolean exist;
	public int i;
	public int count = 0;

	public Ball(MapPic map, Bitmap ball[],int row, int col)
	{
		this.ball = ball;
		this.map = map;
		this.row = row;
		this.col = col;
        map.mapdata[row][col] = 2;
        i = 0;
        exist = true;

	}
	
	//���Ƶķ���
	public void drawSelf(Canvas canvas,Paint paint)//���Ƶķ������̳и���ķ�����ͬ
	{
		if(exist){
			y = row * Constant.BLOCK_HEIGHT;
			x = col * Constant.BLOCK_WIDTH;
			Matrix m2=new Matrix();//ͼƬ�ƶ�һ���ľ���
			m2.setTranslate(x, y);
			canvas.drawBitmap(ball[i], m2, paint);//������ת���ͼƬ	
		}
	}
    public void time(){
  
        ballThread = new BallThread(this);
	    ballThread.start();
    }
	public void explode(){
		explodeThread = new BallExplodeThread(this);
		explodeThread.start();
		
	}
	
}