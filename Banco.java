import java.util.ArrayList;
import java.util.List;

public abstract class Banco {
    protected List<Object> registros;

    public Banco() {
        this.registros = new ArrayList<>();
    }

    public abstract void inserir(Object objeto);
    public abstract List<Object> selecionar();
    public abstract void atualizar(int id, Object novoObjeto);
    public abstract void deletar(int id);
}
