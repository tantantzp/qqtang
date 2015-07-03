package com.example.client.view.myWidgets;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;


/**
 * �����,���������Ϣ,�������
 */
public class MyPerson {
	/**
	 * �����Ϣ���Ͻ�λ��
	 */
	float x;
	float y;
	/**
	 * �����Ϣ���
	 */
	int width;
	int height;
	/**
	 * ���λͼ
	 */
	Bitmap onBitmap;
	Bitmap offBitmap;
	String info;
	boolean isOn=true;
	/**
	 * �����Ϣ
	 */
	public class PersonInfo{
		int id;
	};

	PersonInfo pinfo;
	/**
	 * ���캯��
	 */
	public MyPerson(Bitmap onBitmap,Bitmap offBitmap, float x,float y)
	{
		this.isOn=false;
		this.info = "player";
		this.onBitmap=onBitmap;
		this.offBitmap=offBitmap;
		this.x=x;
		this.y=y;
		this.width=offBitmap.getWidth();
		this.height=offBitmap.getHeight();
	}
	public void SetInfo(String name, String ready){
		this.info = name + '\n' + ready;
	}
	/**
	 * ���Ʒ���
	 */
	public void drawSelf(Canvas canvas,Paint paint)
	{
		if(isOn)
		{
			canvas.drawBitmap(onBitmap, x, y, paint);
			canvas.drawText(info, x + width/5, y + 2*height/5, paint);
		}
		else
		{
			canvas.drawBitmap(offBitmap, x, y, paint);
			canvas.drawText(info, x + width/5, y + 2*height/5, paint);
		}
	}
	public void setswitch()
	{
		isOn=!isOn;
	}
	public boolean isOnflag()
	{
		return isOn;
	}

	/**
	 * һ�����Ƿ��ھ����ڣ������߽磩
	 */
	
	public boolean isPointInRect(float pointx,float pointy)
	{
		if(
				pointx>=x&&pointx<=x+width&&
				pointy>=y&&pointy<=y+height
		  )
		  {
			  return true;
		  }
		return false;
	}
}
