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

    @PostMapping
    public ResponseEntity<PdfDTO> savePdf(@RequestBody PdfDTO pdfDTO) {
        PdfDTO dto = new PdfDTO(
                pdfDTO.getId(),
                pdfDTO.getName()
        );
        return ResponseEntity.created(URI.create(protocol + domain + "/pdfs/" + dto.getId())).body(dto);
    }
}