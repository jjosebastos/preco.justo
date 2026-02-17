CREATE TABLE t_pj_vendedor (
    id_vendedor BIGSERIAL PRIMARY KEY,
    nm_vendedor VARCHAR(50) NOT NULL,
    nr_matricula VARCHAR(9) NOT NULL,
    nr_cpf VARCHAR(15) NOT NULL,
    CONSTRAINT uk_vendedor_cpf UNIQUE (nr_cpf)
)