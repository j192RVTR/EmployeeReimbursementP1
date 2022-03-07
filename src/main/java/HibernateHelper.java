import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateHelper {

    static SessionFactory sessionFactory;
    private HibernateHelper(){}
    public static Session getSession(){
        if(sessionFactory == null){
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory.openSession();
    }



    public static boolean verifySession(HttpServletRequest req){
        HttpSession session = req.getSession();
        String name = (String) session.getAttribute("name");

        return  name !=null && !name.equals("");
    }

    public static void addTestEmployee(){
        Session session = HibernateHelper.getSession();
        Transaction transaction = session.beginTransaction();
        Employee e = new Employee();

        e.setName("Mark");
        e.setEmail("m@gmail.com");
        e.setCountry("USA");
        e.setGender("Male");
        e.setUsername("m2");
        e.setPassword("m2");
        e.setManager(true);

        session.persist(e);
        transaction.commit();
        session.close();
    }
}
