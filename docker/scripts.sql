create IF NOT EXISTS database seguros;

use seguros;

CREATE TABLE IF NOT EXISTS produtos (
    id VARCHAR(36) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    preco_base DOUBLE NOT NULL,
    preco_tarifado DOUBLE NOT NULL
);
INSERT INTO produtos (id, nome, categoria, preco_base, preco_tarifado)
VALUES ('7ced0713-d00a-4a34-86ee-de5d8ebaabdf', 'Seguro Residencial', 'residencial', 100.00, 107.00);

