import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO{
    @Override
    public List<Employee> getAllEmployees() {
        Session session = HibernateHelper.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
        criteria.from(Employee.class);
        List<Employee> employees = session.createQuery(criteria).getResultList();
        session.close();
        return employees;
    }

    @Override
    public Employee getEmployeeByID(int id) {
        Session session = HibernateHelper.getSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        transaction.commit();
        session.close();
        return employee;
    }

    @Override
    public Employee getEmployeeByUsernameAndPassword(String username, String password, boolean manager) {
        Session session = HibernateHelper.getSession();
        Query query = session.createQuery("from Employee where username = :username and password = :password and manager = :manager");
        query.setParameter("username", username);
        query.setParameter("password", password );
        query.setParameter("manager", manager);
        Employee employee = (Employee) query.uniqueResult();
        session.close();
        return employee;
    }

    @Override
    public void update(Employee employee) {
        Session session = HibernateHelper.getSession();
        Transaction transaction = session.beginTransaction();

        session.update(employee);
        transaction.commit();

        session.close();
    }

    @Override
    public void add(Employee employee) {
        Session session = HibernateHelper.getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(employee);
        transaction.commit();

        session.close();
    }
}
