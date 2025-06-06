package konrad.lubaski.manage.cineupdate;

import konrad.lubaski.manage.account.AccountEntity;
import konrad.lubaski.manage.account.AccountJpaRepository;
import konrad.lubaski.manage.common.EmailService;
import konrad.lubaski.manage.pdf.PdfEntity;
import konrad.lubaski.manage.pdf.PdfFileEntity;
import konrad.lubaski.manage.pdf.PdfFileJpaRepository;
import konrad.lubaski.manage.pdf.PdfJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CineupdateService {

    private PdfJpaRepository pdfJpaRepository;
    private PdfFileJpaRepository pdfFileJpaRepository;
    private AccountJpaRepository accountJpaRepository;
    private EmailService emailService;

    public CineupdateService(PdfJpaRepository pdfJpaRepository, PdfFileJpaRepository pdfFileJpaRepository, AccountJpaRepository accountJpaRepository, EmailService emailService) {
        this.pdfJpaRepository = pdfJpaRepository;
        this.pdfFileJpaRepository = pdfFileJpaRepository;
        this.accountJpaRepository = accountJpaRepository;
        this.emailService = emailService;
    }

    public void sendCineupdates(int cineupdateId, String accountEmail) {
        //pierwsze co musimy miec obiekt tego pdfa
        //musimy miec liste adresów email na która wysyłamy maile
        PdfEntity pdfEntity = pdfJpaRepository.findById(cineupdateId).orElseThrow();
        AccountEntity accountEntity = accountJpaRepository.findByEmail(accountEmail).orElseThrow();
        PdfFileEntity pdfFileEntity = pdfFileJpaRepository.findById(pdfEntity.getId()).orElseThrow();
        for (String employeesMail : accountEntity.getEmployeesMails()) {
            emailService.sendHtmlEmailWithAttachment(employeesMail, pdfEntity.getName(), "abcd", pdfFileEntity.getData(), pdfFileEntity.getFileName());
        }
    }
}