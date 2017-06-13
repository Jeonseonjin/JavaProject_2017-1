import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class base extends JFrame{
	final int MAX_OBJECT = 100;
	public int count=0, pressed_index;
	JPanel PanelAttribute;
	JMenu FileMenu;
	JMenuItem NewFile, Open, Save, SaveName, NewJava, Close;
	JButton NewFileButton, OpenButton, SaveButton, SaveNameButton, NewJavaButton, CloseButton, ApplyButton;
	JComboBox compType;
	JTextField startX,startY,Width,Height,txtAttr,compName;
	RectInfo rect[] = new RectInfo[MAX_OBJECT];
	public EditorPanel PanelEditor;
	boolean first_save = true;
	String save_path = null;
	boolean file_loaded = false;
	String loaded_file_path;
	
	base(){
		Dimension dim = new Dimension(1500,1000);
		setPreferredSize(dim);
		setLocation(10,0);
		setTitle("base");
		setVisible(true);
		setDefaultCloseOperation(base.EXIT_ON_CLOSE);
		
		
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
		txtAttr = new JTextField(15);
		box4.add(txtAttr);
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,20)));
		
		Box box5 = Box.createHorizontalBox();
		PanelAttribute.add(box5);
		box5.add(new JLabel("Ÿ�� : "));
		compType = new JComboBox();
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
		
		ApplyButton = new JButton("����");
		PanelAttribute.add(ApplyButton);
		PanelAttribute.add(Box.createRigidArea(new Dimension(1,100)));
		
		// editor �г� ����
		PanelEditor = new EditorPanel();
		PanelEditor.add(new JLabel("Editor Pane"));
		
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
		
		//���� ��ư ������
		ApplyButton.addActionListener(listener);
		
		//frame ��ü�� BorderLayout���� ����
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(ToolBar, BorderLayout.NORTH);
		contentPane.add(PanelAttribute,BorderLayout.WEST);
		contentPane.add(PanelEditor,BorderLayout.CENTER);
		pack();
	}
	
	private class EditorPanel extends JPanel{ // editor �г� ���� ����
		int start_X, start_Y;
		int end_X, end_Y;
		int width, height;
		int move_index = -1;
		int size_index = -1;
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
							pressed = true;
							ShowAttr();
							if(e.getButton() == 3){
								rect[pressed_index].startX = 0;
								rect[pressed_index].startY = 0;
								rect[pressed_index].width = 0;
								rect[pressed_index].height = 0;
								rect[pressed_index].txt = null;
								rect[pressed_index].type =null;
								rect[pressed_index].varName = null;
							}
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
			txtAttr.setText(String.valueOf(rect[pressed_index].txt));
			compType.setSelectedItem(rect[pressed_index].type);
			compName.setText(rect[pressed_index].varName);
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			for(int i=0; i<count; i++){
				if(rect[i] != null){
					if(rect[i].type == null && rect[i].varName == null)
						continue;
				}
				if(rect[i] == null){
					rect[i] = new RectInfo("component"+i);
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
			txt = name;
			varName = name;
		}
	}
	
	private class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == NewFileButton || e.getSource() == NewFile){
				if(file_loaded){
					try {
						JSONParser parser = new JSONParser();
						FileReader loaded_reader = new FileReader(loaded_file_path);
						Object Jarr = parser.parse(loaded_reader);
						JSONArray readJarray = (JSONArray) Jarr;
						
						if(readJarray != null){
							for(int i=0; i<count; i++){
								rect[i] = null;
							}
						  
						  count = 0;
						  Iterator<JSONObject> iterator = readJarray.iterator();
						  JSONObject readJobj = new JSONObject();
						  int i = 0;
						  while(iterator.hasNext()){
							  rect[i] = new RectInfo();
							  readJobj = iterator.next();
							  rect[i].startX = Integer.parseInt(String.valueOf(readJobj.get("startX")));
							  rect[i].startY = Integer.parseInt(String.valueOf(readJobj.get("startY")));
							  rect[i].width = Integer.parseInt(String.valueOf(readJobj.get("width")));
							  rect[i].height = Integer.parseInt(String.valueOf(readJobj.get("height")));
							  rect[i].txt = String.valueOf(readJobj.get("txt"));
							  rect[i].type = String.valueOf(readJobj.get("type"));
							  rect[i].varName = String.valueOf(readJobj.get("varName"));
							  i++;
							  count++;
						  }
						  repaint();
						  }
						
					    loaded_reader.close();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					for(int i=0; i<count; i++){
						rect[i] = null;
					}
					count = 0;
					repaint();
				}
			}
			else if(e.getSource() == OpenButton || e.getSource() == Open){
				JFileChooser JopenDialog = new JFileChooser();
				File temp_file = new File("*.json");
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".json file","json");
				JopenDialog.setFileFilter(filter);
				JopenDialog.setSelectedFile(temp_file);
				int ret = JopenDialog.showOpenDialog(base.this);
				String dirName = JopenDialog.getSelectedFile().getPath();
				if(ret == JFileChooser.APPROVE_OPTION){
					try{
						file_loaded = true;
						loaded_file_path = dirName;
						JSONParser parser = new JSONParser();
						FileReader open_reader = new FileReader(dirName);
						Object Jarr = parser.parse(open_reader);
						JSONArray readJarray = (JSONArray) Jarr;
						
						if(readJarray != null){
							for(int i=0; i<count; i++){
								rect[i] = null;
							}
						  
						  count = 0;
						  Iterator<JSONObject> iterator = readJarray.iterator();
						  JSONObject readJobj = new JSONObject();
						  int i = 0;
						  while(iterator.hasNext()){
							  rect[i] = new RectInfo();
							  readJobj = iterator.next();
							  rect[i].startX = Integer.parseInt(String.valueOf(readJobj.get("startX")));
							  rect[i].startY = Integer.parseInt(String.valueOf(readJobj.get("startY")));
							  rect[i].width = Integer.parseInt(String.valueOf(readJobj.get("width")));
							  rect[i].height = Integer.parseInt(String.valueOf(readJobj.get("height")));
							  rect[i].txt = String.valueOf(readJobj.get("txt"));
							  rect[i].type = String.valueOf(readJobj.get("type"));
							  rect[i].varName = String.valueOf(readJobj.get("varName"));
							  i++;
							  count++;
						  }
						  repaint();
						  }
						
					    open_reader.close();
				       }catch (IOException | ParseException e1) {
					   e1.printStackTrace();
					 }
				}
			}	  
			else if(e.getSource() == CloseButton || e.getSource() == Close){
				System.exit(1);
			}
			else if(e.getSource() == NewJava || e.getSource() == NewJavaButton){
				JFileChooser JsaveDialog = new JFileChooser();
				File temp_file = new File("*.java");
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".java file","java");
				JsaveDialog.setFileFilter(filter);
				JsaveDialog.setSelectedFile(temp_file);
				int ret = JsaveDialog.showSaveDialog(base.this);
				try{
					String dirName = JsaveDialog.getSelectedFile().getPath();
					if(ret == JFileChooser.APPROVE_OPTION){
						StringTokenizer st = new StringTokenizer(JsaveDialog.getSelectedFile().getName(), ".");
						String class_name = st.nextToken();
						FileWriter fout = new FileWriter(dirName);
					    String java_code = "";
					    String[] base_code1 = {"import javax.swing.*;\r\n",
						    	               "public class "+class_name+" extends JFrame{\r\n",
							                   "	"+class_name+"(){\r\n",
							                   "		setTitle(\""+class_name+"\");\r\n",
							                   "		setSize(1200,900);\r\n",
							                   "		setLayout(null);\r\n",
							                   "		setVisible(true);\r\n"};
					    for(int i=0; i<base_code1.length; i++){
					    	java_code = java_code.concat(base_code1[i]);
					    }
					    for(int i=0; i<count; i++){
					    	if(rect[i].type == null)
					    		continue;
						    String inst_code[] = {"		"+rect[i].type+" "+rect[i].varName+" = new "+rect[i].type+"(\""+rect[i].txt+"\");\r\n",
					                              "		"+rect[i].varName+".setLocation("+rect[i].startX+","+rect[i].startY+");\r\n", 
					      		                  "		"+rect[i].varName+".setSize("+rect[i].width+","+rect[i].height+");\r\n",
					      		                  "		add("+rect[i].varName+");\r\n"};
						    for(int j=0; j<inst_code.length; j++){
						    	java_code = java_code.concat(inst_code[j]);
						    }
					    }
					    String[] base_code2 = {"	}\r\n",
						    	               "	public static void main(String[] args) {\r\n",
							                   "		new "+class_name+"();\r\n",
							                   "	}\r\n",
							                   "}"};
					    for(int i=0; i<base_code2.length; i++){
					    	java_code = java_code.concat(base_code2[i]);
					    }
					    fout.write(java_code);
					    fout.close();
					    }
				}catch(Exception e1){				
				}
			}
		   else if(e.getSource() == ApplyButton){
			   System.out.println(pressed_index);
			    rect[pressed_index].startX = Integer.parseInt(startX.getText());
				rect[pressed_index].startY = Integer.parseInt(startY.getText());
			    rect[pressed_index].width = Integer.parseInt(Width.getText());
				rect[pressed_index].height = Integer.parseInt(Height.getText());
			    rect[pressed_index].txt = txtAttr.getText();
			    rect[pressed_index].type = String.valueOf(compType.getSelectedItem());
				rect[pressed_index].varName = compName.getText();
				System.out.println(pressed_index);
				repaint();
				System.out.println(pressed_index);
			}else if(e.getSource() == Save || e.getSource() == SaveButton){
				MenuToolModel model = new MenuToolModel();
				
				if(file_loaded){
					model.save(true, loaded_file_path);
				}else if(first_save){
					save_path = model.save(true, null);
					first_save = false;
				}else{
					model.save(true, save_path);
				}
				
			}else if(e.getSource() == SaveName || e.getSource() == SaveNameButton){
				MenuToolModel model = new MenuToolModel();
				model.save(false, null);
			}
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
	
	class MenuToolModel{
		String save(boolean onlySave, String pre_path){
			String dirName = pre_path;
			JFileChooser saveDialog = new JFileChooser();
			if(onlySave == true && pre_path != null){

			}else{
				File temp_file = new File("*.json");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("json file","json");
				saveDialog.setFileFilter(filter);
				saveDialog.setSelectedFile(temp_file);
				int ret = saveDialog.showSaveDialog(base.this);
				dirName = saveDialog.getSelectedFile().getPath();
			}
				try{
				 FileWriter save_writer = new FileWriter(dirName);

				 JSONObject[] Jrect = new JSONObject[MAX_OBJECT];
				 JSONArray JrectArray = new JSONArray();
		            for(int i=0; i<count; i++){
		            	Jrect[i] = new JSONObject();
		                Jrect[i].put("startX", rect[i].startX);
		                Jrect[i].put("startY", rect[i].startY);
		                Jrect[i].put("width", rect[i].width);
		                Jrect[i].put("height", rect[i].height);
		                Jrect[i].put("txt", rect[i].txt);
		                Jrect[i].put("type", rect[i].type);
		                Jrect[i].put("varName", rect[i].varName);
		                JrectArray.add(i, Jrect[i]);
		            }

		         //���� ������ ����
		         save_writer.write(JrectArray.toJSONString());
		         save_writer.close();
			     } catch (Exception e1) {
			 }
			return dirName;
	}
	}
	
	public static void main(String[] args){
		base a = new base();	
	}
}