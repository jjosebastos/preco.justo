CREATE TABLE t_pj_pato (
    id_pato BIGSERIAL PRIMARY KEY,
    nm_pato VARCHAR(50) NOT NULL,
    id_mae BIGINT,
    st_disponibilidade VARCHAR(20) NOT NULL DEFAULT 'DISPONIVEL',
    CONSTRAINT fk_pato_mae FOREIGN KEY (id_mae) REFERENCES t_pj_pato (id_pato)
);