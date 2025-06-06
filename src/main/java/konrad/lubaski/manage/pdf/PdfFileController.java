package konrad.lubaski.manage.pdf;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/pdf-files")
public class PdfFileController {

    private PdfService pdfService;
    private PdfUrlBuilder pdfUrlBuilder;

    public PdfFileController(PdfService pdfService, PdfUrlBuilder pdfUrlBuilder) {
        this.pdfService = pdfService;
        this.pdfUrlBuilder = pdfUrlBuilder;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable int id) {
        System.out.println("test");
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty!");
        }
        if (!Objects.equals(file.getContentType(), "application/pdf")) {
            return ResponseEntity.badRequest().body("Only PDF files are allowed!");
        }
        try {
            PdfFileEntity pdfFile = pdfService.addPdfFile(new PdfFileEntity(
                    id,
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            ));
            //todo refactor hardcoded domain
            return ResponseEntity.created(URI.create(pdfUrlBuilder.buildUrl("/pdf-files", pdfFile.getId()))).body(pdfFile);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error while saving file!");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadPdf(@PathVariable int id) {
        PdfFileEntity pdf = pdfService.getPdfFile(id);
        ByteArrayResource byteArrayResource = new ByteArrayResource(pdf.getData());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(pdf.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + pdf.getFileName() + "\"")
                .body(byteArrayResource);
    }
}