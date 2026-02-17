CREATE TABLE t_pj_venda (
    id_venda BIGSERIAL PRIMARY KEY,
    dt_venda TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    vl_total DECIMAL(10,2) NOT NULL,
    fl_desconto BOOLEAN DEFAULT FALSE,
    id_cliente BIGINT NOT NULL,
    id_vendedor BIGINT NOT NULL,
    CONSTRAINT fk_cliente_venda
        FOREIGN KEY (id_cliente) REFERENCES t_pj_cliente (id_cliente),
    CONSTRAINT fk_vendedor_venda
        FOREIGN KEY (id_vendedor) REFERENCES t_pj_vendedor (id_vendedor)
);