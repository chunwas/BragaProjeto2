import java.util.ArrayList;
import java.util.List;

public class  Horta {
    private List<Parcela> parcelas;
    Clima clima = new Clima();
    String climaAtual;
    SugestaoRega sugestaoRega;

    public Horta() {
        this.parcelas = new ArrayList<Parcela>();
    }

    public void adicionarParcela(Parcela parcela) {
        parcelas.add(parcela);
        System.out.println("Parcela " + parcela.getNome() + " adicionada à horta.");
    }

    public void regarParcela(String nomeParcela, String data) {
        for (Parcela parcela : parcelas) {
            if (parcela.getNome().equals(nomeParcela)) {
                parcela.registrarRegar(data);
                return;
            }
        }
        System.out.println("Parcela não encontrada.");
    }

    public void adubarParcela(String nomeParcela, String data) {
        for (Parcela parcela : parcelas) {
            if (parcela.getNome().equals(nomeParcela)) {
                parcela.registrarAdubo(data);
                return;
            }
        }
        System.out.println("Parcela não encontrada.");
    }

    public void sugerirAcoes() {
        climaAtual = clima.randomizarClima();
        sugestaoRega = new SugestaoRega(climaAtual);
        System.out.println("Clima atual: " + climaAtual);
        for (Parcela parcela : parcelas) {
            System.out.println(sugestaoRega.gerarSugestao(parcela.getDatasRegas(), parcela.getDatasAdubos()));
        }
    }

    public void gerarRelatorio() {
        System.out.println("Relatório da Horta:");
        for (Parcela parcela : parcelas) {
            System.out.println("Parcela: " + parcela.getNome());
            System.out.println("Tempo de crescimento esperado: " + parcela.getTempoCrescimento() + " dias");
            System.out.println("Precisa de tratamento especial: " + (parcela.isPrecisaTratamentoEspecial() ? "Sim" : "Não"));
            System.out.printf("Clima ideal: %s\n", parcela.getClima());
            System.out.println("Plantas: ");
            parcela.getPlanta();
            System.out.println("Datas de adubo: ");
            parcela.getAdubo();
            System.out.println("Plantas de rega: ");
            parcela.getRega();
            System.out.println("--------------------------");
        }
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }
}
