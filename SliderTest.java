import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SliderTest extends JFrame{
	JTextArea ta;
	JSlider slider;
	int count=0; // 입력된 글자수
	public SliderTest() {
		setTitle("TextArea Practice Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout(10,10));
		KeyListener kl= new KeyListener();
		MouseMotionListener mml = new MouseMotionListener();
		
		ta = new JTextArea();
		ta.addKeyListener(kl); // TextArea컴포넌트에 Key리스너 등록
		c.add(ta,BorderLayout.CENTER);
	
		slider = new JSlider(JSlider.HORIZONTAL,0,100,0); // 최소값이 0, 최대값이 100, 초깃값이 0인 수평슬라이더 생성
		slider.setPaintLabels(true); // label 보이기
		slider.setPaintTicks(true); // tick 보이기
		slider.setPaintTrack(true); // track 보이기
		slider.setMajorTickSpacing(20); // 큰 눈금 간격 지정
		slider.setMinorTickSpacing(5); // 작은 눈금 간격 지정
		slider.addMouseMotionListener(mml); // 슬라이더에 MouseMotion리스너 등록
		
		c.add(slider,BorderLayout.SOUTH);
		
		setSize(300,200);
		setVisible(true);
		c.setFocusable(true);
		c.requestFocus();

	}
	// 내부 클래스로 Key리스너 구현
	class KeyListener extends KeyAdapter{
		@Override
		public void keyTyped(KeyEvent e) {
			if(count >= 100) 	// 입력된 글자수가 100자를 넘어가려고 하면
				ta.setEditable(false); // 편집 불가능하게 하기
	
			// 입력된 글자 수에 맞게 슬라이더 바가 자동으로 움직이게 한다.
			if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE) { // <BackSpace> 키가 입력된 경우
				ta.setEditable(true);
				if(count == 100) { // 입력된 글자수가 100이면,
					ta.setText(ta.getText().substring(0,99)); // 99자의 글자만 나타낸다. 
				}
				if(count > 0) {
					count--; // 글자 수 감소
				}
				slider.setValue(count); // 입력된 글자 수를 슬라이더 값으로 설정
			}
			else{
				if(count < 100) {
					count++; // 글자 수 증가
					slider.setValue(count); // 입력된 글자 수를 슬라이더 값으로 설정
				}
			}
		}
	}
	// 내부 클래스로 MouseMotion리스너 구현
	class MouseMotionListener extends MouseAdapter{	
		@Override
		public void mouseDragged(MouseEvent e) { // 슬라이더 손잡이를 움직였을 때,
			int end = slider.getValue();
			count = end;
			String s = ta.getText();
			if(s.length() > end) // 슬라이더의 크기보다 텍스트길이가 길다면
				ta.setText(s.substring(0,end)); // TextArea에, 텍스트를 슬라이더 크기만큼만 나타낸다.
			else 
				ta.setText(s);
		}
	}
	public static void main(String[] args) {
		new SliderTest();
	}

}
