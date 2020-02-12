package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Vendedor obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vendedor findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = " select s.*, d.name " + " from " + " seller s "
				+ " inner join department d " + " on s.DepartmentId = d.id "
				+ " where s.Id = ? ";

		try {
			st = cnx.prepareStatement(sql);
			st.setInt(1, id);
			
			rs = st.executeQuery();

			if( rs.next()){
				Departamento depto = instanciarDepto(rs);
				Vendedor vendedor = instanciarVendedor(rs, depto);
				
				return vendedor;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	private Vendedor instanciarVendedor(ResultSet rs, Departamento depto) throws SQLException {
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
		depto.setNome(rs.getString("name"));
		
		return depto;
	}

	@Override
	public List<Vendedor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
