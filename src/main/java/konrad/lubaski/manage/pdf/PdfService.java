package konrad.lubaski.manage.pdf;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PdfService {

    private PdfFileJpaRepository pdfFileJpaRepository;
    private PdfJpaRepository pdfJpaRepository;

    public PdfService(PdfFileJpaRepository pdfFileJpaRepository, PdfJpaRepository pdfJpaRepository) {
        this.pdfFileJpaRepository = pdfFileJpaRepository;
        this.pdfJpaRepository = pdfJpaRepository;
    }

    public PdfFileEntity getPdfFile(Integer pdfFileId) {
        return pdfFileJpaRepository.findById(pdfFileId).orElseThrow();
    }

    public PdfDTO getPdfById(int pdfId) {
        PdfEntity entity = pdfJpaRepository.findById(pdfId).orElseThrow();
        return new PdfDTO(
                entity.getId(),
                entity.getName()
        );
    }

    public PdfFileEntity addPdfFile(PdfFileEntity pdfFile) {
        return pdfFileJpaRepository.save(pdfFile);
    }

    public PdfDTO addPdf(PdfDTO pdfDTO) {
        PdfEntity entity = pdfJpaRepository.save(new PdfEntity(
                pdfDTO.getId(),
                pdfDTO.getName()
        ));
        return new PdfDTO(
                entity.getId(),
                entity.getName());
    }
}