package myn;
/**
 * 游戏窗口
 */
import java.awt.Toolkit;
import javax.swing.JFrame;
public class GameFrame extends JFrame{
    /**
	 * By:mayining
	 */
	private static final long serialVersionUID = 4408876078733328049L;

	public GameFrame(){
        super("五子棋");//窗口标题
        //添加棋盘
        ChessBoard chessBoard = new ChessBoard();
        add(chessBoard);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();         
        //将窗口设置为正中显示
        //Toolkit是抽象类，只能用getDefaultToolkit()方法来获取实例。
        int screenSizeX = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int screenSizeY = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int fSizeX = (int)getSize().getWidth();
        int fSizeY = (int)getSize().getHeight();
        setResizable(false);
        setBounds((screenSizeX-fSizeX)/2, (screenSizeY-fSizeY)/2, fSizeX,fSizeY );
        setVisible(true);
    }    
}