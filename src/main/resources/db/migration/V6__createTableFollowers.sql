CREATE TABLE followers (
    idRelation BIGINT AUTO_INCREMENT,
    idUser BIGINT NOT NULL,
    idFollower BIGINT NOT NULL,
    dateStartFollow TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (idRelation),
    UNIQUE (idUser, idFollower),
    CONSTRAINT FK_followers_idUser FOREIGN KEY (idUser)
       REFERENCES users(idUser)
       ON DELETE CASCADE,
    CONSTRAINT FK_followers_idFollower FOREIGN KEY (idFollower)
       REFERENCES users(idUser)
       ON DELETE CASCADE
);
