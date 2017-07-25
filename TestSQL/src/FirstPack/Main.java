package FirstPack;

import java.sql.*;
import java.util.Scanner;

public class Main {

	public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/developer?useSSL=false";

	public static final String USER = "root";

	public static final String PASSWORD = "frfwers2017";

	public static final String UPDATE1 = "UPDATE computers set ";

	public static final String UPDATE2 = " where id=";

	public static final String CREATE_TABLE_COMPUTERS_QUERY = "CREATE TABLE "
			+ "computers(id INT PRIMARY KEY AUTO_INCREMENT," + "motherboard VARCHAR(255)," + "processor VARCHAR(255), "
			+ "ggh INT," + "video_card VARCHAR(255)," + "ram_GB INT," + "rom_GB INT," + "ssd_GB INT," + "price_USD INT,"
			+ "number INT)";

	public static final String INSERT_PC = "INSERT INTO computers"
			+ "(motherboard,processor,ggh,video_card,ram_GB,rom_GB,ssd_GB,price_USD,number) "
			+ "VALUES(?,?,?,?,?,?,?,?,?)";

	public static final String SELECT_COMPUTERS = "SELECT p.id, p.motherboard, p.processor,"
			+ " p.ggh, p.video_card, p.ram_GB, p.rom_GB, p.ssd_GB, p.price_USD, p.number FROM computers p";


	public static void main(String[] args) {

		try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD)) {
			Scanner cin = new Scanner(System.in);
			boolean isRun = true;
			while (isRun) {
				System.out.println("Натисніть 1 щоб додати компютер");
				System.out.println("Натисніть 2 щоб редагувати компютер");
				System.out.println("Натисніть 3 щоб вивести всі компютери");
				System.out.println("Натисніть 4 щоб видалити компютер");
				int a = cin.nextInt();
				switch (a) {
				case 1: {
					System.out.println("Введіть материнську плату");
					String motherboard = cin.next();
					System.out.println("Введіть процесор");
					String processor = cin.next();
					System.out.println("Введіть (ГГЦ)");
					int ggh = cin.nextInt();
					System.out.println("Введіть відеокарту");
					String video_card = cin.next();
					System.out.println("Введіть RAM(ГБ)");
					int ram_GB = cin.nextInt();
					System.out.println("Введіть HHD(ГБ)");
					int rom_GB = cin.nextInt();
					System.out.println("Введіть SSDГБ()");
					int ssd_GB = cin.nextInt();
					System.out.println("Введіть ціну($)");
					int price_USD = cin.nextInt();
					System.out.println("Введіть кількість на складі");
					int number = cin.nextInt();

					PreparedStatement stat = connection.prepareStatement(INSERT_PC);
					stat.setString(1, motherboard);
					stat.setString(2, processor);
					stat.setInt(3, ggh);
					stat.setString(4, video_card);
					stat.setInt(5, ram_GB);
					stat.setInt(6, rom_GB);
					stat.setInt(7, ssd_GB);
					stat.setInt(8, price_USD);
					stat.setInt(9, number);
					stat.executeUpdate();
					stat.close();

				}
					break;
				case 2: {
					update(connection, cin);

				}
					break;
				case 3: {
					PreparedStatement stat = connection.prepareStatement(SELECT_COMPUTERS);
					ResultSet rs = stat.executeQuery();
					while (rs.next()) {
						System.out.println(rs.getInt("id") + " " + rs.getString("motherboard") + " "
								+ rs.getString("processor") + " " + rs.getInt("ggh") + " " + rs.getString("video_card")
								+ " " + rs.getInt("ggh") + " " + rs.getInt("ram_GB") + " " + rs.getInt("rom_GB") + " "
								+ rs.getInt("ssd_GB") + " " + rs.getInt("price_USD") + " " + rs.getInt("number"));
					}
					rs.close();
					stat.close();
				}
					break;
				case 4: {
					delete(connection,cin);
				}
					break;

				default: {
					isRun = false;
					cin.close();
				}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public static void delete(Connection con, Scanner sc) throws SQLException{
		System.out.println("Ведіть id компютера");
		int id = sc.nextInt();
		PreparedStatement stat = con.prepareStatement("DELETE FROM computers where id=?");
		stat.setInt(1, id);
		stat.executeUpdate();
		stat.close();
	}

	public static void update(Connection con, Scanner sc) throws SQLException {
		System.out.println("Ведіть id компютера");
		int id = sc.nextInt();
		System.out.println("Виберіть параметр для редагування");
		System.out.println("//motherboard//processor//ggh//video_card");
		System.out.println("//ram_GB//rom_GB//ssd_GB//price_USD//number");
		String up = sc.next();
		System.out.println(up + "=");
		switch (up) {
		case "motherboard": {
			PreparedStatement stat = con.prepareStatement("UPDATE computers SET motherboard=? WHERE id=?");
			String up1 = sc.next();
			stat.setString(1, up1);
			stat.setInt(2, id);
			stat.executeUpdate();
			stat.close();
		}
			break;
		case "processor": {
			PreparedStatement stat = con.prepareStatement("UPDATE computers SET processor=? WHERE id=?");
			String up1 = sc.next();
			stat.setString(1, up1);
			stat.setInt(2, id);
			stat.executeUpdate();
			stat.close();
		}
			break;
		case "video_card": {
			PreparedStatement stat = con.prepareStatement("UPDATE computers SET video_card=? WHERE id=?");
			String up1 = sc.next();
			stat.setString(1, up1);
			stat.setInt(2, id);
			stat.executeUpdate();
			stat.close();
		}
			break;
		case "ggh": {
			PreparedStatement stat = con.prepareStatement("UPDATE computers SET ggh=? WHERE id=?");
			int up2 = sc.nextInt();
			stat.setInt(1, up2);
			stat.setInt(2, id);
			stat.executeUpdate();
			stat.close();
		}
			break;
		case "ram_GB": {
			PreparedStatement stat = con.prepareStatement("UPDATE computers SET ram_GB=? WHERE id=?");
			int up2 = sc.nextInt();
			stat.setInt(1, up2);
			stat.setInt(2, id);
			stat.executeUpdate();
			stat.close();
		}
			break;
		case "rom_GB": {
			PreparedStatement stat = con.prepareStatement("UPDATE computers SET rom_GB=? WHERE id=?");
			int up2 = sc.nextInt();
			stat.setInt(1, up2);
			stat.setInt(2, id);
			stat.executeUpdate();
			stat.close();
		}
			break;
		case "ssd_GB": {
			PreparedStatement stat = con.prepareStatement("UPDATE computers SET ssd_GB=? WHERE id=?");
			int up2 = sc.nextInt();
			stat.setInt(1, up2);
			stat.setInt(2, id);
			stat.executeUpdate();
			stat.close();
		}
			break;
		case "price_USD": {
			PreparedStatement stat = con.prepareStatement("UPDATE computers SET price_USD=? WHERE id=?");
			int up2 = sc.nextInt();
			stat.setInt(1, up2);
			stat.setInt(2, id);
			stat.executeUpdate();
			stat.close();
		}
			break;
		case "number": {
			PreparedStatement stat = con.prepareStatement("UPDATE computers SET number=? WHERE id=?");
			int up2 = sc.nextInt();
			stat.setInt(1, up2);
			stat.setInt(2, id);
			stat.executeUpdate();
			stat.close();
		}
			break;

		default: {

		}
			break;
		}

	}

}
