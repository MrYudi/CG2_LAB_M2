/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.Classes;

import java.util.Random;

/**
 *
 * @author Yudi-PC
 */
public class GeraMapa {
         
    //Mapas são gerado de 0-5 ou seja 6x6=36
    //Será colocado apenas 66% do total, ou seja, 24 pisos.
    //Verifica se existe caminho valido
    private float[][] lista;
    
    public float[][] criaMapa()
    {
        do
        {
            geraLista();
        }while(!temCaminhoValido());
        
        return lista;
    }
    
    private boolean temCaminhoValido() 
    {
        //algoritmo Vertical (Estrutura de dados)
        return true;
    }
    
    private void geraLista()
    {
        lista = new float[24][3];
        
        for (int i = 0; i < lista.length; i++) 
        {
            float[] ponto;
            
            do
            {               
                ponto = coodenada();
            }while (temEstePonto(ponto));
            
            lista[i] = ponto;
        }
    }
    
    private boolean temEstePonto(float[] ponto)
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
        return (float) new Random().nextInt(6);
    }

}
