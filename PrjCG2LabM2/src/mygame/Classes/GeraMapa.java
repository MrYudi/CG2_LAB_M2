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
    
    
    private final int tamanho = 3; // Define os limites do cubo
    private final int distancia = 30; //Define a quantidade de movimentos do cubo
    
    public float[][] criaMapa()
    {
        return geraLista();
    }
    
    private float[][] geraLista()
    {
        List<float[]> lista = new ArrayList<>();
        Cubo c = new Cubo();
        float[] saida = null;
        
        System.out.println("*** Geracao de mapa ***\n");
        
        //Gera o mapa 
        lista.add(new float[]{0,0,0});
        
        for (int i = 0; i < distancia || saida == null; i++) 
        {
            c.moveCubo(rand(c));
            lista.add(new float[]{c.getX(),c.getY(),0});
            
            if (c.isBaixo() && 
               !(c.getX() == lista.get(0)[0] && c.getY()== lista.get(0)[1])) //Evita que a saida esteja na origem
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
            
            if (!temEstePonto(listaMatriz,lista.get(i)))
            {
                listaMatriz[i][0] = lista.get(i)[0];
                listaMatriz[i][1] = lista.get(i)[1];
                listaMatriz[i][2] = lista.get(i)[2];
            }
        }
        
        System.out.println("\n*** Fim da geração de mapa ***");
        
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
    
     
    private int rand(Cubo c)
    {
        int r = 0;
    
        do
        {
            r = (new Random().nextInt(4) + 1);
                        
            switch(r)
            {
                case 1:
                    if (c.getX() < tamanho) 
                    {
                        return r;
                    }                    
                    break;
                case 2:
                    if (-tamanho < c.getY()) 
                    {
                        return r;
                    }                    
                    break;
                    
                case 3:
                    if (-tamanho < c.getX()) 
                    {
                        return r;
                    }                    
                    break;
                    
                case 4:
                    if (c.getY() < tamanho) 
                    {
                        return r;
                    }
                    break;
            }
        }while (true);
    }

}
