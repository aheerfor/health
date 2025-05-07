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

    static int effect_size = 45;
    static int cause_size = 45;
    static int relation_size = 45;
    static int reference_size = 100;
    public void save() {
        try {
            if (reference.length()>100) {
                reference = reference.substring(0,100);
            }
            //logger.error("Save 1");
            cause = DAO.cut(cause,45);
            effect = DAO.cut(effect,45);
            relation = DAO.cut(relation,45);
            reference = DAO.cut(reference,100);
            session.save(this);
            //logger.error("Save 2");
        } catch (Exception e) {
            logger.error("Effect.save "+e);
            Breaker.breaker();
        }

    }
}
