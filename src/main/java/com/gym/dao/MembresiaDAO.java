package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gym.model.Clase;
import com.gym.model.Membresia;
import com.gym.model.TipoMembresia;
import com.gym.utilidades.Utilidades;

public class MembresiaDAO {
	Connection con;
	
	public MembresiaDAO(Connection con) {
		this.con = con;
	}
	
	public boolean toBoolean(int numero) {
		if(numero != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<Membresia> listar(int administrador_id, String buscar) {

		List<Membresia> resultado = new ArrayList<>();
		
		try {
			
			String sentencia = "call listarMembresias(?)";
			
			// Cedula
			if(Utilidades.isNumber(buscar)) sentencia = "call consultarMembresiasCedula(?, ?)";
			
			// Nombre
			if(!Utilidades.isNumber(buscar) && !buscar.equals("")) sentencia = "call consultarMembresiasNombre(?, ?)";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, administrador_id);
				
				// Consultar
				if(!buscar.equals("")) statement.setString(2, buscar + "%");
				
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet) {
					
					while(resultSet.next()) {
					    resultado.add(new Membresia(
					        resultSet.getInt("m.id"),
					        resultSet.getString("m.fecha_inicio"),
					        resultSet.getString("m.fecha_fin"),
					        resultSet.getInt("m.activo"),
					        resultSet.getInt("m.usuario_id"),
					        resultSet.getInt("m.tipo_membresia_id"),
					        resultSet.getInt("m.factura_id"),
					        resultSet.getString("u.nombre"),
					        resultSet.getString("u.cedula"),
					        resultSet.getString("t.nombre"),
					        resultSet.getInt("t.clase_id"),
					        resultSet.getString("c.clase"),
					        resultSet.getInt("c.entrenador_id"),
					        resultSet.getString("e.nombre"),
					        resultSet.getString("f.numero_factura")
					    ));
					}
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	public List<Membresia> listarMembresiasUsuario(int usuario_id) {

		List<Membresia> resultado = new ArrayList<>();
		
		try {
			
			String sentencia = "call listarMembresiasUsuario(?)";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, usuario_id);
				
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet) {
					
					while(resultSet.next()) {
					    resultado.add(new Membresia(
					        resultSet.getInt("m.id"),
					        resultSet.getString("m.fecha_inicio"),
					        resultSet.getString("m.fecha_fin"),
					        resultSet.getInt("m.activo"),
					        resultSet.getInt("m.usuario_id"),
					        resultSet.getInt("m.tipo_membresia_id"),
					        resultSet.getInt("m.factura_id"),
					        resultSet.getString("u.nombre"),
					        resultSet.getString("u.cedula"),
					        resultSet.getString("t.nombre"),
					        resultSet.getInt("t.clase_id"),
					        resultSet.getString("c.clase"),
					        resultSet.getInt("c.entrenador_id"),
					        resultSet.getString("e.nombre"),
					        resultSet.getString("f.numero_factura")
						    ));
					}
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	public Membresia consulta(int id) {

		try {
			String sentencia = "call consultarMembresia(?)";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					resultSet.next();
					
						return new Membresia(
						        resultSet.getInt("m.id"),
						        resultSet.getString("m.fecha_inicio"),
						        resultSet.getString("m.fecha_fin"),
						        resultSet.getInt("m.activo"),
						        resultSet.getInt("m.usuario_id"),
						        resultSet.getInt("m.tipo_membresia_id"),
						        resultSet.getInt("m.factura_id"),
						        resultSet.getString("u.nombre"),
						        resultSet.getString("u.cedula"),
						        resultSet.getString("t.nombre"),
						        resultSet.getInt("t.clase_id"),
						        resultSet.getString("c.clase"),
						        resultSet.getInt("c.entrenador_id"),
						        resultSet.getString("e.nombre"),
						        resultSet.getString("f.numero_factura")
							    );
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Membresia> listarMembresiaFactura(int administrador_id, int factura_id) {
		List<Membresia> resultado = new ArrayList<>();
		
		try {
			
			String sentencia = "select * from membresia m"
					+ " join tipo_membresia t on t.id = m.tipo_membresia_id"
					+ " join clase c on c.id = t.clase_id"
					+ " join entrenador e on e.id = c.entrenador_id"
					+ " join historial_precio_tipo_membresia h on h.id = m.precio_id"
					+ " where m.administrador_id = ? and m.factura_id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, administrador_id);
				statement.setInt(2, factura_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					while(resultSet.next()) {
						resultado.add(new Membresia(
								resultSet.getInt("m.id"),
								resultSet.getString("t.nombre"),
								resultSet.getString("c.clase"),
								resultSet.getString("e.nombre"),
								resultSet.getDouble("h.precio"),
								resultSet.getInt("t.id")
								));
					}
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	public boolean crearMembresia(int administrador_id, int usuario_id, int factura_id, int tipo_membresia_id) {
		
		int item = 0;
		
		try {
			String sentencia = "call insertarMembresia(?, ?, ?, ?)";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, administrador_id);
				statement.setInt(2, usuario_id);
				statement.setInt(3, factura_id);
				statement.setInt(4, tipo_membresia_id);
				
				item = statement.executeUpdate();
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
		
	}
	
	public List<Clase> listarClase(int administrador_id) {

		try {
			String sentencia = "select * from clase where administrador_id = ?";
			List<Clase> resultado = new ArrayList<>();
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, administrador_id);
				
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet) {
					
					resultado.add(new Clase("-- Selecciona una Clase --"));
					
					while(resultSet.next()) {
						resultado.add(new Clase(
								resultSet.getInt("id"),
								resultSet.getString("clase"),
								resultSet.getString("descripcion"),
								resultSet.getInt("entrenador_id"),
								resultSet.getInt("administrador_id")));
					}
					
					return resultado;
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Membresia consultaUltimaMembresia(int usuario_id) {

		try {
			
			System.out.println(usuario_id);
			
			String sentencia = "select m.*, p.nombre, c.clase"
					+ " from membresia m"
					+ " join plan p ON p.id = m.plan_id"
					+ " join clase c ON c.id = m.clase_id"
					+ " where m.id = ("
					+ "    select max(id) from membresia where usuario_id = ?"
					+ ")"
					+ " and m.usuario_id = ?"
					+ " group by m.id, p.nombre, c.clase;";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, usuario_id);
				statement.setInt(2, usuario_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					resultSet.next();
					
						return new Membresia(
								resultSet.getInt("m.id"),
								resultSet.getString("m.fecha_inicio"),
								resultSet.getString("m.fecha_fin"),
								resultSet.getInt("m.usuario_id"),
								resultSet.getInt("m.plan_id"),
								resultSet.getInt("m.clase_id"),
								resultSet.getFloat("m.valor_extra"),
								resultSet.getFloat("m.valor_total"),
								resultSet.getInt("m.administrador_id"),
								resultSet.getString("p.nombre"),
								resultSet.getString("c.clase"),
								resultSet.getInt("m.activo"),
								resultSet.getInt("m.anticipacion")
								);
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public boolean eliminar(int id) {
		
		try {
			String sentencia = "delete from membresia where id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, id);
				
				return new Utilidades().toBoolean(statement.executeUpdate());
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean eliminarRegistro(int membresia_id) {
		
		int item = 0;
		
		try {
			String sentencia = "delete from registro where membresia_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, membresia_id);
				
				item = statement.executeUpdate();
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
		
	}

	public boolean modificarActivo(int id, int activo) {

		try {
			String sentencia = "update membresia set activo = ?"
					+ " where id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, activo);
				statement.setFloat(2, id);
					
					return toBoolean(statement.executeUpdate());
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public boolean consultaActivo(int usuario_id, int id) {

		try {
			String sentencia = "select activo from membresia where usuario_id = ? and activo = 1 and id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, usuario_id);
				statement.setInt(2, id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
						return resultSet.next();
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public boolean existeMembresia(int usuario_id) {

		try {
			String sentencia = "select * from membresia where usuario_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, usuario_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
						return resultSet.next();
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public List<Integer> consultarClases(int usuario_id) {
		
		List<Integer> clasesActivas = new ArrayList<>();
		
		try {
			String sentencia = "select clase_id from membresia"
					+ " where usuario_id = ? and activo = 1";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, usuario_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					while(resultSet.next()) {
						clasesActivas.add(resultSet.getInt("clase_id"));
					}
					
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return clasesActivas;
	}

	public Membresia consultaMembresia(int usuario_id, int id) {
		Membresia membresia = null;
		
		try {
			String sentencia = "select m.*, p.nombre, c.clase from membresia m"
					+ " join plan p on p.id = m.plan_id"
					+ " join clase c on c.id = m.clase_id"
					+ " where usuario_id = ? and activo <> 0 and m.id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, usuario_id);
				statement.setInt(2, id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					resultSet.next();
					
					membresia =  new Membresia(
							resultSet.getInt("m.id"),
							resultSet.getString("m.fecha_inicio"),
							resultSet.getString("m.fecha_fin"),
							resultSet.getInt("m.usuario_id"),
							resultSet.getInt("m.plan_id"),
							resultSet.getInt("m.clase_id"),
							resultSet.getFloat("m.valor_extra"),
							resultSet.getFloat("m.valor_total"),
							resultSet.getInt("m.administrador_id"),
							resultSet.getString("p.nombre"),
							resultSet.getString("c.clase"),
							resultSet.getInt("m.activo"),
							resultSet.getInt("m.anticipacion")
							);
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return membresia;
		
	}
	
}
