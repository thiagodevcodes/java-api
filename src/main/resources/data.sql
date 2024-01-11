-- Active: 1698781018641@@127.0.0.1@3308@orcamentodb

CREATE TABLE unidade (
  id int AUTO_INCREMENT NOT NULL,
  nome varchar(255) NOT NULL,
  data_cadastro datetime NOT NULL,
  data_alteracao datetime NULL,
  CONSTRAINT PK_Unidade PRIMARY KEY (ID)
);
CREATE TABLE tipo_lancamento (
  id int AUTO_INCREMENT NOT NULL,
  nome varchar(255) NOT NULL,
  data_cadastro datetime NOT NULL,
  data_alteracao datetime NULL,
  CONSTRAINT PK_tipoLancamento PRIMARY KEY (id)
);
CREATE TABLE solicitante (
  id int AUTO_INCREMENT NOT NULL,
  nome varchar(255) NOT NULL,
  data_cadastro datetime NOT NULL,
  data_alteracao datetime NULL,
  CONSTRAINT PK_solicitante PRIMARY KEY (id)
);
CREATE TABLE objetivo_estrategico (
  id int AUTO_INCREMENT NOT NULL,
  nome varchar(255) NOT NULL,
  data_cadastro datetime NOT NULL,
  data_alteracao datetime NULL,
  CONSTRAINT PK_objetivoEstrategico PRIMARY KEY (id)
);
CREATE TABLE unidade_orcamentaria (
  id int AUTO_INCREMENT NOT NULL,
  codigo int NOT NULL,
  nome varchar(255) NOT NULL,
  data_cadastro datetime NOT NULL,
  data_alteracao datetime NULL,
  CONSTRAINT PK_unidadeOrcamentaria PRIMARY KEY (id),
  CONSTRAINT UQ_unidadeOrcamentaria UNIQUE (Codigo)
);
CREATE TABLE programa (
  id int AUTO_INCREMENT NOT NULL,
  codigo int NOT NULL,
  nome varchar(255) NOT NULL,
  data_cadastro datetime NOT NULL,
  data_alteracao datetime NULL,
  CONSTRAINT PK_programa PRIMARY KEY (id),
  CONSTRAINT UQ_programa UNIQUE (Codigo)
);
CREATE TABLE fonte_recurso (
  id int AUTO_INCREMENT NOT NULL,
  codigo int NOT NULL,
  nome varchar(255) NOT NULL,
  data_cadastro datetime NOT NULL,
  data_alteracao datetime NULL,
  CONSTRAINT PK_fonteRecurso PRIMARY KEY (id),
  CONSTRAINT UQ_fonteRecurso UNIQUE (Codigo)
);
CREATE TABLE acao (
  id int AUTO_INCREMENT NOT NULL,
  codigo int NOT NULL,
  nome varchar(255) NOT NULL,
  data_cadastro datetime NOT NULL,
  data_alteracao datetime NULL,
  CONSTRAINT PK_acao PRIMARY KEY (id),
  CONSTRAINT UQ_acao UNIQUE (Codigo)
);
CREATE TABLE grupo_despesa (
  id int AUTO_INCREMENT NOT NULL,
  codigo float NOT NULL,
  nome varchar(255) NOT NULL,
  data_cadastro datetime NOT NULL,
  data_alteracao datetime NULL,
  CONSTRAINT PK_grupoDespesa PRIMARY KEY (id),
  CONSTRAINT UQ_grupoDespesa UNIQUE (Codigo)
);
CREATE TABLE modalidade_aplicacao (
  id int AUTO_INCREMENT NOT NULL,
  codigo int NOT NULL,
  nome varchar(255) NOT NULL,
  data_cadastro datetime NOT NULL,
  data_alteracao datetime NULL,
  CONSTRAINT PK_modalidadeAplicacao PRIMARY KEY (id),
  CONSTRAINT UQ_modalidadeAplicacao UNIQUE (Codigo)
);
CREATE TABLE elemento_despesa (
  id int AUTO_INCREMENT NOT NULL,
  codigo int NOT NULL,
  nome varchar(255) NOT NULL,
  data_cadastro datetime NOT NULL,
  data_alteracao datetime NULL,
  CONSTRAINT PK_elementoDespesa PRIMARY KEY (id),
  CONSTRAINT UQ_elementoDespesa UNIQUE (Codigo)
);
CREATE TABLE tipo_transacao (
  id int AUTO_INCREMENT NOT NULL,
  nome varchar(255) NOT NULL,
  data_cadastro datetime NOT NULL,
  data_alteracao datetime NULL,
  CONSTRAINT PK_tipoTransacao PRIMARY KEY (id)
);
CREATE TABLE lancamentos (
  id int AUTO_INCREMENT NOT NULL,
  lancamento_invalido bit NOT NULL,
  numero_lancamento int NULL,
  id_tipo_lancamento int NOT NULL,
  data_lancamento date NOT NULL,
  id_lancamento_pai int NULL,
  id_unidade int NOT NULL,
  descricao varchar(255) NOT NULL,
  id_unidade_orcamentaria int NOT NULL,
  id_programa int NOT NULL,
  id_acao int NOT NULL,
  id_fonte_recurso int NOT NULL,
  id_grupo_despesa int NOT NULL,
  id_modalidade_aplicacao int NOT NULL,
  id_elemento_despesa int NOT NULL,
  id_solicitante int NULL,
  ged varchar(27) NULL,
  contratado varchar(255) NULL,
  id_objetivo_estrategico int NULL,
  valor real NOT NULL,
  id_tipo_transacao int NOT NULL,
  data_cadastro datetime NOT NULL,
  data_alteracao datetime NULL,
  ano_orcamento smallint NOT NULL,
  CONSTRAINT PK_Lancamentos PRIMARY KEY (id),
  CONSTRAINT fk_lancamentos_tipoLancamento FOREIGN KEY (id_tipo_lancamento) REFERENCES tipo_lancamento (id),
  CONSTRAINT fk_lancamentos_unidade FOREIGN KEY (id_unidade) REFERENCES unidade (id),
  CONSTRAINT fk_lancamentos_unidadeOrcamentaria FOREIGN KEY (id_unidade_orcamentaria) REFERENCES unidade_orcamentaria (id),
  CONSTRAINT fk_lancamentos_elementoDespesa FOREIGN KEY (id_elemento_despesa) REFERENCES elemento_despesa (id),
  CONSTRAINT fk_lancamentos_acao FOREIGN KEY (id_acao) REFERENCES acao (id),
  CONSTRAINT fk_lancamentos_programa FOREIGN KEY (id_programa) REFERENCES programa (id),
  CONSTRAINT fk_lancamentos_solicitante FOREIGN KEY (id_solicitante) REFERENCES solicitante (id),
  CONSTRAINT fk_lancamentos_objetivoEstrategico FOREIGN KEY (id_objetivo_estrategico) REFERENCES objetivo_estrategico (id),
  CONSTRAINT fk_lancamentos_grupoDespesa FOREIGN KEY (id_grupo_despesa) REFERENCES grupo_despesa (id),
  CONSTRAINT fk_lancamentos_modalidadeAplicacao FOREIGN KEY (id_modalidade_aplicacao) REFERENCES modalidade_aplicacao (id),
  CONSTRAINT fk_lancamentos_tipoTransacao FOREIGN KEY (id_tipo_transacao) REFERENCES tipo_transacao (id),
  CONSTRAINT fk_lancamentos_fonteRecurso FOREIGN KEY (id_fonte_recurso) REFERENCES fonte_recurso (id),
  CONSTRAINT fk_lancamentos_lancamentos FOREIGN KEY (id_lancamento_pai) REFERENCES lancamentos (id)
);

INSERT INTO unidade (nome, data_cadastro, data_alteracao) VALUES
('Diretoria Administrativa', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Diretoria de Recursos Humanos', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Diretoria Financeira', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional Direitos Humanos', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional Meio Ambiente', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional Mulher', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional Atividade Criminal', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Coordenadoria de Apoio aos Promotores Eleitorais', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Coordenadoria-Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Corregedoria-Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Gabinete Procuradoria-Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Grupo de Atuacao Especial de Combate ao Crime Organizado', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Gabinete de Seguranca Institucional', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Ouvidoria', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Promotoria de Controle Externo', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Secretaria-Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Subprocuradoria-Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO tipo_lancamento (nome, data_cadastro, data_alteracao) VALUES
('Proposta Inicial da Unidade', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Remanejamento', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Apostilamento', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Empenho COM Reserva de Dotacao', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Empenho SEM Reserva de Dotacao', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO solicitante (nome, data_cadastro, data_alteracao) VALUES
('Diretoria Administrativa', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Diretoria de Recursos Humanos', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Diretoria de Tecnologia', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Diretoria de Gestao', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Procuradoria-Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Subprocuradoria-Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Coordenadoria Recursal', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Secretaria-Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Grupo de Apoio Operacional da Secretaria Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Diretoria Financeira', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Corregedoria-Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Coordenadoria-Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Ouvidoria', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Grupo de Combate a Improbidade Administrativa', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Colegio de Procuradores', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Conselho Superior', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional de Defesa', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional Atividades Civis e Criminais', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional do Meio Ambiente', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional dos Direitos da Mulher', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Coordenadoria de Apoio aos Promotores Eleitorais', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO objetivo_estrategico (nome, data_cadastro, data_alteracao) VALUES
('Incrementar o dialogo e a atuacao conjunta do MP com os orgaos publicos e instituicoes nao governamentais de defesa do consumidor', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Fortalecer as redes de atendimento a grupos', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Intensificar o combate ao crime e o efetivo controle externo da atividade policial', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Aprimorar, informatizar e desburocratizar as rotinas administrativas', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Potencializar praticas resolutivas da atuacao ministerial', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Capacitar, valorizar e motivar todos que atuam no gabinete', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Promover a qualidade de vida no trabalho', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Modernizar e adequar a infraestrutura', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Prover solucoes de TI, mitigando riscos e otimizando recursos', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Assegurar e gerenciar recursos', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO tipo_transacao (nome, data_cadastro, data_alteracao) VALUES
('Debito', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Credito', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO grupo_despesa (nome, codigo, data_cadastro, data_alteracao) VALUES
('Pessoal', 3.1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Outras Despesas Correntes', 3.3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Investimentos', 4.4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO programa (nome, codigo, data_cadastro, data_alteracao) VALUES
('Defesa da Ordem', 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Gestao e Manutencao do Ministerio Publico', 31, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO unidade_orcamentaria (nome, codigo, data_cadastro, data_alteracao) VALUES
('Procuradoria-Geral', 11101, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Fundo para Reconstituicao de Bens Lesados', 11402, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Fundo Especial do Ministerio Publico', 11401, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO modalidade_aplicacao (nome, codigo, data_cadastro, data_alteracao) VALUES
('Envios a Estados e ao Distrito Federal', 30, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Envios a Municipios', 40, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Envios a Instituicoes Privadas sem Fins Lucrativos', 50, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Direta', 90, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Indireta', 91, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO elemento_despesa (nome, codigo, data_cadastro, data_alteracao) VALUES
('Salario Familia', 9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Vencimentos e Vantagens Fixas - Pessoal Civil', 11, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Obrigacoes Patronais', 13, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Outras Despesa Variaveis - Pessoal Civil', 16, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Material de Consumo', 30, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Material de Distribuicao Gratuita', 32, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Passagens e Despesas com Locomocao', 33, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Servicos de Consultoria', 35, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Outros Servicos de Terceiros Pessoa Fisica', 36, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Locacao de Mao de Obra', 37, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Outros Servicos de Terceiros Pessoa Juridica', 39, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Servicos de Tecnologia da Informacao e Comunicacao - Pessoa Juridica', 40, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Contribuicoes', 41, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Auxilio-Alimentacao', 46, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Obrigacoes Tributarias e Contributivas', 47, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Outros Auxilios Financeiros a Pessoa Fisica', 48, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Obras e Instalacoes', 51, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Equipamentos e Material Permanente', 52, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Despesas de Exercicios Anteriores', 92, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Outras Restituicoes e Indenizacoes', 93, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Indenizacoes e Restituicoes Trabalhistas', 94, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Ressarcimento de Despesas de Pessoal Requisitado', 96, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO fonte_recurso (nome, codigo, data_cadastro, data_alteracao) VALUES 
('Recursos do Tesouro do Estado', 1500, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Outros Recursos', 1501, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Governo Federal', 1700, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Privado', 1703, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Recursos Internos (Fundos)', 1759, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO acao (nome, codigo, data_cadastro, data_alteracao) VALUES
('Modernizacao da Infraestrutura Tecnologica', 47, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Pagamento de Pessoal', 83, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Adequacao da Infraestrutura Fisica das Unidades do Ministerio Publico', 87, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Auxilios para Membros e Servidores do Ministerio Publico', 88, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Operacionalizacao das Atividades do Ministerio Publico', 89, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Capacitacao de Recursos Humanos', 91, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Acoes Institucionais', 114, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Implementacao da Politica Institucional do Ministerio Publico', 115, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Reconstituicao de Bens e Direitos Difusos Lesados', 777, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());