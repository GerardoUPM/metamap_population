package edu.ctb.upm.midas.metamap_population.model.jpa;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by gerardo on 20/07/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project demo
 * @className HasSemanticTypePK
 * @see
 */
@Embeddable
public class HasSemanticTypePK implements Serializable {
    private String cui;
    private String semanticType;

    public HasSemanticTypePK() {
    }

    public HasSemanticTypePK(String cui, String semanticType) {
        this.cui = cui;
        this.semanticType = semanticType;
    }

    @Column(name = "cui", nullable = false, length = 8)
    @Id
    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    @Column(name = "semantic_type", nullable = false, length = 8)
    @Id
    public String getSemanticType() {
        return semanticType;
    }

    public void setSemanticType(String semanticType) {
        this.semanticType = semanticType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HasSemanticTypePK that = (HasSemanticTypePK) o;
        return Objects.equals(cui, that.cui) &&
                Objects.equals(semanticType, that.semanticType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cui, semanticType);
    }
}
