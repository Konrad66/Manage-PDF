package konrad.lubaski.manage.pdf;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class PdfController {

    private PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/get-data/{pdfId}")
    public String getMyData(String city, @RequestParam("liczba_ludnosci") Integer peopleCount, @PathVariable Integer pdfId) {
        System.out.println(city + " " + peopleCount);
        System.out.println(pdfId);
        return "hello";
    }

    @GetMapping("/pdfs/{pdfId}")
    public PdfEntity getNewPdf(@PathVariable Integer pdfId) {
        return pdfService.getPdf(pdfId);
    }

    @GetMapping("/pdfs")
    public Collection<PdfEntity> getAllPdfs() {
        return pdfService.getPdfs();
    }

    @PostMapping("/pdfs")
    public void addNewPdf(@RequestBody String pdf) {
        pdfService.addPdf(pdf);
    }
}