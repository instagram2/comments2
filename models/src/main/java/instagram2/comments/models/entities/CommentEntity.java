package instagram2.comments.models.entities;

import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "comments")
@NamedQueries(value =
        {
                @NamedQuery(name = "CommentEntity.getByImageId",
                query = "select c from CommentEntity c where c.imageId = " + ":imageId")
        })

@UuidGenerator(name = "idGenerator")
public class CommentEntity {
    @Id
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "createdAt")
    private Instant createdAt;

    @Column(name = "authorId")
    private String authorId;

    @Column(name = "imageId")
    private Integer imageId;

    @Column(name = "text")
    private String text;

    public String getText() {
        return text;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Integer getImageId() {
        return imageId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getId() {
        return id;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }
}
