package com.example.client.view.myGameStruct;

public class MyMan {
    public float row, col;
    public int type;
    public int state;
                 // 0-���ﳯǰ��ֹ�������Ϊǰ������ 1--ǰ���� 2--�Ҿ� ��
                //3--�Ҷ��� 4--�Ͼ� ��5--�϶��� 6--�󾲣�7--�󶯣�8--���ﱻը��
                //9--�����������򲻴��ڣ�
    public static int index = 0; //�����ƶ�ʱ״̬�仯0~1
    public static int  MAXINDEX = 2;
	public MyMan() {
		row = 0f;
		col = 0f;
		type = 0;
		state = 9;
	}
	public MyMan(float _row, float _col, int _type, int _state) {
		row = _row;
		col = _col;
		type = _type;
		state = _state;
	}
	public void set(float _row, float _col, int _type, int _state) {
		row = _row;
		col = _col;
		type = _type;
		state = _state;
	}
	public void set(MyMan man) {
		row = man.row;
		col = man.col;
		type = man.type;
		state = man.state;
	}
	public static void changeIndex() {
		index++;
		index = index % MAXINDEX;
	}
}
