-- Inserir dados de exemplo para Usuários
INSERT INTO TB_USUARIOS (nome, email, senha, total_kg_reciclado, total_co2_evitado)
VALUES ('João Silva', 'joao@example.com', 'senha123', 25.50, 45.75);

INSERT INTO TB_USUARIOS (nome, email, senha, total_kg_reciclado, total_co2_evitado)
VALUES ('Maria Santos', 'maria@example.com', 'senha456', 15.30, 28.50);

INSERT INTO TB_USUARIOS (nome, email, senha, total_kg_reciclado, total_co2_evitado)
VALUES ('Pedro Oliveira', 'pedro@example.com', 'senha789', 32.80, 60.20);

-- Inserir dados de exemplo para Materiais
INSERT INTO TB_MATERIAIS (nome, categoria, risco_ambiental, como_descartar, is_perigoso, is_reciclavel)
VALUES ('Garrafa de Plástico', 'Plástico', 'Contaminação do solo', 'Lavar e deixar secar completamente', 0, 1);

INSERT INTO TB_MATERIAIS (nome, categoria, risco_ambiental, como_descartar, is_perigoso, is_reciclavel)
VALUES ('Papel e Papelão', 'Papel', 'Baixo impacto ambiental', 'Separar de materiais úmidos', 0, 1);

INSERT INTO TB_MATERIAIS (nome, categoria, risco_ambiental, como_descartar, is_perigoso, is_reciclavel)
VALUES ('Bateria de Carro', 'Eletrônico', 'Contém ácido sulfúrico e chumbo', 'Levar a um ecoponto especializado', 1, 1);

INSERT INTO TB_MATERIAIS (nome, categoria, risco_ambiental, como_descartar, is_perigoso, is_reciclavel)
VALUES ('Vidro', 'Vidro', 'Risco de corte', 'Separar em saco apropriado', 0, 1);

INSERT INTO TB_MATERIAIS (nome, categoria, risco_ambiental, como_descartar, is_perigoso, is_reciclavel)
VALUES ('Tinta', 'Químico', 'Contaminação de água subterrânea', 'Selar bem o recipiente e levar ao ecoponto', 1, 0);

-- Inserir dados de exemplo para Ecopontos
INSERT INTO TB_ECOPONTOS (nome, endereco, latitude, longitude, horario_funcionamento)
VALUES ('Ecoponto Consolação','Rua da Consolação, 1200',-23.548482,-46.649231,'Segunda a Sábado, das 06:00 às 22:00');

INSERT INTO TB_ECOPONTOS (nome, endereco, latitude, longitude, horario_funcionamento)
VALUES ('Ecoponto Santana','Avenida Olavo Fontoura, 1500',-23.516241,-46.639452,'Segunda a Sexta, das 08:00 às 18:00');

INSERT INTO TB_ECOPONTOS (nome, endereco, latitude, longitude, horario_funcionamento)
VALUES ('Ecoponto Itaquera','Avenida Miguel Ignácio Curi, 111',-23.541523,-46.469412,'Segunda a Sábado, das 07:00 às 19:00');

INSERT INTO TB_ECOPONTOS (nome, endereco, latitude, longitude, horario_funcionamento)
VALUES ('Ecoponto Pinheiros','Rua Inácio Pereira da Rocha, 400',-23.555210,-46.690120,'Segunda a Sábado, das 06:00 às 22:00');

-- Inserir dados de exemplo para Agendamentos
INSERT INTO TB_AGENDAMENTOS (id_usuario, id_material, id_ecoponto, data_coleta, status)
VALUES (1, 1, 1, TO_TIMESTAMP('2024-05-15 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'PENDENTE');

INSERT INTO TB_AGENDAMENTOS (id_usuario, id_material, id_ecoponto, data_coleta, status)
VALUES (2, 2, 2, TO_TIMESTAMP('2024-05-16 14:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'CONFIRMADO');

INSERT INTO TB_AGENDAMENTOS (id_usuario, id_material, id_ecoponto, data_coleta, status)
VALUES (3, 3, 3, TO_TIMESTAMP('2024-05-17 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'CONCLUIDO');


