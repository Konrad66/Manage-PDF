package konrad.lubaski.manage.pdf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

//wymagane przez Hibernate
@Entity
public class PdfFileEntity {

    //wymagane przez Hibernate
    @Id
    private int id;
    private String fileName;
    private String contentType;
    // @Column(columnDefinition = "bytea")
    // hibernate powininien domyślnie ustawić typ kolumny na "bytea", a rzeczy powyżej dodaje się dla większej kontorli
    //@Basic(fetch = FetchType.LAZY) //może być potrzebne jeśli często wyciągamy pdfy nie dotykając tej tablicy, żeby się nie ładowała do pamięci
    @Lob
    @JsonIgnore
    private byte[] data;

    public PdfFileEntity(int id, String fileName, String contentType, byte[] data) {
        this.id = id;
        this.fileName = fileName;
        this.contentType = contentType;
        this.data = data;
    }

    public PdfFileEntity(String fileName, String contentType, byte[] data) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.data = data;
    }

    //wymagane przez Hibernate
    //wymagane przez Jackson
    PdfFileEntity() {
    }

    //wymagane przez Jackson
    public int getId() {
        return id;
    }

    //wymagane przez Jackson

    public byte[] getData() {
        return data;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContentType() {
        return contentType;
    }
}
