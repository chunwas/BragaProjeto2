import java.util.List;
import java.util.Random;

public class SugestaoRega {
    private String clima;

    public SugestaoRega(String clima) {
        this.clima = clima;
    }

    public String gerarSugestao(List<String> datasRegas, List<String> datasAdubos) {
        if (clima.equals("Ambiente")) {
            if (datasRegas.size() == datasAdubos.size()) {
                return "Sem sugestão. A parcela está bem cuidada!";
            } else if (datasRegas.size() > datasAdubos.size()) {
                return precisaDeAdubo();
            } else {
                return precisaDeRegar();
            }

        } else if (clima.equals("Chuva")) {
            if (datasRegas.size() == datasAdubos.size()) {
                return precisaDeAdubo();
            } else if (datasRegas.size() > datasAdubos.size()) {
                return precisaDeAdubo();
            } else {
                return precisaDeRegar();
            }

        } else if (clima.equals("Sol")) {
            if (datasAdubos.size() == datasRegas.size()) {
                return precisaDeRegar();
            } else if (datasAdubos.size() > datasRegas.size()) {
                return precisaDeRegar();
            } else {
                return precisaDeAdubo();
            }
        }

        return "Anormalidade identificada. Sugestão: recolhimento imediato para amortização de possíveis prejuízos.";
    }

    private String precisaDeRegar() {
        return "Precisa de rega!";
    }

    private String precisaDeAdubo() {
        return "Precisa de adubo!";
    }
}
