package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dao.*;
import entity.flow;
import entity.room;

class updateView extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JTextField idText;
    private JTextField nameText;
    private JTextField genderText;
    private JTextField ridText;

    private roomDao hdao = new roomDao();
    private customerDao cDao =new customerDao();
    private flowDao fDao=new flowDao();
    /**
     * Launch the application.
     */

    /**
     * Create the frame.
     * @param rid
     */
    public updateView(final String rid) {
        setTitle("续住");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 300);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        Mypicture m2=new Mypicture();
        m2.setBounds(375,275,69,25);
        contentPane.add(m2);



        JLabel lblNewLabel_0 = new JLabel("房间：");
        lblNewLabel_0.setBounds(131, 17, 83, 15);
        contentPane.add(lblNewLabel_0);

        ridText = new JTextField();
        ridText.setBounds(171, 14, 160, 21);
        contentPane.add(ridText);
        ridText.setColumns(10);

        JLabel lblNewLabel = new JLabel("身份证号：");
        lblNewLabel.setBounds(102, 60, 83, 15);
        contentPane.add(lblNewLabel);

        idText = new JTextField();
        idText.setBounds(171, 57, 160, 21);
        contentPane.add(idText);
        idText.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("姓名：");
        lblNewLabel_1.setBounds(132, 103, 43, 15);
        contentPane.add(lblNewLabel_1);

        nameText = new JTextField();
        nameText.setBounds(171, 100, 160, 21);
        contentPane.add(nameText);
        nameText.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("续住天数：");
        lblNewLabel_2.setBounds(111, 144, 83, 15);
        contentPane.add(lblNewLabel_2);

        genderText = new JTextField();
        genderText.setBounds(171, 141, 160, 21);
        contentPane.add(genderText);
        genderText.setColumns(10);

        //保存
        JButton saveBtn = new JButton("保存");
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String rid= ridText.getText();
                String id= idText.getText();
                String name = nameText.getText();
                String days = genderText.getText();
                int Days=Integer.parseInt(days);
                if(id == null || "".equals(id)){
                    JOptionPane.showMessageDialog(contentPane, "请输入身份证号", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(name == null || "".equals(name)){
                    JOptionPane.showMessageDialog(contentPane, "请输入姓名", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(days == null || "".equals(days)){
                    JOptionPane.showMessageDialog(contentPane, "请输入续住天数", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                room person = hdao.getByRid(rid);
                person.setId(id);
                person.setName(name);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date = null;
                try {
                   date =sdf.parse(person.getCheckOutTime());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                String timenow= sdf.format((long) Days *24*60*60*1000+date.getTime());
                String rtype=rid.substring(0,7);
                int amount=0;
                switch (rtype){
                    case "特价舒适大床房" : amount=288;break;
                    case "轻奢优品商务房" : amount=388;break;
                    case "休闲放松棋牌房" : amount=688;break;
                    case "近水楼台观景房" : amount=888;break;
                    case "奢华尊贵总统房" : amount=1888;break;

                }
                flow flowr=new flow();Date date1=new Date();
                flowr.setRid(rid);
                String timen=sdf1.format(date1);
                flowr.setFid(101+timen);
                flowr.setcType("续住");
                flowr.setName(name);
                flowr.setcTime(sdf.format(date1));
                flowr.setcMoney(String.valueOf(amount*Days));
                fDao.addflow(flowr);

                cDao.consume2(id,Days * amount);
                hdao.consume1(rid,Days * amount);
                person.setCheckOutTime(timenow);
                boolean flag = hdao.update(person);
                if(flag){
                    dispose();
                    JOptionPane.showMessageDialog(contentPane, "修改成功，刷新可查看!");
                }else{
                    JOptionPane.showMessageDialog(contentPane, "操作失败", "系统提示", JOptionPane.WARNING_MESSAGE);
                }
                return;


            }
        });
        saveBtn.setBounds(171, 190, 74, 23);
        contentPane.add(saveBtn);

        //取消
        JButton cancleBtn = new JButton("取消");
        cancleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancleBtn.setBounds(257, 190, 74, 23);
        contentPane.add(cancleBtn);

        //数据回显

        room person = hdao.getByRid(rid);
        ridText.setText(person.getRid());
        idText.setText(person.getId());
        nameText.setText(person.getName());
        genderText.setText(null);


    }


}