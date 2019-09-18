
package br.ufjf.minerpo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GHUserSearchBuilder;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;

public class Colaborador {
    // Informações de um colaborador
    public GHUser usuario;
    public int tempoGithub;
    public int seguidores;
    public int tempoAtualizacao;
    public int numRepositorios;
    public  Map<String, Integer> habLinguagemPorNumRepositorio =  new HashMap<String, Integer>();
    public String linguagemMaiorHabilidade;
    public Map<GHRepository, Integer> pesosContribuicaoRepositorios = new HashMap<GHRepository, Integer>();   
    
    // INICIA O COLABORADOR CHAMANDO OS MÉTODOS DESTA CLASSE
    public void montaColaborador(GHUser colaborador) throws IOException, ParseException {
        usuario = colaborador;
        tempoGithub = getTempoNoGitHub(colaborador);
        seguidores = getNumSeguidores(colaborador);
        tempoAtualizacao = getTempoUltimoUpdate(colaborador);
        numRepositorios = getNumRepositorios(colaborador);
        linguagemMaiorHabilidade = linguagemMaiorHabilidade(getLinguagensDeProgramacao(usuario));
        habLinguagemPorNumRepositorio = getLinguagensDeProgramacao(colaborador);
        pesosContribuicaoRepositorios = geraPesoContribuicao(colaborador);
    }
    // ---------------------------------------------------------
    
   
    private int getTempoNoGitHub(GHUser user) throws IOException, ParseException {
        Date date = user.getCreatedAt();
        int compare = comparaDataComHoje(date);
        return compare;
    }
    
    
    private int comparaDataComHoje(Date data) {
        LocalDate localDate = LocalDate.now();
        Date today = convertToDateViaInstant(localDate);

        int diasEntreDatas = (int) ChronoUnit.DAYS.between(data.toInstant(), today.toInstant()) + 1;

        return diasEntreDatas;
    }    

    private Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    private int getNumSeguidores(GHUser user) throws IOException {
        return user.getFollowers().size();
    }

    private int getTempoUltimoUpdate(GHUser user) throws IOException {
        //retorna o numero de dias desde a ultima atualização ate hoje
        int dias = comparaDataComHoje(user.getUpdatedAt());
        return dias;
    }

    private int getNumRepositorios(GHUser user) throws IOException {
        int numRepos = user.getRepositories().size();
        return numRepos;
    }

    private Map<String, Integer> getLinguagensDeProgramacao(GHUser user) throws IOException {
        Map<String, GHRepository> repositorios = user.getRepositories();
        Map<String, Integer> repoLangCount = new HashMap<String, Integer>();
        GHRepository repoWalker = new GHRepository();

        for (int i = 0; i < repositorios.size(); i++) {
            repoWalker = (GHRepository) repositorios.values().toArray()[i];
            if (repoLangCount.keySet().contains(repoWalker.getLanguage())) {
                repoLangCount.put(
                        repoWalker.getLanguage(),
                        repoLangCount.get(repoWalker.getLanguage()) + 1);
            } else {
                repoLangCount.put(repoWalker.getLanguage(), 1);
            }
        }
        return repoLangCount;
    }

    public void imprimeLinguages(GHUser user) throws IOException {
        Map<String, Integer> languages = habLinguagemPorNumRepositorio;
        String melhorLinguagem = linguagemMaiorHabilidade;

        System.out.println("Nome do user: " + user.getLogin());
        for (int i = 0; i < languages.size(); i++) {
            System.out.println("Linguagem: " + languages.keySet().toArray()[i] + ", Num.: " + languages.values().toArray()[i]);
        }
        System.out.println("Melhor linguagem do usuario: " + melhorLinguagem);
    }

    private String linguagemMaiorHabilidade(Map<String, Integer> languages) {
        String melhorLinguagem = "";
        int numMelhorLinguagem = -999;

        for (int i = 0; i < languages.size(); i++) {
            if ((Integer) languages.values().toArray()[i] > numMelhorLinguagem) {
                numMelhorLinguagem = (Integer) languages.values().toArray()[i];
                melhorLinguagem = (String) languages.keySet().toArray()[i];
            }
        }
        return melhorLinguagem;
    }

    private Map<GHRepository, Integer> geraPesoContribuicao(GHUser user) throws IOException {
        System.out.println("2");
        Map<GHRepository, Integer> pesoRepositorios = new HashMap<GHRepository, Integer>();
        GHRepository repoWalker = new GHRepository();
        int contribution = 0;

        for (int i = 0; i < user.getRepositories().size(); i++) {
            System.out.println("4 "+ i);
            repoWalker = (GHRepository) user.getRepositories().values().toArray()[i];
            contribution = getContribuicaoUserRepo(user, repoWalker);
            pesoRepositorios.put(
                    repoWalker,
                    contribution
            );
        }
        return pesoRepositorios;
    }

    private int getContribuicaoUserRepo(GHUser user, GHRepository repo) throws IOException {
        System.out.println("3");
        PagedIterable<GHRepository.Contributor> contributors = repo.listContributors();
        int contribution = 0;

        for (int i = 0; i < contributors.asList().size(); i++) {
            System.out.println("3 "+ i);
            if (contributors.asList().get(i).getLogin().equals(user.getLogin())) {
                contribution = contributors.asList().get(i).getContributions();
                }
           }
        return contribution;
    }
    
    public void imprimePesosContribuição(GHUser user) throws IOException{
        System.out.println("1");
        Map<GHRepository, Integer> pesos = pesosContribuicaoRepositorios;
        GHRepository repoWalker = new GHRepository();
        int pesoWalker = 0;
        
        for(int i=0; i< pesos.size(); i++){
            repoWalker= (GHRepository) pesos.keySet().toArray()[i];
            pesoWalker = (Integer) pesos.values().toArray()[i];
            
            System.out.println("Repo: "+ repoWalker.getName() + " , contribuição: "+  pesoWalker);
        }
    }   
}
