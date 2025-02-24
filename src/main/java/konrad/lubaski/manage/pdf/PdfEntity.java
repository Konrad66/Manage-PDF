package konrad.lubaski.manage.pdf;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//wymagane przez Hibernate
@Entity
public class PdfEntity {

    //wymagane przez Hibernate
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;

    public PdfEntity(String content) {
        this.content = content;
    }

    //wymagane przez Hibernate
    //wymagane przez Jackson
    PdfEntity() {

    }

    //wymagane przez Jackson
    public int getId() {
        return id;
    }

    //wymagane przez Jackson
    public String getContent() {
        return content;
    }
}
