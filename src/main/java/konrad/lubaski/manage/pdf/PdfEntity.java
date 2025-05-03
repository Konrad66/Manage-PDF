package konrad.lubaski.manage.pdf;

import jakarta.persistence.*;

//wymagane przez Hibernate
@Entity
public class PdfEntity {

    //wymagane przez Hibernate
    @Id
    @GeneratedValue
    private int id;
    //@Lob
    // @Column(columnDefinition = "bytea")
    // hibernate powininien domyślnie ustawić typ kolumny na "bytea", a rzeczy powyżej dodaje się dla większej kontorli
    //@Basic(fetch = FetchType.LAZY) //może być potrzebne jeśli często wyciągamy pdfy nie dotykając tej tablicy, żeby się nie ładowała do pamięci
    private byte[] data;

    public PdfEntity(byte[] data) {
        this.data = data;
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

    public byte[] getData() {
        return data;
    }
}
