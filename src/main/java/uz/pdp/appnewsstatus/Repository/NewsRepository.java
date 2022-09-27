package uz.pdp.appnewsstatus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appnewsstatus.entity.News;

public interface NewsRepository extends JpaRepository<News,Long> {
}
