import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class base extends JFrame{
	JLabel la;
	base(){
		Dimension dim = new Dimension(1000,1000);
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
		JPanel PanelAttribute = new JPanel();
		PanelAttribute.setLayout(new GridLayout(7,2,0,5));
		
		PanelAttribute.add(new JLabel("���� x ��ǥ : "));
		JTextField startX = new JTextField("");
		PanelAttribute.add(startX);
		
		PanelAttribute.add(new JLabel("���� y ��ǥ : "));
		JTextField startY = new JTextField("");
		PanelAttribute.add(startY);
		
		PanelAttribute.add(new JLabel("�ʺ� :" ));
		JTextField wide = new JTextField("");
		PanelAttribute.add(wide);
		
		PanelAttribute.add(new JLabel("���� :" ));
		JTextField height = new JTextField("");
		PanelAttribute.add(height);
		
		PanelAttribute.add(new JLabel("�ؽ�Ʈ �Ӽ��� : "));
		JTextField txtAttr = new JTextField("");
		PanelAttribute.add(txtAttr);
		
		PanelAttribute.add(new JLabel("Ÿ�� : "));
		JComboBox compType = new JComboBox();
		compType.addItem("");
		PanelAttribute.add(compType);
		
		PanelAttribute.add(new JLabel("������ : "));
		JTextField compName = new JTextField("");
		PanelAttribute.add(compName);
		
		// editor �г� ����
		JPanel PanelEditor = new JPanel();
		la = new JLabel("Editor Pane");
		PanelEditor.add(la);
		PanelEditor.addMouseListener(new EditorListener());
		
		//frame ��ü�� BorderLayout���� ����
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(ToolBar, BorderLayout.NORTH);
		contentPane.add(PanelAttribute,BorderLayout.WEST);
		contentPane.add(PanelEditor,BorderLayout.CENTER);
		pack();
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
		public void textValueChanged(TextEvent arg0) {
			
		}
	}
	private class AttrListener2 implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent arg0) {

		}
		
	}
	private class EditorListener extends MouseAdapter{

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
			int x = e.getX();
			int y = e.getY();
			la.setLocation(x,y);
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
		
	}
	public static void main(String[] args){
		new base();	
	}
}
