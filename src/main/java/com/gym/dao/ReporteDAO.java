package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.gym.model.Reporte;

public class ReporteDAO extends MembresiaDAO{

	public ReporteDAO(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	public List<Reporte> listar_reporte(int usuario_id) {

		try {
			String sentencia = "select m.*, p.nombre, c.clase from membresia m"
					+ " join plan p on p.id = m.plan_id"
					+ " join clase c on c.id = m.clase_id"
					+ " where usuario_id = ? and activo <> 0";
			
			List<Reporte> resultado = new ArrayList<>();
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, usuario_id);
				
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet) {
					
					while(resultSet.next()) {
						resultado.add(new Reporte(
								resultSet.getString("m.fecha_inicio"),
								resultSet.getString("m.fecha_fin"),
								resultSet.getFloat("m.valor_total"),
								resultSet.getString("p.nombre"),
								resultSet.getString("c.clase"),
								resultSet.getInt("m.activo")
								));
					}
					
					return resultado;
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	

}
