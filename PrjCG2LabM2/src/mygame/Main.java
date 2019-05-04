package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import javax.swing.JOptionPane;
import mygame.Classes.*;


/**
 * @author Yudi
 */
public class Main extends SimpleApplication{
    
    private Cubo c;
    private Mapa m;
    private int mapaAtual = 1; // Defini qual é o mapa atual que deve ser gerado
    private int auxAnimacao = 0; //Variavel auxiliar para controle de animação (0 - parado; 1,2,3,4 - Em animação e qual tipo)
    private int auxCamera = 1; // Auxiliar camera, que tem pre-configurações
    
    public static void main(String[] args) {
        Main app = new Main();
        app.showSettings = false;
        app.start();
    }

    @Override
    public void simpleInitApp() 
    {
        viewPort.setBackgroundColor(ColorRGBA.DarkGray);
        
        m = new Mapa(assetManager,rootNode);
        c = new Cubo(assetManager,rootNode);
        
        m.gerarMapa(mapaAtual);
        c.gerarCubo();
        
        camera(auxCamera);
        controle();
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        
        if (auxAnimacao != 0) 
        {
            c.anima(auxAnimacao, tpf*3); //o Multiplo define a velocidade de animação
            
            if (!c.isAnimando()) 
            {
                c.moveCubo(auxAnimacao);
                
                auxAnimacao = 0;
                
                c.gerarCubo();
                
                camera(auxCamera);
                
                if(VerificarDerrota())
                {
                    renicia();
                }
                
                if (VerificarVitoria()) 
                {
                    trocaDeMapa();
                    renicia();
                }
            }
        }
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) 
        {
            if(!c.isAnimando())
            {                
                if (name.equals("Right") && !keyPressed) {
                    auxAnimacao = 1;
                }
                if (name.equals("Left") && !keyPressed) {
                    auxAnimacao = 3;
                }
                if (name.equals("Up") && !keyPressed) {
                    auxAnimacao = 4;
                }
                if (name.equals("Down") && !keyPressed) {
                    auxAnimacao = 2;
                }
                if (name.equals("Camera1") && !keyPressed) {
                    auxCamera = 1;
                    camera(auxCamera);
                }
                if (name.equals("Camera2") && !keyPressed) {
                    auxCamera = 2;
                    camera(auxCamera);
                }
            }
        }
    };
       
    private void controle() {
        inputManager.addMapping("Right",   new KeyTrigger(KeyInput.KEY_L));
        inputManager.addMapping("Up",  new KeyTrigger(KeyInput.KEY_I));
        inputManager.addMapping("Left",   new KeyTrigger(KeyInput.KEY_J));
        inputManager.addMapping("Down",  new KeyTrigger(KeyInput.KEY_K));
        inputManager.addMapping("Camera1",  new KeyTrigger(KeyInput.KEY_U));
        inputManager.addMapping("Camera2",  new KeyTrigger(KeyInput.KEY_O));
        
        inputManager.addListener(actionListener, "Right");
        inputManager.addListener(actionListener, "Left");
        inputManager.addListener(actionListener, "Up");
        inputManager.addListener(actionListener, "Down");
        inputManager.addListener(actionListener, "Camera1");
        inputManager.addListener(actionListener, "Camera2");
    }
    
    private void camera(int i) {
        
        switch(i)
        {
            case 1:
                cam.setLocation(new Vector3f(-4f + c.getX(), -2f + c.getY(), 7));
                cam.lookAt(new Vector3f(c.getX(), c.getY(), 0), new Vector3f(0, 0, 0));
                break;
                
            case 2:
                cam.setLocation(new Vector3f(4f + c.getX(), 2f + c.getY(), 7));
                cam.lookAt(new Vector3f(c.getX(), c.getY(), 0), new Vector3f(0, 0, 0));
                break;
                
            default:
                throw new Error("ERRO - Valor camera indefinido");
        }
    }
    
    //Esta parte ira confirma se o jogador ganhou ou nao
    private boolean VerificarVitoria() 
    {
        float[][] lista = m.infoMapa(mapaAtual);
        boolean vitoria = false;
        
        if (lista[1][0] == c.getX() && 
            lista[1][1] == c.getY() &&
            c.isBaixo()) 
        {
            vitoria = true;
            System.out.println("Foi encontra uma vitoria");
             
            JOptionPane.showMessageDialog(null, "Vitória");
        }
        
        return vitoria;
    }
    
    //Esta parte ira confirma se o jogador derrubou o cubo
    private boolean VerificarDerrota() 
    {
        float[][] lista = m.infoMapa(mapaAtual);
        boolean derrota = true;
        
        for (float[] ds : lista) 
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
            System.out.println("Foi encontra uma derrota");
            JOptionPane.showMessageDialog(null, "Game Over");
        }
        
        return derrota;
    }
    
    private void renicia()
    {
        auxAnimacao = 0;
        auxCamera = 1;
        rootNode.detachAllChildren();
        c = new Cubo(assetManager, rootNode);
        m = new Mapa(assetManager, rootNode);
        simpleInitApp();
    }

    private void trocaDeMapa() {
        
        mapaAtual++;
        
        if (!(mapaAtual < 3))
        {
            mapaAtual = 1;
        }
    }
    
}
