package com.gym.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		
		if(usuario.getFecha_nacimiento().equals("")) {
			usuario.setFecha_nacimiento("1800-1-1");
		}
		
		try {
			String sentencia = "insert into usuario(nombre, apellido, fecha_nacimiento, sexo, email, cedula, direccion, telefono, administrador_id) "
					+ "values(?,?,?,?,?,?,?,?,?);";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setString(1, usuario.getNombre());
				statement.setString(2, usuario.getApellido());
				statement.setString(3, usuario.getFecha_nacimiento());
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
		
		if(usuario.getFecha_nacimiento().equals("")) {
			usuario.setFecha_nacimiento("1800-1-1");
		}
		
		try {
			String sentencia = "update usuario set nombre = ?, apellido = ?, fecha_nacimiento = ?,"
					+ " sexo = ?, email = ?, cedula = ?, direccion = ?, telefono = ? where id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setString(1, usuario.getNombre());
				statement.setString(2, usuario.getApellido());
				statement.setString(3, usuario.getFecha_nacimiento());
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

	public boolean eliminar(int id) {
		
		eliminarFisico(id);
		eliminarRegistro(id);
		eliminarMembresia(id);
		
		try {
			String sentencia = "delete from usuario where id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, id);
				
				return toBoolean(statement.executeUpdate());
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
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
								resultSet.getString("fecha_nacimiento"),
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

	public Usuario consultar(int id, int administrador_id) {
		
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
								resultSet.getString("fecha_nacimiento"),
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
	
	public List<Usuario> consultarUsuario(int administrador_id, String cedula) {
		
		try {
			String sentencia = "";
			
			if(!cedula.equals("")) {
				sentencia = "select * from usuario where administrador_id = ? and cedula = ?";
			} else {
				sentencia = "select * from usuario where administrador_id = ?";
			}
			
			List<Usuario> resultado = new ArrayList<>();
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				if(!cedula.equals("")) {
					statement.setInt(1, administrador_id);
					statement.setString(2, cedula);
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
								resultSet.getString("fecha_nacimiento"),
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
	
 	public Object[][] consultar(Usuario usuario) {
		Object obj[][]= null;
		//obj= new Object[1][6];
		String sentencia="";

		try {
			if(usuario.getCedula().equals("")) {
				sentencia = "SELECT * FROM usuario  ";
				
			}else {
				sentencia = "SELECT * FROM usuario where cedula =  "+usuario.getCedula();
				}
		    
		    final Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		    //statement.setString(1, usuario.getCedula().toString());
		    
		    try (ResultSet resultSet = statement.executeQuery(sentencia)) {
		    	resultSet.last();
		    	int nf=resultSet.getRow();
				obj =new Object[nf][7];
				resultSet.beforeFirst();
				int f=0;
		        while (resultSet.next()) {
		            obj[f][0] = resultSet.getObject(1);
		            obj[f][1] = resultSet.getObject(2);
		            obj[f][2] = resultSet.getObject(3);
		            obj[f][3] = resultSet.getObject(4);
		            obj[f][4] = resultSet.getObject(5);
		            obj[f][5] = resultSet.getObject(6);
		            obj[f][6] = resultSet.getObject(7);
		            System.out.println("JHJKUH " + obj[0][1]);
		            System.out.println(obj[0][0]);
		            f++;
		        }
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		return obj;

	}
	
}
