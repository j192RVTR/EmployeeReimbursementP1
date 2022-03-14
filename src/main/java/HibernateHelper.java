import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

    public static boolean verifyManager(HttpServletRequest request){
        Object object = request.getSession().getAttribute("manager");
        return object!=null && object.equals(true);
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

    public static String uploadImage(HttpServletRequest request, HttpServletResponse response, String filename) throws ServletException, IOException {

        String savePath = "C:\\Users\\12673\\IdeaProjects\\EmployeeReimbursementP1\\src\\main\\webapp\\img";
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        Part part = request.getPart("file");
        String contentType = part.getContentType();
        if(!contentType.contains("image")){
            return null;
        }

        File writePath = new File(savePath + File.separator + filename);
        StringBuilder filenameBuilder = new StringBuilder(filename);
        while(writePath.exists()){
            filenameBuilder.insert(0, "1");
            writePath = new File(savePath + File.separator + filenameBuilder);
        }
        filename = filenameBuilder.toString();

        part.write(savePath + File.separator + filename);

        return "img/" + filename;
    }
}
