import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class base extends JFrame{
	final int MAX_OBJECT = 100;
	base(){
		Dimension dim = new Dimension(1500,1000);
		setPreferredSize(dim);
		setLocation(10,0);
		setTitle("base");
		setVisible(true);
		setDefaultCloseOperation(base.EXIT_ON_CLOSE);
		
		// 메뉴바구현
		JMenuBar MenuBar = new JMenuBar();
			
		JMenu filemenu = new JMenu("파일(F)");
		filemenu.setMnemonic('F');
			
		JMenuItem newfile = new JMenuItem("새로 만들기(N)");
		newfile.setMnemonic('N');
		filemenu.add(newfile);
		newfile.addActionListener(new MenuListener());
		
		JMenuItem open = new JMenuItem("열기(O)");
		open.setMnemonic('O');
		filemenu.add(open);
		open.addActionListener(new MenuListener());
		
		JMenuItem save = new JMenuItem("저장(S)");
		save.setMnemonic('S');
		filemenu.add(save);
		save.addActionListener(new MenuListener());
		
		JMenuItem saveName = new JMenuItem("다른 이름으로 저장(A)");
		saveName.setMnemonic('A');
		filemenu.add(saveName);
		saveName.addActionListener(new MenuListener());
		
		JMenuItem newJava = new JMenuItem(".java파일 생성(J)");
		newJava.setMnemonic('J');
		filemenu.add(newJava);
		newJava.addActionListener(new MenuListener());
		
		JMenuItem close = new JMenuItem("닫기(C)");
		close.setMnemonic('C');
		filemenu.add(close);
		MenuBar.add(filemenu);
		close.addActionListener(new MenuListener());
		
		setJMenuBar(MenuBar);
		
		// 툴바구현
		JToolBar ToolBar = new JToolBar();
		ToolBar.add(new JButton("새로 만들기"));
		ToolBar.add(new JButton("열기"));
		ToolBar.add(new JButton("저장"));
		ToolBar.add(new JButton("다른 이름으로 저장"));
		ToolBar.add(new JButton(".java 파일 생성"));
		ToolBar.add(new JButton("닫기"));
		
		
		// 속성 패널구현
		JPanel PanelAttribute = new JPanel();
		PanelAttribute.setLayout(new BoxLayout(PanelAttribute, BoxLayout.Y_AXIS));
		
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,20)));
		Box box1 = Box.createHorizontalBox();
		PanelAttribute.add(box1);
		JLabel LstartX = new JLabel("시작 x,y 좌표 : ");
		box1.add(LstartX);
		JTextField startX = new JTextField(10);
		box1.add(startX);
		JTextField startY = new JTextField(10);
		box1.add(startY);
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,20)));
		
		Box box2 = Box.createHorizontalBox();
		PanelAttribute.add(box2);
		box2.add(new JLabel("너비 :" ));
		JTextField wide = new JTextField("");
		box2.add(wide);
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,20)));
		
		Box box3 = Box.createHorizontalBox();
		PanelAttribute.add(box3);
		box3.add(new JLabel("높이 :" ));
		JTextField height = new JTextField("");
		box3.add(height);
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,20)));
		
		Box box4 = Box.createHorizontalBox();
		PanelAttribute.add(box4);
		box4.add(new JLabel("텍스트 속성값 : "));
		JTextField txtAttr = new JTextField("");
		box4.add(txtAttr);
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,20)));
		
		Box box5 = Box.createHorizontalBox();
		PanelAttribute.add(box5);
		box5.add(new JLabel("타입 : "));
		JComboBox compType = new JComboBox();
		compType.addItem("");
		box5.add(compType);
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,20)));
		
		Box box6 = Box.createHorizontalBox();
		PanelAttribute.add(box6);
		box6.add(new JLabel("변수명 : "));
		JTextField compName = new JTextField("");
		box6.add(compName);
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,20)));
		
		JButton apply = new JButton("적용");
		PanelAttribute.add(apply);
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,100)));
		
		// editor 패널 구현
		EditorPanel PanelEditor = new EditorPanel();
		PanelEditor.add(new JLabel("Editor Pane"));
		
		//frame 전체를 BorderLayout으로 설정
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(ToolBar, BorderLayout.NORTH);
		contentPane.add(PanelAttribute,BorderLayout.WEST);
		contentPane.add(PanelEditor,BorderLayout.CENTER);
		pack();
	}
	
	private class EditorPanel extends JPanel{ // editor 패널 동작 구현
		int x[] = new int[MAX_OBJECT];
		int y[] = new int[MAX_OBJECT];
		int count=0;
		EditorPanel(){
			addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {

				}

				@Override
				public void mouseExited(MouseEvent arg0) {

				}

				@Override
				public void mousePressed(MouseEvent e) {
					x[count] = e.getX();
					y[count] = e.getY();
					count++;
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {

				}
				
				@Override
				public void mouseDragged(MouseEvent arg0) {
					
				}
				
				@Override
				public void mouseMoved(MouseEvent arg0) {
					
				}
			});
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			repaint();
			for(int i=0; i<count; i++)
				g.drawRect(x[i], y[i], 50, 50);
		}
	}
	
	
	private class MenuListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == "닫기(C)")
				System.exit(1);
		}
	}
	private class ToolbarListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if(b.getText().equals("닫기"))
				System.exit(1);
		}
		
	}
	private class AttrListener1 implements TextListener{
		@Override
		public void textValueChanged(TextEvent arg0) {
			
		}
	}
	private class AttrListener2 implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent arg0) {

		}
		
	}
	
	public static void main(String[] args){
		new base();	
	}
}
