package konrad.lubaski.manage.pdf;

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

    @GetMapping("/pdfs/{pdfId}")
    public PdfEntity getNewPdf(@PathVariable Integer pdfId) {
        return pdfService.getPdf(pdfId);
    }

    @GetMapping("/pdfs")
    public Collection<PdfEntity> getAllPdfs() {
        return pdfService.getPdfs();
    }

    @PostMapping("/pdfs")
    public void addNewPdf(@RequestBody PdfEntity pdfEntity) {
        pdfService.addPdf(pdfEntity);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Plik jest pusty!");
        }

        // Sprawdzenie czy to PDF
        if (!Objects.equals(file.getContentType(), "application/pdf")) {
            return ResponseEntity.badRequest().body("Tylko pliki PDF są dozwolone!");
        }
//todo czy serwer pozwoli na zapisanie pliku pdf, generowanie id dla pdfów,
        try {
            // Zapis pliku na serwer (np. do katalogu "uploads")
            Path filePath = Paths.get(FILE_PATH + file.getOriginalFilename());
            Files.createDirectories(filePath.getParent()); // Tworzenie katalogu jeśli nie istnieje
            Files.write(filePath, file.getBytes());

            return ResponseEntity.ok("Plik został zapisany: " + filePath);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Błąd podczas zapisywania pliku!");
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadPdf(String fileName) throws IOException {
        // 1. Załóżmy, że masz lokalny plik PDF w katalogu resources
        //    lub na dysku (uwaga na ścieżki w środowisku Docker!)
        File pdfFile = new File(FILE_PATH + fileName);

        // 2. Tworzymy zasób jako InputStreamResource (możesz też użyć ByteArrayResource itd.)
        InputStreamResource resource = new InputStreamResource(new FileInputStream(pdfFile));

        // 3. Tworzymy odpowiedź HTTP
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + pdfFile.getName() + "\"") // lub inline
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdfFile.length())
                .body(resource);
    }
}