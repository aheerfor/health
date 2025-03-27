package com.anders;

import org.apache.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;

import static com.anders.DAO.session;

@Entity
@Table(name = "anders.effect")

@NamedQueries(
        {
                @NamedQuery(
                        name = "ListEffects",
                        query = "from Effect order by effect"
                )
        })


public class Effect implements Serializable {
    private static final Logger logger = Logger.getLogger(Effect.class.getName());

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;
    String cause;
    String effect;
    String relation;
    String reference;

    public void save() {
        try {
            if (reference.length()>100) {
                reference = reference.substring(0,100);
            }
            session.save(this);
        } catch (Exception e) {
            logger.error("Effect.save "+e);
            Breaker.breaker();
        }

    }
}
