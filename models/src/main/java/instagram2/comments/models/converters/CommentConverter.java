package instagram2.comments.models.converters;

import instagram2.comments.lib.Comment;
import instagram2.comments.models.entities.CommentEntity;

public class CommentConverter {

    public static Comment toDto(CommentEntity entity) {
        Comment dto = new Comment();
        dto.setAuthorId(entity.getAuthorId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setImageId(entity.getImageId());
        dto.setText(entity.getText());
        return dto;
    }

    public static CommentEntity toEntity(Comment dto) {
        CommentEntity entity = new CommentEntity();
        entity.setText(dto.getText());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setAuthorId(dto.getAuthorId());
        entity.setImageId(dto.getImageId());

        return entity;
    }
}
