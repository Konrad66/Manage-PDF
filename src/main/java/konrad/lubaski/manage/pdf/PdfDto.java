package konrad.lubaski.manage.pdf;

public class PdfDto {

    private int id;
    private byte[] data;

    public PdfDto(byte[] data) {
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
}