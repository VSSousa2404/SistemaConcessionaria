package Dao;

import ConexaoBd.ConexaoBd;
import Model.Cliente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDao {

    private final Connection conexao;

    public ClienteDao() {
        this.conexao = new ConexaoBd().getConnection();
    }

    public void adicionaCliente(Cliente cliente) {
        String sql = "INSERT into cliente (nome, cpf, endereco, rg, primeiraCompra, email, celular, telefone) values (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getEndereco());
            ps.setString(4, cliente.getRg());
            ps.setBoolean(5, true);
            ps.setString(6, cliente.getEmail());
            ps.setString(7, cliente.getCelular());
            ps.setString(8, cliente.getTelefone());
            ps.execute();
            ps.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public List<Cliente> ListaClientes(String filtroNome, String filtroCpf) {
        List<Cliente> listaClientes = new ArrayList();
        String sql = "SELECT * FROM cliente WHERE 1=1";
        
        boolean temNome = filtroNome != null && !filtroNome.trim().isEmpty();
        boolean temCpf = filtroCpf != null && !filtroCpf.isEmpty();  // sem trim aqui, já está limpo
    
        if (temNome || temCpf) {
            sql += " AND (";
            if (temNome) {
                sql += "nome LIKE ?";
            }
            if (temCpf) {
                if (temNome) {
                    sql += " OR ";
                }
                sql += "REPLACE(REPLACE(REPLACE(cpf, '.', ''), '-', ''), ' ', '') LIKE ?";
            }
            sql += ")";
        }
            
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            int index = 1;
            if (temNome) {
                ps.setString(index++, "%" + filtroNome.trim() + "%");
            }
            if (temCpf) {
                ps.setString(index++, "%" + filtroCpf + "%");
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setEndereco(rs.getString("Endereco"));
                cliente.setRg(rs.getString("RG"));
                cliente.setPrimeiraCompra(rs.getBoolean("PrimeiraCompra"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setTelefone(rs.getString("Telefone"));
                
                listaClientes.add(cliente);
            }
            return listaClientes;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    

    public void atualizaCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, Endereco = ?, Rg = ?, PrimeiraCompra = ?, Email = ?, Celular = ?, Telefone = ? where cpf = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEndereco());
            ps.setString(3, cliente.getRg());
            ps.setBoolean(4, false);
            ps.setString(5, cliente.getEmail());
            ps.setString(6, cliente.getCelular());
            ps.setString(7, cliente.getTelefone());
            ps.setString(8, cliente.getCpf());
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente" + e.getMessage());
        }
    }
    
    public void removeClientes(Cliente cliente){
        String sql = "DELETE FROM cliente where nome = ?";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, "Nome");
            ps.execute();
            ps.close();
        } catch (SQLException e) {
        }
    }

}
