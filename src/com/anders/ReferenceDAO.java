package com.anders;

import org.apache.log4j.Logger;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Vector;

public class ReferenceDAO extends DAO{
    private static final Logger logger = Logger.getLogger(ReferenceDAO.class.getName());
    static Query<Reference> listRefs  = session.getNamedQuery("ListReferences");


    public static List<Reference> listReferences() {
        List<Reference> targetlist = new Vector<>();
        targetlist = listRefs.list();
        return targetlist;
    }



}
