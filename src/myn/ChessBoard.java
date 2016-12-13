package myn;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * 棋盘
 */
public class ChessBoard extends JPanel implements MouseListener, MouseMotionListener {
	/**
	 * By：mayining
	 */
	private static final long serialVersionUID = 4371117313678299287L;
	private final int SPACE = 44;//两条线之间的宽度,故必须为4的倍数
	private final int MARGIN = SPACE;//棋盘与边界间距
	private final int WIDTH = SPACE*16;
	private final int HEIGHT = SPACE*16;
	private final Color BACKGROUND_COLOR = Color.orange;// 背景色，程序中两处用到背景色
	private int SIZE=15;
	private Color[][] chess = new Color[SIZE][SIZE];//默认值是null
	private int gridWithMouseX = -1;//跟随鼠标的红色方框坐标
	private int gridWithMouseY = -1;
	private int lastX;// 上一次绘的红框坐标值
	private int lastY;
	private boolean cur;
	//双方棋子颜色	
	private Color player1Color=Color.black;
	private Color  player2Color=Color.white;
	private JLabel lb;
	public ChessBoard(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));// 棋盘大小
		setBackground(BACKGROUND_COLOR);	
		lb = new JLabel("\u73A9\u5BB6\u4E00\u5148\u624B\u3002");
	    add(lb);
	    lb.setFont(new Font("微软雅黑", Font.BOLD, 24));
	    lb.setBounds(6, 0, 704, 33);
		addMouseMotionListener(this);
		addMouseListener(this);
		newGame();
	}	
	
	/**
	 * 新游戏
	 */
	private void newGame(){
		for(int i = 0; i < SIZE; i++){
			Arrays.fill(chess[i],null);//重设数组
		}
		cur=true;
		repaint();
	}

	/**
	 * 游戏胜利的处理方法
	 */
	private void Win(Color color){
		JOptionPane.showMessageDialog(this, (color == player1Color?"玩家1":"玩家2")+"赢了!");
		newGame();
	}
	
	/**
	 * 判断其位置是否已有棋子
	 */
	private boolean hasChess(int x, int y){
		if(chess[x][y] != null){
			return false;
		}
		return true;
	}
	
	/**
	 * 重写paintComponent()方法
	 */
	protected void paintComponent(Graphics g){		
		int offset = 5;//偏移量
		int r = 6; // 星元和天元的半径
		int chessR = 20; //棋子的半径
		
        super.paintComponent(g);
		//画跟随鼠标的方框
		if(gridWithMouseX > 0 && gridWithMouseY > 0 ){//玩家可以下子时才出现
			lastX = gridWithMouseX;
			lastY = gridWithMouseY;
			g.setColor(Color.red);
			g.drawRect(lastX, lastY, SPACE, SPACE);
			//用背景色将中间的四条线段擦除
			g.setColor(BACKGROUND_COLOR);
			g.drawLine(lastX+SPACE/4, lastY, lastX+SPACE*3/4, lastY);
			g.drawLine(lastX+SPACE/4, lastY+SPACE, lastX+SPACE*3/4, lastY+SPACE);
			g.drawLine(lastX, lastY+SPACE/4, lastX, lastY+SPACE*3/4);
			g.drawLine(lastX+SPACE, lastY+SPACE/4, lastX+SPACE, lastY+SPACE*3/4);
		}
		else{
			//消除上次画的红框，方式是用背景色再画一个框
			g.setColor(BACKGROUND_COLOR);
			g.drawRect(lastX, lastY, SPACE, SPACE);
		}
		//先画棋盘
		g.setColor(Color.black);

		//画外围的粗框，用For画多个矩形		
		for(int i = 0; i <= offset; i++){
			g.drawRect(MARGIN-i, MARGIN-i, SPACE*14+2*i, SPACE*14+2*i);			
		}
		//画细线条，第2~14条
		for(int i = 2; i <= 14; i++){
			g.drawLine(MARGIN+(i-1)*SPACE, MARGIN, MARGIN+(i-1)*SPACE, MARGIN+14*SPACE);
			g.drawLine(MARGIN, MARGIN+(i-1)*SPACE, MARGIN+14*SPACE, MARGIN+(i-1)*SPACE);
		}
		//画九个较粗的小点
		//为方便数组存取，棋盘以左上角为(0,0);
		//星元+天元 一共九个点
		for(int i = 3; i <= 11; i = i + 4){
			for(int j = 3; j <= 11; j = j + 4){
				g.fillOval(getXY(i)-r, getXY(j)-r, 2*r, 2*r);
			}
		}
		//画棋子
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				if(chess[i][j] != null){
					g.setColor(chess[i][j]);
					g.fillOval(getXY(i)-chessR, getXY(j)-chessR, chessR*2, chessR*2);
				}
			}
		}
	}
	
	/**
	 * 把棋盘坐标转化成实际坐标
	 */
	private int  getXY(int xy){
		return MARGIN + xy * SPACE;
	}
	
	/**
	 * 根据鼠标位置定出红框坐标
	 * 同样也就是该位置的鼠标下子的棋盘坐标
	 */
	private int getGridXY(int xy){
		return (xy - (MARGIN - SPACE/2) ) / SPACE;
	}
	

	/**
	 * 下面实现所有的事件监听器
	 * 鼠标移动时，有红框跟随
	 */
	public void mouseMoved(MouseEvent e) {
		//据图计算出来的偏移公式。鼠标在这范围内，选择框的位置不变
		int x = getGridXY(e.getX());//同样也是鼠标对应的棋盘坐标
		int y = getGridXY(e.getY());
		x = x<0?0:x; //将X，Y控制在范围内
		x = x>14?14:x;
		y = y<0?0:y;
		y= y>14?14:y;
		//如果没有子，则出现红色方框跟随鼠标，如果有子，则红色方框消失
		if(!hasChess(x, y) || e.getX() >= MARGIN*3/2 + SPACE*14 || e.getX() <= MARGIN/2 
				|| e.getY() >= MARGIN*3/2 + SPACE*14 || e.getY() <= MARGIN/2){
			gridWithMouseX = -1;
			gridWithMouseY = -1;
		}
		else{
			gridWithMouseX = getXY(x) - SPACE/2;
			gridWithMouseY = getXY(y) - SPACE/2;	
		}
		repaint();
	}
	//下棋
	public void mouseClicked(MouseEvent e) {
		
			int x = getGridXY(e.getX()); //把鼠标坐标转化成棋盘坐标
			int y = getGridXY(e.getY());
			if (hasChess(x,y)){
				if(cur){
					chess[x][y] = player1Color;
					repaint();
					if(CheckWin.check(chess, x, y, player1Color))
						Win(player1Color);
				}
				else{
					chess[x][y] = player2Color;
					repaint();
					if(CheckWin.check(chess, x, y, player2Color))
						Win(player2Color);
				}
				cur=!cur;
			}
	}
	public void mousePressed(MouseEvent e) {
		this.lb.setText("请玩家"+(!cur?"1":"2")+"落子。");
	}
	/**
	 * 以下方法仅为接口
	 */
	public void mouseDragged(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
