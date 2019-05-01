/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.Classes;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Yudi-PC
 */
public class Mapa 
{     
    private AssetManager assetManager;
    private Node pisos = new Node();

    public Mapa(AssetManager assetManager, Node rootNode) {
        this.assetManager = assetManager;
        rootNode.attachChild(pisos);
    }
    
    //Pede para gerar o mapa X
    public void gerarMapa(int id)
    {
        float[][] listaInfo = infoMapa(id);
        renderizar(listaInfo);
    }
    
    public float[][] infoMapa(int id)
    {
        try
        {
            switch(id)
            {
                case 1:
                    return m1();

                case 2:
                    return m2();
            }

            throw new UnsupportedOperationException("ERRO - ID mapa incorreto");
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
        
        return null;
    }
    
    
    //Retorna uma lista com os dados da coordenada X,Y,Z 
    //(Foram separado numa funcao para diminui a complexidade)
    //OBS.: primeiro => inicio e Segundo => fim
    private float[][] m1() 
    {
        float[][] temp = 
        {{0,0,0},
        {2,0,0},
        {1,0,0},
        {1,1,0},
        {0,1,0},
        {-1,1,0},
        {-1,0,0},
        {-1,-1,0},
        {0,-1,0},
        {1,-1,0},
        {2,1,0},
        {3,1,0},
        {3,0,0},
        {3,-1,0},
        {2,-1,0}};
        /*  0 - Entrada, 1 - Saida, 2 - Piso
                
                2 2 2 2 2
                2 0 2 1 2
                2 2 2 2 2
        */
        return temp;
    }
    
    private float[][] m2() 
    {
        float[][] temp = 
        {{0,0,0},
        {5,1,0},
        {1,0,0},
        {1,1,0},
        {0,1,0},
        {-1,1,0},
        {-1,0,0},
        {-1,-1,0},
        {0,-1,0},
        {1,-1,0},
        {2,0,0},
        {3,0,0},
        {3,-1,0},
        {4,-1,0},
        {5,-1,0},
        {5,0,0},
        {-1,2,0},
        {0,2,0},
        {1,2,0},
        {-1,-2,0},
        {0,-2,0},
        {1,-2,0},
        {-2,0,0},
        {-2,1,0},
        {-2,2,0},
        {-2,-1,0},
        {-2,-2,0}};
        /*  0 - Entrada, 1 - Saida, 2 - Piso
                //Resposta: E,C,D,B....
              2 2 2 2
              2 2 2 2 - - - 1
              2 2 0 2 2 2 - 2  
              2 2 2 2 - 2 2 2
              2 2 2 2
        */
        return temp;
    }
    
    //OBS.: listaInfo: 
    //o primeiro => inicio 
    //o segundo => fim
    private void renderizar(float[][] listaInfo) 
    {
        for (int i = 0; i < listaInfo.length; i++) 
        {            
            Geometry g = gerarPiso(i);
            g.move(listaInfo[i][0],listaInfo[i][1],listaInfo[i][2]);
        }
    }
    
    //Cria um piso neste ponto (0-> inicio, 1-> fim, 2ou+->piso)
    private Geometry gerarPiso(int tipo)
    {
        //gl.glPushMatrix();
                                
            float temp = 0.45f; // Use esta variavel para muda o tamanho
                        
            Box b = new Box(temp, temp, 0);
            Geometry geom = new Geometry("Piso"+tipo, b);
            Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            geom.setMaterial(mat);

            pisos.attachChild(geom);
            
            switch(tipo) // seleciona a cor
            {
                case 0: //Inicio
                    mat.setColor("Color", ColorRGBA.Blue);
                    //mat.setTexture("ColorMap", assetManager.loadTexture("Textures/xadrez_a.png"));
                break;

                case 1: //Fim
                    mat.setColor("Color", ColorRGBA.Red);
                    //mat.setTexture("ColorMap", assetManager.loadTexture("Textures/xadrez_v.jpg"));
                break;

                default: //Piso Qualquer
                    mat.setColor("Color", ColorRGBA.Gray);
                    //mat.setTexture("ColorMap", assetManager.loadTexture("Textures/xadrez.jpg"));
            }
           
            return geom;
            //talvez seja ideal: Diminui um pouco ou fazer uma borda
        //gl.glPopMatrix();
    }
    
    public Node getPisos() {
        return pisos;
    }

    public void setPisos(Node pisos) {
        this.pisos = pisos;
    }
    
    
    
}
