package br.ufjf.minerpo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GHUserSearchBuilder;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;

/**
 *
 * @author Rian Alves
 */
public class Minera {

    Conexao conexao = new Conexao();

    public PagedIterable<GHUser> searchUsers(String local) throws IOException {

        GitHub github = conexao.getConexao();
        GHUserSearchBuilder users = github.searchUsers();
        GHUserSearchBuilder userss = users.location(local);

        PagedIterable<GHUser> usersLocal = userss.list();
        for (GHUser user : usersLocal) {
            String linha;
            linha = String.valueOf(user.getId()) + ";" + user.getLogin() + ";" + user.getLocation() + ";" + user.getCreatedAt().toString() + ";" + String.valueOf(user.getFollowersCount()) + ";" + String.valueOf(user.getFollowingCount()) + ";" + user.getPublicGistCount() + ";" + user.getPublicRepoCount() + ";" + user.getUpdatedAt().toString() + ";";
            System.out.println(linha);

        }

        return usersLocal;
    }

    public void montaColaboradores(String local, Instancia inst) throws IOException, ParseException {
        PagedIterable<GHUser> colaboradores = searchUsers(local);
        ArrayList<Colaborador> colabSelecionados = new ArrayList<Colaborador>();

        int maxIteracoes = 0;
        if (colaboradores.asList().size() < inst.numColaboradores) {
            maxIteracoes = colaboradores.asList().size();
        } else {
            maxIteracoes = inst.numColaboradores;
        }

        for (int i = 0; i < maxIteracoes; i++) {
            System.out.println(i);
            if (colaboradores.asList().get(i).getRepositories().size() <= 15) {
                Colaborador colab = new Colaborador();
                colab.montaColaborador((GHUser) colaboradores.asList().toArray()[i]);
                if (colab.pesoMaiorHabilidade > 0 && colab.linguagemMaiorHabilidade != null && colab.linguagemMaiorHabilidade != "") {
                    colabSelecionados.add(colab);
                    System.out.println("A");
                }

            }
        }

        for (int i = 0; i < colabSelecionados.size(); i++) {
            System.out.println("B"+i);
            inst.temposCotribuicao.add(colabSelecionados.get(i).tempoGithub);
            inst.numSeguidores.add(colabSelecionados.get(i).seguidores);
            inst.temposAtualizacao.add(colabSelecionados.get(i).tempoAtualizacao);
            inst.numRepos.add(colabSelecionados.get(i).numRepositorios);
            inst.localidades.add(colabSelecionados.get(i).local);
            inst.linguagues.add(colabSelecionados.get(i).linguagemMaiorHabilidade);
            inst.fatoresXP.add(colabSelecionados.get(i).pesoMaiorHabilidade);
            inst.nomeUsuarios.add((colabSelecionados.get(i).usuario.getLogin()));
        }
        inst.nomalizaXP();
        imprimeInstancia(inst);

        for (int i = 0; i < colabSelecionados.size(); i++) {
            System.out.println(" --------------- Colaborador " + i + "  --------------------------");
            System.out.println("Tempo: " + inst.temposCotribuicao.get(i));
            System.out.println("Número de seguidores: " + inst.numSeguidores.get(i));
            System.out.println("Tempo desde o ultimo update (em dias) " + inst.temposAtualizacao.get(i));
            System.out.println("Número de Repositorios: " + inst.numRepos.get(i));
            System.out.println("Linguagem de maior habilidade: " + inst.linguagues.get(i));
            System.out.println("Peso da liguagem de maior habilidade: " + inst.fatoresXP.get(i));
            System.out.println("Peso da liguagem de maior habilidade  Normalizado: " + inst.fatoresXPNormalizados.get(i));
            System.out.println(" -----------------------------------------------------------------");
        }
        System.out.println("De " + colaboradores.asList().size() + "  colaboradores, " + colabSelecionados.size() + " foram selecionados");
        
    }
    
    private void imprimeInstancia(Instancia inst) throws IOException{
                
        File file = new File("C:\\Users\\vitor\\Desktop\\Instancias\\"+inst.id+".txt");
   
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

  
        for (int i =0; i< inst.tamanhoReal; i++) {
            String linha;
            linha = String.valueOf(inst.nomeUsuarios.get(i)) + ";" + inst.temposCotribuicao.get(i) + ";" + inst.numSeguidores.get(i) + ";" + inst.temposAtualizacao.get(i) + ";" + inst.numRepos.get(i) + ";" + inst.localidades.get(i) + ";" + inst.linguagues.get(i) + ";" + inst.fatoresXPNormalizados.get(i) + ";";
            System.out.println(linha);
            bw.write(linha);
            bw.newLine();
        }
        bw.close();
               
    }

}
