package konrad.lubaski.manage.pdf;

import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

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
        pdfFileJpaRepository.findById(pdfDTO.getId()).orElseThrow();

        if(!pdfFileJpaRepository.existsById(pdfDTO.getId())){
            throw new NoSuchElementException("No pdf file");
        }



        PdfEntity entity = pdfJpaRepository.save(new PdfEntity(
                pdfDTO.getId(),
                pdfDTO.getName()
        ));
        //sprawdzić czy w bazie jest plik pdf o tym samym id
        return new PdfDTO(
                entity.getId(),
                entity.getName());
    }
}