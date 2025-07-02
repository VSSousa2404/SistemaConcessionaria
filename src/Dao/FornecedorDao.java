package Dao;
import Model.Fornecedor;

import ConexaoBd.ConexaoBd;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDao {

    private final Connection conexao;

    public FornecedorDao() {
        this.conexao = new ConexaoBd().getConnection();
    }

    public void adicionaFornecedor(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedor (razaoSocial, cnpj, endereco, telefone, email) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, fornecedor.getRazaoSocial());
            ps.setString(2, fornecedor.getCnpj());
            ps.setString(3, fornecedor.getEndereco());
            ps.setString(4, fornecedor.getTelefone());
            ps.setString(5, fornecedor.getEmail());
            ps.execute();
            ps.close();

            JOptionPane.showMessageDialog(null, "Fornecedor adicionado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar fornecedor: " + e.getMessage());
        }
    }

    public List<Fornecedor> listaFornecedores(String filtroCnpj) {
        List<Fornecedor> listaFornecedores = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor WHERE 1=1";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setRazaoSocial(rs.getString("razaoSocial"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));

                listaFornecedores.add(fornecedor);
            }
            rs.close();
            ps.close();

            return listaFornecedores;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar fornecedores: " + e.getMessage());
            return null;
        }
    }

    public void atualizaFornecedor(Fornecedor fornecedor) {
        String sql = "UPDATE fornecedor SET razaoSocial = ?, endereco = ?, telefone = ?, email = ? WHERE cnpj = ?";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, fornecedor.getRazaoSocial());
            ps.setString(2, fornecedor.getEndereco());
            ps.setString(3, fornecedor.getTelefone());
            ps.setString(4, fornecedor.getEmail());
            ps.setString(5, fornecedor.getCnpj());
            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Fornecedor atualizado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar fornecedor: " + e.getMessage());
        }
    }

    public void removeFornecedor(Fornecedor fornecedor) {
        String sql = "DELETE FROM fornecedor WHERE cnpj = ?";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, fornecedor.getCnpj());
            int linhasAfetadas = ps.executeUpdate();
            ps.close();

            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(null, "Fornecedor removido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum fornecedor encontrado com esse CNPJ.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover fornecedor: " + e.getMessage());
        }
    }

}
