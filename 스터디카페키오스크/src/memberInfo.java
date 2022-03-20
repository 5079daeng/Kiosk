import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;

public class memberInfo extends JDialog {
	private static Frame owner;

	public memberInfo(Member mem) {
		super(owner, "회원 정보 확인", true);
		{
			setSize(300, 300);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
		getContentPane().setLayout(null);

		JLabel infoTitle = new JLabel("이용정보");
		infoTitle.setBounds(107, 23, 62, 18);
		getContentPane().add(infoTitle);

		JLabel memNumber = new JLabel("회원번호");
		memNumber.setBounds(14, 69, 62, 18);
		getContentPane().add(memNumber);

		JLabel memLastDate = new JLabel("이용가능일자");
		memLastDate.setBounds(14, 117, 92, 18);
		getContentPane().add(memLastDate);

		JLabel memSeat = new JLabel("좌석번호");
		memSeat.setBounds(14, 164, 62, 18);
		getContentPane().add(memSeat);

		JLabel memNumber2 = new JLabel(mem.getPhoneNumber());

		memNumber2.setBounds(122, 69, 146, 18);
		getContentPane().add(memNumber2);

		JLabel memLastDate2 = new JLabel(mem.getLastDate());
		memLastDate2.setBounds(120, 117, 148, 18);
		getContentPane().add(memLastDate2);

		JLabel memSeat2 = new JLabel(String.valueOf(mem.getSeat()));
		memSeat2.setBounds(139, 164, 62, 18);
		getContentPane().add(memSeat2);

		JButton confirmbtn = new JButton("확인");
		confirmbtn.setBounds(85, 214, 105, 27);
		getContentPane().add(confirmbtn);

		confirmbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				memberInfo.this.setVisible(false);

			}
		});
	}
}
