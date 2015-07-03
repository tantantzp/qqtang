package com.example.client.view.myGameStruct;

import com.example.client.view.myThread.ManFootThread;
import com.example.client.view.others.Constant;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * ��������Ϸ������,֮����ʹ��
 */
public class Man {
	public float row;
	public float col;
	MapPic map;
	//int mapData[][];
	float x;//ͼƬ��x����
	float y;//ͼƬ��y���� 
	Bitmap man[];//λͼ
	public int dir; //����0:��  1����  2��  3����
	public int i;//�������ߵ�3��ͼƬ


	public Man(Bitmap man[], MapPic map)
	{
		this.map = map;
		this.row = this.col = 0.0f;
		this.man = man;
		this.dir = 0;
		this.i=0;
		
	}
	
	//���Ƶķ���
	public void drawSelf(Canvas canvas,Paint paint)//���Ƶķ������̳и���ķ�����ͬ
	{
		y = row * Constant.BLOCK_HEIGHT;
		x = col * Constant.BLOCK_WIDTH;
		Matrix m2=new Matrix();//ͼƬ�ƶ�һ���ľ���
		m2.setTranslate(x, y);
		canvas.drawBitmap(man[i], m2, paint);//������ת���ͼƬ
	}
	
	public void manGo(int dir)//���ߵķ���,��Ĳ�����ͬ
	{
		int nrow = (int) row;
		int ncol = (int) col;
		this.dir = dir;
		if(dir == 0){
			i = 0;
			//System.out.println("row,col:" +nrow + ncol + row + col);
			if(nrow < Constant.ROW - 1 && map.mapdata[nrow + 1][ncol] < 1 )
			{
				
				ManFootThread foot = new ManFootThread(this, nrow + 1, ncol);
				foot.start();
			    //row ++;
			}

		}
		else if(dir == 1 ){
			i = 3;
			if(ncol < Constant.COL - 1 && map.mapdata[nrow][ncol + 1] < 1)
			{
				ManFootThread foot = new ManFootThread(this, nrow , ncol + 1);
				foot.start();
			    //col ++;
			}
		}
		else if(dir == 2){
			i = 6;
			if( nrow > 0 && map.mapdata[nrow - 1][ncol] < 1)
			{
				
				ManFootThread foot = new ManFootThread(this, nrow - 1, ncol);
				foot.start();
			    //row --;
			}
		}
		else if(dir == 3 ){
			i = 9;
			if(ncol > 0 && map.mapdata[nrow][ncol - 1] < 1 )
			{
				ManFootThread foot = new ManFootThread(this, nrow, ncol - 1);
				foot.start();
				//col --;
			}

		}
	}
}