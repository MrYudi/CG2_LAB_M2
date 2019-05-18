/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Yudi-PC
 */
public class GeraMapa {
         
    //Mapas são gerado de 0-4 ou seja 5x5=25
    //Será colocado apenas 80% do total.
    //Verifica se existe caminho valido
    
    public float[][] criaMapa()
    {
        return geraLista();
    }
    
    
    private float[][] geraLista()
    {
        List<float[]> lista = new ArrayList<float[]>();
        Cubo c = new Cubo();
        float[] saida = null;
        
        //Gera o mapa 
        lista.add(new float[]{0,0,0});
        
        for (int i = 0; i < 30 || saida == null; i++) 
        {
            c.moveCubo(new Random().nextInt(4) + 1);
            lista.add(new float[]{c.getX(),c.getY(),0});
            
            if (c.isBaixo()) 
            {
                saida = new float[]{c.getX(),c.getY(),0};
            }
        }
        
        if (saida == null) 
        {
            throw new Error("Gerador de Mapa: Saida nula");
        }
        
        lista.add(1, saida);
        
        //Converter Lista Para Matriz e remove repetidos
        
        float[][] listaMatriz = new float[lista.size()][3];
        
        for (int i = 0; i < lista.size(); i++) {
            
            if (!temEstePonto(listaMatriz,listaMatriz[i])) //verificar esta parte
            {
                listaMatriz[i][0] = lista.get(i)[0];
                listaMatriz[i][1] = lista.get(i)[1];
                listaMatriz[i][2] = lista.get(i)[2];
            }
        }
        
        return listaMatriz;
        
        /*lista = new float[((5*5)*4)/5][3];
        
        for (int i = 0; i < lista.length; i++) 
        {
            float[] ponto;
            
            do
            {               
                ponto = coodenada();
            }while (temEstePonto(ponto));
            
            lista[i] = ponto;
        }*/
    }
    
    private boolean temEstePonto(float[][] lista, float[] ponto)
    {
        for (float[] lista1 : lista) {
            if (lista1[0] == ponto[0] && lista1[1] == ponto[1] && lista1[2] == ponto[2]) {
                return true;
            }
        }
        return false;
    }
    
    private float[] coodenada()
    {
        float[] f = {rand(),rand(),0};
        return f;
    }
    
    private float rand()
    {
        return (float) new Random().nextInt(5);
    }

}
