package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gym.model.Administrador;
import com.gym.utilidades.Utilidades;

public class AdministradorDAO {
	Connection con;
	
	public AdministradorDAO(Connection con) {
		this.con = con;
	}
	
	private boolean validarClave(String clave) {
		try {
			String sentencia = "select clave from administrador where clave = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setString(1, clave);
				
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				try(resultSet) {
					return resultSet.next();
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean eliminarClave(String clave) {
		try {
			String sentencia = "update administrador set clave = null where clave = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setString(1, clave);
				
				int item = statement.executeUpdate();
				
				return toBoolean(item);
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean toBoolean(int numero) {
		if(numero != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean iniciarSesion(Administrador administrador) {

		try {
			
			String sentencia = "SELECT * FROM administrador where email = ? and password = ? ";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setString(1, administrador.getEmail());
				statement.setString(2, administrador.getPassword());
				
				statement.execute();
				
				
				final ResultSet resultSet = statement.getResultSet();
				
				try(resultSet) {
					return resultSet.next();
				}
			}
				
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	public boolean registrar(Administrador administrador) {

		if(!validarClave(administrador.getClave())) {
			return false;
		}
		
		try {
			
			String sentencia = "insert into administrador(nombre, email, password, sesion_iniciada, super_admin) "
					+ "values(?,?,?,?,?);";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setString(1, administrador.getNombre());
				statement.setString(2, administrador.getEmail());
				statement.setString(3, administrador.getPassword());
				statement.setInt(4, administrador.getSesion_iniciada());
				statement.setInt(5, administrador.isSuper_admin());
				
				int item = statement.executeUpdate();
				
				if(toBoolean(item)) {
					eliminarClave(administrador.getClave());
				}
				
				return toBoolean(item);
			}
				
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public int consultarId(String email) {
		
		try {
			String sentencia = "select id from administrador where email = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setString(1, email);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					resultSet.next();
					
					return resultSet.getInt("id");
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public boolean superUsuario(int administrador_id) {
		int super_admin = 0;
		
		try {
			
			String sentencia = "select super_admin from administrador where id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, administrador_id);
				
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				try(resultSet) {
					resultSet.next();
					
					super_admin = resultSet.getInt("super_admin");
				}
			}
				
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return new Utilidades().toBoolean(super_admin);
	}

	public List<Administrador> listar(String nombre) {
		List<Administrador> resultado = new ArrayList<>();
		
		try {
			
			String sentencia = "select id, nombre, apellido, email, cedula from administrador where super_admin <> 1";
			
			if(!nombre.equals("")) {
				sentencia += " and nombre like ?";
			}
			
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				if(!nombre.equals("")) {
					statement.setString(1, nombre + "%");
				}
				
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				try(resultSet) {
					
					while(resultSet.next()) {
						resultado.add(new Administrador(
								resultSet.getInt("id"),
								resultSet.getString("nombre"),
								resultSet.getString("apellido"),
								resultSet.getString("email"),
								resultSet.getString("cedula")));
					}
				}
			}
				
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
	}

	public boolean modificarClave(String clave, int id) {

		int item = 0;
		
		try {
			
			String sentencia = "update administrador set clave = ? where id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setString(1, clave);
				statement.setInt(2, id);
				
				item = statement.executeUpdate();
				
			}
				
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return new Utilidades().toBoolean(item);
	
	}

	public int getId() {
		
		int item = 0;
		
		try {
			
			String sentencia = "select * from administrador"
					+ " order by id desc"
					+ " limit 1";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					resultSet.next();
					
					item = resultSet.getInt("id");
				}
			}
				
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return item;
	}
	
	public boolean crearCuenta(int id) {
		int item = 0;
		
		try {
			
			String sentencia = "insert into cuenta(administrador_id) values(?)";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, id);
				
				item = statement.executeUpdate();
			}
				
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return new Utilidades().toBoolean(item);
		
	}
	
	public boolean crearRecuperacionCuenta(int id) {
		int item = 0;
		
		try {
			System.out.println("Creando recuperacion " + id);
			String sentencia = "insert into recuperacion_cuenta(administrador_id) values(?)";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, id);
				
				item = statement.executeUpdate();
			}
				
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return new Utilidades().toBoolean(item);
		
	}
	
	public boolean cambiarPassword(String password, int id) {
		int item = 0;
		
		try {
			
			String sentencia = "update administrador set password = ? where id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setString(1, password);
				statement.setInt(2, id);
				
				item = statement.executeUpdate();
				
			}
				
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return new Utilidades().toBoolean(item);
	}
	
}
