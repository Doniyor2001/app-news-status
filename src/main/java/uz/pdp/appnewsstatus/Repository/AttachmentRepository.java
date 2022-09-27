package uz.pdp.appnewsstatus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appnewsstatus.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment,Long> {

}
