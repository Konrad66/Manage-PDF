package konrad.lubaski.manage.pdf;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class CustomPdfRepository implements PdfJpaRepository{
    @Override
    public Optional<PdfEntity> findById(Integer pdfId) {
        return Optional.empty();
    }

    @Override
    public Collection<PdfEntity> findAll() {
        return List.of();
    }

    @Override
    public void save(PdfEntity pdf) {

    }
}
