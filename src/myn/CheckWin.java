package myn;

import java.awt.Color;

/**
 * 检查是否获胜
 */
public class CheckWin {
	public static boolean check(Color[][] chess, int x, int y, Color color){
		int count = 0; //一条直线上的同色棋子个数
		int tempX = x;//保存初始值
		int tempY = y;
		//先检查横线
		while(contains(chess, x-1, y, color)){ //横线，先左后右
			count++;
			x--;
		}
		x = tempX;
		y = tempY;
		while(contains(chess, x+1, y, color)){
			count++;
			x++;
		}
		if(count + 1 >= 5){ // 算左右数时，未计入中间
			return true;
		}
		//再检查竖线
		count = 0;//重新开始检查
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
		//检查左上到右下的斜线
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
		//检查从左下到右上
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
	 * 判断指定位置是否有指定颜色的棋子
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
