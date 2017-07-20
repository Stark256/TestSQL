package FirstPack;

import java.sql.*;
import java.util.Scanner;

public class Main {

	public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/developer?useSSL=false";

	public static final String USER = "root";

	public static final String PASSWORD = "frfwers2017";
	
	public static final String UPDATE1="UPDATE computers set ";
	
	public static final String UPDATE2=" where id=";
	
	public static final String CREATE_TABLE_COMPUTERS_QUERY = "CREATE TABLE "
			+ "computers(id INT PRIMARY KEY AUTO_INCREMENT,"
			+ "motherboard VARCHAR(255),"
			+ "processor VARCHAR(255), "
			+ "ggh INT,"
			+ "video_card VARCHAR(255),"
			+ "ram_GB INT,"
			+ "rom_GB INT,"
			+ "ssd_GB INT,"
			+ "price_USD INT,"
			+ "number INT)";

	public static final String INSERT_PC = "INSERT INTO computers"
			+ "(motherboard,processor,ggh,video_card,ram_GB,rom_GB,ssd_GB,price_USD,number) "
			+ "VALUES(?,?,?,?,?,?,?,?,?)";

	public static final String SELECT_COMPUTERS ="SELECT p.id, p.motherboard, p.processor,"
			+ " p.ggh, p.video_card, p.ram_GB, p.rom_GB, p.ssd_GB, p.price_USD, p.number FROM computers p";
	
	public static final String DELETE="DELETE FROM computers where id=";
	
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
							String motherboard=cin.next();
							System.out.println("Введіть процесор");
							String processor=cin.next();
							System.out.println("Введіть (ГГЦ)");
							int ggh=cin.nextInt();
							System.out.println("Введіть відеокарту");
							String video_card=cin.next();
							System.out.println("Введіть RAM(ГБ)");
							int ram_GB=cin.nextInt();
							System.out.println("Введіть HHD(ГБ)");
							int rom_GB=cin.nextInt();
							System.out.println("Введіть SSDГБ()");
							int ssd_GB=cin.nextInt();
							System.out.println("Введіть ціну($)");
							int price_USD=cin.nextInt();
							System.out.println("Введіть кількість на складі");
							int number=cin.nextInt();							
							
							PreparedStatement stat=connection.prepareStatement(INSERT_PC);
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
						System.out.println("Ведіть id компютера");
						String id=cin.next();
						System.out.println("Виберіть параметр для редагування");
						System.out.println("//motherboard//processor//ggh//video_card");
						System.out.println("//ram_GB//rom_GB//ssd_GB//price_USD//number");
						String up=cin.next();
						System.out.println(up+"=");
						String up1=cin.next();
						String UPDATE=UPDATE1+up+"="+up1+UPDATE2+id;
						PreparedStatement stat=connection.prepareStatement(UPDATE);
						stat.executeUpdate();
						stat.close();
						
					}break;
					case 3: {
						PreparedStatement stat=connection.prepareStatement(SELECT_COMPUTERS);
						ResultSet rs=stat.executeQuery();
						while(rs.next()){
							System.out.println(rs.getInt("id")+" "+rs.getString("motherboard")+" "
						+rs.getString("processor")+" "+rs.getInt("ggh")+" "+rs.getString("video_card")+" "
						+rs.getInt("ggh")+" "+rs.getInt("ram_GB")+" "+rs.getInt("rom_GB")+" "+rs.getInt("ssd_GB")+" "
						+rs.getInt("price_USD")+" "+rs.getInt("number"));
						}
						rs.close();
						stat.close();
					}break;
					case 4: {
						System.out.println("Введіть індекс");
						String ind=cin.next();
						String DEL=DELETE+ind;
						PreparedStatement stat=connection.prepareStatement(DEL);
						stat.executeUpdate();
						stat.close();
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

}
