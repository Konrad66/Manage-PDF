package konrad.lubaski.manage.pdf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PdfUrlBuilder {

    @Value("${domain}")
    private String domain;
    @Value("${protocol}")
    private String protocol;

    public String buildUrl(String resourcePrefix, int id) {
        return protocol + domain + resourcePrefix + "/" + id;
    }
}