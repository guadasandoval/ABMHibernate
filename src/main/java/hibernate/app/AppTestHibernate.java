package hibernate.app;


import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import hibernate.app.DAO.PersonaDAO;
import hibernate.app.DAO.VentaDAO;
import hibernate.app.Entity.PersonaEntity;
import hibernate.app.Entity.VentaEntity;

public class AppTestHibernate {

	public static void main(String[] args) {

		// conexion

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Scanner scan = new Scanner(System.in);

		PersonaDAO perDAO = new PersonaDAO();

		int opcion = mostrarMenu(scan);

		while (opcion != 0) {
			switch (opcion) {
			case 1:
				alta(perDAO, scan);
				break;
			case 2:
				modificacion(perDAO, scan);
				break;
			case 3:
				baja(perDAO, scan);
				break;
			case 4:
				listado(perDAO);
				break;
			case 5:
				venta(perDAO, scan);
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

		List<VentaEntity> ventaList = ventaDAO.listVenta(per);
		for (VentaEntity venta : ventaList) {

			System.out.println(" ID | IMPORTE | FECHA  ");
			System.out.println(venta.getVentaID() + "       " + venta.getImporte() + "       " + venta.getFecha());
		}
	}

	private static void venta(PersonaDAO perDAO, Scanner scan) {

		System.out.println("BIENVENIDO AL SISTEMA DE VENTAS");
		System.out.println();

		VentaDAO ventaDAO = new VentaDAO();
		VentaEntity venta = new VentaEntity();

		System.out.println("Ingrese ID de cliente ");
		int idPersona = scan.nextInt();
		PersonaEntity per = perDAO.buscarPersona(idPersona);

		System.out.println("Nombre de cliente: " + per.getNombre());
		venta.setIdPersona(per);

		System.out.println();
		System.out.println("Ingrese nuevo importe");
		float importe = scan.nextFloat();
		venta.setImporte(importe);


		Date fechaActual = new Date();
		venta.setFecha(fechaActual);

		ventaDAO.insertVenta(venta);
		System.out.println("Venta registrada correctamente");

	}

	private static void alta(PersonaDAO perDAO, Scanner scan) {

		// pedir datos a la persona por teclado

		System.out.println("REGISTRO DE ALTA");
		System.out.println();
		System.out.println("Ingrese nombre: ");
		String nombre = scan.next();
		System.out.println("Ingrese edad: ");
		int edad = scan.nextInt();
		System.out.println("Ingrese fecha de nacimiento (yyyy-mm-dd) ");
		String stringFecha = scan.next();
		PersonaEntity per = new PersonaEntity();
		per.setNombre(nombre);
		per.setFechaNacimiento(stringFecha);
		per.setEdad(edad);

		perDAO.insertModificaPersona(per);
		System.out.println("Registro realizado correctamente");
	}

	private static void listado(PersonaDAO perDAO) {

		System.out.println("LISTADO DE PERSONAS REGISTRADAS");
		System.out.println();

		List<PersonaEntity> personaList = perDAO.listUsers();
		for (PersonaEntity per : personaList) {

			System.out.println(" ID | NOMBRE | EDAD | FECHA_NACIMIENTO ");
			System.out.println(
					per.getPersonaID() + " " + per.getNombre() + " " + per.getEdad() + " " + per.getFechaNacimiento());
		}

	}

	private static void baja(PersonaDAO perDAO, Scanner scan) {

		System.out.println("BAJA DE PERSONA");
		System.out.println();
		System.out.println("Ingrese ID de persona: ");
		Integer personaID = scan.nextInt();

		PersonaEntity per = perDAO.buscarPersona(personaID);

		perDAO.deletePersona(per);

		System.out.println("La operacion se realizo correctamente");
	}

	private static void modificacion(PersonaDAO perDAO, Scanner scan) {

		System.out.println("MODIFICACION DE REGISTRO");
		System.out.println();

		System.out.println("Ingrese ID: ");
		int personaID = scan.nextInt();

		PersonaEntity per = perDAO.buscarPersona(personaID);

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
			perDAO.insertModificaPersona(per);
			break;
		case 2:
			System.out.println("Ingrese nueva fecha: yyyy-mm-dd");
			String stringFechaNueva = scan.next();
			per.setFechaNacimiento(stringFechaNueva);
			perDAO.insertModificaPersona(per);
			break;
		case 3:
			System.out.println("Ingrese edad: ");
			int edadNuevo = scan.nextInt();
			per.setEdad(edadNuevo);
			perDAO.insertModificaPersona(per);
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
