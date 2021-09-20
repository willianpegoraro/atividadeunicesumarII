package br.unicesumar.aula.controle;
 
import br.unicesumar.aula.exceptions.DadoConsultadoException;
import br.unicesumar.aula.modelo.Projeto;
import java.util.ArrayList;
 
import java.util.HashSet;
import java.util.List;
import java.util.Set;
 
public class ProjetoImpl implements ProjetoDAO {
    //Collection que irá armazenar todos os projetos
    private static Set<Projeto> projetos = new HashSet<>(); 
 
 
    @Override
    public void adicionar(Projeto projeto) {
        projetos.add(projeto);
    }
 
    @Override
    public List<Projeto> listar() {
        List<Projeto> projetoList = new ArrayList<>();
        projetoList.addAll(projetos);
        return projetoList;
    }
 
    @Override
    public Projeto consultarPorNome(String nome) throws DadoConsultadoException {
        for(Projeto projeto : projetos){
            if (projeto.getNome().equals(nome))
                return projeto;
        }throw new DadoConsultadoException("Projeto "+nome+" não encontrado");
    }
 
    @Override
    public Projeto alterar(String nome, Projeto projeto) throws DadoConsultadoException {
        Projeto projetoAlterar = consultarPorNome(nome);
        projetoAlterar.setNome(projeto.getNome());
        projetoAlterar.setObjetivo(projeto.getObjetivo());
        projetoAlterar.setNecessidades(projeto.getNecessidades());
        projetoAlterar.setDataInicio(projeto.getDataInicio());
        projetoAlterar.setDataFinal(projeto.getDataFinal());
        projetoAlterar.setStatus(projeto.getStatus());
        return projetoAlterar;
    }
 
    @Override
    public void excluir(Projeto projeto) throws DadoConsultadoException, UnsupportedOperationException {
        if(projetos.contains(projeto)){
            projetos.remove(projeto);
            return;
        }throw new DadoConsultadoException("Projeto "+projeto.getNome()+" não encontrado");
    }
 
    @Override
    public void excluir(String nome) throws DadoConsultadoException, UnsupportedOperationException {
        Projeto projeto = consultarPorNome(nome);
        this.excluir(projeto);
    }
}
