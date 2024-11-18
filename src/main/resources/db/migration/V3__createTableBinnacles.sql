CREATE TABLE binnacles (
   idBinnacle BIGINT AUTO_INCREMENT,
   idUser BIGINT NOT NULL,
   date DATETIME DEFAULT CURRENT_TIMESTAMP,
   content TEXT,
   PRIMARY KEY (idBinnacle),
   CONSTRAINT FK_user_binnacles FOREIGN KEY (idUser)
       REFERENCES users(idUser)
       ON DELETE CASCADE
);