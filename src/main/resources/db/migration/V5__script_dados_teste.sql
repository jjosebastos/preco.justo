
INSERT INTO t_pj_cliente (nm_cliente, fl_desconto, nr_cpf, dt_cadastro)
VALUES
('Joaquim da Silva', FALSE, '12345678901', '2024-01-10 10:00:00'),
('Maria Quack Oliveira', TRUE, '98765432100', '2024-02-15 14:30:00'),
('Pato Donald (Cliente)', FALSE, '11122233344', '2023-02-15 14:30:00');


INSERT INTO t_pj_vendedor (nm_vendedor, nr_matricula, nr_cpf) VALUES
('Ricardo Gerente', 'MAT000001', '55544433322'),
('Fernanda Vendas', 'MAT000002', '88877766611');

INSERT INTO t_pj_pato (nm_pato, id_mae, st_disponibilidade)
VALUES
('Pata Flora', NULL, 'DISPONIVEL'),
('Pata Margarida', NULL, 'VENDIDO');

INSERT INTO t_pj_pato (nm_pato, id_mae, st_disponibilidade)
VALUES
('Patinho Júnior', 1, 'DISPONIVEL'),
('Patinha Bella', 1, 'DISPONIVEL'),
('Pato Huguinho', 2, 'VENDIDO');


INSERT INTO t_pj_pato (nm_pato, id_mae, st_disponibilidade)
VALUES
('Pata Flora', NULL, 'DISPONIVEL'),
('Pata Margarida', NULL, 'VENDIDO');

INSERT INTO t_pj_pato (nm_pato, id_mae, st_disponibilidade) VALUES
('Patinho Júnior', 1, 'DISPONIVEL'),
('Patinha Bella', 1, 'DISPONIVEL'),
('Pato Huguinho', 2, 'VENDIDO');


INSERT INTO t_pj_pato (nm_pato, id_mae, st_disponibilidade)
VALUES
('Pata Flora', NULL, 'DISPONIVEL'), -- Gerará ID 1
('Pata Margarida', NULL, 'VENDIDO'); -- Gerará ID 2

INSERT INTO t_pj_pato (nm_pato, id_mae, st_disponibilidade)
VALUES
('Patinho Júnior', 1, 'DISPONIVEL'),
('Patinha Bella', 1, 'DISPONIVEL'),
('Pato Huguinho', 2, 'VENDIDO');



INSERT INTO t_pj_venda (vl_total, fl_desconto, id_cliente, id_vendedor, dt_venda)
VALUES
(250.00, FALSE, 1, 1, '2024-03-01 09:00:00'),
(1200.50, TRUE, 2, 2, '2024-03-02 16:45:00'),
(85.90, FALSE, 3, 1, CURRENT_TIMESTAMP);