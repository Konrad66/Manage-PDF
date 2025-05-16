package konrad.lubaski.manage.pdf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/pdfs")
public class PdfController {

    @Value("${domain}")
    private String domain;
    @Value("${protocol}")
    private String protocol;
    private PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    //todo poprawić
    @PostMapping
    public ResponseEntity<PdfDTO> savePdf(@RequestBody PdfDTO requestPdf) {
        PdfDTO createdPdf = pdfService.addPdf(requestPdf);
        return ResponseEntity.created(URI.create(protocol + domain + "/pdfs/" + createdPdf.getId())).body(createdPdf);
    }

    @GetMapping("/{id}")
    public PdfDTO getPdfById(@PathVariable int id) {
        return pdfService.getPdfById(id);
    }
}