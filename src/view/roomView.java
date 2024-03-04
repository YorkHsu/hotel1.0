package view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.roomDao;

public class roomView extends JFrame{
    private JPanel contentPane;
    private JTable table;
    private roomDao hdao = new roomDao();
    private JTextField ridText;
    public roomView(){
        setTitle("房间管理");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 450);
        setLocationRelativeTo(null);
        setVisible(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(32,178,170));

        Mypicture m2=new Mypicture();
        m2.setBounds(1100,400,138,50);
        contentPane.add(m2);

        JLabel lblNewLabel = new JLabel("所查找的房间号：");
        lblNewLabel.setFont(new Font("微软雅黑",Font.PLAIN,20));
        lblNewLabel.setBounds(228, 50, 168, 30);
        contentPane.add(lblNewLabel);

        ridText = new JTextField();
        ridText.setBounds(388, 54, 160, 21);
        contentPane.add(ridText);
        ridText.setColumns(10);
        JButton searchBtn=new JButton("查找");
        JCheckBox warning =new JCheckBox("请确保只向所需治安单位提供房间所在顾客的个人信息");
        warning.setFont(new Font("微软雅黑",Font.BOLD,22));
        warning.setForeground(new Color(255,0,0));
        warning.setBounds(150,147,565,30);
        warning.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBtn.setEnabled(true);
            }
        });
        contentPane.add(warning);

        JLabel information1 =new JLabel("身份证号码 ：");
        information1.setBounds(200,200,400,21);
        contentPane.add(information1);
        JLabel information2 =new JLabel("姓名 ：");
        information2.setBounds(200,250,400,21);
        contentPane.add(information2);
        JLabel information3 =new JLabel("性别 ：");
        information3.setBounds(200,300,400,21);
        contentPane.add(information3);
        JLabel information4 =new JLabel("入住时间 ：");
        information4.setBounds(200,350,400,21);
        contentPane.add(information4);

        information4.setFont(new Font("微软雅黑",Font.PLAIN,18));
        information3.setFont(new Font("微软雅黑",Font.PLAIN,18));
        information2.setFont(new Font("微软雅黑",Font.PLAIN,18));
        information1.setFont(new Font("微软雅黑",Font.PLAIN,18));
        information1.setForeground(Color.yellow);
        information2.setForeground(Color.yellow);
        information3.setForeground(Color.yellow);
        information4.setForeground(Color.yellow);

        searchBtn.setBounds(348, 93, 145, 28);
        contentPane.add(searchBtn);
        searchBtn.setEnabled(false);
        searchBtn.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
            String ridtext=ridText.getText();
            int rid=Integer.parseInt(ridtext);
            System.out.println(rid);
            int floor=rid/100;
            int room=rid%100;
            String floor1=String.valueOf(floor);
            String room1=String.valueOf(room);
            if (floor<10)floor1='0'+floor1;
            if (room<10)room1='0'+room1;
            if (floor<=5){
                ridtext="特价舒适大床房"+floor1+"楼"+room1+"号房";
            }else if (floor<=10&&floor>5){
                ridtext="轻奢优品商务房"+floor1+"楼"+room1+"号房";
            }else if (floor<=15&&floor>10){
                ridtext="休闲放松棋牌房"+floor1+"楼"+room1+"号房";
            }else if (floor==21){
                ridtext="奢华尊贵总统房"+floor1+"楼"+room1+"号房";
            }else if (floor<=20&&floor>15){
                ridtext="近水楼台观景房"+floor1+"楼"+room1+"号房";
            }
            String finalRidtext = ridtext;
            entity.room person=hdao.getByRid(finalRidtext);
            information1.setText("身份证号码 ："+person.getId());
            information2.setText("姓名 ："+person.getName());
            information3.setText("性别 ："+person.getGender());
            information4.setText("入住时间 ："+person.getCheckInTime());



            }
        });



    }


}
