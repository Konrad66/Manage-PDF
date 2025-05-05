package konrad.lubaski.manage.pdf;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Objects;

@RestController
public class PdfController {

    private PdfService pdfService;
    private static final String FILE_PATH = "app/";

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/get-data/{pdfId}")
    public String getMyData(String city, @RequestParam("liczba_ludnosci") Integer peopleCount, @PathVariable Integer pdfId) {
        System.out.println(city + " " + peopleCount);
        System.out.println(pdfId);
        return "hello";
    }

    //todo zwracaj Id utworzonego
    @PostMapping("/pdfs")
    public ResponseEntity<String> uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Plik jest pusty!");
        }
        if (!Objects.equals(file.getContentType(), "application/pdf")) {
            return ResponseEntity.badRequest().body("Tylko pliki PDF są dozwolone!");
        }

        try {
            pdfService.addPdf(new PdfDto(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            ));
            return ResponseEntity.ok("Plik został zapisany: ");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Błąd podczas zapisywania pliku!");
        }
    }

    @GetMapping("/pdfs/{id}")
    public ResponseEntity<Resource> downloadPdf(@PathVariable int id) throws IOException {
        PdfDto pdf = pdfService.getPdf(id);
        ByteArrayResource byteArrayResource = new ByteArrayResource(pdf.getData());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(pdf.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + pdf.getFileName() + "\"")
                .body(byteArrayResource);
    }
}