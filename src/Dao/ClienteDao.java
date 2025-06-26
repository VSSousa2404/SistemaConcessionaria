package Dao;

import ConexaoBd.ConexaoBd;
import Model.Clientes;

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

    public void adicionaCliente(Clientes clientes) {
        String sql = "INSERT into cliente (nome, cpf, endereco, rg, primeiraCompra, email, celular, telefone) values (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, clientes.getNome());
            ps.setString(2, clientes.getCpf());
            ps.setString(3, clientes.getEndereco());
            ps.setString(4, clientes.getRg());
            ps.setBoolean(5, true);
            ps.setString(6, clientes.getEmail());
            ps.setString(7, clientes.getCelular());
            ps.setString(8, clientes.getTelefone());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public List<Clientes> ListaClientes(String filtroNome, String filtroCpf) {
        List<Clientes> listaClientes = new ArrayList();
        String sql = "Select * from Cliente";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Clientes clientes = new Clientes();
                clientes.setNome(rs.getString("nome"));
                clientes.setCpf(rs.getString("CPF"));
                clientes.setEndereco(rs.getString("Endereco"));
                clientes.setRg(rs.getString("RG"));
                clientes.setPrimeiraCompra(rs.getBoolean("PrimeiraCompra"));
                clientes.setEmail(rs.getString("Email"));
                clientes.setCelular(rs.getString("Celular"));
                clientes.setTelefone(rs.getString("Telefone"));
                
                listaClientes.add(clientes);
            }
            return listaClientes;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public void atualizaCliente(Clientes cliente) {
        String sql = "UPDATE Cliente SET nome = ?, Cpf = ?, Endereco = ?, Rg = ?, PrimeiraCompra = ?, Email = ?, Celular = ?, Telefone =?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getEndereco());
            ps.setString(4, cliente.getRg());
            ps.setBoolean(5, cliente.getPrimeiraCompra());
            ps.setString(6, cliente.getEmail());
            ps.setString(7, cliente.getCelular());
            ps.setString(8, cliente.getCelular());
            ps.setString(9, cliente.getCelular());

        } catch (Exception e) {
        }
    }
    
    public void removeClientes(Clientes cliente){
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
