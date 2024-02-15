import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;  // Add this line for SimpleDateFormat
import java.util.Date;
import java.text.ParseException;

public class Register extends JFrame{

    private final Container container;
    private final JLabel title1;
    private final JLabel sName;
    private final JTextField tsName;
    private final JLabel email1;
    private final JTextField temail1;
    private final JLabel gender;
    private final JRadioButton male;
    private final JRadioButton female;
    private final ButtonGroup genGrp;
    private final JLabel dob;
    private final JComboBox date;
    private final JComboBox month;
    private final JComboBox year;
    private final JLabel password1;
    private final JPasswordField tpassword1;
    private final JCheckBox term;
    private final JButton signUpButton;
    private final JLabel title2;
    private final JLabel email2;
    private final JTextField temail2;
    private final JLabel password2;
    private final JPasswordField tpassword2;
    private final JButton signInButton;

    String[] dates = {"1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31"};
    String[] months = {"January", "February", "March", "April", "May",
            "Jun", "July", "August", "September", "October", "November",
            "December"};
    String[] years = {"1990","1991","1992","1993","1994","1995", "1996", "1997", "1998", "1999",
            "2000", "2001","2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009",
            "2010", "2011", "2012"};

    Connection connection;
    PreparedStatement pst;

    private static final String dburl = "jdbc:mysql://localhost:3806/registrationform";
    private static final String username = "root";
    private static final String password = "";

    public void connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dburl,username,password);
            System.out.println("Connection is Established successfully");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Register(){

        connect();

        setTitle("Sign Up and Sign In Form");
        setBounds(550,40,800,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        title1 = new JLabel("Sign Up");
        title1.setSize(500,40);
        title1.setLocation(350,50);
        title1.setFont(new Font("Arial",Font.BOLD,30));
        title1.setForeground(Color.BLUE);
        container.add(title1);

        sName = new JLabel(" Name");
        sName.setSize(500,30);
        sName.setLocation(150,100);
        sName.setFont(new Font("Arial",Font.BOLD,20));
        container.add(sName);

        tsName = new JTextField();
        tsName.setSize(300,30);
        tsName.setLocation(300,100);
        tsName.setFont(new Font("Arial",Font.PLAIN,20));
        container.add(tsName);

        email1 = new JLabel(" Email");
        email1.setSize(500,30);
        email1.setLocation(150,150);
        email1.setFont(new Font("Arial",Font.BOLD,20));
        container.add(email1);

        temail1 = new JTextField();
        temail1.setSize(300,30);
        temail1.setLocation(300,150);
        temail1.setFont(new Font("Arial",Font.PLAIN,20));
        container.add(temail1);

        //Gender Field
        gender = new JLabel(" Gender");
        gender.setSize(500,30);
        gender.setLocation(150,200);
        gender.setFont(new Font("Arial",Font.BOLD,20));
        container.add(gender);

        male = new JRadioButton("Male");
        male.setSize(150,50);
        male.setLocation(300,190);
        male.setFont(new Font("Arial",Font.BOLD,20));
        container.add(male);

        female = new JRadioButton("Female");
        female.setSize(150,50);
        female.setLocation(450,190);
        female.setFont(new Font("Arial",Font.BOLD,20));
        container.add(female);

        genGrp = new ButtonGroup();
        genGrp.add(male);
        genGrp.add(female);


        //Birthday Field
        dob = new JLabel(" Birthday");
        dob.setSize(500,30);
        dob.setLocation(150,250);
        dob.setFont(new Font("Arial",Font.BOLD,20));
        container.add(dob);

        date = new JComboBox(dates);
        date.setFont(new Font("Arial",Font.PLAIN,20));
        date.setSize(60,30);
        date.setLocation(300,250);
        container.add(date);

        month = new JComboBox(months);
        month.setFont(new Font("Arial",Font.PLAIN,20));
        month.setSize(120,30);
        month.setLocation(360,250);
        container.add(month);

        year = new JComboBox(years);
        year.setFont(new Font("Arial",Font.PLAIN,20));
        year.setSize(80,30);
        year.setLocation(480,250);
        container.add(year);

        //Password Field
        password1 = new JLabel(" Password");
        password1.setSize(500,30);
        password1.setLocation(150,300);
        password1.setFont(new Font("Arial",Font.BOLD,20));
        container.add(password1);

        tpassword1 = new JPasswordField();
        tpassword1.setSize(300,30);
        tpassword1.setLocation(300,300);
        tpassword1.setFont(new Font("Arial",Font.PLAIN,20));
        container.add(tpassword1);

        term = new JCheckBox("I am not a robot.");
        term.setFont(new Font("Arial",Font.PLAIN,18));
        term.setSize(250,20);
        term.setLocation(150,360);
        container.add(term);

        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 20));
        signUpButton.setSize(450, 40);
        signUpButton.setLocation(170, 410);
        signUpButton.setBackground(Color.BLUE);
        signUpButton.setForeground(Color.white);
        container.add(signUpButton);

        title2 = new JLabel("Sign In");
        title2.setSize(500,40);
        title2.setLocation(350,500);
        title2.setFont(new Font("Arial",Font.BOLD,30));
        title2.setForeground(Color.BLUE);
        container.add(title2);

        email2 = new JLabel(" Email");
        email2.setSize(500,30);
        email2.setLocation(150,550);
        email2.setFont(new Font("Arial",Font.BOLD,20));
        container.add(email2);

        temail2 = new JTextField();
        temail2.setSize(300,30);
        temail2.setLocation(300,550);
        temail2.setFont(new Font("Arial",Font.PLAIN,20));
        container.add(temail2);

        password2 = new JLabel(" Password");
        password2.setSize(500,30);
        password2.setLocation(150,600);
        password2.setFont(new Font("Arial",Font.BOLD,20));
        container.add(password2);

        tpassword2 = new JPasswordField();
        tpassword2.setSize(300,30);
        tpassword2.setLocation(300,600);
        tpassword2.setFont(new Font("Arial",Font.PLAIN,20));
        container.add(tpassword2);

        signInButton = new JButton("Sign In");
        signInButton.setFont(new Font("Arial", Font.BOLD, 20));
        signInButton.setSize(450, 40);
        signInButton.setLocation(170, 650);
        signInButton.setBackground(Color.BLUE);
        signInButton.setForeground(Color.white);
        container.add(signInButton);


        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Name,Email,Gender,Birthday,Password;

                if(term.isSelected()){
                    Name = tsName.getText();
                    Email = temail1.getText();
                    if(male.isSelected()){
                        Gender = "Male";
                    }else{
                        Gender = "Female";
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date selectedDate = null;
                    try {
                        selectedDate = sdf.parse(year.getSelectedItem() + "-" + (month.getSelectedIndex() + 1) + "-" + date.getSelectedItem());
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    Birthday = sdf.format(selectedDate);

                    Password = String.valueOf(tpassword1.getPassword());

                    try {
                        pst = connection.prepareStatement("insert into registrationdetails(Name,Email,Gender,Birthday,Password)values(?,?,?,?,?)");
                        pst.setString(1,Name);
                        pst.setString(2,Email);
                        pst.setString(3,Gender);
                        pst.setString(4,Birthday);
                        pst.setString(5,Password);

                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(null,"New user has been created.");
                    }
                    catch (SQLException except){
                        except.printStackTrace();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Please confirm that you are not a robot");
                }
            }

        });

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Email = temail2.getText();
                String Password = String.valueOf(tpassword2.getPassword());

                if (authenticateUser(Email, Password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    // Navigate the user to another page or perform any necessary actions
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Email or Password");
                }
            }
        });
    }

    public boolean authenticateUser(String email, String password) {
        try {
            String query = "SELECT * FROM registrationdetails WHERE Email=? AND Password=?";
            pst = connection.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            // Execute the query
            ResultSet resultSet = pst.executeQuery();
            // If there is a matching user, return true; otherwise return false
            return resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}


