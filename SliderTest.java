import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SliderTest extends JFrame{
	JTextArea ta;
	JSlider slider;
	int count=0; // �Էµ� ���ڼ�
	public SliderTest() {
		setTitle("TextArea Practice Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout(10,10));
		KeyListener kl= new KeyListener();
		MouseMotionListener mml = new MouseMotionListener();
		
		ta = new JTextArea();
		ta.addKeyListener(kl); // TextArea������Ʈ�� Key������ ���
		c.add(ta,BorderLayout.CENTER);
	
		slider = new JSlider(JSlider.HORIZONTAL,0,100,0); // �ּҰ��� 0, �ִ밪�� 100, �ʱ갪�� 0�� ���򽽶��̴� ����
		slider.setPaintLabels(true); // label ���̱�
		slider.setPaintTicks(true); // tick ���̱�
		slider.setPaintTrack(true); // track ���̱�
		slider.setMajorTickSpacing(20); // ū ���� ���� ����
		slider.setMinorTickSpacing(5); // ���� ���� ���� ����
		slider.addMouseMotionListener(mml); // �����̴��� MouseMotion������ ���
		
		c.add(slider,BorderLayout.SOUTH);
		
		setSize(300,200);
		setVisible(true);
		c.setFocusable(true);
		c.requestFocus();

	}
	// ���� Ŭ������ Key������ ����
	class KeyListener extends KeyAdapter{
		@Override
		public void keyTyped(KeyEvent e) {
			if(count >= 100) 	// �Էµ� ���ڼ��� 100�ڸ� �Ѿ���� �ϸ�
				ta.setEditable(false); // ���� �Ұ����ϰ� �ϱ�
	
			// �Էµ� ���� ���� �°� �����̴� �ٰ� �ڵ����� �����̰� �Ѵ�.
			if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE) { // <BackSpace> Ű�� �Էµ� ���
				ta.setEditable(true);
				if(count == 100) { // �Էµ� ���ڼ��� 100�̸�,
					ta.setText(ta.getText().substring(0,99)); // 99���� ���ڸ� ��Ÿ����. 
				}
				if(count > 0) {
					count--; // ���� �� ����
				}
				slider.setValue(count); // �Էµ� ���� ���� �����̴� ������ ����
			}
			else{
				if(count < 100) {
					count++; // ���� �� ����
					slider.setValue(count); // �Էµ� ���� ���� �����̴� ������ ����
				}
			}
		}
	}
	// ���� Ŭ������ MouseMotion������ ����
	class MouseMotionListener extends MouseAdapter{	
		@Override
		public void mouseDragged(MouseEvent e) { // �����̴� �����̸� �������� ��,
			int end = slider.getValue();
			count = end;
			String s = ta.getText();
			if(s.length() > end) // �����̴��� ũ�⺸�� �ؽ�Ʈ���̰� ��ٸ�
				ta.setText(s.substring(0,end)); // TextArea��, �ؽ�Ʈ�� �����̴� ũ�⸸ŭ�� ��Ÿ����.
			else 
				ta.setText(s);
		}
	}
	public static void main(String[] args) {
		new SliderTest();
	}

}
