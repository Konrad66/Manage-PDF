package konrad.lubaski.manage.pdf;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PdfJpaRepository extends JpaRepository<PdfEntity, Integer> {
}
