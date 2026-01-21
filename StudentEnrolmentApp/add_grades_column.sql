-- Script para adicionar a coluna 'grades' à tabela student existente
-- Execute este script no MySQL

USE enrolment_db;

-- Adiciona a coluna grades se ela não existir
ALTER TABLE student
ADD COLUMN IF NOT EXISTS grades DOUBLE DEFAULT 0.0;

-- Verifica se a coluna foi adicionada
DESCRIBE student;

-- Opcional: Atualiza todos os estudantes existentes com nota 0.0
UPDATE student SET grades = 0.0 WHERE grades IS NULL;

SELECT 'Coluna grades adicionada com sucesso!' AS status;
