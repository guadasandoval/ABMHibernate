package hibernate.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.hibernate.Session;

import hibernate.test.dto.PersonaEntity;
import hibernate.test.dto.VentaEntity;

public class AppTestHibernate {

	public static void main(String[] args) {

		// conexion

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Scanner scan = new Scanner(System.in);
		
		PersonaEntity per = new PersonaEntity();
		PersonaDAO perDAO = new PersonaDAO();
		

		int opcion = mostrarMenu(scan);

		while (opcion != 0) {
			switch (opcion) {
			case 1:
				alta(per,perDAO,scan);
				break;
			case 2:
				modificacion(per,perDAO,scan);
				break;
			case 3:
				baja(per,perDAO,scan);
				break;
			case 4:
				listado(perDAO);
				break;
			case 5:
				Venta(per,perDAO,scan);
				break;
			case 6:
				listadoVenta(scan);
				break;
			}

			opcion = mostrarMenu(scan);

		}

	}

	private static void listadoVenta(Scanner scan) {
		System.out.println("REGISTRO DE VENTAS POR CLIENTE");
		System.out.println();
		
		System.out.println("Ingrese ID de cliente ");
		int idPersona = scan.nextInt();

		PersonaEntity per = new PersonaEntity();
		per.setPersonaID(idPersona);
		
		VentaDAO ventaDAO = new VentaDAO();
		ventaDAO.listVenta(per);
		

		
	}

	private static void Venta(PersonaEntity per, PersonaDAO perDAO,Scanner scan) {
		
		System.out.println("BIENVENIDO AL SISTEMA DE VENTAS");
		System.out.println();

		
		VentaDAO ventaDAO = new VentaDAO();
		VentaEntity venta = new VentaEntity();
		
		System.out.println("Ingrese ID de cliente ");
		int idPersona = scan.nextInt();
		per = perDAO.buscarPersona(idPersona);
		
		System.out.println("Nombre de cliente: " + per.getNombre());
		venta.setIdPersona(per);
		
		System.out.println();
		System.out.println("Ingrese nuevo importe");
		float importe = scan.nextFloat();
		venta.setImporte(importe);
		

		SimpleDateFormat fechaVenta = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaActual = new Date();
		String strFechaVenta = fechaVenta.format(fechaActual);
		venta.setFecha(strFechaVenta);
		
		ventaDAO.insertVenta(venta);
		System.out.println("Venta registrada correctamente");

		
	}

	private static void alta(PersonaEntity per, PersonaDAO perDAO, Scanner scan) {


		// pedir datos a la persona por teclado

		System.out.println("REGISTRO DE ALTA");
		System.out.println();
		System.out.println("Ingrese nombre: ");
		String nombre = scan.next();
		System.out.println("Ingrese edad: ");
		int edad = scan.nextInt();
		System.out.println("Ingrese fecha de nacimiento (yyyy-mm-dd) ");
		String stringFecha = scan.next();

		per.setNombre(nombre);
		per.setFechaNacimiento(stringFecha);
		per.setEdad(edad);

		

		perDAO.insertPersona(per);

		System.out.println("Registro realizado correctamente");
	}

	private static void listado(PersonaDAO perDAO) {

		System.out.println("LISTADO DE PERSONAS REGISTRADAS");
		System.out.println();

		
		perDAO.listUsers();

	}

	private static void baja(PersonaEntity per, PersonaDAO perDAO,Scanner scan) {

		System.out.println("BAJA DE PERSONA");
		System.out.println();
		System.out.println("Ingrese ID de persona: ");
		Integer personaID = scan.nextInt();

	
		per = perDAO.buscarPersona(personaID);

		perDAO.deletePersona(per);

		System.out.println("La operacion se realizo correctamente");
	}

	private static void modificacion(PersonaEntity per, PersonaDAO perDAO,Scanner scan) {

		System.out.println("MODIFICACION DE REGISTRO");
		System.out.println();

		System.out.println("Ingrese ID: ");
		int personaID = scan.nextInt();
		
		
	per = perDAO.buscarPersona(personaID);
		
		System.out.println("Ingrese campo que desea modificar:");
		System.out.println("1. Nombre");
		System.out.println("2. Fecha de nacimiento");
		System.out.println("3. Edad");

		int opcion = scan.nextInt();

		switch (opcion) {
		case 1:
			System.out.println("Ingrese nuevo nombre: ");
			String nombreNuevo = scan.next();
			per.setNombre(nombreNuevo);
			perDAO.modificaPersona(per);
			break;
		case 2:
			System.out.println("Ingrese nueva fecha: ");
			String stringFechaNueva = scan.next();
			per.setFechaNacimiento(stringFechaNueva);
			perDAO.modificaPersona(per);
			break;
		case 3:
			System.out.println("Ingrese edad: ");
			int edadNuevo = scan.nextInt();
			per.setEdad(edadNuevo);
			perDAO.modificaPersona(per);
			break;

		}



	}

	private static int mostrarMenu(Scanner scan) {

		System.out.println();
		System.out.println("SISTEMA REGISTO DE PERSONAS");
		System.out.println("---------------------------");
		System.out.println("Ingrese: ");
		System.out.println("1. ALTA");
		System.out.println("2. MODIFICACION");
		System.out.println("3. BAJA");
		System.out.println("4. LISTADO");
		System.out.println("5. VENTA");
		System.out.println("6. LISTADO VENTA");

		int opcion = scan.nextInt();

		return opcion;
	}

}
