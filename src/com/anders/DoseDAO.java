package anders;

import org.apache.log4j.Logger;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Vector;

public class DoseDAO extends DAO {
    private static final Logger logger = Logger.getLogger(DoseDAO.class.getName());
    static Query<Dose> listDose  = session.getNamedQuery("ListDose");


    public static List<Dose> listDose() {
        List<Dose> targetlist = new Vector<>();
        targetlist = listDose.list();
        return targetlist;
    }


}
