package uz.pdp.appnewsstatus.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.pdp.appnewsstatus.entity.abs.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "news")
@SQLDelete(sql = "update news set deleted=true where id=?")
@Where(clause = "deleted=false")
public class News extends AbsEntity {

    private String title;
    private String text;
    private LocalDateTime date;

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Attachment attachment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        News news = (News) o;
        return getId() != null && Objects.equals(getId(), news.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
