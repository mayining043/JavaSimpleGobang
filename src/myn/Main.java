package myn;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("������");
		frame.setBackground(SystemColor.desktop);
		frame.setAutoRequestFocus(false);
        //�������
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.setBackground(new Color(255, 204, 102));
        frame.getContentPane().add(chessBoard, BorderLayout.SOUTH);
        chessBoard.setLayout(null);
        
    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();         
        //����������Ϊ������ʾ
        //Toolkit�ǳ����ֻ࣬����getDefaultToolkit()��������ȡʵ����
        int screenSizeX = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int screenSizeY = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int fSizeX = (int)frame.getWidth();
        int fSizeY = (int)frame.getHeight();
        frame.setResizable(false);
        frame.setBounds((screenSizeX-fSizeX)/2, (screenSizeY-fSizeY)/2, 708,755 );
        frame. setVisible(true);
	}

}
