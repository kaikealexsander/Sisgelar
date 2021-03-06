package sisgelar.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sisgelar.model.OrdemServico;

public class OrdemServicoDao extends Conexao{
    private PreparedStatement ps;
    private ResultSet rs;
    
    public boolean inserir(OrdemServico p){
        try {
            query = "insert into tb_OS values(0,?,?,?,?,?,?,?,?)";
            
            ps = con.prepareStatement(query);
            ps.setInt(1, p.getIdOS());
            ps.setInt(2, p.getIdCliente());
            ps.setInt(3, p.getIdProdCliente());
            ps.setString(5, p.getHorario());
            ps.setString(6, p.getDescricao());
            ps.setDouble(7, p.getValor());
            ps.setString(8, p.getConcluido());
            if (ps.executeUpdate() > 0)
                return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao inserir dados na tabela tb_OS! \n"+e);
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean excluir(OrdemServico p){
        try {
            query = "delete from tb_OrdemServico where idOrdemServico=?";
            ps = con.prepareStatement(query);
            ps.setInt(1, p.getIdOS());
            
            if (ps.executeUpdate() > 0)
                return true;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir dados na tabela OrdemServico! \n"+e);
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean alterar(OrdemServico p){
        try {
             query = "update tb_OrdemServicos set nome=?, cpf=?, endereco=?, cep=?, bairro=?, email=?, telefone=?, "
                    + "celular=?, celular2=?, fantasia=?, numero=? where idOrdemServico=?";
            ps = con.prepareStatement(query);
            ps.setInt(1, p.getIdOS());
            ps.setInt(2, p.getIdCliente());
            ps.setString(3, p.getHorario());
            ps.setInt(4, p.getIdOS());
            
            if (ps.executeUpdate() > 0)
                return true;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao alterar dados na tabela pessoas! \n"+e);
            e.printStackTrace();
        }
        return false;
    }
    
    public List<OrdemServico> buscar(OrdemServico p){
        return new ArrayList<OrdemServico>();
    }

    public List<OrdemServico> buscar(String t) {
               List<OrdemServico> lista = new ArrayList<OrdemServico>();
       try {
            query = "select * from tb_OS where data like concat('%',?,'%') order by idOS";
            ps = con.prepareStatement(query);
            ps.setString(1, t);
            rs = ps.executeQuery();
            while (rs.next())
                lista.add(new OrdemServico(
                        rs.getInt("idOs"),
                        rs.getInt("idPessoa"),
                        rs.getInt("idProdCliente"),
                        rs.getString("data"),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        rs.getString("concluido")
                ));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar dados na tabela pessoas! \n"+e);
            e.printStackTrace();
            lista = null;
        }
        return lista;
    }
    
    public List<OrdemServico> buscar(int t) {
               List<OrdemServico> lista = new ArrayList<OrdemServico>();
       try {
            query = "select * from tb_OS where idOS = ? order by idOS";
            ps = con.prepareStatement(query);
            ps.setInt(1, t);
            rs = ps.executeQuery();
            while (rs.next())
                lista.add(new OrdemServico(
                        rs.getInt("idOs"),
                        rs.getInt("idPessoa"),
                        rs.getInt("idProdCliente"),
                        rs.getString("data"),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        rs.getString("concluido")
                ));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar dados na tabela OrdemServico! \n"+e);
            e.printStackTrace();
            lista = null;
        }
        return lista;
    }
}