CREATE TABLE posts (
   idPost BIGINT AUTO_INCREMENT,
   idUser BIGINT NOT NULL,
   postDate DATETIME DEFAULT CURRENT_TIMESTAMP,
   postContent TEXT,
   conter INT,
   mood VARCHAR(100),
   PRIMARY KEY (idPost),
   CONSTRAINT FK_user_posts FOREIGN KEY (idUser)
       REFERENCES users(idUser)
       ON DELETE CASCADE
);