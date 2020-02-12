package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entidade.Departamento;
import model.entidade.Vendedor;

public class Program01 {

	public static void main(String[] args) {


		VendedorDao vendedorDao = new DaoFactory().createVendedorDao();
		
		Vendedor vendedor = vendedorDao.findById(7);
		
		System.out.println("Vendedor Encontrado ==> " + vendedor);
				
		
	}

}
