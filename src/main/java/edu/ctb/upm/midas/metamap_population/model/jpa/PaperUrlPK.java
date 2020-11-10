package edu.ctb.upm.midas.metamap_population.model.jpa;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by gerardo on 10/04/2018.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project eidw
 * @className PaperUrlPK
 * @see
 */
public class PaperUrlPK implements Serializable {
    private String paperId;
    private String urlId;

    public PaperUrlPK() {
    }

    public PaperUrlPK(String paperId, String urlId) {
        this.paperId = paperId;
        this.urlId = urlId;
    }

    @Column(name = "paper_id", nullable = false, length = 250)
    @Id
    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    @Column(name = "url_id", nullable = false, length = 250)
    @Id
    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperUrlPK that = (PaperUrlPK) o;
        return Objects.equals(paperId, that.paperId) &&
                Objects.equals(urlId, that.urlId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paperId, urlId);
    }
}
