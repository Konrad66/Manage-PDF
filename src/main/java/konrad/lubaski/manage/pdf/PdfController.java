package konrad.lubaski.manage.pdf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PdfController {

    @GetMapping("/get-data")
    public String getMyData() {
        return "hello";
    }
}
