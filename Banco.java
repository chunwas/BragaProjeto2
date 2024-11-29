import java.util.*;

public class Banco {
    protected Map<String, List<Map<String, String>>> tabelas;

    public Banco() {
        tabelas = new HashMap<>();
        inicializarTabelas();
    }

    protected void inicializarTabelas() {
        createTable("adubo");
        createTable("regas");
        createTable("parcelas");
    }

    public void createTable(String nomeTabela) {
        if (!tabelas.containsKey(nomeTabela)) {
            tabelas.put(nomeTabela, new ArrayList<>());
        }
        System.out.println("Tabela criada: " + nomeTabela);
    }
    public void insert(String nomeTabela, Map<String, String> valores) {
        if (!tabelas.containsKey(nomeTabela)) {
            System.out.println("Tabela " + nomeTabela + " não existe.");
            return;
        }
        tabelas.get(nomeTabela).add(valores);
        System.out.println("Registro inserido na tabela " + nomeTabela + ": " + valores);
    }

    public List<Map<String, String>> select(String nomeTabela) {
        if (!tabelas.containsKey(nomeTabela)) {
            System.out.println("Tabela " + nomeTabela + " não existe.");
            return Collections.emptyList();
        }
        return tabelas.get(nomeTabela);
    }

    public void update(String tabela, Map<String, String> valores) {
        List<Map<String, String>> registros = tabelas.get(tabela);
        if (registros != null) {
            boolean atualizado = false;

            for (Map<String, String> registro : registros) {
                if (registro.get("nome").equals(valores.get("nome"))) {
                    registro.put("tempoCrescimento", valores.get("tempoCrescimento"));
                    registro.put("clima", valores.get("clima"));
                    registro.put("tratamentoEspecial", valores.get("tratamentoEspecial"));
                    atualizado = true;
                    System.out.println("Registro atualizado com sucesso na tabela " + tabela);
                    break;
                }
            }

            if (!atualizado) {
                System.out.println("Parcela não encontrada para atualização.");
            }
        } else {
            System.out.println("Tabela não encontrada: " + tabela);
        }
    }


    public void printTable(String nomeTabela) {
        if (!tabelas.containsKey(nomeTabela)) {
            System.out.println("Tabela " + nomeTabela + " não existe.");
            return;
        }
        List<Map<String, String>> registros = tabelas.get(nomeTabela);
        System.out.println("Tabela: " + nomeTabela);
        for (Map<String, String> registro : registros) {
            System.out.println(registro);
        }
    }

    public void delete(String tableName, Map<String, String> condicoes) {
        List<Map<String, String>> tabela = tabelas.get(tableName);
        if (tabela == null) {
            System.out.println("Tabela '" + tableName + "' não encontrada.");
            return;
        }
        tabela.removeIf(registro -> condicoes.entrySet().stream()
                .allMatch(condicao -> condicao.getValue().equals(registro.get(condicao.getKey()))));
        System.out.println("Registros deletados da tabela '" + tableName + "' com condições: " + condicoes);
    }
}
