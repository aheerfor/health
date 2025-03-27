package com.anders;

import org.apache.log4j.Logger;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Vector;

public class NutrientDAO extends DAO {
    private static final Logger logger = Logger.getLogger(NutrientDAO.class.getName());
    static Query<Nutrient> listNuts  = session.getNamedQuery("ListNutrients");


    public static List<Nutrient> listNutrients() {
        List<Nutrient> targetlist = new Vector<>();
        targetlist = listNuts.list();
        return targetlist;
    }

    static public Nutrient getByName(String name) {
        List<Nutrient> list = listNutrients();
        for (Nutrient n: list) {
            if (n.name.equalsIgnoreCase(name)) {
                return n;
            }
        }
        return null;
    }

}
