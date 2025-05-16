package konrad.lubaski.manage.pdf;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PdfEntity {

    @Id
    private int id;
    private String name;

    public PdfEntity() {
    }

    public PdfEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}