package konrad.lubaski.manage.pdf;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/pdfs")
public class PdfController {

    private PdfService pdfService;
    private PdfUrlBuilder pdfUrlBuilder;

    public PdfController(PdfService pdfService, PdfUrlBuilder pdfUrlBuilder) {
        this.pdfService = pdfService;
        this.pdfUrlBuilder = pdfUrlBuilder;
    }

    @PostMapping
    public ResponseEntity<PdfDTO> savePdf(@RequestBody PdfDTO requestPdf) {
        PdfDTO createdPdf = pdfService.addPdf(requestPdf);
        try {
            createdPdf.setFileUrl(pdfUrlBuilder.buildUrl("/pdf-files", createdPdf.getId()));
            return ResponseEntity.created(URI.create(pdfUrlBuilder.buildUrl("/pdfs", createdPdf.getId()))).body(createdPdf);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public PdfDTO getPdfById(@PathVariable int id) {
        PdfDTO pdf = pdfService.getPdfById(id);
        pdf.setFileUrl(pdfUrlBuilder.buildUrl("/pdf-files", pdf.getId()));
        return pdf;
    }
}