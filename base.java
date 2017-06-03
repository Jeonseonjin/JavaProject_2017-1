import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class base extends JFrame{
	final int MAX_OBJECT = 100;
	JPanel PanelAttribute;
	JMenu FileMenu;
	JMenuItem NewFile, Open, Save, SaveName, NewJava, Close;
	JButton NewFileButton, OpenButton, SaveButton, SaveNameButton, NewJavaButton, CloseButton;
	public EditorPanel PanelEditor;
	JTextField startX,startY,Width,Height,compName;
	RectInfo rect[] = new RectInfo[MAX_OBJECT];
	public int count=0;
	
	base(){
		
		Dimension dim = new Dimension(1100,1000);
		setPreferredSize(dim);
		setLocation(10,0);
		setTitle("base");
		setDefaultCloseOperation(base.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		// �޴��ٱ���
		JMenuBar MenuBar = new JMenuBar();
			
		FileMenu = new JMenu("����(F)");
		FileMenu.setMnemonic('F');
			
		NewFile = new JMenuItem("���� �����(N)");
		NewFile.setMnemonic('N');
		FileMenu.add(NewFile);
		
		Open = new JMenuItem("����(O)");
		Open.setMnemonic('O');
		FileMenu.add(Open);
		
		Save = new JMenuItem("����(S)");
		Save.setMnemonic('S');
		FileMenu.add(Save);
		
		SaveName = new JMenuItem("�ٸ� �̸����� ����(A)");
		SaveName.setMnemonic('A');
		FileMenu.add(SaveName);
		
		NewJava = new JMenuItem(".java���� ����(J)");
		NewJava.setMnemonic('J');
		FileMenu.add(NewJava);
		
		Close = new JMenuItem("�ݱ�(C)");
		Close.setMnemonic('C');
		FileMenu.add(Close);
		
		MenuBar.add(FileMenu);
		setJMenuBar(MenuBar);
		
		
		// ���ٱ���
		JToolBar ToolBar = new JToolBar();
		
		ImageIcon newfile = new ImageIcon("images/newfile.png");
		NewFileButton = new JButton(newfile);
		ImageIcon open = new ImageIcon("images/open.png");
		OpenButton = new JButton(open);
		ImageIcon save = new ImageIcon("images/save.png");
		SaveButton = new JButton(save);
		SaveNameButton = new JButton("�ٸ� �̸����� ����");
		NewJavaButton = new JButton(".java ���� ����");
		CloseButton = new JButton("�ݱ�");
		
		ToolBar.add(NewFileButton);
		ToolBar.add(OpenButton);
		ToolBar.add(SaveButton);
		ToolBar.add(SaveNameButton);
		ToolBar.add(NewJavaButton);
		ToolBar.add(CloseButton);
		
		
		MyActionListener listener = new MyActionListener();
		//�޴��� ������
		NewFile.addActionListener(listener);
		Open.addActionListener(listener);
		Save.addActionListener(listener);
		SaveName.addActionListener(listener);
		NewJava.addActionListener(listener);
		Close.addActionListener(listener);
		//���� ������
		NewFileButton.addActionListener(listener);
		OpenButton.addActionListener(listener);
		SaveButton.addActionListener(listener);
		SaveNameButton.addActionListener(listener);
		NewJavaButton.addActionListener(listener);
		CloseButton.addActionListener(listener);
		
	
		
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
		startY = new JTextField(5);
		box1.add(startY);
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,20)));
		
		Box box2 = Box.createHorizontalBox();
		PanelAttribute.add(box2);
		box2.add(new JLabel("�ʺ� :" ));
		Width = new JTextField(5);
		box2.add(Width);
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,20)));
		
		Box box3 = Box.createHorizontalBox();
		PanelAttribute.add(box3);
		box3.add(new JLabel("���� :" ));
		Height = new JTextField(5);
		box3.add(Height);
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
		compType.addItem("JButton");
		compType.addItem("JLabel");
		compType.addItem("JTextField");
		box5.add(compType);
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,20)));
		
		Box box6 = Box.createHorizontalBox();
		PanelAttribute.add(box6);
		box6.add(new JLabel("������ : "));
		compName = new JTextField(15);
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
	}
	
	private class EditorPanel extends JPanel{ // editor �г� ���� ����
		int start_X, start_Y;
		int end_X, end_Y;
		int width, height;
		int move_index = -1;
		int size_index = -1;
		int pressed_index;
		boolean pressed;
		
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
					start_X = e.getX();
					start_Y = e.getY();
					for(int i=0; i<count; i++){
						if(((rect[i].startX < e.getX()) && (e.getX() < rect[i].startX + rect[i].width)) && 
						   ((rect[i].startY < e.getY()) && (e.getY() < rect[i].startY + rect[i].height))){
							pressed_index = i;
							ShowAttr();
							pressed = true;
						}
						if((rect[i].startX < e.getX()) && (e.getX() < rect[i].startX + rect[i].width - (rect[i].width*(0.1))) &&
								(rect[i].startY < e.getY()) && (e.getY() < rect[i].startY + rect[i].height - (rect[i].height*(0.1)))){
								move_index = i;
							}else if( (rect[i].startX + rect[i].width - (rect[i].width*(0.1)) <= e.getX()) && (e.getX() <= rect[i].startX + rect[i].width) || 
									(rect[i].startY + rect[i].height - (rect[i].height*(0.1)) <= e.getY()) && (e.getY() <= rect[i].startY + rect[i].height)){
								size_index = i;
							}	
					}	
					repaint();
				}

				@Override
				public void mouseReleased(MouseEvent e) {
				
				 	end_X = e.getX();
					end_Y = e.getY();
				
					if(move_index < 0 && size_index < 0){
						width = end_X - start_X;
						height = end_Y - start_Y;
						if((width > 0) && (height > 0))
							count++;
					}
					else if(move_index >= 0){
						rect[move_index].startX = e.getX() - (rect[move_index].width / 2);
						rect[move_index].startY = e.getY() - (rect[move_index].height / 2);
						ShowAttr();
					}else if(size_index >=0){
						    int temp_width, temp_height;
						    temp_width = rect[size_index].width;
							temp_height = rect[size_index].height;
							
							rect[size_index].width += (end_X - start_X);
							rect[size_index].height += (end_Y - start_Y);
							
							if(rect[size_index].width < 0 || rect[size_index].height < 0){
								rect[size_index].width = temp_width;
								rect[size_index].height = temp_height;
							}
		
							ShowAttr();
					}
					
					size_index = -1;
					move_index = -1;
					
					pressed = false;
					repaint();
					
				}
				
				@Override
				public void mouseDragged(MouseEvent e) {
				}
				
				@Override
				public void mouseMoved(MouseEvent e) {				
				}
		});
	}
		
		void ShowAttr(){
			startX.setText(String.valueOf(rect[pressed_index].startX));
			startY.setText(String.valueOf(rect[pressed_index].startY));
			Width.setText(String.valueOf(rect[pressed_index].width));
			Height.setText(String.valueOf(rect[pressed_index].height));
			compName.setText(rect[pressed_index].varName);
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			for(int i=0; i<count; i++){
				if(rect[i] == null){
					rect[i] = new RectInfo(i+"��° �簢��");
					rect[i].width = width;
					rect[i].height = height;
					rect[i].startX = start_X;
					rect[i].startY = start_Y;
					pressed_index = i;
					ShowAttr();
				}
				
				if(pressed == true && i == pressed_index){
					g.fillRect(rect[pressed_index].startX, rect[pressed_index].startY,rect[pressed_index].width, rect[pressed_index].height);
				}else{
					g.drawRect(rect[i].startX, rect[i].startY, rect[i].width, rect[i].height);
				}
				
			}
		}
	}
	
	class RectInfo{
		int startX,startY;
		int width, height;
		String txt;
		String type = "JButton";
		String varName;
		
		RectInfo(){
		}
		
		RectInfo(String name){
			varName = name;
		}
	}
	
	private class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == NewFileButton || e.getSource() == NewFile){
			}
			else if(e.getSource() == OpenButton || e.getSource() == Open){
				
			}
			else if(e.getSource() == CloseButton || e.getSource() == Close)
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

