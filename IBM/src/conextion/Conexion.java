package conextion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	

		private static final String URL = "jdbc:mysql://localhost:3306/";
		private static final String BBDD = "ibm";
		private static final String PARAMETROS = "?serverTimezone=UTC";
		private static final String USUARIO = "Tu usuario";
		private static final String CLAVE = "tu constraseña";

		public static Connection conectar() {
			Connection conexion = null;

			try {
				conexion = DriverManager.getConnection(URL + BBDD + PARAMETROS, USUARIO, CLAVE);
				System.out.println("Conexion OK");
			} catch (SQLException e) {
				System.out.println("Error en la conexion");
				e.printStackTrace();
			}

			return conexion;
		}
		
		

	

}
