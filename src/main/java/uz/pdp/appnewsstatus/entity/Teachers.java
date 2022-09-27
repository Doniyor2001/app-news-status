package uz.pdp.appnewsstatus.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.pdp.appnewsstatus.entity.abs.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "teachers")
@SQLDelete(sql = "update teachers set deleted=true where id=?")
@Where(clause = "deleted=false")
@FieldNameConstants
public class Teachers extends AbsEntity {

    private String fullName;
    private String subject;
    private LocalDateTime date;

    @OneToOne
    private Attachment attachment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Teachers teachers = (Teachers) o;
        return getId() != null && Objects.equals(getId(), teachers.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
