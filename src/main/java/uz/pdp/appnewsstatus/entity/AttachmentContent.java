package uz.pdp.appnewsstatus.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.Hibernate;
import uz.pdp.appnewsstatus.entity.abs.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "attachment_content")
@FieldNameConstants
public class AttachmentContent extends AbsEntity {

    private byte[] bytes;
    private byte[] base64;

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Attachment attachment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AttachmentContent that = (AttachmentContent) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
