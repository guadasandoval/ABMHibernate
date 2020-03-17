package hibernate.test;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import hibernate.test.dto.PersonaEntity;
import hibernate.test.dto.VentaEntity;

public class VentaDAO {
	
	public void insertVenta(VentaEntity venta) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(venta);
		session.getTransaction().commit();

		HibernateUtil.shutdown();
	

	}
	
	
			@SuppressWarnings("unchecked")

			public List<VentaEntity> listVenta(PersonaEntity per) {

				Session sesn = HibernateUtil.getSessionFactory().openSession();

				List<VentaEntity> ventaList = new ArrayList<VentaEntity>();

				try {

					ventaList = sesn.createQuery("FROM VentaEntity WHERE ID_PERSONA=" +per.getPersonaID()).list();

					for (VentaEntity venta : ventaList) {
						
						System.out.println(" ID | IMPORTE | FECHA  ");
						System.out.println(venta.getVentaID() + "       " + venta.getImporte() +"       " + venta.getFecha());
					}
				} catch (HibernateException e) {
					e.printStackTrace();
				} finally {
					sesn.close();
				}

				HibernateUtil.shutdown();
				return ventaList;
			}

}
