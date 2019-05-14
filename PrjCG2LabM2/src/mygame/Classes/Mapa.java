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
    private float[][] listaInfo;

    public Mapa(AssetManager assetManager, Node rootNode) {
        this.assetManager = assetManager;
        rootNode.attachChild(pisos);
    }
    
    //Pede para gerar o mapa X
    public void gerarMapa(int id)
    {
        listaInfo = infoMapa(id);
        renderizar(listaInfo);
    }
    
    private float[][] infoMapa(int id)
    {
        try
        {
            switch(id)
            {
                case 1:
                    return m1();
                case 2:
                    return m2();
                case 3:
                    return m3();
                case 4:
                    return criaMapa();
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
    
    private float[][] m3() {
        float[][] temp = 
        {{0,0,0},
        {2,0,0},
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
                2 0 - 1 2
                2 2 2 2 2
        */
        return temp;
    }
    
    private float[][] criaMapa() 
    {
        return new GeraMapa().criaMapa();
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
        float temp = 0.5f; // Use esta variavel para muda o tamanho

        Box b = new Box(temp, temp, 0);
        Geometry geom = new Geometry("Piso"+tipo, b);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        geom.setMaterial(mat);

        pisos.attachChild(geom);

        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/texturaPiso.jpg"));

        switch(tipo) // seleciona a cor
        {
            case 0: //Inicio
                mat.setColor("Color", ColorRGBA.Blue);
            break;

            case 1: //Fim
                mat.setColor("Color", ColorRGBA.Red);
            break;

            default: //Piso Qualquer
                mat.setColor("Color", ColorRGBA.Gray);
        }

        return geom;
    }
    
    //-------------------------------------------------------------------------------
    //Get e Set
    
    public Node getPisos() {
        return pisos;
    }

    public float[][] getListaInfo() {
        return listaInfo;
    }    
    
}
