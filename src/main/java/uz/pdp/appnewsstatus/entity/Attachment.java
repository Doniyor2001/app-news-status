package uz.pdp.appnewsstatus.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.Hibernate;
import uz.pdp.appnewsstatus.entity.abs.AbsEntity;

import javax.persistence.Entity;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "attachment")
@FieldNameConstants
public class Attachment extends AbsEntity {

    private String originalName;
    private Long size;
    private String contentType;
    private String name;

    public Attachment(String originalName, Long size, String contentType) {
        this.originalName = originalName;
        this.size = size;
        this.contentType = contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Attachment that = (Attachment) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
