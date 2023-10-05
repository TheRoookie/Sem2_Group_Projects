
// System.out.println("test pass");System.exit(0);

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

class Clothing_projcp {

    public static void main(String[] args) throws Exception {

        Connection con = connection("localhost:3306/e-clothing");

        Shop s = new Shop(con);

        while (s.login()) {
        }

        s.home();

        con.close();
    }

    static Connection connection(String url) throws Exception {
        String dburl = "jdbc:mysql://" + url;
        String dbuser = "root";
        String dbpassword = "";
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);
        return con;
    }

    static Connection connection(String url, String user, String pass) throws Exception {
        String dburl = "jdbc:mysql://" + url;
        String dbuser = user;
        String dbpassword = pass;
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);
        return con;
    }
}

class Shop {
    Connection con;
    int current_user;
    Scanner sc = new Scanner(System.in);

    Shop(Connection con) throws Exception {
        this.con = con;
    }

    boolean login() throws Exception {

        long phone = 0l;
        String userName, name, email, pass, address, gender;
        userName = name = pass = address = email = gender = "";

        System.out.println("Welcome to Your Fav E-Mart !!!!!!!");
        System.out.println("-------------------------------------------------------");
        System.out.println("1 -> Sign up");
        System.out.println("2 -> Log in");
        int res1 = 0;
        while (true) {
            try {
                res1 = sc.nextInt();
                if (res1 == 1 || res1 == 2) {
                    sc.nextLine();
                    break;
                } else {
                    throw new Exception();
                }

            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Invalid Input. Try Again.");
            }
        }

        switch (res1) {
            case 1:

                String sql = "INSERT INTO customer (db_cust_user_name, db_cust_name, db_cust_pass, db_cust_address, db_cust_email , db_cust_phone , db_cust_gender) VALUES( ? , ?, ? , ? , ? , ? , ? );";
                PreparedStatement pst = con.prepareStatement(sql);

                System.out.println("Registration.....");
                System.out.println();
                System.out.println("Enter your phone number.");/* **************************************** */
                while (true) {
                    try {
                        phone = sc.nextLong();

                        Statement st = con.createStatement();
                        ResultSet boo = st.executeQuery(
                                "SELECT db_cust_phone from customer where db_cust_phone = '" + phone + "';");

                        // System.out.println(boo.next());
                        if (boo.next()) {
                            System.out.println("Number already registered!... Try Diffrent Number.");
                        } else {
                            pst.setLong(6, phone);
                            System.out.println("Number aproved.");
                            sc.nextLine();
                            break;
                        }

                    } catch (SQLException se) {
                        System.out.println("Internal SQL Execption! sorry.");
                        System.exit(0);
                    } catch (Exception e) {
                        sc.nextLine();
                        System.out.println("Phone number not valid. Try again.");
                    }

                }
                System.out.println();
                System.out.println("Enter your name.");/* ******************************************* */
                while (true) {
                    try {
                        name = sc.nextLine();
                        pst.setString(2, name);
                        break;
                    } catch (SQLException se) {
                        System.out.println("Internal SQL Execption! sorry.");
                        System.exit(0);
                    } catch (Exception e) {
                        sc.nextLine();
                        System.out.println("Name not valid. Try again.");
                    }
                }
                System.out.println();
                System.out.println("Enter your user name. (Must be Unique)");/* ******************************* */
                while (true) {
                    try {
                        userName = sc.nextLine();

                        Statement st = con.createStatement();
                        ResultSet boo = st.executeQuery(
                                "SELECT db_cust_user_name from customer where db_cust_user_name = '" + userName + "';");

                        // boo.next gives if select has the element or not.
                        if (boo.next()) {
                            System.out.println("User Name taken... Try again.");
                        } else {
                            pst.setString(1, userName);
                            System.out.println("User Name aproved.");
                            break;
                        }

                    } catch (SQLException se) {
                        System.out.println("Internal SQL Execption! sorry.");
                        System.exit(0);
                    } catch (Exception e) {
                        sc.nextLine();
                        System.out.println("User name not valid. Try again.");
                    }

                }
                System.out.println();
                System.out.println("Enter your email."); /* ************************************ */
                while (true) {
                    try {
                        email = sc.nextLine();

                        Statement st = con.createStatement();
                        ResultSet boo = st.executeQuery(
                                "SELECT db_cust_email from customer where db_cust_email = '" + email + "';");

                        // boo.next gives if select has the element or not.
                        if (boo.next()) {
                            System.out.println("Email linked with another account... Try alternate email.");
                        } else {
                            pst.setString(5, email);
                            System.out.println("Email aproved.");
                            break;
                        }

                    } catch (SQLException se) {
                        System.out.println("Internal SQL Execption! sorry.");
                        System.exit(0);
                    } catch (Exception e) {
                        sc.nextLine();
                        System.out.println("Email not valid. Try again.");
                    }
                }
                System.out.println();
                System.out.println("Enter your password.");/* ******************************************* */

                outer: while (true) {
                    pass = sc.next();
                    String str = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*";
                    if (pass.length() < 8) {
                        System.out.println("INVALID Password HAVE SMALL SIZE");
                        continue outer;
                    } else if (pass.length() >= 8) {
                        if (pass.matches(str)) {
                            char passi[] = new char[pass.length()];
                            for (int j = 0; j < pass.length(); j++) {
                                passi[j] = pass.charAt(j);
                                if (passi[j] == ' ') {
                                    System.out.println("INVALID Password CONTAINS SPACE");
                                    continue outer;
                                }
                            }
                        } else if (pass.matches(str) == false) {
                            System.out.println("INVALID Password DON'T HAVE SPECIAL");
                            continue outer;
                        }
                        break outer;
                    }
                }
                pst.setString(3, pass);

                System.out.println();
                System.out.println("Enter your Address.");/* ******************************************* */
                while (true) {
                    try {
                        address = sc.nextLine();
                        pst.setString(4, address);
                        break;
                    } catch (SQLException se) {
                        System.out.println("Internal SQL Execption! sorry.");
                        System.exit(0);
                    } catch (Exception e) {
                        sc.nextLine();
                        System.out.println("Address not valid. Try again.");
                    }
                }
                System.out.println();
                System.out.println("Enter Your Gender(Optional)"); /* ***************************************** */
                System.out.println("-------------------------------------------------------");
                System.out.println("1 -> Male");
                System.out.println("2 -> Female");
                System.out.println("3 -> Rather not say");
                int genRes = 0;
                while (true) {
                    try {
                        genRes = sc.nextInt();
                        if (genRes == 1 || genRes == 2 || genRes == 3) {
                            sc.nextLine();
                            break;
                        } else {
                            throw new Exception();
                        }

                    } catch (Exception e) {
                        sc.nextLine();
                        System.out.println("Invalid Input. Try Again.");
                    }
                }
                switch (genRes) {
                    case 1:
                        gender = "male";
                        break;
                    case 2:
                        gender = "female";
                        break;
                    case 3:
                        gender = null;
                        break;

                    default:
                        System.out.println(
                                "I don't Know what the fuck just happened!!! Contact Customer Support. You are just very unlucky.");
                        System.exit(0);
                        break;
                }
                pst.setString(7, gender);

                try {
                    pst.executeUpdate();
                    System.out.println("Registration Success!");
                    System.out.println("Going to Login page.");
                    return true;
                } catch (Exception e) {
                    System.out.println("Internal SQL Execption! sorry.");
                    System.out.println("Sign-up Failed!");
                    System.exit(-1);
                }

                break;
            case 2:

                System.out.println();
                System.out.println("Enter Your user name.");

                while (true) {
                    try {
                        userName = sc.nextLine();

                        Statement st = con.createStatement();
                        ResultSet boo = st.executeQuery(
                                "SELECT db_cust_user_name from customer where db_cust_user_name = '" + userName + "';");

                        // System.out.println(boo.next());
                        if (boo.next()) {
                            break;
                        } else {
                            System.out.println("User not Found!");
                        }
                    } catch (SQLException se) {
                        System.out.println("Internal SQL Execption! sorry.");
                        System.exit(0);
                    } catch (Exception e) {
                        sc.nextLine();
                        System.out.println("Invalid Input. Try again.");
                    }
                }
                System.out.println();
                System.out.println("Welcome back " + userName + " !!");
                System.out.println();
                Statement st = con.createStatement();
                ResultSet rs = st
                        .executeQuery("select db_cust_id,db_cust_pass from customer where db_cust_user_name = '"
                                + userName + "' ;");
                rs.next();

                System.out.println("Pass is - " + rs.getString("db_cust_pass"));

                System.out.println("Enter Password.");
                for (int i = 0; i < 5; i++) {
                    String passchk = sc.nextLine();

                    if (passchk.equals(rs.getString(2))) {
                        current_user = rs.getInt(1);
                        System.out.println("Login Success!");
                        return false;
                    } else {
                        System.out.println("Incorrect Password. " + (4 - i) + " attempts left.");
                    }

                }
                System.out.println("Login Failed! Back to Login page.");

                return true;

            default:
                System.out.println(
                        "I don't Know what just happened!!! Contact Customer Support. You are just very unlucky.");
                System.exit(0);
                break;
        }

        System.out.println("How did we get here? Going to Login page.");

        return true;

    }

    int save_price;
    String save_colour;
    String save_size;
    String male = "male";
    String female = "female";

    void bill(ArrayList<Integer> a) throws Exception {

        System.out.println("┌────────────────────────────────────────────────────┐");
        System.out.println("│                     Bill Option's                  │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│ -------------------------------------------------- │");
        System.out.println("│ 1. I want to Purchase the item from above !!       │");
        System.out.println("│ 2. To Add Product in My Wishlist !!                │");
        System.out.println("│ 3. To Add Product in My Cart !!                    │");
        System.out.println("│ 4. To Exit!!                                       │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│ Please select an option (1-4):                     │");
        System.out.println("└────────────────────────────────────────────────────┘");
        int yn = sc.nextInt();
        switch (yn) {
            case 1:
                System.out.println("Please Enter The ID of the Product You want to enter ");
                int billid = sc.nextInt();
                if (!a.contains(billid)) {
                    System.out.println("Product with ID " + billid + " is not found in the list.");
                    con.close();
                    return;
                }
                String sql = "SELECT * FROM product WHERE ID = ?";
                PreparedStatement p = con.prepareStatement(sql);
                p.setInt(1, billid);

                ResultSet rs = p.executeQuery();
                double totalBill = 0.0;

                int bill_id = 0;
                String bill_name = "";
                String bill_color = "";
                Double bill_price = 0.0;
                System.out.println(
                        "┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
                System.out.println(
                        "│                     ALL PRODUCT's                                                                                                             │");
                System.out.println(
                        "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
                while (rs.next()) {
                    bill_id = rs.getInt("ID");
                    bill_name = rs.getString("p_name");
                    bill_color = rs.getString("color");
                    bill_price = rs.getDouble("price");

                    System.out.println(
                            "│                                                                                                                                         \t│");
                    System.out.println(
                            "│ ID » " + bill_id + " │ Name » " + bill_name + "\t│ Color » " + bill_color + " │ Price » "
                                    + bill_price
                                    + "                                                              \t\t\t│");

                    totalBill += bill_price;
                }
                System.out.println(
                        "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
                System.out.println(
                        "│ Please select an option (1-4):                                                                                                                │");
                System.out.println(
                        "└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");

                System.out.println(
                        "┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
                System.out.println(
                        "│                     Bill For Your Product                                                                                                     │");
                System.out.println(
                        "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
                System.out.println(
                        "│                                                                                                                                         \t│");
                System.out.println(
                        "│ ID » " + bill_id + " │ Name » " + bill_name + "\t│ Color » " + bill_color + " │ Price » "
                                + bill_price + "                                                              \t\t\t│");
                System.out.println(
                        "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
                System.out.println(
                        "│                                                                                                                                         \t│");
                System.out.println("│ You Bill is : " + totalBill
                        + "                                                                                                            \t\t│");
                System.out.println(
                        "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");

                System.out.println(
                        "└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");

                sc.nextLine();
                System.out.println("Do You want to Download Bill ??");
                String dow_bill = sc.next();
                if (dow_bill.equalsIgnoreCase("yes")) {
                    sc.nextLine();
                    System.out.println("Enter the name of the folder : ");
                    String n = sc.nextLine();
                    File f = new File("D:\\CODE\\Java\\My Projects\\" + n);

                    if (!f.exists()) {
                        f.mkdir();
                    }

                    File f1 = new File(f, bill_name + ".txt");

                    Date currentDate = new Date();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = dateFormat.format(currentDate);

                    FileWriter fw = new FileWriter(f1);

                    fw.write("-------------------------------------------------------\n");
                    fw.write("│ Date and Time: " + formattedDate);
                    fw.write("\n-------------------------------------------------------\n");
                    fw.write("│ ID : " + bill_id + "\n│ Name : " + bill_name + "\n│ Color : " + bill_color
                            + "\n│ Price : " + bill_price);
                    fw.write("\n-------------------------------------------------------");

                    fw.close();
                } else {
                    System.out.println("okk Good Bye");
                }

                break;

            case 2:
                System.out.println("Please Enter The Product ID You Want to Add to Wishlist: ");
                int productId = sc.nextInt();
                if (!a.contains(productId)) {
                    System.out.println("Product with ID " + productId + " is not found in the list.");
                    return;
                }

                String sql1 = "SELECT * FROM product WHERE ID = ?";
                PreparedStatement p1 = con.prepareStatement(sql1);
                p1.setInt(1, productId);
                ResultSet rs1 = p1.executeQuery();
                // int cid = current_user.lid;

                if (rs1.next()) {
                    int id = rs1.getInt("ID");
                    String pname = rs1.getString("p_name");
                    String color = rs1.getString("color");
                    String size = rs1.getString("size");
                    Double price = rs1.getDouble("price");
                    String gender = rs1.getString("gender");

                    String sql2 = "INSERT INTO wishlist (cust_id, ID, p_name, color, size, price, gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement p2 = con.prepareStatement(sql2);
                    p2.setInt(1, 1);
                    p2.setInt(2, id);
                    p2.setString(3, pname);
                    p2.setString(4, color);
                    p2.setString(5, size);
                    p2.setDouble(6, price);
                    p2.setString(7, gender);

                    int rowsAffected = p2.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Product added to wishlist successfully!");
                    } else {
                        System.out.println("Failed to add product to wishlist.");
                    }
                } else {
                    System.out.println("Product with ID " + productId + " not found in the product table.");
                    // }
                }
                break;

            case 3:
                System.out.println("Please Enter The Product ID You Want to Add to Cart: ");
                int productId_incart = sc.nextInt();
                if (!a.contains(productId_incart)) {
                    System.out.println("Product with ID " + productId_incart + " is not found in the list.");
                    con.close();
                    return;
                }

                String sql_cart = "SELECT * FROM product WHERE ID = ?";
                PreparedStatement p_cart = con.prepareStatement(sql_cart);
                p_cart.setInt(1, productId_incart);
                ResultSet rs_cid_cart = p_cart.executeQuery();
                // cid = current_user.lid;

                if (rs_cid_cart.next()) {
                    int id = rs_cid_cart.getInt("ID");
                    String pname = rs_cid_cart.getString("p_name");
                    String color = rs_cid_cart.getString("color");
                    String size = rs_cid_cart.getString("size");
                    Double price = rs_cid_cart.getDouble("price");
                    String gender = rs_cid_cart.getString("gender");

                    String sql_ins_cart = "INSERT INTO  cart (cust_id, ID, p_name, color, size, price, gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement p_ins_cart = con.prepareStatement(sql_ins_cart);
                    p_ins_cart.setInt(1, 1);
                    p_ins_cart.setInt(2, id);
                    p_ins_cart.setString(3, pname);
                    p_ins_cart.setString(4, color);
                    p_ins_cart.setString(5, size);
                    p_ins_cart.setDouble(6, price);
                    p_ins_cart.setString(7, gender);

                    int rowsAffected = p_ins_cart.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Product added to wishlist successfully!");
                    } else {
                        System.out.println("Failed to add product to wishlist.");
                    }
                } else {
                    System.out.println("Product with ID " + productId_incart + " not found in the product table.");
                    // }
                }
                break;

            case 4:
                break;

            default:
                break;
        }
        con.close();
    }

    void bycolor(int i, String gen) throws Exception {
        ArrayList<Integer> idList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String dburl = "jdbc:mysql://localhost:3306/parth";
        String dbuser = "root";
        String dbpassword = "";
        String driver = "com.mysql.jdbc.Driver";

        Class.forName(driver);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);

        Boolean flag = false;
        int bill = 0;
        String sql = "";

        if (gen == male) {
            sql = "SELECT * FROM product WHERE p_id_m = ? AND color = ? AND gender = ?";
        } else {
            sql = "SELECT * FROM product WHERE p_id_wm = ? AND color = ? AND gender = ?";
        }
        PreparedStatement p = con.prepareStatement(sql);

        System.out.println("┌────────────────────────────────────────────────────┐");
        System.out.println("│                     By Color                       │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│ -------------------------------------------------- │");
        System.out.println("│ 1. Write Red                                       │");
        System.out.println("│ 2. Write Blue                                      │");
        System.out.println("│ 3. Write exit to exit                              │");
        System.out.println("└────────────────────────────────────────────────────┘");

        String co = sc.next();
        if (co.equalsIgnoreCase("red") || co.equalsIgnoreCase("blue")) {

            p.setInt(1, i);
            p.setString(2, co);
            p.setString(3, gen);
            ResultSet rs = p.executeQuery();

            System.out.println("1 -> Do you want to see all");
            System.out.println("2 -> move ahed and save this filter");
            int cf = sc.nextInt();
            switch (cf) {
                case 1:
                    System.out.println(
                            "┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
                    System.out.println(
                            "│                     ALL PRODUCT's                                                                                                             │");
                    System.out.println(
                            "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
                    while (rs.next()) {
                        int id = rs.getInt("ID");
                        String name = rs.getString("p_name");
                        String color = rs.getString("color");
                        Double price = rs.getDouble("price");

                        idList.add(id);

                        System.out.println(
                                "│                                                                                                                                         \t│");
                        System.out.println(
                                "│ ID » " + id + " │ Name » " + name + "\t│ Color » " + color + " │ Price » " + price
                                        + "                                                              \t\t\t│");

                        flag = true;
                        bill += price;

                    }
                    System.out.println(
                            "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
                    System.out.println(
                            "│ Please select an option (1-4):                                                                                                                │");
                    System.out.println(
                            "└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");

                    System.out.println("The total bill is : " + bill);

                    if (!flag) {
                        System.out.println("There are no products in this salary range.");
                    }
                    bill(idList);
                    break;

                case 2:
                    save_colour = co;
                    flag = true;
                    break;

                default:
                    break;
            }

        } else if (co.equalsIgnoreCase("exit")) {
            flag = true;
        } else {
            System.out.println("Choose from above code ");
        }
        sc.close();
    }

    void byprice(int i, String gen) throws Exception {
        ArrayList<Integer> idList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        Boolean flag = false;
        int bill = 0;
        Double price = 0.0;

        String sql = "";

        System.out.println("┌────────────────────────────────────────────────────┐");
        System.out.println("│                     By Price                       │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│ -------------------------------------------------- │");
        System.out.println("│ 1. ₹200 - 500                                      │");
        System.out.println("│ 2. ₹500 - 1000                                     │");
        System.out.println("│ 3. ₹1000 - 1500                                    │");
        System.out.println("│ 4. To Exit!!                                       │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│ Please select an option (1-4):                     │");
        System.out.println("└────────────────────────────────────────────────────┘");

        int pn = sc.nextInt();
        if (gen == male) {
            switch (pn) {
                case 1:
                    sql = "SELECT * FROM product WHERE p_id_m = ? AND gender = ? AND price BETWEEN 200 AND 500";
                    break;
                case 2:
                    sql = "SELECT * FROM product WHERE p_id_m = ? AND gender = ? AND price BETWEEN 500 AND 1000";
                    break;
                case 3:
                    sql = "SELECT * FROM product WHERE p_id_m = ? AND gender = ? AND  price BETWEEN 1000 AND 1500";
                    break;
                default:
                    System.out.println("Invalid price range selected.");
                    flag = true;
                    break;
            }
        } else {
            switch (pn) {
                case 1:
                    sql = "SELECT * FROM product WHERE p_id_wm = ? AND gender = ? AND price BETWEEN 200 AND 500";
                    break;
                case 2:
                    sql = "SELECT * FROM product WHERE p_id_wm = ? AND gender = ? AND price BETWEEN 500 AND 1000";
                    break;
                case 3:
                    sql = "SELECT * FROM product WHERE p_id_wm = ? AND gender = ? AND  price BETWEEN 1000 AND 1500";
                    break;
                default:
                    System.out.println("Invalid price range selected.");
                    flag = true;
                    break;
            }
        }

        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, i);
        p.setString(2, gen);
        ResultSet rs = p.executeQuery();

        System.out.println("1 -> Do you want to see all");
        System.out.println("2 -> Move ahed and save this filter");
        int cf = sc.nextInt();
        switch (cf) {
            case 1:
                System.out.println(
                        "┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
                System.out.println(
                        "│                     ALL PRODUCT's                                                                                                             │");
                System.out.println(
                        "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String name = rs.getString("p_name");
                    String color = rs.getString("color");
                    price = rs.getDouble("price");

                    idList.add(id);
                    System.out.println(
                            "│                                                                                                                                         \t│");
                    System.out.println(
                            "│ ID » " + id + " │ Name » " + name + "\t│ Color » " + color + " │ Price » " + price
                                    + "                                                              \t\t\t│");

                    flag = true;
                    bill += price;
                }
                System.out.println(
                        "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
                System.out.println(
                        "│ Please select an option (1-4):                                                                                                                │");
                System.out.println(
                        "└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");

                System.out.println("The total bill is : " + bill);

                if (!flag) {
                    System.out.println("There are no products in this salary range.");
                }
                bill(idList);
                break;

            case 2:
                save_price = pn;
                flag = true;
                break;

            default:
                break;
        }
        sc.close();
    }

    void bysize(int i, String gen) throws Exception {
        ArrayList<Integer> idList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        Boolean flag = false;
        int bill = 0;
        String sql = "";

        if (gen == male) {
            sql = "SELECT * FROM product WHERE p_id_m = ? AND size = ? AND gender = ?";
        } else {
            sql = "SELECT * FROM product WHERE p_id_wm = ? AND size = ? AND gender = ?";
        }
        PreparedStatement p = con.prepareStatement(sql);

        System.out.println("┌────────────────────────────────────────────────────┐");
        System.out.println("│                     By Size                        │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│ -------------------------------------------------- │");
        System.out.println("│ 1. Write Large                                     │");
        System.out.println("│ 2. Write Medium                                    │");
        System.out.println("│ 3. Write Small                                     │");
        System.out.println("│ 4. Write exit to exit                              │");
        System.out.println("└────────────────────────────────────────────────────┘");

        String sz = sc.next();
        if (sz.equalsIgnoreCase("Large") || sz.equalsIgnoreCase("Medium") || sz.equalsIgnoreCase("Small")) {

            p.setInt(1, i);
            p.setString(2, sz);
            p.setString(3, gen);
            ResultSet rs = p.executeQuery();

            System.out.println("1 -> Do you want to see all");
            System.out.println("2 -> move ahed and save this filter");
            int cf = sc.nextInt();
            switch (cf) {
                case 1:
                    System.out.println(
                            "┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
                    System.out.println(
                            "│                     ALL PRODUCT's                                                                                                             │");
                    System.out.println(
                            "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
                    while (rs.next()) {
                        int id = rs.getInt("ID");
                        String name = rs.getString("p_name");
                        String color = rs.getString("color");
                        Double price = rs.getDouble("price");

                        idList.add(id);
                        System.out.println(
                                "│                                                                                                                                         \t│");
                        System.out.println(
                                "│ ID » " + id + " │ Name » " + name + "\t│ Color » " + color + " │ Price » " + price
                                        + "                                                              \t\t\t│");

                        flag = true;
                        bill += price;
                    }
                    System.out.println(
                            "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
                    System.out.println(
                            "│ Please select an option (1-4):                                                                                                                │");
                    System.out.println(
                            "└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");

                    System.out.println("The total bill is : " + bill);

                    if (!flag) {
                        System.out.println("There are no products in this salary range.");
                    }
                    bill(idList);
                    break;

                case 2:
                    save_size = sz;
                    flag = true;
                    break;

                default:
                    break;
            }

        } else if (sz.equalsIgnoreCase("exit")) {
            flag = true;
        } else {
            System.out.println("Choose from above code ");
        }
        sc.close();
    }

    void showall(int i, String gen) throws Exception {
        ArrayList<Integer> idList = new ArrayList<>();

        String sql = "";
        if (gen == male) {
            sql = "select * from product Where p_id_m = ? and gender = ?";
        } else {
            sql = "select * from product Where p_id_wm = ? and gender = ?";
        }

        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, i);
        p.setString(2, gen);
        ResultSet rs = p.executeQuery();

        System.out.println(
                "┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println(
                "│                     ALL PRODUCT's                                                                                                             │");
        System.out.println(
                "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
        while (rs.next()) {
            int id = rs.getInt("ID");
            String name = rs.getString("p_name");
            String color = rs.getString("color");
            Double price = rs.getDouble("price");

            idList.add(id);

            System.out.println(
                    "│                                                                                                                                         \t│");
            System.out.println("│ ID » " + id + " │ Name » " + name + "\t│ Color » " + color + " │ Price » " + price
                    + "                                                              \t\t\t│");

        }
        System.out.println(
                "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
        System.out.println(
                "│ Please select an option (1-4):                                                                                                                │");
        System.out.println(
                "└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");

        bill(idList);

    }

    // void show_whis_cart(String whis_cart) throws Exception {

    // Double bill_c = 0.0;
    // int id;
    // String name;
    // String color;
    // String size;
    // Double price;

    // String sql_wishlist = "SELECT * FROM " + whis_cart + " WHERE cust_id = ?";
    // PreparedStatement p_wishlist = con.prepareStatement(sql_wishlist);
    // p_wishlist.setInt(1, cid);
    // ResultSet rs_wishlist = p_wishlist.executeQuery();

    // if (rs_wishlist.next()) {
    // System.out.println(
    // "┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
    // System.out.println(
    // "│ ALL " + whis_cart
    // + " PRODUCT's │");
    // System.out.println(
    // "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
    // do {
    // id = rs_wishlist.getInt("ID");
    // name = rs_wishlist.getString("p_name");
    // color = rs_wishlist.getString("color");
    // size = rs_wishlist.getString("size");
    // price = rs_wishlist.getDouble("price");

    // System.out.println(
    // "│ \t│");
    // System.out.println("│ ID » " + id + " │ Name » " + name + "\t│ Size » " +
    // size + " | Color » " + color
    // + " │ Price » " + price
    // + " \t\t\t│");

    // } while (rs_wishlist.next());
    // System.out.println(
    // "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
    // System.out.println(
    // "└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
    // sc.nextLine();
    // if (whis_cart.equals("cart")) {
    // System.out.println("Do You Want to Buy All The Product above!!");
    // String c_op = sc.nextLine();
    // if (c_op.equals("yes")) {
    // System.out.println(
    // "┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
    // System.out.println(
    // "│ Bill For Your Product │");
    // System.out.println(
    // "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
    // System.out.println(
    // "│ \t│");

    // ResultSet rs_wishlist1 = p_wishlist.executeQuery();
    // while (rs_wishlist1.next()) {
    // id = rs_wishlist1.getInt("ID");
    // name = rs_wishlist1.getString("p_name");
    // color = rs_wishlist1.getString("color");
    // price = rs_wishlist1.getDouble("price");

    // System.out.println(
    // "│ \t│");
    // System.out.println(
    // "│ ID » " + id + " │ Name » " + name + "\t│ Color » " + color + " │ Price » "
    // + price
    // + " \t\t\t│");
    // bill_c = bill_c + price;

    // }
    // ;
    // System.out.println(
    // "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
    // System.out.println(
    // "│ \t│");
    // System.out.println("│ You Bill is : " + bill_c
    // + " \t\t│");
    // System.out.println(
    // "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");

    // System.out.println(
    // "└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");

    // }
    // } else {
    // System.out.println("noooooooooooooooooooooooo");
    // }

    // System.out.println("Do you want to delete any item form " + whis_cart + "
    // ??");
    // System.out.println("Write Yes or No");
    // String dw = sc.nextLine();
    // switch (dw) {
    // case "yes":
    // System.out.println("Enter the Product id from the " + whis_cart);
    // int wdid = sc.nextInt();

    // String sql_delete_wishlist = "Delete FROM " + whis_cart + " WHERE cust_id = ?
    // and ID = ?";
    // PreparedStatement p_delete_wishlist =
    // con.prepareStatement(sql_delete_wishlist);
    // p_delete_wishlist.setInt(1, cid);
    // p_delete_wishlist.setInt(2, wdid);

    // int rowsDeleted = p_delete_wishlist.executeUpdate();
    // if (rowsDeleted > 0) {
    // System.out.println("Item has been removed successfully");
    // } else {
    // System.out.println("There is no item from " + whis_cart);
    // }
    // home();
    // break;

    // case "no":
    // home();
    // break;

    // default:
    // break;
    // }

    // } else {
    // System.out.println(whis_cart + " is empty!!");
    // home();
    // }

    // }

    void show_byfilter(int i, int save_price, String save_colour, String save_size, String gen) throws Exception {
        ArrayList<Integer> idList = new ArrayList<>();

        System.out.println(save_price);
        System.out.println(save_colour);
        System.out.println(save_size);

        String sql = "";
        if (gen == male) {
            switch (save_price) {
                case 1:
                    sql = "select * from product Where p_id_m = ? and color = ? and size = ? AND gender = ? and price BETWEEN 200 AND 500";
                    break;
                case 2:
                    sql = "select * from product Where p_id_m = ? and color = ? and size = ? AND gender = ? and price BETWEEN 500 AND 1000";
                    break;
                case 3:
                    sql = "select * from product Where p_id_m = ? and color = ? and size = ? AND gender = ? and price BETWEEN 1000 AND 1500";
                    break;
                default:
                    System.out.println("Invalid price range selected.");
                    break;
            }
        } else {
            switch (save_price) {
                case 1:
                    sql = "select * from product Where p_id_wm = ? and color = ? and size = ? AND gender = ? and price BETWEEN 200 AND 500";
                    break;
                case 2:
                    sql = "select * from product Where p_id_wm = ? and color = ? and size = ? AND gender = ? and price BETWEEN 500 AND 1000";
                    break;
                case 3:
                    sql = "select * from product Where p_id_wm = ? and color = ? and size = ? AND gender = ? and price BETWEEN 1000 AND 1500";
                    break;
                default:
                    System.out.println("Invalid price range selected.");
                    break;
            }

        }
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, i);
        p.setString(2, save_colour);
        p.setString(3, save_size);
        p.setString(4, gen);
        ResultSet rs = p.executeQuery();

        System.out.println(
                "┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println(
                "│                     ALL PRODUCT's                                                                                                             │");
        System.out.println(
                "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
        while (rs.next()) {
            int id = rs.getInt("ID");
            String name = rs.getString("p_name");
            String color = rs.getString("color");
            Double price = rs.getDouble("price");

            idList.add(id);

            System.out.println(
                    "│                                                                                                                                         \t│");
            System.out.println("│ ID » " + id + " │ Name » " + name + "\t│ Color » " + color + " │ Price » " + price
                    + "                                                              \t\t\t│");
        }
        System.out.println(
                "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
        System.out.println(
                "│ Please select an option (1-4):                                                                                                                │");
        System.out.println(
                "└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");

        bill(idList);
        con.close();
    }

    int sn1 = 0;

    void Val(String gen) throws Exception {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("┌────────────────────────────────────────────────────┐");
            System.out.println("│                       FOR MEN                      │");
            System.out.println("├────────────────────────────────────────────────────┤");
            System.out.println("│ -------------------------------------------------- │");
            System.out.println("│ 1. T-shirts                                        │");
            System.out.println("│ 2. Over Sized T-shirts                             │");
            System.out.println("│ 3. Shirts                                          │");
            System.out.println("│ 4. Go TO Main Menu                                 │");
            System.out.println("├────────────────────────────────────────────────────┤");
            System.out.println("│ Please select an option (1-4):                     │");
            System.out.println("└────────────────────────────────────────────────────┘");

            sn1 = sc.nextInt();
            switch (sn1) {
                case 1:
                    m_wm_filters(gen);
                    break;
                case 2:
                    break;

                case 4:
                    home();
                    break;
            }
        } while (sn1 != 4);
        con.close();
        sc.close();
    }

    void m_wm_filters(String gen) throws Exception {
        int a1;

        System.out.println("┌────────────────────────────────────────────────────┐");
        System.out.println("│                       OPTION's                     │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│ -------------------------------------------------- │");
        System.out.println("│ 1. To See All                                      │");
        System.out.println("│ 2. See By Filter                                   │");
        System.out.println("│ 3. Go TO Main Menu                                 │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│ Please select an option (1-3):                     │");
        System.out.println("└────────────────────────────────────────────────────┘");

        a1 = sc.nextInt();
        switch (a1) {
            case 1:
                showall(sn1, gen);
                // bill();
                break;

            case 2:
                int fn;
                do {
                    System.out.println("┌────────────────────────────────────────────────────┐");
                    System.out.println("│                       Filter's                     │");
                    System.out.println("├────────────────────────────────────────────────────┤");
                    System.out.println("│ -------------------------------------------------- │");
                    System.out.println("│ 1. Price                                           │");
                    System.out.println("│ 2. colour                                          │");
                    System.out.println("│ 3. Size                                            │");
                    System.out.println("│ 4. see by saved filters                            │");
                    System.out.println("│ 5. Go TO Main Menu                                 │");
                    System.out.println("├────────────────────────────────────────────────────┤");
                    System.out.println("│ Please select an option (1-5):                     │");
                    System.out.println("└────────────────────────────────────────────────────┘");

                    fn = sc.nextInt();
                    switch (fn) {
                        case 1:
                            byprice(sn1, gen);

                            break;
                        case 2:
                            bycolor(sn1, gen);

                            break;
                        case 3:
                            bysize(sn1, gen);

                            break;
                        case 4:
                            if (save_price != 0 && save_colour != "" && save_size != "") {
                                show_byfilter(sn1, save_price, save_colour, save_size, gen);

                            } else {
                                System.out.println("Please save all three Filter's");
                            }
                            break;

                        default:
                            break;
                    }

                } while (fn != 5);
                break;

            case 3:
                home();
                break;
        }

    }

    void Val1(String gen) throws Exception {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("┌────────────────────────────────────────────────────┐");
            System.out.println("│                       FOR WOMEN                    │");
            System.out.println("├────────────────────────────────────────────────────┤");
            System.out.println("│ -------------------------------------------------- │");
            System.out.println("│ 1. Kurti                                           │");
            System.out.println("│ 2. Dress                                           │");
            System.out.println("│ 3.                                                 │");
            System.out.println("│ 4. Go TO Main Menu                                 │");
            System.out.println("├────────────────────────────────────────────────────┤");
            System.out.println("│ Please select an option (1-4):                     │");
            System.out.println("└────────────────────────────────────────────────────┘");

            sn1 = sc.nextInt();
            switch (sn1) {
                case 1:

                    m_wm_filters(gen);
                    break;
                case 2:

                    m_wm_filters(gen);
                    break;

                case 4:
                    home();

                    break;
            }
        } while (sn1 != 4);
        con.close();
        sc.close();
    }

    void orderHistory() {

    }

    void home() throws Exception {

        System.out.println("┌────────────────────────────────────────────────────┐");
        System.out.println("│        Welcome to The Amazing Clothing Mart        │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│ Main Menu:                                         │");
        System.out.println("│ -------------------------------------------------- │");
        System.out.println("│ 1. Shop For Men                                    │");
        System.out.println("│ 2. Shop For Women                                  │");
        System.out.println("│ 3. Explore New Fashion Products                    │");
        System.out.println("│ 4. View Your Wishlist                              │");
        System.out.println("│ 5. View Your Shopping Cart                         │");
        System.out.println("│ 6. Check Your Order History                        │");
        System.out.println("│ 7. Exit                                            │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│ Please select an option (1-7):                     │");
        System.out.println("└────────────────────────────────────────────────────┘");

        int hn = 0;
        try {
            hn = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid Input.");
            home();
        }

        switch (hn) {
            case 1:
                Val(male);
                break;

            case 2:
                Val1(female);
                break;

            case 4:
                // String w = "wishlist";
                // show_whis_cart(w);
                break;

            case 5:
                // String c = "cart";
                // show_whis_cart(c);
                break;
            case 6:
                orderHistory();
                break;

            case 7:
                System.out.println("Exiting...Visit Again!");
                return;

            default:
                System.out.println("Invalid Number.");
                home();
                break;
        }

    }
}
