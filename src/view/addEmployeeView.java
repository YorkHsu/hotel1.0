package view;

import dao.employeeDao;
import dao.roomDao;
import entity.employee;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class addEmployeeView extends JFrame {
    private JPanel contentPane;
    private JTextField idText;
    private JTextField nameText;
    private JTextField genderText;
    private JTextField roomTypeText;
    Random random = new Random();
    private roomDao hdao = new roomDao();
    employeeDao eDao =new employeeDao();
    public addEmployeeView(){
        setTitle("添加员工");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 643, 400);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("身份证号：");
        lblNewLabel.setBounds(92, 50, 188, 15);
        contentPane.add(lblNewLabel);

        idText = new JTextField();
        idText.setBounds(151, 47, 160, 21);
        contentPane.add(idText);
        idText.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("姓名：");
        lblNewLabel_1.setBounds(112, 93, 43, 15);
        contentPane.add(lblNewLabel_1);

        nameText = new JTextField();
        nameText.setBounds(151, 90, 160, 21);
        contentPane.add(nameText);
        nameText.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("工种：");
        lblNewLabel_2.setBounds(111, 134, 43, 15);
        contentPane.add(lblNewLabel_2);

        genderText = new JTextField();
        genderText.setBounds(151, 131, 160, 21);
        contentPane.add(genderText);
        genderText.setColumns(10);

        JButton saveBtn = new JButton("保存");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employee person=new employee();
                String id=idText.getText();
                String name=nameText.getText();
                String eType=genderText.getText();
                int eid1=0;
                switch (eType){
                    case "清洁工": eid1=1;break;
                    case "维修工": eid1=2;break;
                    case "配送员": eid1=3;break;
                    case "厨师": eid1=4;break;
                }
                Date date=new Date();
                String intime=new SimpleDateFormat("yyyyMMddHHmmss").format(date);
                String eid=eid1+intime;

                person.setEtype(eType);
                person.setId(id);
                person.setRid("空");
                person.setName(name);
                person.setWorktime("休息");
                person.setEid(eid);
                boolean flag = eDao.addemployee(person);

                if(flag){
                    dispose();
                    JOptionPane.showMessageDialog(contentPane, "添加成功，刷新可查看!");

                }else{
                    JOptionPane.showMessageDialog(contentPane, "操作失败", "系统提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        contentPane.add(saveBtn);
        saveBtn.setBounds(151, 280, 74, 23);
    }
}
