package konrad.lubaski.manage.pdf;

import org.springframework.beans.factory.annotation.Value;
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
    @Value("${domain}")
    private String domain;
    @Value("${protocol}")
    private String protocol;

    public PdfFileController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable int id) {
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
            return ResponseEntity.created(URI.create(protocol + domain + "/pdf-files/" + pdfFile.getId())).body(pdfFile);
        } catch (Exception e) {
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