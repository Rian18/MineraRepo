package br.ufjf.minerpo;

import java.io.IOException;
import org.kohsuke.github.GitHub;

/**
 *
 * @author Rian Alves
 */
public class Conexao {
     GitHub gitHub;

    public GitHub getConexao() throws IOException {
        if (gitHub == null) {
            //gitHub = GitHub.connectUsingOAuth("83b5a96b5ba3f08c7dc1bc9ced2668b220ec6490");
            gitHub = GitHub.connectUsingOAuth("662274eaa7f6f5f5d80d0cb7418257fae99e19dd");
            
        }
        return gitHub;
    }
}
