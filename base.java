import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class base {
	base(){
		Dimension dim = new Dimension(1000,1000);
		
		JFrame frame = new JFrame("abcd");
		frame.setLocation(10,0);
		frame.setPreferredSize(dim);
		
		// �޴��ٱ���
		JMenuBar MenuBar = new JMenuBar();
		MenuBar.setLayout(new BoxLayout(MenuBar,BoxLayout.X_AXIS));
			
		JMenu filemenu = new JMenu("����(F)");
		filemenu.setMnemonic('F');
			
		JMenuItem newfile = new JMenuItem("���� �����(N)");
		newfile.setMnemonic('N');
		filemenu.add(newfile);
		
		JMenuItem open = new JMenuItem("����(O)");
		open.setMnemonic('O');
		filemenu.add(open);
		
		JMenuItem save = new JMenuItem("����(S)");
		save.setMnemonic('S');
		filemenu.add(save);
		
		JMenuItem saveName = new JMenuItem("�ٸ� �̸����� ����(A)");
		saveName.setMnemonic('A');
		filemenu.add(saveName);
		
		JMenuItem newJava = new JMenuItem(".java���� ����(J)");
		newJava.setMnemonic('J');
		filemenu.add(newJava);
		
		JMenuItem close = new JMenuItem("�ݱ�(C)");
		close.setMnemonic('C');
		filemenu.add(close);
		MenuBar.add(filemenu);
		
		// ���ٱ���
		JToolBar ToolBar = new JToolBar();
		ToolBar.setLayout(new BoxLayout(ToolBar,BoxLayout.X_AXIS));
		ToolBar.add(new JButton("���� �����"));
		ToolBar.add(new JButton("����"));
		ToolBar.add(new JButton("����"));
		ToolBar.add(new JButton("�ٸ� �̸����� ����"));
		ToolBar.add(new JButton(".java ���� ����"));
		ToolBar.add(new JButton("�ݱ�"));
		
		
		//�̺�Ʈ ������
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
		
		
		// �Ӽ�/Editor�гα���
		/* �Ӽ� �г� ���� ���� */
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
		/* �Ӽ� �г� ���� �� */
		
		JPanel PanelEditor = new JPanel();
		PanelEditor.setLayout(new FlowLayout(FlowLayout.RIGHT,30,40));
		PanelEditor.add(new JLabel("Editor Pane"));
		
		// �Ӽ����ΰ� ������������ ���� �г�
		JPanel Subpanel = new JPanel();
		Subpanel.setLayout(new BoxLayout(Subpanel,BoxLayout.X_AXIS));
		Subpanel.add(PanelAttribute);
		Subpanel.add(PanelEditor);
		
		// �����г� ����(Frame���� �޴���,����,�гε� ǥ���ϱ� ����)
		JPanel Mainpanel = new JPanel();
		Mainpanel.setLayout(new BoxLayout(Mainpanel,BoxLayout.Y_AXIS));
		Mainpanel.add(MenuBar);
		Mainpanel.add(ToolBar);
		Mainpanel.add(Subpanel);
		
		
		frame.add(Mainpanel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		base Base = new base();		
	}
}


