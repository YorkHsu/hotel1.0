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
import entity.room;

public class checkInView extends JFrame {

    private JPanel contentPane;
    private JTextField idText;
    private JTextField nameText;
    private JTextField genderText;
    private JTextField phoneText;
    Random random = new Random();
    private roomDao hdao = new roomDao();
    private customerDao cDao= new customerDao();
    private flowDao fDao=new flowDao();
    /**
     * Launch the application.
     */

    /**
     * Create the frame.
     */
    public checkInView() {
        setTitle("入住办理");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 643, 400);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(32,178,170));

        JLabel lblNewLabel = new JLabel("身份证号：");
        lblNewLabel.setBounds(162, 50, 188, 15);
        contentPane.add(lblNewLabel);

        idText = new JTextField();
        idText.setBounds(221, 47, 160, 21);
        contentPane.add(idText);
        idText.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("姓名：");
        lblNewLabel_1.setBounds(182, 93, 43, 15);
        contentPane.add(lblNewLabel_1);

        nameText = new JTextField();
        nameText.setBounds(221, 90, 160, 21);
        contentPane.add(nameText);
        nameText.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("性别：");
        lblNewLabel_2.setBounds(181, 134, 43, 15);
        contentPane.add(lblNewLabel_2);

        genderText = new JTextField();
        genderText.setBounds(221, 131, 160, 21);
        contentPane.add(genderText);
        genderText.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("电话：");
        lblNewLabel_4.setBounds(181, 224, 43, 15);
        contentPane.add(lblNewLabel_4);

        phoneText = new JTextField();
        phoneText.setBounds(221, 221, 160, 21);
        contentPane.add(phoneText);
        phoneText.setColumns(10);

        JLabel lblNewLabel_3= new JLabel("房型：");
        lblNewLabel_3.setBounds(181, 180, 43, 15);
        contentPane.add(lblNewLabel_3);

        JComboBox roomTypeText=new JComboBox();
        roomTypeText.setBounds(221,177,160,21);
        roomTypeText.addItem("大床房");
        roomTypeText.addItem("商务房");
        roomTypeText.addItem("棋牌房");
        roomTypeText.addItem("总统房");
        roomTypeText.addItem("观景房");
        contentPane.add(roomTypeText);

        //保存
        JButton saveBtn = new JButton("保存");
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idText.getText();
                String name = nameText.getText();
                String sex = genderText.getText();
                String phone=phoneText.getText();
                String roomType= (String) roomTypeText.getSelectedItem();
                if(id == null || "".equals(id)){
                    JOptionPane.showMessageDialog(contentPane, "请输入身份证号", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(name == null || "".equals(name)){
                    JOptionPane.showMessageDialog(contentPane, "请输入姓名", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(sex == null || "".equals(sex)){
                    JOptionPane.showMessageDialog(contentPane, "请输入性别", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(phone == null || "".equals(phone)){
                    JOptionPane.showMessageDialog(contentPane, "请输入手机号", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(roomType == null || "".equals(roomType)){
                    JOptionPane.showMessageDialog(contentPane, "请选择房型", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                room rooM = new room();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date =new Date();
                String timenow1=sdf1.format(date);
                String timenow= sdf.format(date);
                rooM.setId(id);
                rooM.setName(name);
                rooM.setGender(sex);
                rooM.setCheckInTime(timenow);




                //房型选择
                String floor1 = "",doorNumber1="";
                int amount=0;
                if (roomType.equals("大床房")){
                    int floor=random.nextInt(5)+1;
                    int doorNumber=random.nextInt(20)+1;
                    floor1=String.valueOf(floor);
                    doorNumber1=String.valueOf(doorNumber);
                    if (floor/10==0){
                        floor1= "0"+floor1;
                    }
                    if (doorNumber/10==0){
                        doorNumber1="0"+doorNumber1;
                    }
                    rooM.setRid("特价舒适大床房"+floor1+"楼"+doorNumber1+"号房");
                    amount=288;

                }else if (roomType.equals("商务房")){
                    int floor=random.nextInt(5)+6;
                    int doorNumber=random.nextInt(20)+1;
                    floor1=String.valueOf(floor);
                    doorNumber1=String.valueOf(doorNumber);
                    if (floor/10==0){
                        floor1= "0"+floor1;
                    }
                    if (doorNumber/10==0){
                        doorNumber1="0"+doorNumber1;
                    }
                    rooM.setRid("轻奢优品商务房"+floor1+"楼"+doorNumber1+"号房");
                    amount=388;
                }else if (roomType.equals("棋牌房")){
                    int floor=random.nextInt(5)+11;
                    int doorNumber=random.nextInt(20)+1;
                    floor1=String.valueOf(floor);
                    doorNumber1=String.valueOf(doorNumber);
                    if (floor/10==0){
                        floor1= "0"+floor1;
                    }
                    if (doorNumber/10==0){
                        doorNumber1="0"+doorNumber1;
                    }
                    rooM.setRid("休闲放松棋牌房"+floor1+"楼"+doorNumber1+"号房");
                    amount=688;
                }else if (roomType.equals("观景房")){
                    int floor=random.nextInt(5)+16;
                    int doorNumber=random.nextInt(20)+1;
                    floor1=String.valueOf(floor);
                    doorNumber1=String.valueOf(doorNumber);
                    if (floor/10==0){
                        floor1= "0"+floor1;
                    }
                    if (doorNumber/10==0){
                        doorNumber1="0"+doorNumber1;
                    }
                    rooM.setRid("近水楼台观景房"+floor1+"楼"+doorNumber1+"号房");
                    amount=888;
                }else if (roomType.equals("总统房")){
                    int floor=random.nextInt(1)+21;
                    int doorNumber=random.nextInt(20)+1;
                    floor1=String.valueOf(floor);
                    doorNumber1=String.valueOf(doorNumber);
                    if (floor/10==0){
                        floor1= "0"+floor1;
                    }
                    if (doorNumber/10==0){
                        doorNumber1="0"+doorNumber1;
                    }
                    rooM.setRid("奢华尊贵总统房"+floor1+"楼"+doorNumber1+"号房");
                    amount=1888;
                }

                //添加顾客
                customer person=cDao.getById(id);
                if (person==null){
                person=new customer();
                person.setId(id);
                person.setName(name);
                person.setGender(sex);
                person.setPhoneNumber(phone);
                person.setAccount(amount);
                cDao.addCustomer(person);}
                person.addroom(floor1+""+doorNumber1);

                //添加到交易记录中
                flow flowr=new flow();
                flowr.setName(name);
                flowr.setFid(100+timenow1);
                flowr.setRid(rooM.getRid());
                flowr.setcTime(timenow);
                flowr.setcType("订房");
                flowr.setcMoney(String.valueOf(amount));
                fDao.addflow(flowr);


                boolean flag = hdao.CheckIn(rooM);
                hdao.consume1(rooM.getRid(),amount);
                if(flag){
                    dispose();
                    JOptionPane.showMessageDialog(contentPane, "办理入住成功，刷新可查看!");
                }else{
                    JOptionPane.showMessageDialog(contentPane, "操作失败", "系统提示", JOptionPane.WARNING_MESSAGE);
                }
                return;


            }
        });
        saveBtn.setBounds(221, 280, 74, 23);
        contentPane.add(saveBtn);

        //取消
        JButton cancleBtn = new JButton("取消");
        cancleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancleBtn.setBounds(307, 280, 74, 23);
        contentPane.add(cancleBtn);
    }

}