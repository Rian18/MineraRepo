package br.ufjf.minerpo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public void searchUsers() throws IOException {

        File file = new File("C:\\Users\\Rian Alves\\Desktop\\mineraBraZil.txt");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        GitHub github = conexao.getConexao();
        GHUserSearchBuilder users = github.searchUsers();
        GHUserSearchBuilder userss = users.location("Brazil");

        PagedIterable<GHUser> usersJF = userss.list();
        for (GHUser user : usersJF) 
        {
            String linha;
            linha = String.valueOf(user.getId()) + ";" + user.getLogin() + ";" + user.getLocation() + ";" + user.getCreatedAt().toString() + ";" + String.valueOf(user.getFollowersCount()) + ";" + String.valueOf(user.getFollowingCount()) + ";" + user.getPublicGistCount() + ";" + user.getPublicRepoCount() + ";" + user.getUpdatedAt().toString() + ";";
            System.out.println(linha);
            bw.write(linha);
            bw.newLine();
                        
        }
        bw.close();
    }
}
