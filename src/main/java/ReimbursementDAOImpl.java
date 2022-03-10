import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class ReimbursementDAOImpl implements ReimbursementDAO{
    @Override
    public boolean addReimbursement(Reimbursement reimbursement) {
        Session session = HibernateHelper.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(reimbursement);
        transaction.commit();
        session.close();
        return true;
    }

    public void update(Reimbursement reimbursement){
        Session session = HibernateHelper.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(reimbursement);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Reimbursement> getReimbursements() {
        Session session = HibernateHelper.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Reimbursement> criteria = builder.createQuery(Reimbursement.class);
        criteria.from(Reimbursement.class);
        List<Reimbursement> reimbursements = session.createQuery(criteria).getResultList();
        session.close();
        return reimbursements;
    }

    @Override
    public List<Reimbursement> getReimbursementsByEmpId(int id) {
        return getReimbursements()
                .stream()
                .filter(reimbursement -> reimbursement.getEmp_id()==id)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reimbursement> getReimbursementsByManId() {
        return null;
    }

    @Override
    public boolean approve(int man_id, int id, String status) {
        Session session = HibernateHelper.getSession();
        Transaction transaction = session.beginTransaction();

        Reimbursement reimbursement = session.load(Reimbursement.class, id);
        reimbursement.setStatus(status);
        reimbursement.setMan_id(man_id);

        session.update(reimbursement);

        transaction.commit();
        session.close();
        return true;
    }
}
