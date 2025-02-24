package konrad.lubaski.manage.pdf;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PdfController {

    private Map<Integer, String> pdfMap = new HashMap<>();
    private int nextId = 1;


    @GetMapping("/get-data/{pdfId}")
    public String getMyData(String city, @RequestParam("liczba_ludnosci") Integer peopleCount, @PathVariable Integer pdfId) {
        System.out.println(city + " " + peopleCount);
        System.out.println(pdfId);
        return "hello";
    }

    @GetMapping("/pdfs/{pdfId}")
    public String getNewPdf(@PathVariable Integer pdfId) {
        return pdfMap.get(pdfId);
    }

    @GetMapping("/pdfs")
    public Collection<String> getAllPdfs() {
        return pdfMap.values();
    }

    @PostMapping("/pdfs")
    public void addNewPdf(@RequestBody String pdf) {
        pdfMap.put(nextId, pdf);
        nextId++;
    }


}
