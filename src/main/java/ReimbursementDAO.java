import java.util.List;

public interface ReimbursementDAO {
    boolean addReimbursement(Reimbursement reimbursement);
    boolean approve(int man_id, int id, String status);
    Reimbursement getReimbursementById(int id);
    List<Reimbursement> getReimbursements();
    List<Reimbursement> getReimbursementsByEmpId(int id);
    List<Reimbursement> getReimbursementsByManId();

}
