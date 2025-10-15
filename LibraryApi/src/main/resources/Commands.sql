CREATE TABLE autor (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    nacionalidade VARCHAR(50) NOT NULL,
    data_cadastro TIMESTAMP,
    data_atualizacao TIMESTAMP,
    id_usuario UUID
);

CREATE TABLE livro (
    id UUID,
    isbn VARCHAR(20) NOT NULL,
    titulo VARCHAR(150) NOT NULL,
    data_publicacao DATE NOT NULL,
    genero VARCHAR (30) NOT NULL,
    preco NUMERIC (12,2) NOT NULL,
    data_cadastro TIMESTAMP,
    data_atualizacao TIMESTAMP,
    id_usuario UUID,
    idAutor UUID NOT NULL,

    CONSTRAINT chk_genero CHECK (genero IN ('FICCAO', 'FANTASIA', 'MISTERIO', 'ROMANCE', 'BIOGRAFIA', 'CIENCIA')),
    CONSTRAINT uniq_isbn UNIQUE (isbn),
    CONSTRAINT id_pk PRIMARY KEY (id),
    CONSTRAINT id_autor_fk FOREIGN KEY (idAutor) REFERENCES autor(id)
);

DROP TABLE autor;
DROP TABLE livro;