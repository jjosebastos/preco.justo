CREATE TABLE t_pj_cliente(
    id_cliente BIGSERIAL PRIMARY KEY,
    nm_cliente VARCHAR(50) NOT NULL,
    fl_desconto BOOLEAN NOT NULL DEFAULT FALSE,
    nr_cpf VARCHAR(15) NOT NULL,
    dt_cadastro TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT uk_cliente_cpf UNIQUE (nr_cpf)
);