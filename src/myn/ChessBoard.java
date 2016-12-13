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
 * ����
 */
public class ChessBoard extends JPanel implements MouseListener, MouseMotionListener {
	/**
	 * By��mayining
	 */
	private static final long serialVersionUID = 4371117313678299287L;
	private final int SPACE = 44;//������֮��Ŀ��,�ʱ���Ϊ4�ı���
	private final int MARGIN = SPACE;//������߽���
	private final int WIDTH = SPACE*16;
	private final int HEIGHT = SPACE*16;
	private final Color BACKGROUND_COLOR = Color.orange;// ����ɫ�������������õ�����ɫ
	private int SIZE=15;
	private Color[][] chess = new Color[SIZE][SIZE];//Ĭ��ֵ��null
	private int gridWithMouseX = -1;//�������ĺ�ɫ��������
	private int gridWithMouseY = -1;
	private int lastX;// ��һ�λ�ĺ������ֵ
	private int lastY;
	private boolean cur;
	//˫��������ɫ	
	private Color player1Color=Color.black;
	private Color  player2Color=Color.white;
	private JLabel lb;
	public ChessBoard(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));// ���̴�С
		setBackground(BACKGROUND_COLOR);	
		lb = new JLabel("\u73A9\u5BB6\u4E00\u5148\u624B\u3002");
	    add(lb);
	    lb.setFont(new Font("΢���ź�", Font.BOLD, 24));
	    lb.setBounds(6, 0, 704, 33);
		addMouseMotionListener(this);
		addMouseListener(this);
		newGame();
	}	
	
	/**
	 * ����Ϸ
	 */
	private void newGame(){
		for(int i = 0; i < SIZE; i++){
			Arrays.fill(chess[i],null);//��������
		}
		cur=true;
		repaint();
	}

	/**
	 * ��Ϸʤ���Ĵ�����
	 */
	private void Win(Color color){
		JOptionPane.showMessageDialog(this, (color == player1Color?"���1":"���2")+"Ӯ��!");
		newGame();
	}
	
	/**
	 * �ж���λ���Ƿ���������
	 */
	private boolean hasChess(int x, int y){
		if(chess[x][y] != null){
			return false;
		}
		return true;
	}
	
	/**
	 * ��дpaintComponent()����
	 */
	protected void paintComponent(Graphics g){		
		int offset = 5;//ƫ����
		int r = 6; // ��Ԫ����Ԫ�İ뾶
		int chessR = 20; //���ӵİ뾶
		
        super.paintComponent(g);
		//���������ķ���
		if(gridWithMouseX > 0 && gridWithMouseY > 0 ){//��ҿ�������ʱ�ų���
			lastX = gridWithMouseX;
			lastY = gridWithMouseY;
			g.setColor(Color.red);
			g.drawRect(lastX, lastY, SPACE, SPACE);
			//�ñ���ɫ���м�������߶β���
			g.setColor(BACKGROUND_COLOR);
			g.drawLine(lastX+SPACE/4, lastY, lastX+SPACE*3/4, lastY);
			g.drawLine(lastX+SPACE/4, lastY+SPACE, lastX+SPACE*3/4, lastY+SPACE);
			g.drawLine(lastX, lastY+SPACE/4, lastX, lastY+SPACE*3/4);
			g.drawLine(lastX+SPACE, lastY+SPACE/4, lastX+SPACE, lastY+SPACE*3/4);
		}
		else{
			//�����ϴλ��ĺ�򣬷�ʽ���ñ���ɫ�ٻ�һ����
			g.setColor(BACKGROUND_COLOR);
			g.drawRect(lastX, lastY, SPACE, SPACE);
		}
		//�Ȼ�����
		g.setColor(Color.black);

		//����Χ�Ĵֿ���For���������		
		for(int i = 0; i <= offset; i++){
			g.drawRect(MARGIN-i, MARGIN-i, SPACE*14+2*i, SPACE*14+2*i);			
		}
		//��ϸ��������2~14��
		for(int i = 2; i <= 14; i++){
			g.drawLine(MARGIN+(i-1)*SPACE, MARGIN, MARGIN+(i-1)*SPACE, MARGIN+14*SPACE);
			g.drawLine(MARGIN, MARGIN+(i-1)*SPACE, MARGIN+14*SPACE, MARGIN+(i-1)*SPACE);
		}
		//���Ÿ��ϴֵ�С��
		//Ϊ���������ȡ�����������Ͻ�Ϊ(0,0);
		//��Ԫ+��Ԫ һ���Ÿ���
		for(int i = 3; i <= 11; i = i + 4){
			for(int j = 3; j <= 11; j = j + 4){
				g.fillOval(getXY(i)-r, getXY(j)-r, 2*r, 2*r);
			}
		}
		//������
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
	 * ����������ת����ʵ������
	 */
	private int  getXY(int xy){
		return MARGIN + xy * SPACE;
	}
	
	/**
	 * �������λ�ö����������
	 * ͬ��Ҳ���Ǹ�λ�õ�������ӵ���������
	 */
	private int getGridXY(int xy){
		return (xy - (MARGIN - SPACE/2) ) / SPACE;
	}
	

	/**
	 * ����ʵ�����е��¼�������
	 * ����ƶ�ʱ���к�����
	 */
	public void mouseMoved(MouseEvent e) {
		//��ͼ���������ƫ�ƹ�ʽ��������ⷶΧ�ڣ�ѡ����λ�ò���
		int x = getGridXY(e.getX());//ͬ��Ҳ������Ӧ����������
		int y = getGridXY(e.getY());
		x = x<0?0:x; //��X��Y�����ڷ�Χ��
		x = x>14?14:x;
		y = y<0?0:y;
		y= y>14?14:y;
		//���û���ӣ�����ֺ�ɫ���������꣬������ӣ����ɫ������ʧ
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
	//����
	public void mouseClicked(MouseEvent e) {
		
			int x = getGridXY(e.getX()); //���������ת������������
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
		this.lb.setText("�����"+(!cur?"1":"2")+"���ӡ�");
	}
	/**
	 * ���·�����Ϊ�ӿ�
	 */
	public void mouseDragged(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
