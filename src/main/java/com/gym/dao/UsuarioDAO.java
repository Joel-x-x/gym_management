package com.gym.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gym.model.Membresia;
import com.gym.model.Usuario;


public class UsuarioDAO {
	Connection con;
	
	public UsuarioDAO(Connection con) {
		this.con  = con;
	}
	
	public boolean toBoolean(int numero) {
		if(numero != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean guardar(Usuario usuario) {
		
		try {
			String sentencia = "insert into usuario(nombre, apellido, fecha_nacimiento, sexo, email, cedula, direccion, telefono, administrador_id) "
					+ "values(?,?,?,?,?,?,?,?,?);";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setString(1, usuario.getNombre());
				statement.setString(2, usuario.getApellido());
				statement.setDate(3, usuario.getFecha_nacimiento());
				statement.setString(4, usuario.getSexo());
				statement.setString(5, usuario.getEmail());
				statement.setString(6, usuario.getCedula());
				statement.setString(7, usuario.getDireccion());
				statement.setString(8, usuario.getTelefono());
				statement.setInt(9, usuario.getAdministrador_id());
				
				return toBoolean(statement.executeUpdate());
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public boolean modificar(Usuario usuario) {
		
		try {
			String sentencia = "update usuario set nombre = ?, apellido = ?, fecha_nacimiento = ?,"
					+ " sexo = ?, email = ?, cedula = ?, direccion = ?, telefono = ? where id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setString(1, usuario.getNombre());
				statement.setString(2, usuario.getApellido());
				statement.setDate(3, usuario.getFecha_nacimiento());
				statement.setString(4, usuario.getSexo());
				statement.setString(5, usuario.getEmail());
				statement.setString(6, usuario.getCedula());
				statement.setString(7, usuario.getDireccion());
				statement.setString(8, usuario.getTelefono());
				statement.setInt(9, usuario.getId());
				
				return toBoolean(statement.executeUpdate());
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public int eliminar(int id) {
		
//		eliminarFisico(id);
//		eliminarRegistro(id);
//		eliminarMembresia(id);
		
		int item = 0;
		
		try {
			String sentencia = "delete from usuario where id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, id);
				
				item = statement.executeUpdate();
			}
			
		} catch(SQLException e) {
			item = e.getErrorCode();
		}
		
		return item;
		
	}

	public boolean eliminarFisico(int id) {
		
		try {
			String sentencia = "delete from fisico where usuario_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, id);
				
				return toBoolean(statement.executeUpdate());
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public boolean eliminarRegistro(int id) {
		
		try {
			String sentencia = "delete from registro where usuario_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, id);
				
				return toBoolean(statement.executeUpdate());
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public boolean eliminarMembresia(int id) {
		
		try {
			String sentencia = "delete from membresia where usuario_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, id);
				
				return toBoolean(statement.executeUpdate());
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Usuario> listar(int administrador_id) {
		
		try {
			String sentencia = "select * from usuario where administrador_id = ?";
			List<Usuario> resultado = new ArrayList<>();
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, administrador_id);
				
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet) {
					
					while(resultSet.next()) {
						resultado.add(new Usuario(
								resultSet.getInt("id"),
								resultSet.getString("nombre"),
								resultSet.getString("apellido"),
								resultSet.getDate("fecha_nacimiento"),
								resultSet.getString("sexo"),
								resultSet.getString("email"),
								resultSet.getString("cedula"),
								resultSet.getString("direccion"),
								resultSet.getString("telefono"),
								resultSet.getString("fecha_creacion")));
					}
					
					return resultado;
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public Usuario consulta(int id, int administrador_id) {
		
		try {
			String sentencia = "select * from usuario where id = ? and administrador_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, id);
				statement.setInt(2, administrador_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					resultSet.next();
					
						return new Usuario(
								resultSet.getInt("id"),
								resultSet.getString("nombre"),
								resultSet.getString("apellido"),
								resultSet.getDate("fecha_nacimiento"),
								resultSet.getString("sexo"),
								resultSet.getString("email"),
								resultSet.getString("cedula"),
								resultSet.getString("direccion"),
								resultSet.getString("telefono"),
								resultSet.getString("fecha_creacion"));
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Usuario> consultar(int administrador_id, String nombre) {
		
		try {
			String sentencia = "";
			
			if(!nombre.equals("")) {
				sentencia = "select * from usuario where administrador_id = ? and nombre like ?";
			} else {
				sentencia = "select * from usuario where administrador_id = ?";
			}
			
			List<Usuario> resultado = new ArrayList<>();
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				if(!nombre.equals("")) {
					statement.setInt(1, administrador_id);
					statement.setString(2, nombre + "%");
				} else {
					statement.setInt(1, administrador_id);
				}
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					while(resultSet.next()) {
						resultado.add(new Usuario(
								resultSet.getInt("id"),
								resultSet.getString("nombre"),
								resultSet.getString("apellido"),
								resultSet.getDate("fecha_nacimiento"),
								resultSet.getString("sexo"),
								resultSet.getString("email"),
								resultSet.getString("cedula"),
								resultSet.getString("direccion"),
								resultSet.getString("telefono"),
								resultSet.getString("fecha_creacion")));
					}
					
					return resultado;
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public Membresia consultaMembresia(int usuario_id) {
		try {
			String sentencia = "select * from membresia where usuario_id = ? and activo = 1";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, usuario_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					resultSet.next();
					
					return  new Membresia(
							resultSet.getInt("id"),
							resultSet.getString("fecha_inicio"),
							resultSet.getString("fecha_fin"),
							resultSet.getInt("usuario_id"),
							resultSet.getInt("activo"),
							resultSet.getInt("anticipacion")
							);
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
