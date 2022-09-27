package uz.pdp.appnewsstatus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appnewsstatus.entity.AttachmentContent;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Long> {

    Optional<AttachmentContent> findByAttachmentId(Long attachmentId);
}
