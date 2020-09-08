package biz.mintchoco.complexjpabackend.entity;

import biz.mintchoco.complexjpabackend.entity.base.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(schema= AbstractEntity.SCHEMA, name="master")
@AllArgsConstructor
@NoArgsConstructor
public @Builder @Getter @Setter class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String content1;

    @Column
    private String content2;

    @Column
    private String etc1;

    @Column
    private String etc2;

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="master")
    private Subtype subtype;
}
