package edu.ctb.upm.midas.metamap_population.model.jpa;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

/**
 * Created by gerardo on 20/07/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project demo
 * @className Code
 * @see
 */
@Entity
@Table(name = "code", catalog = "edsssdb", schema = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Code.findAll", query = "SELECT c FROM Code c")
        , @NamedQuery(name = "Code.findById", query = "SELECT c FROM Code c WHERE c.code = :code AND c.resourceId = :resourceId")
        , @NamedQuery(name = "Code.findByCode", query = "SELECT c FROM Code c WHERE c.code = :code")
        , @NamedQuery(name = "Code.findByResourceId", query = "SELECT c FROM Code c WHERE c.resourceId = :resourceId")
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Code.findByIdNativeMapping",
                query = "SELECT c.code, c.resource_id "
                        + "FROM code c WHERE c.code = :code AND c.resource_id = :resourceId",
                resultSetMapping="CodeMapping"

        ),
        @NamedNativeQuery(
                name = "Code.findByIdNativeResultClass",
                query = "SELECT c.code, c.resource_id "
                        + "FROM code c WHERE c.code = :code AND c.resource_id = :resourceId",
                resultClass = Code.class
        ),
        @NamedNativeQuery(
                name = "Code.findByIdNative",
                query = "SELECT c.code, c.resource_id "
                        + "FROM code c WHERE c.code = :code AND c.resource_id = :resourceId"
        ),


        @NamedNativeQuery(
                name = "Code.insertNative",
                query = "INSERT INTO code (code, resource_id) "
                        + "VALUES (:code, :resourceId)"
        ),
        @NamedNativeQuery(
                name = "HasCode.insertNative",
                query = "INSERT IGNORE INTO has_code (document_id, date, code, resource_id) "
                        + "VALUES (:documentId, :date, :code, :resourceId)"
        ),
        @NamedNativeQuery(
                name = "CodeUrl.insertNative",
                query = "INSERT INTO code_url (code, resource_id, url_id) "
                        + "VALUES (:code, :resourceId, :urlId)"
        ),
        @NamedNativeQuery(
                name = "CodeRetrievalMethod.insertNative",
                query = "INSERT IGNORE INTO code_retrieval_method (code, resource_id, retrieval_method_id) "
                        + "VALUES (:code, :resourceId, :retrievalMethodId)"
        ),
        @NamedNativeQuery(
                name = "Code.findBySourceAndSnapshotAndDiseaseIdAndResourceNameNative",
                query = "SELECT DISTINCT c.code, c.resource_id " +
                        "FROM disease d\n" +
                        "       INNER JOIN has_disease hd ON hd.disease_id = d.disease_id\n" +
                        "       INNER JOIN document doc ON doc.document_id = hd.document_id AND doc.date = hd.date\n" +
                        "  -- source\\n\n" +
                        "       INNER JOIN has_source hs ON hs.document_id = doc.document_id AND hs.date = doc.date\n" +
                        "       INNER JOIN source sce ON sce.source_id = hs.source_id\n" +
                        "  -- codes\\n\n" +
                        "       INNER JOIN has_code hc ON hc.document_id = doc.document_id AND hc.date = doc.date\n" +
                        "       INNER JOIN code c ON c.code = hc.code AND c.resource_id = hc.resource_id\n" +
                        "       INNER JOIN resource r ON r.resource_id = c.resource_id\n" +
                        "WHERE sce.name = :source " +
                        "  AND doc.date = :snapshot " +
                        "  AND r.name = :resource " +
                        "  AND d.disease_id = :diseaseId "
        ),
        @NamedNativeQuery(
                name = "Code.findBySourceAndSnapshotAndDiseaseIdNative",
                query = "SELECT DISTINCT c.code, c.resource_id " +
                        "FROM disease d\n" +
                        "       INNER JOIN has_disease hd ON hd.disease_id = d.disease_id\n" +
                        "       INNER JOIN document doc ON doc.document_id = hd.document_id AND doc.date = hd.date\n" +
                        "  -- source\\n\n" +
                        "       INNER JOIN has_source hs ON hs.document_id = doc.document_id AND hs.date = doc.date\n" +
                        "       INNER JOIN source sce ON sce.source_id = hs.source_id\n" +
                        "  -- codes\\n\n" +
                        "       INNER JOIN has_code hc ON hc.document_id = doc.document_id AND hc.date = doc.date\n" +
                        "       INNER JOIN code c ON c.code = hc.code AND c.resource_id = hc.resource_id\n" +
                        "       INNER JOIN resource r ON r.resource_id = c.resource_id\n" +
                        "WHERE sce.name = :source " +
                        "  AND doc.date = :snapshot " +
                        "  AND d.disease_id = :diseaseId "
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "CodeMapping",
                entities = @EntityResult(
                        entityClass = Code.class,
                        fields = {
                                @FieldResult(name = "code", column = "code"),
                                @FieldResult(name = "resourceId", column = "resource_id")
                        }
                )
        )
})

@IdClass(CodePK.class)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="codePK")
public class Code {
    private String code;
    private Integer resourceId;
    private Resource resourceByResourceId;
    private List<CodeUrl> codeUrls;
    private List<HasCode> hasCodes;
    private List<SynonymCode> synonymCodes;

    @Id
    @Column(name = "code", nullable = false, length = 150)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Id
    @Column(name = "resource_id", nullable = false)
    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Code code1 = (Code) o;
        return Objects.equals(code, code1.code) &&
                Objects.equals(resourceId, code1.resourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, resourceId);
    }

    @ManyToOne
    @JoinColumn(name = "resource_id", referencedColumnName = "resource_id", nullable = false, insertable = false, updatable = false)
    public Resource getResourceByResourceId() {
        return resourceByResourceId;
    }

    public void setResourceByResourceId(Resource resourceByResourceId) {
        this.resourceByResourceId = resourceByResourceId;
    }

    @OneToMany(mappedBy = "code_0")
    public List<CodeUrl> getCodeUrls() {
        return codeUrls;
    }

    public void setCodeUrls(List<CodeUrl> codeUrls) {
        this.codeUrls = codeUrls;
    }

    @OneToMany(mappedBy = "code_0")
    public List<HasCode> getHasCodes() {
        return hasCodes;
    }

    public void setHasCodes(List<HasCode> hasCodes) {
        this.hasCodes = hasCodes;
    }

    @OneToMany(mappedBy = "code_0")
    public List<SynonymCode> getSynonymCodes() {
        return synonymCodes;
    }

    public void setSynonymCodes(List<SynonymCode> synonymCodes) {
        this.synonymCodes = synonymCodes;
    }

    @Override
    public String toString() {
        return "Code{" +
                "code='" + code + '\'' +
                ", resourceId=" + resourceId +
                '}';
    }
}
