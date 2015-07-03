package com.example.client.view.myGameStruct;

import com.example.client.view.others.Constant;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
/**
 * ��������Ϸ������,֮����ʹ��
 */
public class MapPic {
	int row, col;
    public int mapdata[][];
	Bitmap[] backpic;//����ͼƬ

	public MapPic(int [][] data, Bitmap[] pic)
	{
		row = Constant.ROW;
		col = Constant.COL;
		
		mapdata = data;
		backpic = pic;
	}
	public void drawSelf(Canvas canvas,Paint paint)//���Ʊ���ͼƬ
	{
		for(int i = 0; i < row; i ++){
			for(int j = 0; j < col; j ++){
				if(mapdata[i][j] == 1)
				    canvas.drawBitmap(backpic[1],j * Constant.BLOCK_WIDTH, i * Constant.BLOCK_HEIGHT,  paint);
				else{
					canvas.drawBitmap(backpic[0],j * Constant.BLOCK_WIDTH, i * Constant.BLOCK_HEIGHT,  paint);
				}
			}
		}
		
	}

}