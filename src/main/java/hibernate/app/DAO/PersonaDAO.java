package hibernate.app.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import hibernate.app.HibernateUtil;
import hibernate.app.Entity.PersonaEntity;

public class PersonaDAO {

	// insertar persona
	public void insertModificaPersona(PersonaEntity per) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(per);
		session.getTransaction().commit();

		HibernateUtil.shutdown();

	}

	// get persona
	public PersonaEntity buscarPersona(int personaID) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		PersonaEntity per = (PersonaEntity) session.createQuery(" FROM PersonaEntity WHERE ID=" + personaID)
				.uniqueResult();
		HibernateUtil.shutdown();
		return per;
	}

	// dar de baja personas
	public void deletePersona(PersonaEntity per) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(per);
		session.getTransaction().commit();

		HibernateUtil.shutdown();
	}


	// listar personas
	@SuppressWarnings("unchecked")

	public List<PersonaEntity> listUsers() {

		Session sesn = HibernateUtil.getSessionFactory().openSession();

		List<PersonaEntity> personaList = new ArrayList<PersonaEntity>();

		personaList = sesn.createQuery("From PersonaEntity").list();

		sesn.close();

		HibernateUtil.shutdown();
		return personaList;
	}

}
