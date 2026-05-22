-- Correção do schema do banco de dados
-- Adicionar colunas faltantes em TB_ECOPONTOS

ALTER TABLE TB_ECOPONTOS ADD (
    cep VARCHAR2(8),
    bairro VARCHAR2(100),
    cidade VARCHAR2(100),
    uf VARCHAR2(2)
);

-- Atualizar dados dos Ecopontos com informações completas
ALTER TABLE TB_USUARIOS ADD (
    cep VARCHAR2(8),
    bairro VARCHAR2(100),
    cidade VARCHAR2(100),
    uf VARCHAR2(2)
);
UPDATE TB_ECOPONTOS SET cep = '01302001', bairro = 'Consolação', cidade = 'São Paulo', uf = 'SP' WHERE id_ecoponto = 1;
UPDATE TB_ECOPONTOS SET cep = '02012021', bairro = 'Santana', cidade = 'São Paulo', uf = 'SP' WHERE id_ecoponto = 2;
UPDATE TB_ECOPONTOS SET cep = '08220000', bairro = 'Itaquera', cidade = 'São Paulo', uf = 'SP' WHERE id_ecoponto = 3;
UPDATE TB_ECOPONTOS SET cep = '05432001', bairro = 'Pinheiros', cidade = 'São Paulo', uf = 'SP' WHERE id_ecoponto = 4;

UPDATE TB_USUARIOS SET cep = '01501000', bairro = 'Liberdade', cidade = 'São Paulo', uf = 'SP' WHERE id_usuario = 1;
UPDATE TB_USUARIOS SET cep = '02302000', bairro = 'Tucuruvi', cidade = 'São Paulo', uf = 'SP' WHERE id_usuario = 2;
UPDATE TB_USUARIOS SET cep = '03314000', bairro = 'Tatuapé', cidade = 'São Paulo', uf = 'SP' WHERE id_usuario = 3;

COMMIT;
