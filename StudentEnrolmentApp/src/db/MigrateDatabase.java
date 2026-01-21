package db;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Script de migração para adicionar a coluna 'grades' à tabela student
 * Execute este arquivo uma vez para atualizar o banco de dados existente
 */
public class MigrateDatabase {

    public static void main(String[] args) {
        System.out.println("Iniciando migração do banco de dados...");

        try (Connection con = DBConnection.getConnection()) {
            if (con == null) {
                System.err.println("Erro: Não foi possível conectar ao banco de dados!");
                return;
            }

            Statement st = con.createStatement();

            // Adiciona a coluna grades se ela não existir
            try {
                st.execute("ALTER TABLE student ADD COLUMN grades DOUBLE DEFAULT 0.0");
                System.out.println("✅ Coluna 'grades' adicionada com sucesso!");
            } catch (Exception e) {
                if (e.getMessage().contains("Duplicate column name")) {
                    System.out.println("ℹ️  Coluna 'grades' já existe no banco de dados.");
                } else {
                    throw e;
                }
            }

            // Atualiza estudantes existentes que possam ter grades NULL
            st.execute("UPDATE student SET grades = 0.0 WHERE grades IS NULL");
            System.out.println("✅ Estudantes existentes atualizados com grades = 0.0");

            System.out.println("\n🎉 Migração concluída com sucesso!");
            System.out.println("Agora você pode executar a aplicação normalmente.");

        } catch (Exception e) {
            System.err.println("❌ Erro durante a migração: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
