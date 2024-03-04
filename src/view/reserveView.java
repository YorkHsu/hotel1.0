package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dao.*;
import entity.customer;
import entity.flow;

public class reserveView extends JFrame {

    private JPanel contentPane;
    private JTextField phonetext;
    private JTextField timetext;
    private JTextField nametext;
    private JTextField roomTypeText;
    Random random = new Random();
    private roomDao hdao = new roomDao();
    private customerDao cDao=new customerDao();
    private flowDao fDao=new flowDao();
    /**
     * Launch the application.
     */

    /**
     * Create the frame.
     */
    public reserveView() {
        setTitle("预约");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 643, 400);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(32,178,170));

        JLabel lblNewLabel = new JLabel("电话号码：");
        lblNewLabel.setBounds(72, 50, 188, 18);
        contentPane.add(lblNewLabel);

        phonetext = new JTextField();
        phonetext.setBounds(151, 47, 160, 21);
        contentPane.add(phonetext);
        phonetext.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("姓名：");
        lblNewLabel_1.setBounds(102, 93, 43, 18);
        contentPane.add(lblNewLabel_1);

        nametext = new JTextField();
        nametext.setBounds(151, 90, 160, 21);
        contentPane.add(nametext);
        nametext.setColumns(10);


        JLabel lblNewLabel_3= new JLabel("预约类型：");
        lblNewLabel_3.setBounds(71, 135, 93, 18);
        contentPane.add(lblNewLabel_3);

        lblNewLabel.setForeground(Color.yellow);
        lblNewLabel_1.setForeground(Color.yellow);
        lblNewLabel_3.setForeground(Color.yellow);
        lblNewLabel.setFont(new Font("微软雅黑",Font.PLAIN,14));
        lblNewLabel_1.setFont(new Font("微软雅黑",Font.PLAIN,14));
        lblNewLabel_3.setFont(new Font("微软雅黑",Font.PLAIN,14));


        JComboBox roomTypeText=new JComboBox();
        roomTypeText.setBounds(151,135,160,21);
        roomTypeText.addItem("婚礼庆宴");
        roomTypeText.addItem("豪华包厢");
        roomTypeText.addItem("海鲜自助");
        roomTypeText.addItem("户外烤肉");
        roomTypeText.addItem("米其林三星餐厅");
        roomTypeText.addItem("洗浴足浴");
        roomTypeText.addItem("天然温泉");
        roomTypeText.addItem("桑拿汗蒸");
        roomTypeText.addItem("商务快餐");
        contentPane.add(roomTypeText);

        JLabel lblNewLabel_4= new JLabel("预约时间：");
        lblNewLabel_4.setBounds(71, 180, 93, 18);
        contentPane.add(lblNewLabel_4);
        lblNewLabel_4.setForeground(Color.yellow);
        lblNewLabel_4.setFont(new Font("微软雅黑",Font.PLAIN,14));

        timetext = new JTextField();
        timetext.setBounds(151, 180, 160, 21);
        contentPane.add(timetext);
        timetext.setColumns(10);

        JButton timeset=new JButton("日期选择");
        timeset.setBounds(321,180,120,21);
        timeset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDateChooser jtime=new JDateChooser();
                jtime.setVisible(true);
                int year =jtime.getYear();
                int month=jtime.getMonth();
                int day=jtime.getDate();
                timetext.setText(year+"年"+month+"月"+day+"日");
            }
        });
        contentPane.add(timeset);

        JComboBox rtime=new JComboBox();
        rtime.setBounds(441,180,80,21);
        rtime.addItem("08:00");
        rtime.addItem("09:00");
        rtime.addItem("10:00");
        rtime.addItem("11:00");
        rtime.addItem("12:00");
        rtime.addItem("13:00");
        rtime.addItem("14:00");
        rtime.addItem("15:00");
        rtime.addItem("16:00");
        rtime.addItem("17:00");
        rtime.addItem("18:00");
        rtime.addItem("19:00");
        rtime.addItem("20:00");
        rtime.addItem("21:00");
        contentPane.add(rtime);
        rtime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date=timetext.getText();
                String time=rtime.getSelectedItem().toString();
                timetext.setText(date+" "+time);
            }
        });

        //保存
        JButton saveBtn = new JButton("保存");
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String phone = phonetext.getText();
                String name = nametext.getText();
                String time = timetext.getText();
                String roomType= (String) roomTypeText.getSelectedItem();
                if(phone == null || "".equals(phone)){
                    JOptionPane.showMessageDialog(contentPane, "请输入电话号码", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(name == null || "".equals(name)){
                    JOptionPane.showMessageDialog(contentPane, "请输入姓名", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(time == null || "".equals(time)){
                    JOptionPane.showMessageDialog(contentPane, "请选择预约时间", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }


//
//                room person = new room();
//                person.setName(name);
//                person.setGender(time);


                String acitivity=roomTypeText.getSelectedItem().toString();
                int amount=0;
                switch (acitivity){
                    case "婚礼庆宴" : amount=198888;break;
                    case "米其林三星餐厅" : amount=98888;break;
                    case "豪华包厢" : amount=8888;break;
                    case "海鲜自助" : amount=688;break;
                    case "户外烤肉" : amount=388;break;
                    case "洗浴足浴" : amount=2888;break;
                    case "桑拿汗蒸" : amount=2888;break;
                    case "天然温泉" : amount=3888;break;
                    case "商务快餐" : amount=88;break;
                }
                customer person=hdao.getByName(name);
                System.out.println(person.getId());
               cDao.consume2(person.getId(),amount);

                flow flowc=new flow();
                flowc.setRid("");
                flowc.setcMoney(String.valueOf(amount));
                flowc.setcType(acitivity);
                flowc.setName(name);
                Date date = new Date();
                String inTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                String inTime1 = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
                flowc.setcTime(inTime);
                flowc.setFid("201"+inTime1);
                boolean flag=fDao.addflow(flowc);

                if(flag){
                    dispose();
                    JOptionPane.showMessageDialog(contentPane, "预约成功!");
                }else{
                    JOptionPane.showMessageDialog(contentPane, "操作失败", "系统提示", JOptionPane.WARNING_MESSAGE);
                }
                return;


            }
        });
        saveBtn.setBounds(151, 280, 74, 23);
        contentPane.add(saveBtn);

        //取消
        JButton cancleBtn = new JButton("取消");
        cancleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancleBtn.setBounds(237, 280, 74, 23);
        contentPane.add(cancleBtn);
    }

}