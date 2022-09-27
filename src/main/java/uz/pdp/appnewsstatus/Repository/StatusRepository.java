package uz.pdp.appnewsstatus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appnewsstatus.entity.Status;

public interface StatusRepository extends JpaRepository<Status,Long> {

}
