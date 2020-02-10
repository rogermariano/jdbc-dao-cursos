package application;

import java.util.Date;

import model.entidade.Departamento;
import model.entidade.Vendedor;

public class Program01 {

	public static void main(String[] args) {

		Departamento depto = new Departamento(1, "Computadores");

		Vendedor vendedor = new Vendedor(1,"Silvia H.D. Souza","Silvia_H_Dias@Hotmail", new Date(), 3000.0, depto );
		System.out.println("Vendedor =======> " + vendedor);
		System.out.println("Departamento ===> " + depto);
		
	}

}
