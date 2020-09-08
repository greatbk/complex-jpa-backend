package biz.mintchoco.complexjpabackend.entity.base;

import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Collection;

@MappedSuperclass
public abstract class AbstractMetadata {

    private @Column(length=4000) String attrValue;
    private String attrType;

    public void setAttrValueObject(Object value) {
        try {
            if (value instanceof Collection) {
                attrValue = StringUtils.isEmpty(value) ? null : StringUtils.collectionToCommaDelimitedString((Collection<?>) value);
                attrType = Collection.class.getName();
            } else if (value instanceof Number) {
                attrValue = (StringUtils.isEmpty(value) ? null : value.toString());
                attrType = Number.class.getName();
            } else if (value instanceof Boolean) {
                attrValue = (StringUtils.isEmpty(value) ? null : value.toString());
                attrType = Boolean.class.getName();
            } else {
                attrValue = (StringUtils.isEmpty(value) ? null : value.toString());
                attrType = String.class.getName();
            }
        } catch (Exception e) {
            attrValue = (StringUtils.isEmpty(value) ? null : value.toString());
            attrType = String.class.getName();
        }
    }

    public Object getAttrValueObject() {
        try {
            if (Collection.class.getName().equals(attrType)) {
                return StringUtils.commaDelimitedListToSet(attrValue);
            } else if (Number.class.getName().equals(attrType)) {
                return Long.parseLong(attrValue);
            } else if (Boolean.class.getName().equals(attrType)) {
                return Boolean.parseBoolean(attrValue);
            } else {
                return attrValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return attrValue;
        }
    }
}
