CREATE TABLE notes (
    idNote BIGINT AUTO_INCREMENT,
    idUser BIGINT NOT NULL,
    date DATETIME DEFAULT CURRENT_TIMESTAMP,
    content TEXT,
    state BOOLEAN,
    PRIMARY KEY (idNote),
    CONSTRAINT FK_user FOREIGN KEY (idUser)
        REFERENCES users(idUser)
        ON DELETE CASCADE
);