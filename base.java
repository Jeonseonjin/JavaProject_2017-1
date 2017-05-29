import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyButton extends JButton{
	MyButton(String s){
		super(s);
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
				setBackground(Color.BLACK);
			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
			}
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
			}
		});
	}
}

public class base extends JFrame{
	final int MAX_OBJECT = 100;
	JPanel PanelAttribute;
	EditorPanel PanelEditor;
	JTextField startX;
	MyButton button[] = new MyButton[MAX_OBJECT];
	
	base(){
		Dimension dim = new Dimension(1500,1000);
		setPreferredSize(dim);
		setLocation(10,0);
		setTitle("base");
		setVisible(true);
		setDefaultCloseOperation(base.EXIT_ON_CLOSE);
		
		// �޴��ٱ���
		JMenuBar MenuBar = new JMenuBar();
			
		JMenu filemenu = new JMenu("����(F)");
		filemenu.setMnemonic('F');
			
		JMenuItem newfile = new JMenuItem("���� �����(N)");
		newfile.setMnemonic('N');
		filemenu.add(newfile);
		newfile.addActionListener(new MenuListener());
		
		JMenuItem open = new JMenuItem("����(O)");
		open.setMnemonic('O');
		filemenu.add(open);
		open.addActionListener(new MenuListener());
		
		JMenuItem save = new JMenuItem("����(S)");
		save.setMnemonic('S');
		filemenu.add(save);
		save.addActionListener(new MenuListener());
		
		JMenuItem saveName = new JMenuItem("�ٸ� �̸����� ����(A)");
		saveName.setMnemonic('A');
		filemenu.add(saveName);
		saveName.addActionListener(new MenuListener());
		
		JMenuItem newJava = new JMenuItem(".java���� ����(J)");
		newJava.setMnemonic('J');
		filemenu.add(newJava);
		newJava.addActionListener(new MenuListener());
		
		JMenuItem close = new JMenuItem("�ݱ�(C)");
		close.setMnemonic('C');
		filemenu.add(close);
		MenuBar.add(filemenu);
		close.addActionListener(new MenuListener());
		
		setJMenuBar(MenuBar);
		
		// ���ٱ���
		JToolBar ToolBar = new JToolBar();
		ToolBar.add(new JButton("���� �����"));
		ToolBar.add(new JButton("����"));
		ToolBar.add(new JButton("����"));
		ToolBar.add(new JButton("�ٸ� �̸����� ����"));
		ToolBar.add(new JButton(".java ���� ����"));
		ToolBar.add(new JButton("�ݱ�"));
		
		
		// �Ӽ� �гα���
		PanelAttribute = new JPanel();
		PanelAttribute.setLayout(new BoxLayout(PanelAttribute, BoxLayout.Y_AXIS));
		
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,20)));
		Box box1 = Box.createHorizontalBox();
		PanelAttribute.add(box1);
		JLabel LstartX = new JLabel("���� x,y ��ǥ : ");
		box1.add(LstartX);
		LstartX.setSize(15, 10);
		startX = new JTextField(5);
		box1.add(startX);
		JTextField startY = new JTextField(5);
		box1.add(startY);
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,20)));
		
		Box box2 = Box.createHorizontalBox();
		PanelAttribute.add(box2);
		box2.add(new JLabel("�ʺ� :" ));
		JTextField wide = new JTextField(5);
		box2.add(wide);
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,20)));
		
		Box box3 = Box.createHorizontalBox();
		PanelAttribute.add(box3);
		box3.add(new JLabel("���� :" ));
		JTextField height = new JTextField(5);
		box3.add(height);
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,20)));
		
		Box box4 = Box.createHorizontalBox();
		PanelAttribute.add(box4);
		box4.add(new JLabel("�ؽ�Ʈ �Ӽ��� : "));
		JTextField txtAttr = new JTextField(15);
		box4.add(txtAttr);
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,20)));
		
		Box box5 = Box.createHorizontalBox();
		PanelAttribute.add(box5);
		box5.add(new JLabel("Ÿ�� : "));
		JComboBox compType = new JComboBox();
		compType.addItem("");
		box5.add(compType);
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,20)));
		
		Box box6 = Box.createHorizontalBox();
		PanelAttribute.add(box6);
		box6.add(new JLabel("������ : "));
		JTextField compName = new JTextField(15);
		box6.add(compName);
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,20)));
		
		JButton apply = new JButton("����");
		PanelAttribute.add(apply);
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,100)));
		
		// editor �г� ����
		PanelEditor = new EditorPanel();
		PanelEditor.add(new JLabel("Editor Pane"));
		
		//frame ��ü�� BorderLayout���� ����
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(ToolBar, BorderLayout.NORTH);
		contentPane.add(PanelAttribute,BorderLayout.WEST);
		contentPane.add(PanelEditor,BorderLayout.CENTER);
		pack();
	}
	
	private class EditorPanel extends JPanel{ // editor �г� ���� ����
		int start_x[] = new int[MAX_OBJECT];
		int start_y[] = new int[MAX_OBJECT];
		int end_x[] = new int[MAX_OBJECT];
		int end_y[] = new int[MAX_OBJECT];
		int width[] = new int[MAX_OBJECT];
		int height[] = new int[MAX_OBJECT];
		int assi_x, assi_y;
		int assi_count = 0;
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
					start_x[count] = e.getX();
					start_y[count] = e.getY();
					startX.setText(Integer.toString(start_x[count]));
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					end_x[count] = e.getX();
					end_y[count] = e.getY();
					width[count] = end_x[count] - start_x[count];
					height[count] = end_y[count] - start_y[count];
					if((width[count] > 0) && (height[count] > 0))
						count++;
					repaint();
				}
				
				@Override
				public void mouseDragged(MouseEvent e) {
				}
				
				@Override
				public void mouseMoved(MouseEvent arg0) {
				}
			});
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			for(int i=0; i<count; i++){
				button[i] = new MyButton("��ư"+i);
				button[i].setSize(width[i],height[i]);
				button[i].setLocation(start_x[i],start_y[i]);
				PanelEditor.add(button[i]);
			}
		}
	}
	
	
	private class MenuListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == "�ݱ�(C)")
				System.exit(1);
		}
	}
	private class ToolbarListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if(b.getText().equals("�ݱ�"))
				System.exit(1);
		}
		
	}
	private class AttrListener1 implements TextListener{
		@Override
		public void textValueChanged(TextEvent e) {
			if(e.getSource() == startX){
			}
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
