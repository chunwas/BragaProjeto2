import java.util.ArrayList;
import java.util.List;

public class Parcela {
    private String nome;
    private int tempoCrescimento, clima;
    private List<String> datasRegas;
    private List<String> datasAdubos;
    private List<String> plantas;
    private boolean precisaTratamentoEspecial;


    public String getClima () {
        if (this.clima == 1) { return "Sol"; }
        else if (this.clima == 2) { return "Chuva"; }
        else if (this.clima == 3) { return "Ambiente"; }
        return "Clima não registrado.";
    }


    public List<String> getDatasRegas() { return datasRegas; }

    public List<String> getDatasAdubos() { return datasAdubos; }
    
    public Parcela(String nome, int tempoCrescimento, int clima) {
        this.nome = nome;
        this.clima = clima;
        this.tempoCrescimento = tempoCrescimento;
        this.datasRegas = new ArrayList<String>();
        this.datasAdubos = new ArrayList<String>();
        this.plantas = new ArrayList<String>();
        this.precisaTratamentoEspecial = false;
    }

    public void registrarPlanta(String nome) {
        this.plantas.add(nome);
    }

    public void getPlanta() {
        int val = 1;
        for(String planta: this.plantas){
            System.out.printf("%d - %s\n", val, planta);
            val++;
      }
    }

    public void getRega() {
        if(datasRegas.isEmpty()) System.out.println("Ainda não foi regado.");
        else for(String data: this.datasRegas){ System.out.println(data); }

    }

    public void getAdubo() {
        if(datasAdubos.isEmpty()) System.out.println("Ainda não foi adubado.");
        else for(String data: this.datasAdubos){ System.out.println(data); }

    }

    public void registrarRegar(String data) {
        datasRegas.add(data);
        System.out.println("Parcela " + nome + " foi regada em " + data);
    }

    public void registrarAdubo(String data) {
        datasAdubos.add(data);
        System.out.println("Parcela " + nome + " recebeu adubo em " + data);
    }

    public void marcarTratamentoEspecial() {
        this.precisaTratamentoEspecial = true;
        System.out.println("Parcela " + nome + " precisa de tratamento especial.");
    }


    public String getNome() {
        return nome;
    }

    public int getTempoCrescimento() {
        return tempoCrescimento;
    }

    public boolean isPrecisaTratamentoEspecial() {
        return precisaTratamentoEspecial;
    }
}
