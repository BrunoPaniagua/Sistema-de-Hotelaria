CREATE DATABASE hotelaria;
USE hotelaria;

CREATE TABLE quarto(
	numero INT NOT NULL,
	capacidade INT NOT NULL,
	estado ENUM('disponivel', 'ocupado', 'manutencao') NOT NULL,
	PRIMARY KEY(numero)
);

CREATE TABLE cliente(
	cpf VARCHAR(11) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(13) NOT NULL,
    PRIMARY KEY(cpf)
);

CREATE TABLE reserva(
	id_reserva INT AUTO_INCREMENT,
    entrada DATETIME NOT NULL,
    saida DATETIME NOT NULL,
    cliente_cpf  VARCHAR(11) NOT NULL,
    quarto_numero INT NOT NULL,
	PRIMARY KEY(id_reserva) ,
    FOREIGN KEY(cliente_cpf) REFERENCES cliente(cpf),
    FOREIGN KEY(quarto_numero) REFERENCES quarto(numero),
    CHECK (entrada < saida)
);

INSERT INTO quarto(numero, capacidade, estado)
VALUES
(1, 2,'disponivel'),
(2, 1,'manutencao'),
(3, 1,'disponivel');
