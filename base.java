import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class base {
	base(){
		Dimension dim = new Dimension(800,500);
		
		JFrame frame = new JFrame("abcd");
		frame.setLocation(300,400);
		frame.setPreferredSize(dim);
		
		// �޴��ٱ���
		JMenuBar MenuBar = new JMenuBar();
		MenuBar.setLayout(new BoxLayout(MenuBar,BoxLayout.X_AXIS));
			
		JMenu filemenu = new JMenu("����(F)");
		filemenu.setMnemonic('F');
			
		JMenuItem newfile = new JMenuItem("������(N)");
		newfile.setMnemonic('N');
		filemenu.add(newfile);
			
		JMenuItem close = new JMenuItem("�ݱ�(C)");
		close.setMnemonic('C');
		filemenu.add(close);
			
		MenuBar.add(filemenu);
		
		/*ActionListener action = new ActionListener();{
			public void actionPerformed(ActionEvent e){
				Object obj = e.getSource();
				if(obj == newfile){
					JOptionPane.showMessageDialog("���ο� ���� ����",JOptionPane.INFORMATION_MESSAGE);
				}
				else if(obj == close){
					System.exit(1);
				}
			}
		}
		
		newfile.addActionListener(action);
		close.addActionListener(action);
		*/
		
		
		
		// ���ٱ���
		JToolBar ToolBar = new JToolBar();
		ToolBar.setLayout(new BoxLayout(ToolBar,BoxLayout.X_AXIS));
		ToolBar.add(new JLabel("ToolBar"));
		
		// �Ӽ�/Editor�гα���
		JPanel PanelAttribute = new JPanel();
		PanelAttribute.setLayout(new FlowLayout(FlowLayout.LEFT,30,40));
		PanelAttribute.add(new JLabel("Attribute Pane"));
		
		JPanel PanelEditor = new JPanel();
		PanelEditor.setLayout(new FlowLayout(FlowLayout.RIGHT,30,40));
		PanelEditor.add(new JLabel("Editor Pane"));
		
		// �����г� ����(Frame���� �޴���,����,�гε� ǥ���ϱ� ����)
		JPanel Mainpanel = new JPanel();
		Mainpanel.setLayout(new BoxLayout(Mainpanel, BoxLayout.Y_AXIS));
		Mainpanel.add(MenuBar);
		Mainpanel.add(ToolBar);
		Mainpanel.add(PanelAttribute);
		Mainpanel.add(PanelEditor);
		
		
		frame.add(Mainpanel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		base Base = new base();		
	}
}


