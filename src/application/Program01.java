package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entidade.Departamento;
import model.entidade.Vendedor;

public class Program01 {

	public static void main(String[] args) {


		VendedorDao vendedorDao = new DaoFactory().createVendedorDao();
		
		Vendedor vendedor = vendedorDao.findById(7);
		
		System.out.println("==-== Teste n� 1 - FindById");
		System.out.println("Vendedor Encontrado ==> " + vendedor);
				
		System.out.println("==-== Teste n� 2 - FindByDepartment");
		Departamento depto = new Departamento(2, null);
		
		List<Vendedor> listaVendedor = vendedorDao.findByDepartment(depto);
		
		for(Vendedor vend : listaVendedor){
			System.out.println(vend);
		}
		
		
		System.out.println("==-== Teste n� 3 - FindAll");
		
		listaVendedor = vendedorDao.findAll();
		
		for(Vendedor vend : listaVendedor){
			System.out.println(vend);
		}
	}

}
