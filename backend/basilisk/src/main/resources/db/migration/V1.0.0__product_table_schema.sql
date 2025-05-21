CREATE TABLE IF NOT EXISTS product (
    id varchar(36) NOT NULL PRIMARY KEY,
    "name" varchar(255) NOT null,
    description TEXT,
    status boolean NOT null
)

INSERT INTO product (id, "name", description, status) VALUES('d9da4fc5-b520-4351-ad14-9554b752fe8d', 'Fone de ouvido sem fio', 'Fone de ouvido resitente a impactos e a água até uma profundidade de 70 metros', true);
INSERT INTO product (id, "name", description, status) VALUES('f5ea542d-6e52-4c40-b809-66bb23d58437', 'Relógio Smart-Watch com fone bluetooth', 'Relógio Smart-Watch com fone bluetooth integrado na carcaça do dispositivo', true);
INSERT INTO product (id, "name", description, status) VALUES('9a1686ac-13fe-4cfb-b669-4ad1522fe73a', 'Urso de pelúcia branco', 'Urso de pelúcia branco segurando um pote de mel', true);
INSERT INTO product (id, "name", description, status) VALUES('d664cec4-564e-4069-ab2d-d61a8cdc9851', 'Carro elétrico - Tesla', 'Carro Elétrico modelo WFp-0234', true);
INSERT INTO product (id, "name", description, status) VALUES('189c8a9e-c789-4fc1-96af-b55fea363026', 'Almanaque rasgado da Turma da Mônica', 'Almanaque rasgado da Turma da Mônica - Edição 348', false);
INSERT INTO product (id, "name", description, status) VALUES('ab96b17e-92a6-4371-95f2-d2fd39f239ab', 'Toalha rosa de algodão', 'Toalha rosa de algodão do egito', false);