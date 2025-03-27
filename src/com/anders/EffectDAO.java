package com.anders;

import org.apache.log4j.Logger;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Vector;

public class EffectDAO extends DAO {
    private static final Logger logger = Logger.getLogger(EffectDAO.class.getName());
    static Query<Effect> listEffect  = session.getNamedQuery("ListEffects");


    public static List<Effect> listEffect() {
        List<Effect> targetlist = new Vector<>();
        targetlist = listEffect.list();
        return targetlist;
    }


}
