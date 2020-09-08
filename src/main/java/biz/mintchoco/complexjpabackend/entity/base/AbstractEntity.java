package biz.mintchoco.complexjpabackend.entity.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract @Getter @Setter class AbstractEntity {

    public static final String SCHEMA = "PUBLIC";

    @CreatedDate
    private Long insertedDatetime;

    @CreatedBy
    private String insertedUser;

    @LastModifiedDate
    private Long updatedDatetime;

    @LastModifiedBy
    private String updatedUser;

    public AbstractEntity() {}

    public AbstractEntity(Long insertedDatetime, String insertedUser, Long updatedDatetime, String updatedUser) {
        this.insertedDatetime = insertedDatetime;
        this.insertedUser = insertedUser;
        this.updatedDatetime = updatedDatetime;
        this.updatedUser = updatedUser;
    }

    @Transient
    protected static String[] IGNORES = new String[]{"", ""};

    @Override
    public String toString() {
        return getClass().getName();
    }
}
