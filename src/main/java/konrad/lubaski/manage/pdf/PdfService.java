package konrad.lubaski.manage.pdf;

import konrad.lubaski.manage.common.DisableSpringSecurity;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class PdfService {


    private DisableSpringSecurity disableSpringSecurity;

    public PdfService(DisableSpringSecurity disableSpringSecurity) {
        this.disableSpringSecurity = disableSpringSecurity;

        System.out.println("Created pdf service");
    }
}
