package konrad.lubaski.manage.pdf;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PdfService {

    private PdfJpaRepository pdfJpaRepository;

    public PdfService(PdfJpaRepository pdfJpaRepository) {
        this.pdfJpaRepository = pdfJpaRepository;
    }

    public PdfDto getPdf(Integer pdfId) {
        PdfEntity entity = pdfJpaRepository.findById(pdfId).orElseThrow();
        return new PdfDto(
                entity.getId(),
                entity.getFileName(),
                entity.getContentType(),
                entity.getData()
        );

    }

    public Collection<PdfEntity> getPdfs() {
        return pdfJpaRepository.findAll();
    }

    public void addPdf(PdfDto pdf) {
        pdfJpaRepository.save(new PdfEntity(
                pdf.getFileName(),
                pdf.getContentType(),
                pdf.getData()
        ));
    }
}