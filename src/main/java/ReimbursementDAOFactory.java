public class ReimbursementDAOFactory {
    private static ReimbursementDAO reimbursementDAO;
    private ReimbursementDAOFactory(){}
    public static ReimbursementDAO getReimbursementDAO(){
        if(reimbursementDAO == null){
            reimbursementDAO = new ReimbursementDAOImpl();
        }
        return reimbursementDAO;
    }
}
