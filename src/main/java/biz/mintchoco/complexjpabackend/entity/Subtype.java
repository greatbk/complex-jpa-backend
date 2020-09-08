package biz.mintchoco.complexjpabackend.entity;

import biz.mintchoco.complexjpabackend.entity.base.AbstractEntity;
import biz.mintchoco.complexjpabackend.entity.base.AbstractMetadata;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(schema=AbstractEntity.SCHEMA, name="subtype")
public class Subtype extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter @Setter Long id;

    @OneToOne(fetch=FetchType.LAZY)
    private @Getter @Setter Master master;

    @OneToMany(mappedBy = "id.parent", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
    @MapKey(name="id.attrName")
    private @Getter @Setter Map<String, Metadata> meta = new HashMap<>();

    public Subtype() {
        super();
    }

    @Builder
    public Subtype(Long insertedDateTime, String insertedUser, Long updatedDatetime, String updatedUser, Master master, Map<String, Object> metadata) {
        super(insertedDateTime, insertedUser, updatedDatetime, updatedUser);
        this.master = master;
        //this.metadata = metadata;
    }

    @Entity
    @Table(schema=AbstractEntity.SCHEMA, name="metadata")
    public static @Getter @Setter class Metadata extends AbstractMetadata {

        @EmbeddedId
        private Id id;

        public Metadata() {
            setId(new Id());
        }

        public Metadata(Subtype parent, String key, Object value) {
            this();
            id.setParent(parent);
            id.setAttrName(key);
            setAttrValueObject(value);
        }

        @Embeddable
        public static @Getter @Setter class Id implements Serializable {

            @ManyToOne(fetch=FetchType.LAZY)
            private Subtype parent;

            private String attrName;
        }
    }
}
