import java.sql.*;
import java.util.*;
import java.io.*;

class other {
    Scanner sc = new Scanner(System.in);
    String name, ad, login_id, password;
    int id, lid = 1;

    void entry() throws Exception {
        String dburl = "jdbc:mysql://localhost:3306/e-clothing";
        String dbuser = "root";
        String dbpassword = "";
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);

        Boolean flag = false;

        do {

            System.out.println("Welcome to Your Fav E-Mark !!!!!!!");
            System.out.println("-------------------------------------------------------");
            System.out.println("1 -> Sign up");
            System.out.println("2 -> Log in");
            int sl = sc.nextInt();
            switch (sl) {
                case 1:

                    String sql = "INSERT INTO customer (c_id, c_name, c_pw, c_address, c_email, c_pno) VALUES(?, ?, ?, ?, ?, ?)";
                    PreparedStatement p = con.prepareStatement(sql);

                    System.out.println("Enter the Your ID :");
                    id = sc.nextInt();
                    p.setInt(1, id);

                    sc.nextLine();
                    System.out.println("Enter Your Name :");
                    String n = sc.next();
                    p.setString(2, n);

                    String pw;
                    outer: while (true) {
                        System.out.println("Enter Your Password :");
                        pw = sc.next();
                        String str = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*";
                        if (pw.length() < 8) {
                            System.out.println("INVALID Password HAVE SMALL SIZE");
                            continue outer;
                        } else if (pw.length() >= 8) {
                            if (pw.matches(str)) {
                                char pass[] = new char[pw.length()];
                                for (int j = 0; j < pw.length(); j++) {
                                    pass[j] = pw.charAt(j);
                                    if (pass[j] == ' ') {
                                        System.out.println("INVALID Password CONTAINS SPACE");
                                        continue outer;
                                    }
                                }
                            } else if (pw.matches(str) == false) {
                                System.out.println("INVALID Password DON'T HAVE SPECIAL");
                                continue outer;
                            }
                            break outer;
                        }
                    }
                    System.out.println("PASSWORD SET SUCCESSFULLY");
                    p.setString(3, pw);

                    sc.nextLine();
                    System.out.println("Enter the Address :");
                    String add = sc.nextLine();
                    p.setString(4, add);

                    System.out.println("Enter the E-mail :");
                    String email = sc.nextLine();
                    p.setString(5, email);

                    System.out.println("Enter the pno :");
                    int pno = sc.nextInt();
                    p.setInt(6, pno);

                    int rowsInserted = p.executeUpdate();

                    if (rowsInserted > 0) {
                        System.out.println("A new record was inserted successfully.");
                        // flag = true;
                    } else {
                        System.out.println("Insertion failed.");
                    }
                    break;
                case 2:

                    String sql2 = "select * from customer where c_id = ? and c_name = ?";
                    PreparedStatement p2 = con.prepareStatement(sql2);
                    System.out.println("Enter Your ID : ");
                    lid = sc.nextInt();
                    System.out.println("Enter Your Name : ");
                    String lname = sc.next();
                    p2.setInt(1, lid);
                    p2.setString(2, lname);
                    ResultSet rs = p2.executeQuery();

                    if (!rs.next()) {
                        System.out.println("You Don't Have An Account, Please Sign-In ");
                    } else {
                        System.out.println("YOU have Loged-in Successfully");
                        flag = true;
                    }

                default:
                    break;
            }
        } while (!flag);
    }
}

class Shop {
    other o = new other();

    int save_price;
    String save_colour;
    String save_size;
    String male = "male";
    String female = "female";

    Scanner sc = new Scanner(System.in);

    void download_bill() {
        try {

            // System.out.println("=====================================");
            // System.out.println(" Invoice ");
            // System.out.println("=====================================");
            // System.out.println("Order Number: " + orderNumber);
            // System.out.println("Order Date: " + new
            // SimpleDateFormat("MM/dd/yyyy").format(orderDate));
            // System.out.println("Shipping Address: " + shippingAddress);
            // System.out.println("Billing Address: " + billingAddress);
            // System.out.println("=====================================");
            // System.out.println("Product Details:");
            // System.out.println("=====================================");
            // System.out.println(productNames[i]);
            // System.out.println("Description: " + productDescriptions[i]);
            // System.out.println("Quantity: " + quantities[i]);
            // System.out.println("Price per Unit: $" + pricesPerUnit[i]);
            // System.out.println("Total Price: $" + (quantities[i] * pricesPerUnit[i]));
            // System.out.println("------------------------------");

            // System.out.println("=====================================");
            // System.out.println("Subtotal: $" + subtotal);
            // System.out.println("Shipping and Handling: $" + shippingAndHandling);
            // System.out.println("Tax (" + (taxRate * 100) + "%): $" + tax);
            // System.out.println("Total Amount: $" + totalAmount);
            // System.out.println("=====================================");

            // String s1 = "kemcho";
            // File f2 = new File("D:\\CODE\\Java\\My Projects\\"+s1+".txt");
            // f2.createNewFile();
            System.out.println("Enter the name of the folder : ");
            String n = sc.nextLine();
            File f = new File("D:\\CODE\\Java\\My Projects" + n);
            f.mkdir();

            System.out.println("Enter the File Name : ");
            String fn = sc.nextLine();

            File f1 = new File(f, fn + ".txt");

            FileWriter fw = new FileWriter(f1);

            System.out.println("And Your Name : ");
            String s = sc.nextLine();
            fw.write("Name : " + s);
            fw.write("\n--------------------\n");
            System.out.println("And Your G-Mail : ");
            String g = sc.nextLine();
            fw.write("G-Mail : " + g);
            fw.write("\n--------------------\n");
            System.out.println("And Your Address : ");
            String a = sc.nextLine();
            fw.write("Address : " + a);
            fw.write("\n--------------------\n");
            System.out.println("And Your Contact Number : ");
            String p = sc.nextLine();
            fw.write("Contact Number : " + p);
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Success...");
    }

    void bill() throws Exception {
        String dburl = "jdbc:mysql://localhost:3306/e-clothing";
        String dbuser = "root";
        String dbpassword = "";
        String driver = "com.mysql.jdbc.Driver";

        Class.forName(driver);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);

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
        // System.out.println("1 -> I want to Purchase the item from above !!");
        // System.out.println("2 -> To Add Product in My Wishlist !!");
        // System.out.println("3 -> To Add Product in My Cart !!");
        // System.out.println("4 -> To Exit!!");
        int yn = sc.nextInt();
        switch (yn) {
            case 1:
                System.out.println("Please Enter The ID of the Product You want to enter ");
                int billid = sc.nextInt();
                String sql = "select * from product Where ID = ? ";
                PreparedStatement p = con.prepareStatement(sql);
                p.setInt(1, billid);
                ResultSet rs = p.executeQuery();
                double totalBill = 0.0;
                // if (!rs.next()) {
                // System.out.println("Product with ID " + billid + " is not found in the
                // database.");
                // } else {
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
                            "│ ID » " + bill_id + " │ Name » " + bill_name + "\t│ Color » " + bill_color + " │ Price » " + bill_price
                                    + "                                                              \t\t\t│");
                    // System.out.println("ID: " + id + ", Product Name: " + name + ", Color: "
                    // +color+ "Price: " + price);
                    totalBill += bill_price;
                }
                System.out.println(
                        "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
                System.out.println(
                        "│ Please select an option (1-4):                                                                                                                │");
                System.out.println(
                        "└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
                // while (rs.next()) {
                // int id = rs.getInt("ID");
                // String name = rs.getString("p_name");
                // String color = rs.getString("color");
                // Double price = rs.getDouble("price");

                // System.out.println("ID: " + id + ", Product Name: " + name + ", Color: " +
                // color
                // + "Price: " + price);
                // // }
                // }
                System.out.println("┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
                System.out.println("│                     Bill For Your Product                                                                                                     │");
                System.out.println("├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
                System.out.println("│                                                                                                                                         \t│");
                System.out.println("│ ID » " + bill_id + " │ Name » " + bill_name + "\t│ Color » " + bill_color + " │ Price » " + bill_price+ "                                                              \t\t\t│");
                System.out.println("├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
                System.out.println("│                                                                                                                                         \t│");
                System.out.println("│ You Bill is : " + totalBill+ "                                                                                                            \t\t│");
                System.out.println("├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
                // System.out.println("│ Please select an option (1-4):                                                                                                                │");
                System.out.println("└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");

                break;

            case 2:
                System.out.println("Please Enter The Product ID You Want to Add to Wishlist: ");
                int productId = sc.nextInt();

                // System.out.println("Enter Your customer id as you saved ago");
                // int cid = sc.nextInt();
                // String sql_wishlist = "SELECT * FROM customer WHERE c_id = ?";
                // PreparedStatement p_wishlist = con.prepareStatement(sql_wishlist);
                // p_wishlist.setInt(1, cid);
                // ResultSet rs_wishlist = p_wishlist.executeQuery();

                // if (!rs_wishlist.next()) {
                // System.out.println("PLEASE Enter The correct Customer id");
                // } else {
                String sql1 = "SELECT * FROM product WHERE ID = ?";
                PreparedStatement p1 = con.prepareStatement(sql1);
                p1.setInt(1, productId);
                ResultSet rs1 = p1.executeQuery();
                int cid = o.lid;

                if (rs1.next()) {
                    int id = rs1.getInt("ID");
                    String pname = rs1.getString("p_name");
                    String color = rs1.getString("color");
                    String size = rs1.getString("size");
                    Double price = rs1.getDouble("price");
                    String gender = rs1.getString("gender");

                    String sql2 = "INSERT INTO wishlist (cust_id, ID, p_name, color, size, price, gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement p2 = con.prepareStatement(sql2);
                    p2.setInt(1, cid);
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

                // System.out.println("Enter Your customer id as you saved ago");
                // int cid = sc.nextInt();
                // String sql_wishlist = "SELECT * FROM customer WHERE c_id = ?";
                // PreparedStatement p_wishlist = con.prepareStatement(sql_wishlist);
                // p_wishlist.setInt(1, cid);
                // ResultSet rs_wishlist = p_wishlist.executeQuery();

                // if (!rs_wishlist.next()) {
                // System.out.println("PLEASE Enter The correct Customer id");
                // } else {
                String sql_cart = "SELECT * FROM product WHERE ID = ?";
                PreparedStatement p_cart = con.prepareStatement(sql_cart);
                p_cart.setInt(1, productId_incart);
                ResultSet rs_cid_cart = p_cart.executeQuery();
                cid = o.lid;

                if (rs_cid_cart.next()) {
                    int id = rs_cid_cart.getInt("ID");
                    String pname = rs_cid_cart.getString("p_name");
                    String color = rs_cid_cart.getString("color");
                    String size = rs_cid_cart.getString("size");
                    Double price = rs_cid_cart.getDouble("price");
                    String gender = rs_cid_cart.getString("gender");

                    String sql_ins_cart = "INSERT INTO  cart (cust_id, ID, p_name, color, size, price, gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement p_ins_cart = con.prepareStatement(sql_ins_cart);
                    p_ins_cart.setInt(1, cid);
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
        Scanner sc = new Scanner(System.in);
        String dburl = "jdbc:mysql://localhost:3306/e-clothing";
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
        // sc.nextLine();

        // while (!flag) {

        System.out.println("┌────────────────────────────────────────────────────┐");
        System.out.println("│                     By Color                       │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│ -------------------------------------------------- │");
        System.out.println("│ 1. Write Red                                       │");
        System.out.println("│ 2. Write Blue                                      │");
        System.out.println("│ 3. Write exit to exit                              │");
        System.out.println("└────────────────────────────────────────────────────┘");
        // System.out.println("write red");
        // System.out.println("write blue");
        // System.out.println("write exit to exit");
        // System.out.println("Enter the above numbers: ");
        String co = sc.next();
        if (co.equalsIgnoreCase("red") || co.equalsIgnoreCase("blue")) {
            // System.out.println("Choose from above ");
            // int id1 = sc.nextInt();
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

                        System.out.println(
                                "│                                                                                                                                         \t│");
                        System.out.println(
                                "│ ID » " + id + " │ Name » " + name + "\t│ Color » " + color + " │ Price » " + price
                                        + "                                                              \t\t\t│");
                        // System.out.println("ID: " + id + ", Product Name: " + name + ", Color: "
                        // +color+ "Price: " + price);
                        flag = true;
                        bill += price;

                    }
                    System.out.println(
                            "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
                    System.out.println(
                            "│ Please select an option (1-4):                                                                                                                │");
                    System.out.println(
                            "└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
                    // while (rs.next()) {
                    // int id = rs.getInt("ID");
                    // String name = rs.getString("p_name");
                    // String color = rs.getString("color");
                    // Double price = rs.getDouble("price");

                    // System.out.println("ID: " + id + ", Product Name: " + name + ", Color: " +
                    // color
                    // + "Price: " + price);

                    // flag = true;
                    // bill += price;
                    // }

                    System.out.println("The total bill is : " + bill);

                    if (!flag) {
                        System.out.println("There are no products in this salary range.");
                    }
                    bill();
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
        // }
    }

    void byprice(int i, String gen) throws Exception {
        Scanner sc = new Scanner(System.in);
        String dburl = "jdbc:mysql://localhost:3306/e-clothing";
        String dbuser = "root";
        String dbpassword = "";
        String driver = "com.mysql.jdbc.Driver";

        Class.forName(driver);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);

        Boolean flag = false;
        int bill = 0;
        Double price = 0.0;

        String sql = "";

        // for ₹ -> clt + alt + 4
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
        // System.out.println("1 -> ₹200-500");
        // System.out.println("2 -> ₹500-1000");
        // System.out.println("3 -> ₹1000-1500");
        // System.out.println("Enter the above numbers: ");
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

                    System.out.println(
                            "│                                                                                                                                         \t│");
                    System.out.println(
                            "│ ID » " + id + " │ Name » " + name + "\t│ Color » " + color + " │ Price » " + price
                                    + "                                                              \t\t\t│");
                    // System.out.println("ID: " + id + ", Product Name: " + name + ", Color: "
                    // +color+ "Price: " + price);
                    flag = true;
                    bill += price;
                }
                System.out.println(
                        "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
                System.out.println(
                        "│ Please select an option (1-4):                                                                                                                │");
                System.out.println(
                        "└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
                // while (rs.next()) {
                // int id = rs.getInt("ID");
                // String name = rs.getString("p_name");
                // String color = rs.getString("color");
                // price = rs.getDouble("price");

                // System.out.println("ID: " + id + ", Product Name: " + name + ", Color: " +
                // color
                // + "Price: " + price);

                // flag = true;
                // bill += price;
                // }

                System.out.println("The total bill is : " + bill);

                if (!flag) {
                    System.out.println("There are no products in this salary range.");
                }
                bill();
                break;

            case 2:
                save_price = pn;
                flag = true;
                break;

            default:
                break;
        }
    }

    void bysize(int i, String gen) throws Exception {
        Scanner sc = new Scanner(System.in);
        String dburl = "jdbc:mysql://localhost:3306/e-clothing";
        String dbuser = "root";
        String dbpassword = "";
        String driver = "com.mysql.jdbc.Driver";

        Class.forName(driver);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);

        Boolean flag = false;
        int bill = 0;
        String sql = "";

        if (gen == male) {
            sql = "SELECT * FROM product WHERE p_id_m = ? AND size = ? AND gender = ?";
        } else {
            sql = "SELECT * FROM product WHERE p_id_wm = ? AND size = ? AND gender = ?";
        }
        PreparedStatement p = con.prepareStatement(sql);
        // sc.nextLine();

        System.out.println("┌────────────────────────────────────────────────────┐");
        System.out.println("│                     By Size                        │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│ -------------------------------------------------- │");
        System.out.println("│ 1. Write Large                                     │");
        System.out.println("│ 2. Write Medium                                    │");
        System.out.println("│ 3. Write Small                                     │");
        System.out.println("│ 4. Write exit to exit                              │");
        System.out.println("└────────────────────────────────────────────────────┘");
        // System.out.println("write Large");
        // System.out.println("write Medium");
        // System.out.println("write Small");
        // System.out.println("write exit to exit");
        // System.out.println("Enter the above numbers: ");
        String sz = sc.next();
        if (sz.equalsIgnoreCase("Large") || sz.equalsIgnoreCase("Medium") || sz.equalsIgnoreCase("Small")) {
            // System.out.println("Choose from above ");
            // int id1 = sc.nextInt();
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

                        System.out.println(
                                "│                                                                                                                                         \t│");
                        System.out.println(
                                "│ ID » " + id + " │ Name » " + name + "\t│ Color » " + color + " │ Price » " + price
                                        + "                                                              \t\t\t│");
                        // System.out.println("ID: " + id + ", Product Name: " + name + ", Color: "
                        // +color+ "Price: " + price);
                        flag = true;
                        bill += price;
                    }
                    System.out.println(
                            "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
                    System.out.println(
                            "│ Please select an option (1-4):                                                                                                                │");
                    System.out.println(
                            "└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
                    // while (rs.next()) {
                    // int id = rs.getInt("ID");
                    // String name = rs.getString("p_name");
                    // String color = rs.getString("color");
                    // Double price = rs.getDouble("price");

                    // System.out.println("ID: " + id + ", Product Name: " + name + ", Color: " +
                    // color
                    // + "Price: " + price);

                    // flag = true;
                    // bill += price;
                    // }

                    System.out.println("The total bill is : " + bill);

                    if (!flag) {
                        System.out.println("There are no products in this salary range.");
                    }
                    bill();
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
    }

    // void bycolor1(int i) throws Exception {
    // Scanner sc = new Scanner(System.in);
    // String dburl = "jdbc:mysql://localhost:3306/e-clothing";
    // String dbuser = "root";
    // String dbpassword = "";
    // String driver = "com.mysql.jdbc.Driver";

    // Class.forName(driver);
    // Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);
    // if (con != null) {
    // System.out.println("Connected Successfull");
    // } else {
    // System.out.println("Connection Failed");
    // }

    // boolean flag = false;
    // String co;
    // int pn;

    // System.out.println("1 -> 200-500");
    // System.out.println("2 -> 500-1000");
    // System.out.println("3 -> 1000-1500");
    // System.out.println("Enter the above numbers: ");
    // pn = sc.nextInt();

    // while (!flag) {
    // String sql = "";

    // switch (pn) {
    // case 1:
    // sql = "SELECT * FROM product WHERE p_id_m = ? AND color = ? AND price BETWEEN
    // 200 AND 500";
    // break;
    // case 2:
    // sql = "SELECT * FROM product WHERE p_id_m = ? AND color = ? AND price BETWEEN
    // 500 AND 1000";
    // break;
    // case 3:
    // sql = "SELECT * FROM product WHERE p_id_m = ? AND color = ? AND price BETWEEN
    // 1000 AND 1500";
    // break;
    // default:
    // System.out.println("Invalid price range selected.");
    // flag = true;
    // break;
    // }

    // if (!flag) {
    // PreparedStatement p = con.prepareStatement(sql);
    // sc.nextLine();
    // System.out.println("write red");
    // System.out.println("write blue");
    // System.out.println("write exit to exit");
    // System.out.println("Enter the above numbers: ");
    // co = sc.nextLine();

    // if (co.equalsIgnoreCase("red") || co.equalsIgnoreCase("blue")) {
    // p.setInt(1, i);
    // p.setString(2, co);
    // ResultSet rs = p.executeQuery();

    // boolean found = false; // Flag to check if any products were found in the
    // price range

    // while (rs.next()) {
    // int id = rs.getInt("p_id_m");
    // String name = rs.getString("p_name");
    // String color = rs.getString("color");
    // Double price = rs.getDouble("price");

    // System.out.println("ID: " + id + ", Product Name: " + name + ", Color: " +
    // color
    // + "Price: " + price);

    // found = true; // Products were found in the price range
    // }

    // if (!found) {
    // System.out.println("There are no products in this price range.");
    // } else {
    // // o.bill(i,co); // Print the total bill
    // }
    // } else if (co.equalsIgnoreCase("exit")) {
    // flag = true;
    // } else {
    // System.out.println("Choose from above code ");
    // }
    // }
    // }
    // con.close();
    // }

    void showall(int i, String gen) throws Exception {
        String dburl = "jdbc:mysql://localhost:3306/e-clothing";
        String dbuser = "root";
        String dbpassword = "";
        String driver = "com.mysql.jdbc.Driver";

        Class.forName(driver);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);
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

            // System.out.printf("|Name: %s, Color: %s|", name, color);
            System.out.println(
                    "│                                                                                                                                         \t│");
            System.out.println("│ ID » " + id + " │ Name » " + name + "\t│ Color » " + color + " │ Price » " + price
                    + "                                                              \t\t\t│");
            // System.out.println("ID: " + id + ", Product Name: " + name + ", Color: ");
            // +color+ "Price: " + price);
        }
        System.out.println(
                "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
        System.out.println(
                "│ Please select an option (1-4):                                                                                                                │");
        System.out.println(
                "└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
        con.close();
    }

    void show_wishlist() throws Exception {
        String dburl = "jdbc:mysql://localhost:3306/e-clothing";
        String dbuser = "root";
        String dbpassword = "";
        String driver = "com.mysql.jdbc.Driver";

        Class.forName(driver);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);
        int cid = o.lid;
        // System.out.println("Enter Your customer id as you saved ago");
        // int cid = sc.nextInt();

        String sql_wishlist = "SELECT * FROM wishlist WHERE cust_id = ?";
        PreparedStatement p_wishlist = con.prepareStatement(sql_wishlist);
        p_wishlist.setInt(1, cid);
        ResultSet rs_wishlist = p_wishlist.executeQuery();

        if (rs_wishlist.next()) {
            System.out.println(
                    "┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
            System.out.println(
                    "│                     ALL WISHLIST PRODUCT's                                                                                                    │");
            System.out.println(
                    "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
            do {
                int id = rs_wishlist.getInt("ID");
                String name = rs_wishlist.getString("p_name");
                String color = rs_wishlist.getString("color");
                String size = rs_wishlist.getString("size");
                Double price = rs_wishlist.getDouble("price");

                System.out.println(
                        "│                                                                                                                                         \t│");
                System.out.println("│ ID » " + id + " │ Name » " + name + "\t│ Color » " + color + " │ Price » " + price
                        + "                                                              \t\t\t│");
                // System.out.println("ID: " + id + ", Product Name: " + name + ", Color: " +
                // color + "Price: " + price
                // + "Size: " + size);

            } while (rs_wishlist.next());
            System.out.println(
                    "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
            System.out.println(
                    "└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
            sc.nextLine();
            System.out.println("Do you want to delete any item form wishlist ??");
            System.out.println("Write Yes or No");
            String dw = sc.nextLine();
            switch (dw) {
                case "yes":
                    System.out.println("Enter the Product id from the wishlist");
                    int wdid = sc.nextInt();
                    // cid = 0;
                    String sql_delete_wishlist = "Delete FROM wishlist WHERE cust_id = ? and ID = ?";
                    PreparedStatement p_delete_wishlist = con.prepareStatement(sql_delete_wishlist);
                    p_delete_wishlist.setInt(1, cid);
                    p_delete_wishlist.setInt(2, wdid);
                    // ResultSet rs_delete_wishlist = p_delete_wishlist.executeQuery();
                    int rowsDeleted = p_delete_wishlist.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Item has been removed successfully");
                    } else {
                        System.out.println("There is no item there in wishlist");
                    }
                    home();
                    break;
                case "no":
                    home();
                    break;

                default:
                    break;
            }

        } else {
            System.out.println("wishlist is empty!!");
            home();
        }

        con.close();

    }

    void show_cart() throws Exception {
        String dburl = "jdbc:mysql://localhost:3306/e-clothing";
        String dbuser = "root";
        String dbpassword = "";
        String driver = "com.mysql.jdbc.Driver";

        Class.forName(driver);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);
        int cid = o.lid;
        // System.out.println("Enter Your customer id as you saved ago");
        // int cid = sc.nextInt();

        String sql_cart = "SELECT * FROM cart WHERE cust_id = ?";
        PreparedStatement p_cart = con.prepareStatement(sql_cart);
        p_cart.setInt(1, cid);
        ResultSet rs_cart = p_cart.executeQuery();

        if (rs_cart.next()) {
            System.out.println(
                    "┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
            System.out.println(
                    "│                     ALL CART PRODUCT's                                                                                                        │");
            System.out.println(
                    "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
            System.out.println(
                    "│                                                                                                                                         \t│");
            do {
                int id = rs_cart.getInt("ID");
                String name = rs_cart.getString("p_name");
                String color = rs_cart.getString("color");
                String size = rs_cart.getString("size");
                Double price = rs_cart.getDouble("price");

                System.out.println("│ ID » " + id + " │ Name » " + name + "\t│ Color » " + color + " │ Price » " + price
                        + "                                                              \t\t\t│");
                // System.out.println("ID: " + id + ", Product Name: " + name + ", Color: " +
                // color + "Price: " + price
                // + "Size: " + size);

            } while (rs_cart.next());
            System.out.println(
                    "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
            System.out.println(
                    "└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
            sc.nextLine();
            System.out.println("Do you want to delete any item form cart ??");
            System.out.println("Write Yes or No");
            String dw = sc.nextLine();
            switch (dw) {
                case "yes":
                    System.out.println("Enter the Product id from the cart");
                    int wdid = sc.nextInt();
                    // cid = 0;
                    String sql_delete_cart = "Delete FROM cart WHERE cust_id = ? and ID = ?";
                    PreparedStatement p_delete_cart = con.prepareStatement(sql_delete_cart);
                    p_delete_cart.setInt(1, cid);
                    p_delete_cart.setInt(2, wdid);
                    // ResultSet rs_delete_cart = p_delete_cart.executeQuery();
                    int rowsDeleted = p_delete_cart.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Item has been removed successfully");
                    } else {
                        System.out.println("There is no item there in cart");
                    }
                    home();
                    break;
                case "no":
                    home();
                    break;

                default:
                    break;
            }

        } else {
            System.out.println("Cart is empty!!");
            home();
        }

        con.close();

    }

    // void showall1(int i) throws Exception {
    // String dburl = "jdbc:mysql://localhost:3306/e-clothing";
    // String dbuser = "root";
    // String dbpassword = "";
    // String driver = "com.mysql.jdbc.Driver";

    // Class.forName(driver);
    // Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);
    // if (con != null) {
    // System.out.println("Connected Successfull");
    // } else {
    // System.out.println("Connection Failed");
    // }

    // String sql = "select * from product Where p_id_wm = ?";
    // PreparedStatement p = con.prepareStatement(sql);
    // p.setInt(1, i);
    // ResultSet rs = p.executeQuery();

    // while (rs.next()) {
    // int id = rs.getInt("ID");
    // String name = rs.getString("p_name");
    // String color = rs.getString("color");
    // Double price = rs.getDouble("price");

    // System.out.println("ID: " + id + ", Product Name: " + name + ", Color: " +
    // color
    // + "Price: " + price);
    // }
    // con.close();
    // }

    void show_byfilter(int i, int save_price, String save_colour, String save_size, String gen) throws Exception {
        String dburl = "jdbc:mysql://localhost:3306/e-clothing";
        String dbuser = "root";
        String dbpassword = "";
        String driver = "com.mysql.jdbc.Driver";

        System.out.println(save_price);
        System.out.println(save_colour);
        System.out.println(save_size);
        Class.forName(driver);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);

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

            System.out.println(
                    "│                                                                                                                                         \t│");
            System.out.println("│ ID » " + id + " │ Name » " + name + "\t│ Color » " + color + " │ Price » " + price
                    + "                                                              \t\t\t│");
            // System.out.println("ID: " + id + ", Product Name: " + name + ", Color: "
            // +color+ "Price: " + price);
        }
        System.out.println(
                "├───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
        System.out.println(
                "│ Please select an option (1-4):                                                                                                                │");
        System.out.println(
                "└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
        // while (rs.next()) {
        // int id = rs.getInt("ID");
        // String name = rs.getString("p_name");
        // String color = rs.getString("color");
        // Double price = rs.getDouble("price");

        // System.out.println("ID: " + id + ", Product Name: " + name + ", Color: " +
        // color
        // + "Price: " + price);
        // }
        con.close();
    }

    int sn1;

    void Val(String gen) throws Exception {
        Scanner sc = new Scanner(System.in);
        String dburl = "jdbc:mysql://localhost:3306/mydb";
        String dbuser = "root";
        String dbpassword = "";
        String driver = "com.mysql.jdbc.Driver";

        Class.forName(driver);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);

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
            // System.out.println("1 -> T-shirts");
            // System.out.println("2 -> Over Sized T-shirts");
            // System.out.println("3 -> Shirts");
            // System.out.println("4 -> Go TO Home");
            // System.out.println("Enter the above numbers: ");
            sn1 = sc.nextInt();
            switch (sn1) {
                case 1:
                    men_filters(gen);
                    break;
                case 2:
                    break;

                case 4:
                    home();
                    break;
            }
        } while (sn1 != 4);
        con.close();
    }

    void men_filters(String gen) throws Exception {
        int a1;
        // do {
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
        // System.out.println("1 -> To See All");
        // System.out.println("2 -> See By Filter");
        // System.out.println("3 -> Home");
        // System.out.println("Enter the above numbers: ");
        a1 = sc.nextInt();
        switch (a1) {
            case 1:
                showall(sn1, gen);
                bill();
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
                    // System.out.println("1 -> Price");
                    // System.out.println("2 -> colour");
                    // System.out.println("3 -> Size");
                    // System.out.println("4 -> see by saved filters");
                    // System.out.println("5 -> Exit");
                    fn = sc.nextInt();
                    switch (fn) {
                        case 1:
                            byprice(sn1, gen);
                            // bill();
                            break;
                        case 2:
                            bycolor(sn1, gen);
                            // bill();
                            break;
                        case 3:
                            bysize(sn1, gen);
                            // bill();
                            break;
                        case 4:
                            if (save_price != 0 && save_colour != "" && save_size != "") {
                                show_byfilter(sn1, save_price, save_colour, save_size, gen);
                                bill();
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
        // } while (a1 != 3);
    }

    int sn2;

    void Val1(String gen) throws Exception {
        Scanner sc = new Scanner(System.in);
        String dburl = "jdbc:mysql://localhost:3306/mydb";
        String dbuser = "root";
        String dbpassword = "";
        String driver = "com.mysql.jdbc.Driver";

        Class.forName(driver);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);
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
            // System.out.println("1 -> Kurti");
            // System.out.println("2 -> Dress");
            // System.out.println("3 -> ");
            // System.out.println("4 -> Go TO Home");
            // System.out.println("Enter the above numbers: ");
            sn2 = sc.nextInt();
            switch (sn2) {
                case 1:
                    Women_filters(gen);
                    break;
                case 2:
                    Women_filters(gen);
                    break;

                case 4:
                    home();

                    break;
            }
        } while (sn2 != 4);
        con.close();
    }

    void Women_filters(String gen) throws Exception {
        int a1;
        // do {
        System.out.println("┌────────────────────────────────────────────────────┐");
        System.out.println("│                       OPTION's                     │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│ -------------------------------------------------- │");
        System.out.println("│ 1. To See All                                      │");
        System.out.println("│ 2. See By Filter                                   │");
        System.out.println("│ 3. Size                                            │");
        System.out.println("│ 4. Go TO Main Menu                                 │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│ Please select an option (1-4):                     │");
        System.out.println("└────────────────────────────────────────────────────┘");
        // System.out.println("1 -> To See All");
        // System.out.println("2 -> See By Filter");
        // System.out.println("3 -> Home");
        // System.out.println("Enter the above numbers: ");
        a1 = sc.nextInt();
        switch (a1) {
            case 1:
                showall(sn2, gen);
                bill();
                // o.bill();
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
                    // System.out.println("1 -> Price");
                    // System.out.println("2 -> colour");
                    // System.out.println("3 -> Size");
                    // System.out.println("4 -> see by saved filters");
                    // System.out.println("5 -> Exit");
                    fn = sc.nextInt();
                    switch (fn) {
                        case 1:
                            byprice(sn2, gen);
                            // bill();
                            break;
                        case 2:
                            bycolor(sn2, gen);
                            // bill();
                            break;
                        case 3:
                            bysize(sn2, gen);
                            // bill();
                            break;
                        case 4:
                            if (save_price != 0 && save_colour != "" && save_size != "") {
                                show_byfilter(sn2, save_price, save_colour, save_size, gen);
                                bill();
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
        // } while (a1 != 3);
    }

    void home() throws Exception {
        int hn;

        // for ├ -> alt + 195
        // for ┌ -> alt + 218
        // for ─ -> alt + 196
        // for │ -> alt + 179
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
        // do {
        // System.out.println("1 -> Shop For Men");
        // System.out.println("2 -> Shop For Women");
        // System.out.println("3 -> See new Fashion Products");
        // System.out.println("4 -> See WishList");
        // System.out.println("5 -> See Cart");
        // System.out.println("6 -> See Your Orders");
        // System.out.println("7 -> TO EXIT The Amazing Clothing Mart");
        hn = sc.nextInt();

        switch (hn) {
            case 1:
                Val(male);
                break;

            case 2:
                Val1(female);
                break;

            case 4:
                show_wishlist();
                break;

            case 5:
                show_cart();
                break;

            case 7:
                break;

            default:
                break;
        }
        // } while (hn != 7);
    }
}

class Clothing_proj {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        String dburl = "jdbc:mysql://localhost:3306/e-clothing";
        String dbuser = "root";
        String dbpassword = "";
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver);
        Connection con = DriverManager.getConnection(dburl, dbuser, dbpassword);

        other o = new other();
        Shop s = new Shop();
        // o.entry();
        s.home();

    }
}