import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO{
    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee getEmployeeByID(int id) {
        return null;
    }

    @Override
    public Employee getEmployeeByUsernameAndPassword(String username, String password, boolean manager) {
        Session session = HibernateHelper.getSession();
        Query query = session.createQuery("from Employee where username = :username and password = :password and manager = :manager");
        query.setParameter("username", username);
        query.setParameter("password", password );
        query.setParameter("manager", manager);
        Employee employee = (Employee) query.uniqueResult();
        return employee;
    }
}
