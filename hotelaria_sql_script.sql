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

INSERT INTO cliente(cpf, nome, telefone)
VALUES
('12345678901','Fernando','5519123456789'),
('10987654321','Alberto','5519987654321');

INSERT INTO reserva(entrada, saida, cliente_cpf, quarto_numero)
VALUES
('2025-03-15 14:00:00', '2025-03-20 12:00:00', '12345678901', 2),
('2025-03-18 10:00:00', '2025-03-22 11:00:00', '10987654321', 3);

SELECT *
FROM quarto q
INNER JOIN reserva r ON r.quarto_numero = q.numero
INNER JOIN cliente c ON c.cpf = r.cliente_cpf;


