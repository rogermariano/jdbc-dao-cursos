package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dao.VendedorDao;
import model.entidade.Departamento;
import model.entidade.Vendedor;
import db.DB;
import db.DbException;

public class VendedorDaoJDBC implements VendedorDao {

	public Connection cnx = null;
	
	

	public VendedorDaoJDBC(Connection cnx) {
		this.cnx = cnx;
	}

	@Override
	public void insert(Vendedor obj) {

		PreparedStatement st = null;
		String sql = "insert into seller (Name, Email, BirthDate, BaseSalary, DepartmentId) values "
				+ " (?, ?, ?, ?, ?)";
		try {
			st = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getDtNasc().getTime()));
			st.setDouble(4, obj.getSalarioBase());
			st.setInt(5, obj.getDepto().getId());

			int inc = st.executeUpdate();

			if (inc > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					System.out.println(rs.getInt(1) + "incluído !!!");
					obj.setId(rs.getInt(1));
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException(
						"Erro inesperado !!! :::: Registro não incluído :::::");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);

		}

	}

	@Override
	public void update(Vendedor obj) {
		PreparedStatement st = null;
		String sql = "update seller set Name = ?, Email = ? , BirthDate = ? , BaseSalary = ?, DepartmentId = ? "
				+ " where id = ? ";

		try {
			st = cnx.prepareStatement(sql);

			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getDtNasc().getTime()));
			st.setDouble(4, obj.getSalarioBase());
			st.setInt(5, obj.getDepto().getId());
			st.setInt(6, obj.getId());

			st.executeUpdate();

			System.out.println("Registro Autalizado com sucesso !!! ");

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);

		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;

		String sql = " delete from seller where id = ? ";

		try {
			st = cnx.prepareStatement(sql);
			st.setInt(1, id);
			
			int exclusao = st.executeUpdate();
			
			if(exclusao == 0){
				throw new DbException("Id do vendedor nº " + id + " não encontrado !!!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Vendedor findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = " select s.*, d.name as Nome " + " from " + " seller s "
				+ " inner join department d " + " on s.DepartmentId = d.id "
				+ " where s.Id = ? ";

		try {
			st = cnx.prepareStatement(sql);
			st.setInt(1, id);

			rs = st.executeQuery();

			if (rs.next()) {
				Departamento depto = instanciarDepto(rs);
				Vendedor vendedor = instanciarVendedor(rs, depto);

				return vendedor;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	private Vendedor instanciarVendedor(ResultSet rs, Departamento depto)
			throws SQLException {
		Vendedor vendedor = new Vendedor();
		vendedor.setId(rs.getInt("id"));
		vendedor.setNome(rs.getString("Name"));
		vendedor.setEmail(rs.getString("Email"));
		vendedor.setDtNasc(rs.getDate("BirthDate"));
		vendedor.setSalarioBase(rs.getDouble("BaseSalary"));
		vendedor.setDepto(depto);

		return vendedor;
	}

	private Departamento instanciarDepto(ResultSet rs) throws SQLException {
		Departamento depto = new Departamento();
		depto.setId(rs.getInt("DepartmentId"));
		depto.setNome(rs.getString("Nome"));

		return depto;
	}

	@Override
	/******************************************************************/
	public List<Vendedor> findAll() {
		/******************************************************************/
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select s.*, d.name as Nome " + " from " + " seller s "
				+ " inner join department d " + " on s.DepartmentId = d.id "
				+ " order by s.name ";

		try {
			st = cnx.prepareStatement(sql);

			rs = st.executeQuery();

			List<Vendedor> listaVendedor = new ArrayList<>();

			Map<Integer, Departamento> mapDepto = new HashMap<>();

			while (rs.next()) {

				Departamento depto = mapDepto.get(rs.getInt("DepartmentId"));

				if (depto == null) {
					depto = instanciarDepto(rs);
					mapDepto.put(rs.getInt("DepartmentId"), depto);
				}

				Vendedor vendedor = instanciarVendedor(rs, depto);

				listaVendedor.add(vendedor);

			}
			return listaVendedor;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	/******************************************************************/
	public List<Vendedor> findByDepartment(Departamento departamento) {
		/******************************************************************/
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select s.*, d.name as Nome " + " from " + " seller s "
				+ " inner join department d " + " on s.DepartmentId = d.id "
				+ " where d.id = ? " + " order by s.name ";

		try {
			st = cnx.prepareStatement(sql);
			st.setInt(1, departamento.getId());

			rs = st.executeQuery();

			List<Vendedor> listaVendedor = new ArrayList<>();

			Map<Integer, Departamento> mapDepto = new HashMap<>();

			while (rs.next()) {

				Departamento depto = mapDepto.get(rs.getInt("DepartmentId"));

				if (depto == null) {
					depto = instanciarDepto(rs);
					mapDepto.put(rs.getInt("DepartmentId"), depto);
				}

				Vendedor vendedor = instanciarVendedor(rs, depto);

				listaVendedor.add(vendedor);

			}
			return listaVendedor;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

}
