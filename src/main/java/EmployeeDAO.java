import java.util.List;

public interface EmployeeDAO {
    List<Employee> getAllEmployees();
    Employee getEmployeeByID(int id);
    Employee getEmployeeByUsernameAndPassword(String username, String password, boolean manager);
    void update(Employee employee);
    void add(Employee employee);
    Employee getEmployeeByUsername(String username, boolean manager);

}
