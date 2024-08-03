CREATE TABLE `user` (
    `id`	BIGINT	NOT NULL    AUTO_INCREMENT,
    `name`	VARCHAR(255)	NULL,
    `username`	VARCHAR(255)	NULL,
    `password`	VARCHAR(255)	NULL,
    `created_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
    `updated_at`	TIMESTAMP	NOT NULL	DEFAULT NOW()   ON UPDATE NOW(),
    PRIMARY KEY (id)
);

CREATE TABLE `room` (
    `id`	BIGINT	NOT NULL    AUTO_INCREMENT,
    `type`	VARCHAR(255)	NOT NULL	COMMENT 'PRIVATE, GROUP',
    `title`	VARCHAR(255)	NULL,
    `created_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
    `updated_at`	TIMESTAMP	NOT NULL	DEFAULT NOW()   ON UPDATE NOW(),
    PRIMARY KEY (id)
);

CREATE TABLE `participant` (
    `id`	BIGINT	NOT NULL    AUTO_INCREMENT,
    `user_id`	BIGINT	NOT NULL,
    `room_id`	BIGINT	NOT NULL,
    `created_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
    `updated_at`	TIMESTAMP	NOT NULL	DEFAULT NOW()   ON UPDATE NOW(),
    PRIMARY KEY (id)
);

CREATE TABLE `friend` (
    `id`	BIGINT	NOT NULL    AUTO_INCREMENT,
    `user_id`	BIGINT	NOT NULL,
    `friend_id`	BIGINT	NOT NULL,
    `created_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
    `updated_at`	TIMESTAMP	NOT NULL	DEFAULT NOW()   ON UPDATE NOW(),
    PRIMARY KEY (id)
);


-- Foreign Key
ALTER TABLE `participant` ADD CONSTRAINT `FK_user_TO_participant_1` FOREIGN KEY (
    `user_id`
)
REFERENCES `user` (
    `id`
);

ALTER TABLE `participant` ADD CONSTRAINT `FK_room_TO_participant_1` FOREIGN KEY (
    `room_id`
)
REFERENCES `room` (
    `id`
);

ALTER TABLE `friend` ADD CONSTRAINT `FK_user_TO_friend_1` FOREIGN KEY (
    `user_id`
)
REFERENCES `user` (
    `id`
);

ALTER TABLE `friend` ADD CONSTRAINT `FK_user_TO_friend_2` FOREIGN KEY (
    `friend_id`
)
REFERENCES `user` (
    `id`
);