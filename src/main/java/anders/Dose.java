package anders;

import org.apache.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;

import static anders.DAO.session;

@Entity
@Table(name = "anders.dose")


@NamedQueries(
        {
                @NamedQuery(
                        name = "ListDose",
                        query = "from Dose order by nutrient"
                )
        })


public class Dose implements Serializable {
    private static final Logger logger = Logger.getLogger(Dose.class.getName());

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;
    String nutrient;
    String comment;
    Double min;
    Double max;
    String reference;

    public void save() {
        try {
            session.save(this);
        } catch (Exception e) {
            logger.error("Dose.save "+e);
            Breaker.breaker();
        }

    }
}
