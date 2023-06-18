package modelo;

public class Contato {
    private int idContato;
    private String nomeContato;
    private int foneContato;


    public Contato(){
        idContato = 0;
        nomeContato = "";
        foneContato = 0;
    }
    public Contato(int idContato, String nomeContato, int foneContato) {
        this.idContato = idContato;
        this.nomeContato = nomeContato;
        this.foneContato = foneContato;
    }
    public int getIdContato() {
        return idContato;
    }
    public void setIdContato(int idContato) {
        this.idContato = idContato;
    }
    public String getNomeContato() {
        return nomeContato;
    }
    public void setNomeContato(String nomePesq) {
        this.nomeContato = nomePesq;
    }
    public int getFoneContato() {
        return foneContato;
    }
    public void setFoneContato(int foneContato) {
        this.foneContato = foneContato;
    }

    
}
