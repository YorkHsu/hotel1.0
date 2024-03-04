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

public class vipView extends JFrame {

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
    public vipView() {
        setTitle("会员办理");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 703, 400);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(32,178,170));

        JLabel lblNewLabel = new JLabel("身份证号：");
        lblNewLabel.setBounds(192, 50, 188, 15);
        contentPane.add(lblNewLabel);

        idText = new JTextField();
        idText.setBounds(251, 47, 160, 21);
        contentPane.add(idText);
        idText.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("姓名：");
        lblNewLabel_1.setBounds(212, 93, 43, 15);
        contentPane.add(lblNewLabel_1);

        nameText = new JTextField();
        nameText.setBounds(251, 90, 160, 21);
        contentPane.add(nameText);
        nameText.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("性别：");
        lblNewLabel_2.setBounds(211, 134, 43, 15);
        contentPane.add(lblNewLabel_2);

        genderText = new JTextField();
        genderText.setBounds(251, 131, 160, 21);
        contentPane.add(genderText);
        genderText.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("电话：");
        lblNewLabel_4.setBounds(211, 224, 43, 15);
        contentPane.add(lblNewLabel_4);

        phoneText = new JTextField();
        phoneText.setBounds(251, 221, 160, 21);
        contentPane.add(phoneText);
        phoneText.setColumns(10);

        JLabel lblNewLabel_3= new JLabel("vip类型：");
        lblNewLabel_3.setBounds(197, 180, 73, 15);
        contentPane.add(lblNewLabel_3);

        JComboBox vipText=new JComboBox();
        vipText.setBounds(251,177,160,21);
        vipText.addItem("希尔顿普通会员");
        vipText.addItem("希尔顿铂金会员");
        vipText.addItem("希尔顿钻石会员");
        vipText.addItem("希尔顿至臻黑钻会员");
        contentPane.add(vipText);

        //保存
        JButton saveBtn = new JButton("保存");
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idText.getText();
                String name = nameText.getText();
                String sex = genderText.getText();
                String phone=phoneText.getText();
                String vip=vipText.getSelectedItem().toString();
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
                int amount=0;
                switch (vip){
                    case "希尔顿普通会员" : amount=1688;break;
                    case "希尔顿铂金会员" : amount=3688;break;
                    case "希尔顿钻石会员" : amount=6688;break;
                    case "希尔顿至臻黑钻会员" : amount=9688;break;
                }

                customer person=cDao.getById(id);
                    //添加顾客
                    if (person == null) {
                        person = new customer();
                        person.setId(id);
                        person.setName(name);
                        person.setGender(sex);
                        person.setPhoneNumber(phone);
                        person.setAccount(amount);
                        person.setVip(vip);
                        cDao.addCustomer(person);
                    }
                    cDao.vip(id,vip);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date=new Date();
                String timenow1=sdf1.format(date);
                String timenow=sdf.format(date);
                //添加到交易记录中
                flow flowr=new flow();
                flowr.setName(name);
                flowr.setFid(100+timenow1);
                flowr.setRid("（非房间服务）");
                flowr.setcTime(timenow);
                flowr.setcType("会员登记");
                flowr.setcMoney(String.valueOf(amount));
                fDao.addflow(flowr);

                boolean flag=cDao.consume2(id,amount);
                if(flag){
                    dispose();
                    JOptionPane.showMessageDialog(contentPane, "会员登记成功，刷新可查看!");
                }else{
                    JOptionPane.showMessageDialog(contentPane, "操作失败", "系统提示", JOptionPane.WARNING_MESSAGE);
                }
                return;


            }
        });
        saveBtn.setBounds(251, 280, 74, 23);
        contentPane.add(saveBtn);

        //取消
        JButton cancleBtn = new JButton("取消");
        cancleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancleBtn.setBounds(337, 280, 74, 23);
        contentPane.add(cancleBtn);
    }

}