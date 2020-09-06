package biz.mintchoco.complexjpabackend.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public @Builder @Getter @Setter class MetadataPk implements Serializable {

    private String attrName;
    private Long parentId;
}
