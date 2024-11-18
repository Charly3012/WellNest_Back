CREATE TABLE Reactions (
   idReaction INT AUTO_INCREMENT,
   idPost  BIGINT NOT NULL,
   idUser  BIGINT NOT NULL,
   reactionType VARCHAR(20) DEFAULT 'Me encanta',
   reactionDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY(idReaction),
   CONSTRAINT FK_user_reactions FOREIGN KEY (idUser)
       REFERENCES users(idUser)
       ON DELETE CASCADE,
   CONSTRAINT FK_post_reactions FOREIGN KEY (idUser)
       REFERENCES users(idUser)
       ON DELETE CASCADE
);