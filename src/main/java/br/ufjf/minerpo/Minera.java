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

/**
 *
 * @author Rian Alves
 */
public class Minera {

    Conexao conexao = new Conexao();

    public PagedIterable<GHUser> searchUsers(String local) throws IOException {

        //File file = new File("C:\\Users\\Rian Alves\\Desktop\\mineraBraZil.txt");
        File file = new File("C:\\Users\\vitor\\Desktop\\mineraBrasil.txt");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        GitHub github = conexao.getConexao();
        GHUserSearchBuilder users = github.searchUsers();
        GHUserSearchBuilder userss = users.location(local);

        PagedIterable<GHUser> usersLocal = userss.list();
        for (GHUser user : usersLocal) 
        {
            String linha;
            linha = String.valueOf(user.getId()) + ";" + user.getLogin() + ";" + user.getLocation() + ";" + user.getCreatedAt().toString() + ";" + String.valueOf(user.getFollowersCount()) + ";" + String.valueOf(user.getFollowingCount()) + ";" + user.getPublicGistCount() + ";" + user.getPublicRepoCount() + ";" + user.getUpdatedAt().toString() + ";";
            System.out.println(linha);
            bw.write(linha);
            bw.newLine();                        
        }
        bw.close();
        return usersLocal;
    } 

    public void montaColaboradores(String local) throws IOException, ParseException{
        PagedIterable<GHUser> colaboradores = searchUsers(local);
        
        Colaborador colab2 = new Colaborador();        
        colab2.montaColaborador((GHUser) colaboradores.asList().toArray()[2]);
        
        System.out.println("Tempo: " + colab2.tempoGithub);
        System.out.println("Número de seguidores: " + colab2.seguidores);
        System.out.println("Tempo desde o ultimo update (em dias) " + colab2.tempoAtualizacao);
        System.out.println("Número de Repositorios: " + colab2.numRepositorios);
        colab2.imprimeLinguages(colab2.usuario);
        colab2.imprimePesosContribuição(colab2.usuario);
        
        
    }

}
