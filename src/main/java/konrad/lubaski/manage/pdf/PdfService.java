package konrad.lubaski.manage.pdf;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PdfService {

    private PdfJpaRepository pdfJpaRepository;

    public PdfService(PdfJpaRepository pdfJpaRepository) {
        this.pdfJpaRepository = pdfJpaRepository;
    }

    public PdfEntity getPdf(Integer pdfId) {
        return pdfJpaRepository.findById(pdfId).orElseThrow();
    }

    public Collection<PdfEntity> getPdfs() {
        return pdfJpaRepository.findAll();
    }

    public void addPdf(PdfDto pdf) {
        pdfJpaRepository.save(new PdfEntity(
                pdf.getData()
        ));
    }
}