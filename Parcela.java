import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parcela {
    private String nome;
    private int tempoCrescimento, clima;
    private boolean precisaTratamentoEspecial;


    public String getClima () {
        if (this.clima == 1) { return "Sol"; }
        else if (this.clima == 2) { return "Chuva"; }
        else if (this.clima == 3) { return "Ambiente"; }
        return "Clima não registrado.";
    }
    
    public Parcela(String nome, int tempoCrescimento, int clima) {
        this.nome = nome;
        this.clima = clima;
        this.tempoCrescimento = tempoCrescimento;
        this.precisaTratamentoEspecial = false;
    }

    public void registrarPlanta(Banco banco, String planta) {
        Map<String, String> valores = new HashMap<>();
        valores.put("parcela", this.nome); 
        valores.put("planta", planta);
        banco.insert("plantas", valores); 
        System.out.println("Planta " + planta + " registrada na parcela " + nome);
    }

    public void registrarRegar(Banco banco, String data) {
        Map<String, String> valores = new HashMap<>();
        valores.put("parcela", this.nome);
        valores.put("data", data);
        banco.insert("regas", valores);
        System.out.println("Parcela " + nome + " foi regada em " + data);
    }

    public void registrarAdubo(Banco banco, String data) {
        Map<String, String> valores = new HashMap<>();
        valores.put("parcela", this.nome);
        valores.put("data", data);
        banco.insert("adubos", valores);
        System.out.println("Parcela " + nome + " recebeu adubo em " + data);
    }

    public void getAdubo(Banco banco) {
        List<Map<String, String>> registros = banco.select("adubos");
        boolean encontrado = false;
        for (Map<String, String> registro : registros) {
            if (registro.get("parcela").equals(this.nome)) {
                System.out.println("Adubo registrado em: " + registro.get("data"));
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum adubo registrado para a parcela " + nome);
        }
    }

    public void getRega(Banco banco) {
        List<Map<String, String>> registros = banco.select("regas");
        boolean encontrado = false;
        for (Map<String, String> registro : registros) {
            if (registro.get("parcela").equals(this.nome)) {
                System.out.println("Rega registrada em: " + registro.get("data"));
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhuma rega registrada para a parcela " + nome);
        }
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
