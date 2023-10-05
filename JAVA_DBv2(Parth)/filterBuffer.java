public class filterBuffer {
    void filter(int i, int save_price, String save_colour, String save_size, String gen) throws Exception {
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
}
