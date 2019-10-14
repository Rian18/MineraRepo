package br.ufjf.minerpo;

import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author Rian Alves
 */
public class main {

    public static void main(String[] args) throws IOException, ParseException {

        // Monta o minerador e as instancias
        Minera m = new Minera();

        Instancia saoPaulo = new Instancia(); //1
        Instancia rioDeJaneiro = new Instancia(); //2
        Instancia brasilia = new Instancia(); //3
        Instancia salvador = new Instancia(); // 4
        Instancia fortaleza = new Instancia(); // 5
        Instancia beloHorizonte = new Instancia();// 6
        Instancia manaus = new Instancia(); // 7
        Instancia curitiba = new Instancia();// 8
        Instancia recife = new Instancia();// 9
        Instancia portoAlegre = new Instancia();// 10

        // Setando os limites de cada instancia
        portoAlegre.id = "1";
        portoAlegre.nome = "teams-u100-t10_TL1-TU5";
        portoAlegre.numColaboradores = 100;
        portoAlegre.numEquipes = 10;

        recife.id = "2";
        recife.nome = "teams-u100-t10_TL1-TU5";
        recife.numColaboradores = 100;
        recife.numEquipes = 10;

        curitiba.id = "3";
        curitiba.nome = "teams-u100-t10_TL1-TU5";
        curitiba.numColaboradores = 100;
        curitiba.numEquipes = 10;

        manaus.id = "4";
        manaus.nome = "teams-u150-t15_TL1-TU5";
        manaus.numColaboradores = 150;
        manaus.numEquipes = 15;

        beloHorizonte.id = "5";
        beloHorizonte.nome = "teams-u150-t15_TL1-TU5";
        beloHorizonte.numColaboradores = 150;
        beloHorizonte.numEquipes = 15;

        fortaleza.id = "6";
        fortaleza.nome = "teams-u150-t15_TL1-TU5";
        fortaleza.numColaboradores = 150;
        fortaleza.numEquipes = 15;

        salvador.id = "7";
        salvador.nome = "teams-u150-t15_TL1-TU5";
        salvador.numColaboradores = 150;
        salvador.numEquipes = 15;

        brasilia.id = "8";
        brasilia.nome = "teams-u200-t20_TL1-TU5";
        brasilia.numColaboradores = 200;
        brasilia.numEquipes = 20;

        rioDeJaneiro.id = "9";
        rioDeJaneiro.nome = "teams-u200-t20_TL1-TU5";
        rioDeJaneiro.numColaboradores = 200;
        rioDeJaneiro.numEquipes = 20;

        saoPaulo.id = "10";
        saoPaulo.nome = "teams-u200-t20_TL1-TU5";
        saoPaulo.numColaboradores = 200;
        saoPaulo.numEquipes = 20;

        // Extrai as informações de colaboradores nas localidades específicas
        //m.montaColaboradores("Porto Alegre", portoAlegre); // 10
       // m.montaColaboradores("Recife", recife); // 9
        m.montaColaboradores("Curitiba", curitiba); // 8
        m.montaColaboradores("Manaus", manaus); // 7
        m.montaColaboradores("Belo Horizonte", beloHorizonte); // 6
        m.montaColaboradores("Fortaleza", fortaleza); // 5
        m.montaColaboradores("Salvador", salvador); // 4
        m.montaColaboradores("Brasilia", brasilia); // 3
        m.montaColaboradores("Rio de Janeiro", rioDeJaneiro); // 2
        m.montaColaboradores("São Paulo", saoPaulo); // 1

    }

}
