package Dao;

import ConexaoBd.ConexaoBd;
import Model.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public List<Clientes> listaClientes() {
        List<Clientes> listaClientes = new ArrayList();
        String sql = "Select * from Clientes";
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
            }
            return listaClientes;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public void atualizaCliente(Clientes cliente) {
        String sql = "UPDATE Cliente SET nome = ?, Cpg = ?, Endereco = ?, Rg = ?, PrimeiraCompra = ?, Email = ?, Celular = ?, Telefone =?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString
        } catch (Exception e) {
        }
    }

}
