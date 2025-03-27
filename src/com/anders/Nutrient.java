package com.anders;

import org.apache.log4j.Logger;

import javax.persistence.*;

import java.io.Serializable;

import static com.anders.DAO.session;

@Entity
@Table(name = "anders.nutrient")

@NamedQueries(
        {
                @NamedQuery(
                        name = "ListNutrients",
                        query = "from Nutrient order by name"
                )
        })


public class Nutrient implements Serializable {
    private static final Logger logger = Logger.getLogger(Nutrient.class.getName());

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;
    String name;
    //String value;
    Double dailygrams;

    public void save() {
        try {
            session.save(this);
        } catch (Exception e) {
            logger.error("Nutrient.save "+e);
            Breaker.breaker();
        }

    }
}
