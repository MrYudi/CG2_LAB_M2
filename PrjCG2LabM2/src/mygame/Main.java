package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import mygame.Classes.Mapa;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    
    //private Cubo c = new Cubo();
    private Mapa m;
    private int mapaAtual = 2;

    public static void main(String[] args) {
        Main app = new Main();
        app.showSettings = false;
        app.start();
    }

    @Override
    public void simpleInitApp() {
        m = new Mapa(assetManager,rootNode);
        
        m.gerarMapa(mapaAtual);
        
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    
    //Esta parte ira confirma se o jogador ganhou ou nao
    private boolean VerificarVitoria() 
    {
        /*double[][] lista = m.infoMapa(selectFase);
        boolean vitoria = false;
        verificar = true;
        
        if (lista[1][0] == c.getX() && 
            lista[1][1] == c.getY() &&
            c.isBaixo()) 
        {
            vitoria = true;
            System.out.println("Foi encontra uma vitoria");
             
         
            verificar = false;
            JOptionPane.showMessageDialog(null, "Vitória");
        }
        
        return vitoria;*/
        return false;
    }
    
  
    //Esta parte ira confirma se o jogador derrubou o cubo
    private boolean VerificarDerrota() 
    {
        /*double[][] lista = m.infoMapa(selectFase);
        boolean derrota = true;
        
        for (double[] ds : lista) 
        {
            if (ds[0] == c.getX() && 
                ds[1] == c.getY()) 
            {
                derrota = false;
                break;
            }
            
        }
        
        if (derrota) 
        {
            verificar = false;
 
            System.out.println("Foi encontra uma derrota");
              JOptionPane.showMessageDialog(null, "Game Over");
        }
        
        return derrota;*/
        return false;
    }
    
    private boolean inverterBoolean(boolean b) 
    {
        return !b;
    }
}
