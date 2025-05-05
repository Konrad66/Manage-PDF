package konrad.lubaski.manage.pdf;

import org.springframework.stereotype.Component;

@Component
public class PdfService {

    private PdfFileJpaRepository pdfFileJpaRepository;

    public PdfService(PdfFileJpaRepository pdfFileJpaRepository) {
        this.pdfFileJpaRepository = pdfFileJpaRepository;
    }

    public PdfFileEntity getPdf(Integer pdfId) {
        PdfFileEntity entity = pdfFileJpaRepository.findById(pdfId).orElseThrow();
        return new PdfFileEntity(
                entity.getId(),
                entity.getFileName(),
                entity.getContentType(),
                entity.getData()
        );
    }

    public PdfFileEntity addPdf(PdfFileEntity pdf) {
        return pdfFileJpaRepository.save(pdf);
    }
}