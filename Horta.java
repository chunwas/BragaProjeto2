import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Horta extends Banco {
    private List<Parcela> parcelas;
    Clima clima = new Clima();
    String climaAtual;
    SugestaoRega sugestaoRega;

    

    public Horta() {
        this.parcelas = new ArrayList<Parcela>();
    }

    @Override
    protected void inicializarTabelas() {
        super.inicializarTabelas();
        createTable("plantas");
    }

    public void registrarPlanta(Banco banco, String planta, String nome) {
        Map<String, String> valores = new HashMap<>();
        valores.put("parcela", nome);
        valores.put("planta", planta);
        banco.insert("plantas", valores);
        System.out.println("Planta " + planta + " registrada na parcela " + nome);
    }

    

    public void adicionarParcela(Parcela parcela) {
        parcelas.add(parcela);
        System.out.println("Parcela " + parcela.getNome() + " adicionada à horta.");

        Map<String, String> valores = new HashMap<>();
        valores.put("nome", parcela.getNome());
        valores.put("tempoCrescimento", String.valueOf(parcela.getTempoCrescimento()));
        valores.put("clima", parcela.getClima());
        valores.put("tratamentoEspecial", parcela.isPrecisaTratamentoEspecial() ? "Sim" : "Não");
        insert("parcelas", valores);
    }

    public void regarParcela(String nomeParcela, String data) {
        for (Parcela parcela : parcelas) {
            if (parcela.getNome().equals(nomeParcela)) {
                parcela.registrarRegar(this, data);
                return;
            }
        }
        System.out.println("Parcela não encontrada.");
    }

    public void adubarParcela(String nomeParcela, String data) {
        for (Parcela parcela : parcelas) {
            if (parcela.getNome().equals(nomeParcela)) {
                parcela.registrarAdubo(this, data);
                return;
            }
        }
        System.out.println("Parcela não encontrada.");
    }

    public void sugerirAcoes(Banco banco) {
        climaAtual = clima.randomizarClima();
        sugestaoRega = new SugestaoRega(climaAtual);
        System.out.println("Clima atual: " + climaAtual);

        for (Parcela parcela : parcelas) {
            parcela.getRega(this);
            parcela.getAdubo(this);
            String sugestao = sugestaoRega.gerarSugestao(banco, parcela.getNome());
            System.out.println("Sugestão para a parcela '" + parcela.getNome() + "': " + sugestao);
        }
    }

    public void gerarRelatorio() {
        System.out.println("Relatório da Horta:");

        List<Map<String, String>> registrosParcelas = select("parcelas");
        
        for (Map<String, String> registro : registrosParcelas) {
            System.out.println("Parcela: " + registro.get("nome"));
            System.out.println("Tempo de crescimento esperado: " + registro.get("tempoCrescimento") + " dias");
            System.out.println("Precisa de tratamento especial: " + registro.get("tratamentoEspecial"));
            System.out.printf("Clima ideal: %s\n", registro.get("clima"));
            System.out.println("--------------------------");
        }
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }
}
