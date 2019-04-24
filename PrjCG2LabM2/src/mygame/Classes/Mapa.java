/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.Classes;

/**
 *
 * @author Yudi-PC
 */
public class Mapa 
{     
    //Pede para gerar o mapa X
    public void gerarMapa(int id)
    {
        double[][] listaInfo = infoMapa(id);
        renderizar(listaInfo);
    }
    
    public double[][] infoMapa(int id)
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
    private double[][] m1() 
    {
        double[][] temp = 
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
    
    private double[][] m2() 
    {
        double[][] temp = 
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
    private void renderizar(double[][] listaInfo) 
    {
        for (int i = 0; i < listaInfo.length; i++) 
        {
            //gl.glTranslated(listaInfo[i][0],listaInfo[i][1],listaInfo[i][2]);
            gerarPiso(i);
        }
    }
    
    //Cria um piso neste ponto (0-> inicio, 1-> fim, 2ou+->piso)
    private void gerarPiso(int tipo)
    {
        //gl.glPushMatrix();
            
            switch(tipo)
            {
                case 0: //Inicio
                    //gl.glColor3f(0.0f, 0.0f, 1.0f);
                break;

                case 1: //Fim
                    //gl.glColor3f(1.0f, 0.0f, 0.0f);
                break;

                default: //Piso Qualquer
                    //gl.glColor3f(1.0f, 1.0f, 0f);
            }
            // Desenha um quadrado preenchido com a cor corrente
            
            double temp = 0.45; // Use esta variavel para muda o tamanho
            /*gl.glBegin(GL2.GL_QUADS);
                      gl.glVertex3d(temp,temp,0);
                      gl.glVertex3d(-temp,temp,0);
                      gl.glVertex3d(-temp,-temp,0);
                      gl.glVertex3d(temp,-temp,0);               
            gl.glEnd();*/
            //talvez seja ideal: Diminui um pouco ou fazer uma borda
        //gl.glPopMatrix();
    }
    
}
