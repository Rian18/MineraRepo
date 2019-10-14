
package br.ufjf.minerpo;

import java.util.ArrayList;

public class Instancia {
    // Fatores das instancias em si
    String id;
    String nome;
    int numColaboradores;
    int numEquipes;
    int numArestas;


    // Fatores para calcular o peso
    ArrayList<Integer> temposCotribuicao = new ArrayList<Integer>();
    ArrayList<Integer> numSeguidores = new ArrayList<Integer>();
    ArrayList<Integer> temposAtualizacao = new ArrayList<Integer>();
    ArrayList<Integer> numRepos = new ArrayList<Integer>();
    ArrayList<String> localidades = new ArrayList<String>();
    ArrayList<String> linguagues = new ArrayList<String>();
    ArrayList<Float> fatoresXP = new ArrayList<Float>();
    ArrayList<Float> fatoresXPNormalizados = new ArrayList<Float>();
    ArrayList<String> nomeUsuarios = new ArrayList<String>();
    int tamanhoReal;
   
    
    public void nomalizaXP(){
        float maiorXP = -999;
        
        for(int i=0 ; i< fatoresXP.size(); i++){
            if(fatoresXP.get(i) > maiorXP)
                maiorXP = fatoresXP.get(i);
        }
        
        for(int i =0 ; i< fatoresXP.size(); i++){
            fatoresXPNormalizados.add((float)fatoresXP.get(i)/maiorXP);
        }
        tamanhoReal = nomeUsuarios.size();
    }
    
}
