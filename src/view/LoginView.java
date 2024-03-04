package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.*;
import entity.employee;

public class LoginView extends JFrame {
    JTextField userId=new JTextField();
    JTextField password=new JTextField();
    employeeDao eDao=new employeeDao();
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginView frame = new LoginView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public LoginView(){
        setTitle("希尔顿国际酒店管理系统");
        setBounds(1040, 140, 643, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel root=new JPanel();
        root.setBounds(0,0,643, 400);
        setContentPane(root);
        root.setBackground(new Color(70,130,180));
        root.setLayout(null);
        Mypicture m1=new Mypicture();
        root.add(m1);


        m1.setBounds(240,70,138,50);
        userId.setBounds(188,150,300,25);
        password.setBounds(188,200,300,25);
        root.add(userId);
        root.add(password);
        eDao.owner();


        JLabel uid=new JLabel("用户名");
        JLabel pass=new JLabel("密码");
        uid.setBounds(120,143,66,30);
        pass.setBounds(120,193,66,30);
        uid.setFont(new Font("微软雅黑",Font.PLAIN,18));
        pass.setFont(new Font("微软雅黑",Font.PLAIN,18));
        root.add(uid);
        root.add(pass);

        JButton user =new JButton("登录");
        root.add(user);
        user.setBounds(255,249,90,30);
        user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eid=userId.getText();
                String passwor=password.getText();
                employee person=eDao.getByEid(eid);
                if(person!=null&&(person.getEtype().equals("董事长")||person.getEtype().equals("前台经理"))&&passwor.equals(person.getId().substring(12))){
                    MainFrame view =new MainFrame("希尔顿国际酒店管理系统");
                    view.setVisible(true);
                    setVisible(false);
                }else {
                    JOptionPane.showMessageDialog(root, "用户名或密码错误", "系统提示", JOptionPane.WARNING_MESSAGE);
                }


            }
        });





    }

}
