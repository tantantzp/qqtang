package com.example.client.view.myWidgets;

import com.example.client.view.myView.GameActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * ��ť�࣬���ư�ť
 */
public class MyButton {
	float x;//ͼƬ�����ϵ�x����
	float y;//ͼƬ�����ϵ�y����
	int width;//���ⰴť�Ŀ�
	int height;//���ⰴť�ĸ�
	Bitmap onBitmap;//����ͼƬ
	Bitmap offBitmap;//̧��ͼƬ
	String info;
	boolean isOn=true;//����״̬Ϊfalse
	
	public MyButton(Bitmap onBitmap,Bitmap offBitmap, float x,float y,String info)
	{
		this.isOn = false;
		this.onBitmap = onBitmap;
		this.offBitmap = offBitmap;
		this.info = info;
		this.x = x;
		this.y = y;
		this.width = onBitmap.getWidth();
		this.height= onBitmap.getHeight();
	}
	/**
	 * ���캯��
	 */
	public void drawSelf(Canvas canvas,Paint paint)//�������ⰴť
	{
		if(isOn)
		{
			canvas.drawBitmap(onBitmap, x, y, paint);
			paint.setTextSize(15.0f);
			canvas.drawText(info,x + width/4, y + height/3, paint);
		}
		else
		{
			canvas.drawBitmap(offBitmap, x, y, paint);
			paint.setTextSize(15.0f);
			canvas.drawText(info,x + width/4, y + height/2, paint);
			
		}
	}
	/**
	 * ���ð�ť�ı�����
	 */
    public void setInfo(String _info)
    {
    	info = _info;
    }
	/**
	 * �õ��ı�
	 */
    public String getInfo(){
    	return info;
    }
	/**
	 * �Ƿ��ڼ�����£�״̬
	 */
	public boolean isOnflag()//����isOn��״̬
	{
		return isOn;
	}
	/**
	 * ���ü���״̬
	 */
	public void setOnflag(boolean flag)
	{
	    isOn = flag;
	}
	

	/**
	 * һ�����Ƿ��ھ����ڣ������߽磩
	 */
	public boolean isPointInRect(float pointx,float pointy)
	{
		if( pointx>=x&&pointx<=x+width&&
				pointy>=y&&pointy<=y+height
		  )
		  {
			  isOn = true;
			  return true;//����ڣ�����true
		  }
		isOn = false;
		return false;//���򷵻�false
	}	
}
