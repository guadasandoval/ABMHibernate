package hibernate.app.DAO;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Session;

import hibernate.app.HibernateUtil;
import hibernate.app.Entity.PersonaEntity;
import hibernate.app.Entity.VentaEntity;

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

		ventaList = sesn.createQuery("FROM VentaEntity WHERE ID_PERSONA=" + per.getPersonaID()).list();

		sesn.close();

		HibernateUtil.shutdown();
		return ventaList;
	}

}
