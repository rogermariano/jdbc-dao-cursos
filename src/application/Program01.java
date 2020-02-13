package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.dao.impl.VendedorDaoJDBC;
import model.entidade.Departamento;
import model.entidade.Vendedor;

public class Program01 {

	public static void main(String[] args) {


		VendedorDao vendedorDao = new DaoFactory().createVendedorDao();
		
		Vendedor vendedor = vendedorDao.findById(7);
		
		System.out.println("==-== Teste nº 1 - FindById");
		System.out.println("Vendedor Encontrado ==> " + vendedor);
				
		System.out.println("==-== Teste nº 2 - FindByDepartment");
		Departamento depto = new Departamento(2, null);
		
		List<Vendedor> listaVendedor = vendedorDao.findByDepartment(depto);
		
		for(Vendedor vend : listaVendedor){
			System.out.println(vend);
		}
		
		
		System.out.println("==-== Teste nº 3 - FindAll");
		
		listaVendedor = vendedorDao.findAll();
		
		for(Vendedor vend : listaVendedor){
			System.out.println(vend);
		}
		
		System.out.println("==-== Teste nº 4 - Insert");
		Vendedor vendor = new Vendedor(null, "Silvia Helena", "shdDias@yahoo.com.br", new Date(), 12000.0, depto);
		
		vendedorDao.insert(vendor);
		
		System.out.println("Inserido com sucesso!!! " +  vendor.getId());

		System.out.println("==-== Teste nº 5 - UpDate");
		vendedor = vendedorDao.findById(11);
		vendedor.setEmail("SilviaHDiasSouza@gmail.com");
		
		vendedorDao.update(vendedor);
		
		System.out.println("Alterado com sucesso!!! ");
	}			
	
	}			
	
	

