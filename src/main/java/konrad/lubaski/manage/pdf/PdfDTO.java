package konrad.lubaski.manage.pdf;

public class PdfDTO {

    private int id;
    private String name;

    public PdfDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PdfDTO() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}