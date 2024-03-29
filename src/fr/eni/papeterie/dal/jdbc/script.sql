CREATE TABLE Articles(
                             idArticle INTEGER NOT NULL,
                             reference nchar(10) NOT NULL,
                             marque nvarchar(200) NOT NULL,
                             designation nvarchar(250) NOT NULL,
                             prixUnitaire float NOT NULL,
                             qteStock int NOT NULL,
                             grammage int NULL,
                             couleur nvarchar(50) NULL,
                             type nchar(10) NOT NULL,
                             CONSTRAINT PK_Articles PRIMARY KEY
                                 (
                                  idArticle autoincrement
                                     ))
