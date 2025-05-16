package konrad.lubaski.manage.pdf;

public class PdfDTO {

    private int id;
    private String name;
    private String fileUrl;

    public PdfDTO(int id, String name, String fileUrl) {
        this.id = id;
        this.name = name;
        this.fileUrl = fileUrl;
    }

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

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}