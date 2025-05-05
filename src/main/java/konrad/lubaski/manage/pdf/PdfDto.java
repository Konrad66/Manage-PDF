package konrad.lubaski.manage.pdf;

public class PdfDto {

    private int id;
    private String fileName;
    private String contentType;
    private byte[] data;


    public PdfDto(String fileName, String contentType, byte[] data) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.data = data;
    }

    public PdfDto(int id, String fileName, String contentType, byte[] data) {
        this.id = id;
        this.fileName = fileName;
        this.contentType = contentType;
        this.data = data;
    }

    public PdfDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}