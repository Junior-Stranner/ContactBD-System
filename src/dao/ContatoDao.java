package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Contato;

public class ContatoDao {
        
    ArrayList<Contato> contatos = new ArrayList<>();

    
    public void salvarDadosBD(Contato contato) throws SQLException{

        String sql = "INSERT INTO contatos (nomecontato, fonecontato) VALUES (?,?)";

        Connection con = null;
        PreparedStatement stm = null;

        try {
        con = Conexao.conectarBD();
        stm = con.prepareStatement(sql);

          //  int id = contatos.size()+1;
            stm.setString(1, contato.getNomeContato());
            stm.setInt(2, contato.getFoneContato());

            stm.execute();
        } catch (SQLException s) {
            // TODO: handle exception
            System.out.println("ERRO....");
            s.printStackTrace();
        }finally{
            if(stm != null){
                stm.close();
            }
                if(con != null)
                 con.close();
        }
    }

    public ArrayList<Contato> lerDadosBD()  throws SQLException{

        String sql = "Select * from contatos";

        Connection con = null;
        PreparedStatement codigo = null; 
        ResultSet dadosBD = null;

         try {
        con = Conexao.conectarBD();
        codigo = con.prepareStatement(sql);
        dadosBD = codigo.executeQuery();

        while(dadosBD.next()){
         Contato contato = new Contato();
         contato.setIdContato(dadosBD.getInt("idContato"));
         contato.setNomeContato(dadosBD.getString("nomeContato"));
         contato.setFoneContato(dadosBD.getInt("foneContato"));

         contatos.add(contato);
        }

        } catch (SQLException x) {
            // TODO: handle exception
        }finally{
            if(con != null){
                con.close();
            }
             if(codigo != null)
              codigo.close();
        }

        return contatos;
    }

    public Contato pesquisarContatoBD(String nome) throws SQLException{
        String sql = "select * from contatos where nomecontato = ?";
        
        Contato contato = null;
        Connection con;
        PreparedStatement stm;
        ResultSet dadosBD = null;

        try {
        con = Conexao.conectarBD();
        stm = con.prepareStatement(sql);
        stm.setString(1, nome);
        dadosBD = stm.executeQuery();

        contato.setIdContato(dadosBD.getInt("idcontato"));
        contato.setNomeContato(dadosBD.getString("nomecontato"));
        contato.setFoneContato(dadosBD.getInt("fonecontato"));
        
        } catch (Exception e) {
            // TODO: handle exception
        }

        return contato;

    }

    public void alterarContatoBD(Contato contato) throws SQLException{
        
        String sql = "Update contatos set fonecontatos = ? Where nomecontato = ?"; 
       
        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = Conexao.conectarBD();
            stm = con.prepareStatement(sql);
            stm.setInt(1, contato.getFoneContato());
            stm.setString(2, contato.getNomeContato());

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            
            if (stm != null) {
				stm.close();
			}
			if (con != null)
				con.close();

      }
   }

   public void deletarContatoBD(Contato contato) throws SQLException {
		
		String sql1 =  "DELETE FROM contatos WHERE nomecontato = ?";

		Connection con = null;
		PreparedStatement stm = null;

		try {
			con = Conexao.conectarBD();
			stm = con.prepareStatement(sql1);
			stm.setString(1, contato.getNomeContato());
			stm.execute();			
		}
		catch (SQLException e) {
			System.out.println("ERRO DELETE.....");
			e.printStackTrace();
		}finally {
			if (stm != null) {
				stm.close();
			}
			if (con != null)
				con.close();
		}		
	}

}

