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
('Diretoria de Tecnologia da Informação e Comunicação', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Diretoria de Gestão Estratégica e Orçamentária', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Diretoria Financeira', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Divisão de Auditoria Interna', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Escola Superior do Ministério Público', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional Direitos Humanos', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional Educação', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional Infância e Adolescência', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional Meio Ambiente', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional Mulher', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional Patrimônio Público', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional Saúde', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional São Francisco', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional Segurança Pública', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional Atividade Criminal', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro Médico', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Coordenadoria Permanente de Autocomposição e Paz', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Coordenadoria de Apoio aos Promotores Eleitorais', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Coordenadoria de Documentação e Memória', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Coordenadoria-Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Corregedoria-Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Divisão de Comunicação, Cerimonial e Eventos', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Divisão de Perícia Contábil', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Divisão de Perícia Técnica', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Divisão de Equipe Interdisciplinar', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Gabinete Procuradoria-Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Grupo de Atuação Especial de Combate ao Crime Organizado', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Gabinete de Segurança Institucional', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Ouvidoria', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Promotoria de Controle Externo', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Promotoria de Defesa da Saúde', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Promotoria de Direitos do Cidadão', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Secretaria-Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Subprocuradoria-Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


INSERT INTO tipo_lancamento (nome, data_cadastro, data_alteracao) VALUES
('Proposta Inicial da Unidade', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Aprovação do Colégio de Procuradores de Justiça', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Aprovação do Governo (Cota Orçamentária)', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Revisão da Proposta Inicial da Unidade', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Alteração da Revisão da Proposta Inicial da Unidade', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Remanejamento', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Crédito Suplementar Interno', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Crédito Suplementar Entrada', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Crédito Suplementar Saída', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Crédito Extraordinário Interno', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Crédito Extraordinário Entrada', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Crédito Extraordinário Saída', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Crédito Especial Interno', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Crédito Especial Entrada', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Crédito Especial Saída', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Transferência', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Reserva de Dotação', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Alteração de Reserva de Dotação', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Apostilamento', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Alteração de Apostilamento', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Empenho COM Reserva de Dotação', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Empenho SEM Reserva de Dotação', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Alteração de Empenho', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Despesa Não Prevista', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO solicitante (nome, data_cadastro, data_alteracao) VALUES
('Diretoria Administrativa', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Diretoria de Recursos Humanos', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Diretoria de Tecnologia da Informação e Comunicação)', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Diretoria de Gestão Estratégica e Orçamentária', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Divisão de Auditoria Interna', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Escola Superior do Ministério Público', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Procuradoria-Geral de Justiça', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Subprocuradoria-Geral de Justiça', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Coordenadoria Recursal', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Gabinete de Segurança Institucional', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Comissão de Concurso', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Gabinete do Grupo de Atuação Especial de Combate ao Crime Organizado', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Coordenadoria Permanente de Autocomposição e Paz', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Coordenadoria de Promoção de Igualdade Étnico-Racial', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Cartório 2º Grau', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Divisão de Comunicação, Cerimonial e Eventos', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Secretaria-Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Grupo de Apoio Operacional da Secretaria Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Assessoria Jurídica', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro Médico', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Central de Expedições de Diligências', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Divisão de Material', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Divisão de Apoio Administrativo', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Divisão de Engenharia e Manutenção', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Divisão de Patrimônio', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Diretoria Financeira', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Corregedoria-Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Coordenadoria-Geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Ouvidoria', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Grupo de Combate a Improbidade Administrativa', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Colégio de Procuradores de Justiça', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Conselho Superior do Ministério Público', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional de Segurança Pública', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional da Infância e da Adolescência', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional de Defesa do Patrimônio Público, da Ordem Tributária e do Terceiro Setor', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional Atividades Cíveis e Criminais', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Divisão de Perícia Técnica', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Divisão de Perícia Contábil', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Divisão de Equipe Interdisciplinar', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional de Defesa dos Direitos Humanos', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional de Proteção ao Rio São Francisco e às Nascentes', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional dos Direitos à Educação', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional dos Direitos à Saúde', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional do Meio Ambiente, Urbanismo, Patrimônio Social e Cultural', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Centro de Apoio Operacional dos Direitos da Mulher', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Coordenadoria de Apoio aos Promotores Eleitorais', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('1ª Procuradoria de Justiça', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('2ª Procuradoria de Justiça', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('3ª Procuradoria de Justiça', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('4ª Procuradoria de Justiça', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('5ª Procuradoria de Justiça', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('6ª Procuradoria de Justiça', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('7ª Procuradoria de Justiça', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('8ª Procuradoria de Justiça', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('9ª Procuradoria de Justiça', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('10ª Procuradoria de Justiça', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('11ª Procuradoria de Justiça', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('12ª Procuradoria de Justiça', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('13ª Procuradoria de Justiça', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('14ª Procuradoria de Justiça', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('1ª Promotoria Direitos do Cidadão - Defesa do Patrimônio Público e na área de Previdência Pública', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('2ª Promotoria Direitos do Cidadão - Defesa dos Direitos à Saúde', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('3ª Promotoria Direitos do Cidadão - Controle Externo da Atividade policial e em Questões Agrárias', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('4ª Promotoria Direitos do Cidadão - Defesa do Acidentado do Trabalho, do Idoso, do Deficiente, dos Direitos Humanos em Geral e dos Direitos à Assistência Social', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('5ª Promotoria Direitos do Cidadão - Controle e Fiscalização do Terceiro Setor ', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('6º Promotoria Direitos do Cidadão - Defesa do Direito à Educação', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('7ª Promotoria Direitos do Cidadão - Defesa da Ordem Tributária', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('8ª Promotoria Direitos do Cidadão - Defesa dos Direitos da Criança e do Adolescente', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('9ª Promotoria Direitos do Cidadão - Defesa dos Direitos à Saúde', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('10ª Promotoria Direitos do Cidadão - Defesa do Meio Ambiente, Urbanismo, Patrimônio Social e Cultural e dos Serviços de Relevância Pública', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('1ª Promotoria (1ª, 2º, 4º, 6º, 7º, 8º, 9º Varas Cíveis) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('2ª Promotoria (14ª Vara Cível) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('3ª Promotoria Cível de Aracaju (5ª, 10ª, 11ª, 13ª, 15ª e 21ª Varas Cíveis) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('1ª Promotoria da Curadoria da Fazenda Pública (3ª Vara Cível) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('2ª Promotoria da Curadoria da Fazenda Pública (12ª Vara Cível) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('3ª Promotoria da Curadoria da Fazenda Pública (18ª Vara Cível) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('4ª Promotoria da Curadoria da Fazenda Pública (Juizado Especial da Fazenda Pública) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('1ª Promotoria Criminal (1ª Vara Criminal) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('2ª Promotoria Criminal (2º Vara Criminal) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('3ª Promotoria Criminal (3ª Vara Criminal) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('4ª Promotoria Criminal (4ª Vara Criminal) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Promotoria de Defesa do Consumidor de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('5ª Promotoria Criminal (9ª Vara Criminal) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('6ª Promotoria Criminal (11ª Vara Criminal - Juizado de Violência Doméstica contra a Mulher) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('1ª Promotoria Do Tribunal do Júri (5ª Vara Criminal) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('2ª Promotoria Do Tribunal do Júri  (8ª Vara Criminal) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('3ª  Promotoria Do Tribunal do Júri (5ª Vara Criminal) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('4ª  Promotoria Do Tribunal do Júri (8ª Vara Criminal) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Promotoria Militar (Auditoria Militar - 6º Vara Criminal) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('1ª Promotoria das Execuções Criminais (7ª Vara Criminal) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('3ª Promotoria das Execuções Criminais (7ª Vara Criminal) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Promotoria de Acidentes e de Delitos de Trânsito de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('1ª Promotoria da Curadoria da Infância e da Adolescência (Juizado da Infância e Juventude - 16ª Vara Cível) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('2ª Promotoria da Curadoria da Infância e da Adolescência (Juizado da Infância e Juventude - 17ª Vara Cível) de Aracaju', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('5ª Promotoria Distrital - (5ª Vara de Assistência Judiciária - 26ª Vara Cível)', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('6ª Promotoria Distrital (6ª Vara de Assistência Judiciária - 27ª Vara Cível)', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('1ª Promotoria Distrital (1ª Vara de Assistência Judiciária - 19ª Vara Cível)', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('3ª Promotoria Distrital (3ª Vara de Assistência Judiciária - 24ª Vara Cível)', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('2ª Promotoria Distrital (2ª Vara de Assistência Judiciária - 23ª Vara Cível)', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO objetivo_estrategico (nome, data_cadastro, data_alteracao) VALUES
('Incrementar o diálogo e a atuação conjunta do MP com os órgãos públicos e instituições não governamentais de defesa do consumidor', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Fortalecer as redes de atendimento a grupos vulneráveis junto aos Órgãos Públicos', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Atuar na universalização do acesso à educação e à saúde com a prestação de serviços de qualidade', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Aprimorar as atividades de combate à corrupção, defesa do patrimônio público e fiscalização do terceiro setor', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Contribuir para a preservação do meio ambiente e patrimônio histórico e cultural e para o desenvolvimento urbano de forma sustentável, em sintonia com as demais instituições e com a sociedade em geral', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Intensificar o combate à criminalidade e o efetivo controle externo da atividade policial', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Aprimorar, informatizar e desburocratizar as rotinas administrativas', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Potencializar práticas resolutivas da atuação ministerial', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Aprimorar o processo de gestão e governança', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Capacitar, valorizar e motivar todos que atuam na instituição', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Promover a qualidade de vida no trabalho e a valorização dos Membros e Servidores', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Aperfeiçoar a comunicação efetiva com a sociedade e o relacionamento institucional', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Modernizar e adequar a infraestrutura física', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Prover soluções de TI, entregando benefícios, mitigando riscos e otimizando recursos', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Assegurar recursos orçamentários, otimizar sua alocação e aperfeiçoar o gerenciamento', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO tipo_transacao (nome, data_cadastro, data_alteracao) VALUES
('Débito', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Crédito', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO grupo_despesa (nome, codigo, data_cadastro, data_alteracao) VALUES
('Pessoal', 3.1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Outras Despesas Correntes', 3.3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Investimentos', 4.4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO programa (nome, codigo, data_cadastro, data_alteracao) VALUES
('Defesa da Ordem Jurídica e Social', 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Gestão e Manutenção do Ministério Público', 31, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO unidade_orcamentaria (nome, codigo, data_cadastro, data_alteracao) VALUES
('Procuradoria-Geral de Justiça', 11101, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Fundo para Reconstituição de Bens Lesados', 11402, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Fundo Especial do Ministério Público', 11401, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO modalidade_aplicacao (nome, codigo, data_cadastro, data_alteracao) VALUES
('Transferências a Estados e ao Distrito Federal', 30, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Transferências a Municípios', 40, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Transferências a Instituições Privadas sem Fins Lucrativos', 50, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Aplicação Direta', 90, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Aplicação Indireta', 91, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO elemento_despesa (nome, codigo, data_cadastro, data_alteracao) VALUES
('Contribuição a Entidades Fechadas de Previdência', 7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Outros Benefícios Assistenciais', 8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Salário Família', 9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Vencimentos e Vantagens Fixas - Pessoal Civil', 11, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Obrigações Patronais', 13, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Outras Despesa Variáveis - Pessoal Civil', 16, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Material de Consumo', 30, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Material de Distribuição Gratuita', 32, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Passagens e Despesas com Locomoção ', 33, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Serviços de Consultoria', 35, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Outros Serviços de Terceiros Pessoa Física', 36, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Locação de Mão de Obra', 37, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Outros Serviços de Terceiros Pessoa Jurídica', 39, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Serviços de Tecnologia da Informação e Comunicação - Pessoa Jurídica', 40, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Contribuições', 41, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Auxílio-Alimentação', 46, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Obrigações Tributárias e Contributivas', 47, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Outros Auxílios Financeiros a Pessoa Física', 48, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Obras e Instalações', 51, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Equipamentos e Material Permanente', 52, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Despesas de Exercícios Anteriores', 92, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Outras Restituições e Indenizações', 93, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Indenizações e Restituições Trabalhistas ', 94, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Ressarcimento de Despesas de Pessoal Requisitado', 96, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO fonte_recurso (nome, codigo, data_cadastro, data_alteracao) VALUES 
('Recursos do Tesouro do Estado', 1500, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Outros Recursos não Vinculados', 1501, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Recursos de Convênio - Governo Federal', 1700, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Recursos de Convênio - Privado', 1703, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Receita de Alienação de Bens', 1755, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Recursos Próprios (Fundos)', 1759, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO acao (nome, codigo, data_cadastro, data_alteracao) VALUES
('Modernização da Infraestrutura Tecnológica', 47, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Pagamento de Pessoal', 83, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Adequação da Infraestrutura Física das Unidades do Ministério Público', 87, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Auxílios para Membros e Servidores do Ministério Público', 88, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Operacionalização das Atividades do Ministério Público', 89, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Capacitação de Recursos Humanos', 91, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Ações Institucionais', 114, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Implementação da Política Institucional do Ministério Público', 115, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
('Reconstituição de Bens e Direitos Difusos Lesados', 777, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
