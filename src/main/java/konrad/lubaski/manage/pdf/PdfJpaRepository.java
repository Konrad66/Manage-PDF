package konrad.lubaski.manage.pdf;

//import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface PdfJpaRepository {


    Optional<PdfEntity> findById(Integer pdfId);

    Collection<PdfEntity> findAll();

    void save(PdfEntity pdf);
}
