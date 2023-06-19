package controll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dao.ContatoDao;
import modelo.Contato;

public class TesteContato {

    private static ContatoDao contatoDao = new ContatoDao();
    static Scanner in = new Scanner(System.in);
    static ArrayList<Contato> contatos = new ArrayList<>();
    
    public static void lerMenu(Contato contato) throws SQLException{

        int op = 0;

        do{
            System.out.println("Menu Contatos"
            +"\n 1 = Cadastrar Contato"
            +"\n 2 = Mostrar Contatos"
            +"\n 3 = Pesquisar Contato"
            +"\n 4 = Alterar Contato "
            +"\n 5 = Deletar Contato"
            +"\n 6 = Finalizar Sistema !");   
              
        switch(op){
                
                 case 1:contatoDao.salvarDadosBD(novoContato(contato));break;
                 case 2:contatos = contatoDao.lerDadosBD(); mostrarContatos(contato);break;
                 case 3:pesquisarContato();break;
                 case 4:alteraContato();break;
                 case 5:excluirContato();break;  
                 case 6:System.out.println("Finalizar Sistema");
                 default:System.out.println("Opção inválida");
            }

        }while(op!= 6);

    }

    private static void excluirContato() {

        System.out.println("Digite o nome do Contato");
        String nomePesquisar = in.nextLine();

        Contato contato;
        try {
            contato = contatoDao.pesquisarContatoBD(nomePesquisar);

             if(contato != null){
            mostrarContatos(contato);

            System.out.println("Confirma a esclusão"
            +"\n 1 - Sim "
            +"\n 2 - Não ");
            int res = Integer.parseInt(in.nextLine());
            switch(res){

                case 1:contatoDao.deletarContatoBD(contato);
                System.out.println("Exclusão realizada com Sucesso !");

                case 2: System.out.println("Erro ao Excluir Contato !");
            }

        } else{
            System.out.println("Contato Inexistente !");
        }
         
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void alteraContato() {
        int alt = 0;

        System.out.println("Digite o nome do Contato");
        String nomePesquisar = in.nextLine();

        try {
            Contato contato = contatoDao.pesquisarContatoBD(nomePesquisar);

            if(contato != null){
                mostrarContatos(contato);

                System.out.println("Digite o novo numero");
                int nvoNumero = Integer.parseInt(in.nextLine());
                
                if(confirma()){
                  contato.setIdContato(nvoNumero);
                }

                System.out.println("Dados Atualizados !");
            } else System.out.println("Numero não encontrado !");
                
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static boolean confirma() {
        int res = 0;
        System.out.println("Confirma a sua Alteração ?"
        +"\n 1 = Sim"
        +"\n 2 = Não");
     res = Integer.parseInt(in.nextLine());
        if(res == 1){
            return true;
        }else{
            return false;
        }
    }

    private static void pesquisarContato() {

        System.out.println("Digite o nome completo do Usuaário");
        String nomePesquisar = in.nextLine();

        for (Contato contato : contatos) {

             if(contato.getNomeContato().equalsIgnoreCase(nomePesquisar));{
                mostrarContatos(contato);
             }
        } 
    }

    private static void mostrarContatos(Contato contato) {

       System.out.println("Contato"
        +"\n Código = " + contato.getFoneContato() 
        +"\n Nome =" + contato.getNomeContato() 
        +"\n Telefone =" + contato.getFoneContato());
    
    }

    private static Contato novoContato(Contato contato) {
      Scanner lerStr = new Scanner(System.in);
      Scanner lerInt = new Scanner(System.in);

        System.out.println("Digite o nome ");
        String nome = lerStr.nextLine();

        System.out.println("Digite o seu numero de Telefone");
        int fone = lerInt.nextInt();

            contato.setNomeContato(nome);
            contato.setFoneContato(fone);

       return contato;
    }
}
