package org.project.kakaotalk.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import org.project.kakaotalk.global.enums.MessageTypeEnum;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "message")
public class MessageEntity {

    @Id
    private String id;

    private Long roomId;

    private Long userId;

    private MessageTypeEnum type;

    private String content;
}
