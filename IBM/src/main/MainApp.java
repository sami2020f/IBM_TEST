package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conextion.Conexion;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		escribir(args[0]);

	}

	public static void escribir(String nomfich) {
		Connection cn = Conexion.conectar();
		int numcolum = 0;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT id_proveedor,nombre,date_format(fecha_de_alta, \"%d-%m-%Y\") as fecha_formateada ,id_cliente FROM "
				+ "proveedores" + ";";
		// Al realizar la query , formateamos ya de paso nuestra fecha ya que al ser
		// Datetime viene con un formato poco usual para nosotros (yyyy-mm-dd hh-mm-ss)
		// y
		// nosotros solo queremos(dd-mm-yy) y en ese orden
		PrintWriter out = null;

		try {
			stmt = cn.createStatement();
			rs = stmt.executeQuery(sql);
			numcolum = rs.getMetaData().getColumnCount();

			// abrimos el fichero donde vamos a volcar nuestra información
			out = new PrintWriter(new FileWriter(nomfich));

			while (rs.next()) {
				String linea = "";
				for (int i = 1; i <= numcolum; i++) {

					linea = linea + rs.getString(i) + "," + " ";
				}
				out.println(linea);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (out != null)
				out.close(); // Cierra el fichero
		}

	}

}
