package myn;

import java.awt.Color;

/**
 * ����Ƿ��ʤ
 */
public class CheckWin {
	public static boolean check(Color[][] chess, int x, int y, Color color){
		int count = 0; //һ��ֱ���ϵ�ͬɫ���Ӹ���
		int tempX = x;//�����ʼֵ
		int tempY = y;
		//�ȼ�����
		while(contains(chess, x-1, y, color)){ //���ߣ��������
			count++;
			x--;
		}
		x = tempX;
		y = tempY;
		while(contains(chess, x+1, y, color)){
			count++;
			x++;
		}
		if(count + 1 >= 5){ // ��������ʱ��δ�����м�
			return true;
		}
		//�ټ������
		count = 0;//���¿�ʼ���
		x = tempX;
		y = tempY;
		while(contains(chess, x, y-1, color)){
			count++;
			y--;
		}
		x = tempX;
		y = tempY;
		while(contains(chess, x, y+1, color)){
			count++;
			y++;
		}
		if(count + 1 >= 5){
			return true;
		}
		//������ϵ����µ�б��
		count = 0;
		x = tempX;
		y = tempY;
		while(contains(chess, x-1, y-1, color)){
			count++;
			x -= 1;
			y -= 1;
		}
		x = tempX;
		y = tempY;
		while(contains(chess, x+1, y+1, color)){
			count++;
			x += 1;
			y += 1;
		}
		if(count + 1 == 5){
			return true;
		}
		//�������µ�����
		count = 0;
		x = tempX;
		y = tempY;
		while(contains(chess, x-1, y+1, color)){
			count++;
			x -= 1;
			y += 1;
		}
		x = tempX;
		y = tempY;
		while(contains(chess, x+1, y-1, color)){
			count++;
			x += 1;
			y -= 1;
		}
		if (count + 1 == 5) {
			return true;			
		}
		return false;		
	}
	
	/**
	 * �ж�ָ��λ���Ƿ���ָ����ɫ������
	 */
	private static boolean contains(Color[][] chess, int x, int y, Color color){
		if(x < 0 || x > 14 || y < 0 || y > 14){
			return false;
		}
		else if (chess[x][y] == color){
			return true;
		}
		return false;
	}
}
