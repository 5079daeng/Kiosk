
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JRadioButton;
import java.awt.Font;

class kiosk extends JFrame {

	public kiosk() {
		super("YELLOW SUBMARINE STUDY CAFE");

		setSize(800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

public class Main {
	static CardLayout card;
	static JPanel panel;
	static MemberRepository mr;
	private static JTextField textField;

	static StringBuilder sb = new StringBuilder();
	static ArrayList<Integer> arr = new ArrayList<>();
	static String selectedSeatbtnNum = null;
	static Member mem;

	static JCheckBox admit;
	static JTextField phonenumber;

	static JPanel workPnl;
	static JPanel studyPnl;
	static JLabel selectedSeat;
	private static JTextField ticketphnum;

	public static void main(String[] args) {
		mr = new MemberRepository();
		kiosk kiosk_ = new kiosk();
		kiosk_.getContentPane().setBackground(new Color(145, 116, 86));
		kiosk_.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(51, 86, 675, 621);
		panel.setBackground(new Color(145, 116, 86));
		kiosk_.getContentPane().add(panel);
		card = new CardLayout();
		panel.setLayout(card);
		JButton showRule = new JButton("이용 안내");
		showRule.setFont(new Font("HY견고딕", Font.PLAIN, 15));

		showRule.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(panel, "first");

			}
		});
		showRule.setBounds(51, 32, 105, 27);
		kiosk_.getContentPane().add(showRule);

		JButton showSeat = new JButton("좌석 선택하기");
		showSeat.setFont(new Font("HY견고딕", Font.PLAIN, 15));

		showSeat.setBounds(205, 32, 135, 27);
		kiosk_.getContentPane().add(showSeat);

		JButton showTicket = new JButton("정기권 구매하기");
		showTicket.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		showTicket.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (sb.length() > 0 && arr.size() > 0) {
					sb.delete(0, sb.length());
					arr.removeAll(arr);
				}
				ticketphnum.setText("-를 제외한 11자리 번호를 입력하세요");
				card.show(panel, "fifth");

			}
		});
		showTicket.setBounds(392, 32, 149, 27);
		kiosk_.getContentPane().add(showTicket);

		JPanel rule = new JPanel();
		rule.setBackground(new Color(145, 116, 86));
		ImageIcon img1 = new ImageIcon("메인이미지.png");
		rule.add(new JLabel(img1));
		panel.add(rule, "first");

		JPanel seatPnl = new JPanel();
		System.out.println(seatPnl.getSize());
		seatPnl.setBackground(Color.WHITE);
		panel.add(seatPnl, "second");
		seatPnl.setLayout(null);

		JLabel studylbl = new JLabel("스터디존");
		studylbl.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		studylbl.setBounds(85, 341, 62, 18);
		seatPnl.add(studylbl);

		JLabel worklbl = new JLabel("워크존");
		worklbl.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		worklbl.setBounds(85, 12, 62, 18);
		seatPnl.add(worklbl);

		JLabel lounge = new JLabel("휴게실");
		lounge.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		lounge.setBounds(503, 12, 50, 18);
		seatPnl.add(lounge);

		JLabel coffeeM = new JLabel("커피머신");
		coffeeM.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		coffeeM.setBackground(Color.DARK_GRAY);
		coffeeM.setBounds(585, 41, 62, 124);
		seatPnl.add(coffeeM);

		JLabel table = new JLabel("테이블");
		table.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		table.setBackground(Color.DARK_GRAY);
		table.setBounds(409, 42, 62, 161);
		seatPnl.add(table);

		JLabel door = new JLabel("출입문");
		door.setBounds(639, 252, 22, 135);
		seatPnl.add(door);

		workPnl = new JPanel();
		workPnl.setBackground(Color.WHITE);
		workPnl.setBounds(14, 41, 206, 274);
		seatPnl.add(workPnl);
		workPnl.setLayout(new GridLayout(4, 0, 80, 50));

		studyPnl = new JPanel();
		studyPnl.setBackground(Color.WHITE);
		studyPnl.setBounds(14, 371, 635, 238);
		seatPnl.add(studyPnl);
		studyPnl.setLayout(new GridLayout(5, 4, 80, 20));

		showSeat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetSeatPnl();

			}
		});

		JPanel ticketPnl = new JPanel();
		ticketPnl.setBackground(Color.WHITE);
		panel.add(ticketPnl, "third");
		ticketPnl.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("정기권 구매");
		lblNewLabel_6.setFont(new Font("HY견고딕", Font.BOLD, 22));
		lblNewLabel_6.setBounds(29, 82, 182, 59);
		ticketPnl.add(lblNewLabel_6);

		JRadioButton twoWeeks = new JRadioButton("2주권");
		twoWeeks.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		twoWeeks.setBackground(Color.WHITE);
		twoWeeks.setBounds(44, 165, 139, 27);
		ticketPnl.add(twoWeeks);

		JRadioButton month = new JRadioButton("4주권");
		month.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		month.setBackground(Color.WHITE);
		month.setBounds(270, 165, 139, 27);
		ticketPnl.add(month);

		JRadioButton twoMonth = new JRadioButton("8주권");
		twoMonth.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		twoMonth.setBackground(Color.WHITE);
		twoMonth.setBounds(490, 165, 139, 27);
		ticketPnl.add(twoMonth);

		ButtonGroup btng = new ButtonGroup();
		btng.add(twoWeeks);
		btng.add(month);
		btng.add(twoMonth);

		JButton buyTicket = new JButton("정기권 구매하기");
		buyTicket.setForeground(Color.BLACK);
		buyTicket.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		buyTicket.setBounds(256, 497, 153, 27);
		ticketPnl.add(buyTicket);

		JPanel Loginpnl = new JPanel();
		panel.add(Loginpnl, "fourth");
		Loginpnl.setLayout(null);

		selectedSeat = new JLabel("0");
		selectedSeat.setBounds(36, 24, 90, 39);
		Loginpnl.add(selectedSeat);

		admit = new JCheckBox("카페 이용을 위한 개인정보 수집/이용/조회에 동의합니다");
		admit.setBounds(132, 36, 407, 27);
		Loginpnl.add(admit);

		phonenumber = new JTextField("-를 제외한 11자리를 입력하세요");
		phonenumber.setBounds(110, 106, 450, 50);
		Loginpnl.add(phonenumber);
		phonenumber.setColumns(10);

		JButton phoneNumConfirm = new JButton("등록 확인");
		phoneNumConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				int k = 0;
				if (sb.length() != 11) {

					JOptionPane.showMessageDialog(null, "핸드폰 번호를 11자리 입력하세요");
				} else if (sb.length() == 11) {
// 해당하는 번호가 등록 된 번호인지 조회하는 것 
					String a = mr.check(sb.toString());
//선택한 자리에 등록된 번호인지 확인하는 것 
					String b = mr.getPhoneNumber(selectedSeat.getText());
					if (b == null) {

						if (a == null) {
							JOptionPane.showMessageDialog(null, "이용권 구매창으로 이동합니다");
							Main.card.show(Main.panel, "third");
							mem = new Member(sb.toString(), LocalDate.now().toString(), null, 0);

						} else {// 다른 자리에 등록 된 번호라면
							int seat = mr.checkMemberSeat(sb.toString());
							Member mem2 = mr.selectMember(sb.toString());
							mem = new Member(mem2.getPhoneNumber(), mem2.getStartDate(), mem2.getLastDate(), seat);

							if (seat != 0) {

								JOptionPane.showMessageDialog(null,
										seat + " 에서" + selectedSeat.getText() + " 로 자리 이동합니다.");

							}
							mem.setSeat(Integer.valueOf(selectedSeat.getText()));
							mr.updateSeat(selectedSeat.getText(), sb.toString());
							resetSeatPnl();
							card.show(panel, "second");
						}
					} else {
						if (sb.toString().equals(b)) {
							int out = JOptionPane.showConfirmDialog(null, "퇴실하시겠습니까?", "퇴실확인",
									JOptionPane.YES_NO_OPTION);
							if (out == 0) {
								mr.updateSeat(String.valueOf(0), sb.toString());
								card.show(panel, "first");
							}
						} else if (!sb.toString().equals(b)) {
							JOptionPane.showMessageDialog(null, "해당 번호가 사용중인 좌석이 아닙니다.");
							card.show(panel, "first");
						}
					}
				}

			}
		});
		phoneNumConfirm.setBounds(284, 530, 105, 44);
		Loginpnl.add(phoneNumConfirm);

		JPanel forLogin = new JPanel(new GridLayout(0, 3, 10, 10));
		forLogin.setBounds(110, 183, 450, 311);
		Loginpnl.add(forLogin);

		for (int i = 1; i < 10; i++) {
			String number = String.valueOf(i);
			JButton numberbtn = new JButton(number);

			ActionListener li = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (!admit.isSelected()) {

						JOptionPane.showMessageDialog(null, "이용약관을 먼저 확인하세요 ");
					} else {
						Object o = e.getSource();
						arr.add(Integer.valueOf(number));

						sb.append(Integer.valueOf(number));

						if (sb.length() == 12) {
							JOptionPane.showMessageDialog(null, "11자리를 입력하세요");
							sb.setLength(sb.length() - 1);
							arr.remove(arr.size() - 1);
						}
						phonenumber.setText(sb.toString());
					}

				}
			};
			numberbtn.addActionListener(li);
			forLogin.add(numberbtn);
		}
		JButton deleteAll = new JButton("X");
		JButton zero = new JButton("0");
		JButton delete = new JButton("←");
		forLogin.add(deleteAll);
		forLogin.add(zero);
		forLogin.add(delete);
		ActionListener li2 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!admit.isSelected()) {

					JOptionPane.showMessageDialog(null, "이용약관을 먼저 확인하세요 ");
				} else {
					Object o = e.getSource();
					if (o == zero) {
						arr.add(0);

						sb.append(0);
					} else if (o == deleteAll) {
						sb.delete(0, sb.length());
						arr.removeAll(arr);
						System.out.println(arr);
					} else if (o == delete) {
						sb.delete(sb.length() - 1, sb.length());
						arr.remove(arr.size() - 1);
						System.out.println(arr);
					}

					if (sb.length() == 12) {
						JOptionPane.showMessageDialog(null, "11자리를 입력하세요");
						sb.setLength(sb.length() - 1);
						arr.remove(arr.size() - 1);
					}
					phonenumber.setText(sb.toString());
				}

			}
		};
		zero.addActionListener(li2);
		deleteAll.addActionListener(li2);
		delete.addActionListener(li2);
		forLogin.setPreferredSize(new Dimension(400, -50));

		JPanel ticketLogin = new JPanel();
		panel.add(ticketLogin, "fifth");
		ticketLogin.setLayout(null);

		JCheckBox checkinfo = new JCheckBox("카페 이용을 위한 개인정보 수집/이용/조회에 동의합니다");
		checkinfo.setBounds(148, 47, 390, 27);
		ticketLogin.add(checkinfo);

		ticketphnum = new JTextField();
		ticketphnum.setBounds(148, 109, 390, 27);
		ticketLogin.add(ticketphnum);
		ticketphnum.setColumns(10);

		panel.add(new checkFrame().panel, "sixth");

		JPanel forLogin2 = new JPanel(new GridLayout(0, 3, 10, 10));
		forLogin2.setBounds(121, 207, 437, 307);
		ticketLogin.add(forLogin2);

		for (int i = 1; i < 10; i++) {
			String number = String.valueOf(i);
			JButton numberbtn = new JButton(number);

			ActionListener li = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (!checkinfo.isSelected()) {

						JOptionPane.showMessageDialog(null, "이용약관을 먼저 확인하세요 ");
					} else {
						Object o = e.getSource();
						arr.add(Integer.valueOf(number));

						sb.append(Integer.valueOf(number));

						if (sb.length() == 12) {
							JOptionPane.showMessageDialog(null, "11자리를 입력하세요");
							sb.setLength(sb.length() - 1);
							arr.remove(arr.size() - 1);
						}
						ticketphnum.setText(sb.toString());
					}

				}
			};
			numberbtn.addActionListener(li);
			forLogin2.add(numberbtn);
		}
		JButton deleteAll2 = new JButton("X");
		JButton zero2 = new JButton("0");
		JButton delete2 = new JButton("←");
		forLogin2.setLayout(new GridLayout(0, 3, 0, 0));
		forLogin2.add(deleteAll2);
		forLogin2.add(zero2);
		forLogin2.add(delete2);
		ActionListener li3 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!checkinfo.isSelected()) {

					JOptionPane.showMessageDialog(null, "이용약관을 먼저 확인하세요 ");
				} else {
					Object o = e.getSource();
					if (o == zero2) {
						arr.add(0);

						sb.append(0);
					} else if (o == deleteAll2) {
						sb.delete(0, sb.length());
						arr.removeAll(arr);
						System.out.println(arr);
					} else if (o == delete2) {
						sb.delete(sb.length() - 1, sb.length());
						arr.remove(arr.size() - 1);
						System.out.println(arr);
					}

					if (sb.length() == 12) {
						JOptionPane.showMessageDialog(null, "11자리를 입력하세요");
						sb.setLength(sb.length() - 1);
						arr.remove(arr.size() - 1);
					}
					ticketphnum.setText(sb.toString());
				}

			}
		};
		zero2.addActionListener(li3);
		deleteAll2.addActionListener(li3);
		delete2.addActionListener(li3);
		forLogin2.setPreferredSize(new Dimension(400, -50));

		JButton btnNewButton = new JButton("정기권 구매하기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String number = mr.check(ticketphnum.getText());
				if (number == null) {
					mem = new Member(ticketphnum.getText(), LocalDate.now().toString(), null, 0);
					card.show(panel, "third");
				} else {
					Member mem2 = mr.selectMember(ticketphnum.getText());
					mem = new Member(mem2.getPhoneNumber(), mem2.getStartDate(), mem2.getLastDate(), mem2.getSeat());
					card.show(panel, "third");
				}

			}
		});
		btnNewButton.setBounds(302, 543, 105, 27);
		ticketLogin.add(btnNewButton);

		JButton checkmember = new JButton("이용정보 조회");
		checkmember.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		checkmember.setBounds(581, 32, 130, 27);
		kiosk_.getContentPane().add(checkmember);
		checkmember.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(panel, "sixth");
			}
		});
		JButton master = new JButton("관리자 로그인");
		master.setBounds(652, 714, 105, 27);
		kiosk_.getContentPane().add(master);

		kiosk_.setVisible(true);
		buyTicket.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("확인" + mr.selectMember(mem.getPhoneNumber()));
				System.out.println(("확인" + (LocalDate.now()).plusDays(14)));
				if (mr.selectMember(mem.getPhoneNumber()) == null) {
					if (twoWeeks.isSelected()) {
						mem.setLastDate(LocalDateTime.now().plusDays(14)
								.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString());
					} else if (month.isSelected()) {
						mem.setLastDate(LocalDateTime.now().plusDays(28)
								.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString());
					} else if (twoMonth.isSelected()) {
						mem.setLastDate(LocalDateTime.now().plusDays(56)
								.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString());
					}

					mr.joinMember(mem);
					new memberInfo(mem).setVisible(true);
				} else {
					if (twoWeeks.isSelected()) {
						mr.oldupdateTicketPeriod(14, mem.getPhoneNumber());
					} else if (month.isSelected()) {
						mr.oldupdateTicketPeriod(28, mem.getPhoneNumber());
					} else if (twoMonth.isSelected()) {
						mr.oldupdateTicketPeriod(56, mem.getPhoneNumber());
					}
					new memberInfo(mem).setVisible(true);

				}

				card.show(panel, "second");
				resetSeatPnl();

			}
		});

//		int result = rs.joinMember(new Member("01040437274", "2022-02-08", 20));
//		System.out.println(result);
	}

	public static void resetSeatPnl() {
		card.show(panel, "second");
		Main.workPnl.removeAll();
		workPnl.revalidate();
		workPnl.repaint();
		for (int i = 1; i < 9; i++) {
			JButton j = new JButton(String.valueOf(i));
			if (mr.checkSelectedSeat(Integer.valueOf(j.getText()))) {

				j.setBackground(Color.RED);
			} else {
				j.setBackground(Color.GREEN);
			}
			j.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (j.getBackground() == Color.RED) {
						// 로그아웃, 자리 이동 프레임 나오게 하기 ;
					}
					selectedSeat.setText(j.getText());

					admit.setSelected(false);
					if (sb.length() > 0 && arr.size() > 0) {
						sb.delete(0, sb.length());
						arr.removeAll(arr);
					}
					phonenumber.setText("-를 제외한 11자리 번호를 입력하세요");
					card.show(panel, "fourth");
				}
			});
			workPnl.add(j);

		}
		studyPnl.removeAll();
		studyPnl.revalidate();
		for (int i = 9; i < 31; i++) {
			JButton j = new JButton(String.valueOf(i));
			if (mr.checkSelectedSeat(Integer.valueOf(j.getText()))) {
				j.setBackground(Color.red);
			} else {
				j.setBackground(Color.GREEN);
			}
			j.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					selectedSeat.setText(j.getText());
					admit.setSelected(false);
					if (sb.length() > 0 && arr.size() > 0) {
						sb.delete(0, sb.length());
						arr.removeAll(arr);
					}
					phonenumber.setText("-를 제외한 11자리 번호를 입력하세요");
					card.show(panel, "fourth");

				}
			});
			studyPnl.add(j);

		}
	}

}
