package anders;

import org.apache.log4j.Logger;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static anders.DAO.session;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "anders.reference")

@NamedQueries(
        {
                @NamedQuery(
                        name = "ListReferences",
                        query = "from Reference order by author"
                )
        })


public class Reference implements Serializable {
    private static final Logger logger = Logger.getLogger(Reference.class.getName());

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int id;
    String webaddress;
    String title;
    String author;
    //String reference;

    public void save() {
        try {
            webaddress = DAO.cut(webaddress,100);
            title = DAO.cut(title,100);
            author = DAO.cut(author,45);

            session.save(this);
        } catch (Exception e) {
            logger.error("Nutrient.save "+e);
            Breaker.breaker();
        }

    }
}
