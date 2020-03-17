package hibernate.test;

	import java.util.ArrayList;
	import java.util.List;

	import org.hibernate.HibernateException;
	import org.hibernate.Session;

	import hibernate.test.dto.PersonaEntity;

	public class PersonaDAO {

		// insertar persona
		public void insertPersona(PersonaEntity per) {

			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(per);
			session.getTransaction().commit();

			HibernateUtil.shutdown();

		}

		// get persona
		public PersonaEntity buscarPersona(int personaID) {

			Session session = HibernateUtil.getSessionFactory().openSession();

			PersonaEntity per =  (PersonaEntity) session.createQuery(" FROM PersonaEntity WHERE ID=" + personaID).uniqueResult();
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

		// mofidicar personas
		public void modificaPersona(PersonaEntity per) {

			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			session.saveOrUpdate(per);
			session.getTransaction().commit();

			listUsers();
			HibernateUtil.shutdown();
		}

		// listar personas
		@SuppressWarnings("unchecked")

		public List<PersonaEntity> listUsers() {

			Session sesn = HibernateUtil.getSessionFactory().openSession();

			List<PersonaEntity> personaList = new ArrayList<PersonaEntity>();

			try {

				personaList = sesn.createQuery("From PersonaEntity").list();

				for (PersonaEntity per : personaList) {

					System.out.println(" ID | NOMBRE | EDAD | FECHA_NACIMIENTO ");
					System.out.println(per.getPersonaID() + " " + per.getNombre() + " " + per.getEdad() + " "
							+ per.getFechaNacimiento());
				}
			} catch (HibernateException e) {
				e.printStackTrace();
			} finally {
				sesn.close();
			}

			HibernateUtil.shutdown();
			return personaList;
		}


	}


