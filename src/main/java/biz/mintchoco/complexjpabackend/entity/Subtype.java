package biz.mintchoco.complexjpabackend.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public @Builder @Getter @Setter class Subtype {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch=FetchType.LAZY)
    private Master master;

    /*
    @Column
    private Map<String, Metadata> metadata;
     */

    @Column
    private long insertedDatetime;

    @Column
    private String insertedUser;

    @Column
    private long updatedDatetime;

    @Column
    private String updatedUser;

    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    public static @Builder @Getter @Setter class Metadata {

        @EmbeddedId
        private MetadataPk metadataPK;

        @Column
        private String attrType;

        @Column
        private String attrValues;

        @Embeddable
        @AllArgsConstructor
        @NoArgsConstructor
        public static @Builder @Getter @Setter class MetadataPk implements Serializable {

            @Column
            private String attrName;

            @Column
            private Long parentId;
        }
    }
}
