package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.audio.AudioNode;
import com.jme3.font.BitmapText;
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
    private int mapaAtual = 1; // Defini qual é o mapa atual que deve ser gerado (4 é um novo)
    private int auxAnimacao = 0; //Variavel auxiliar para controle de animação (0 - parado; 1,2,3,4 - Em animação e qual tipo)
    private int auxCamera = 1; // Auxiliar camera, que tem pre-configurações
    private AudioNode audioAmbiente;
    private boolean configuracaoBasica = false; //Usado somente para otimiza e resolve o problema de audio
    
    public static void main(String[] args) {
        Main app = new Main();
        app.showSettings = false;
        app.start();
    }

    @Override
    public void simpleInitApp() 
    {        
        m = new Mapa(assetManager,rootNode);
        c = new Cubo(assetManager,rootNode);
        
        m.gerarMapa(mapaAtual);
        c.gerarCubo();
        c.getNodeCubo().setLocalTranslation(m.getListaInfo()[0][0],m.getListaInfo()[0][1],m.getListaInfo()[0][2]);
        
        camera(auxCamera);
        
        if (!configuracaoBasica) {
            viewPort.setBackgroundColor(ColorRGBA.DarkGray);
            audio(); //Comente esta linha, caso não queira musica
            controle();
            interface1();
            configuracaoBasica = !configuracaoBasica;
        }
        
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
                    //Musica Derrota
                    renicia(false);
                }
                
                if (VerificarVitoria()) 
                {
                    //Musica vitoria
                    trocaDeMapa();
                    renicia(true);
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
                if (name.equals("Restart") && !keyPressed) {
                    renicia(false);
                }
                if (name.equals("Gera") && !keyPressed) {
                    mapaAtual = 4;
                    renicia(true);
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
        inputManager.addMapping("Restart",  new KeyTrigger(KeyInput.KEY_R));
        inputManager.addMapping("Gera",  new KeyTrigger(KeyInput.KEY_T));
        
        inputManager.addListener(actionListener, "Right");
        inputManager.addListener(actionListener, "Left");
        inputManager.addListener(actionListener, "Up");
        inputManager.addListener(actionListener, "Down");
        inputManager.addListener(actionListener, "Camera1");
        inputManager.addListener(actionListener, "Camera2");
        inputManager.addListener(actionListener, "Restart");
        inputManager.addListener(actionListener, "Gera");
    }
    
    private void interface1(){
        guiNode.detachAllChildren();//node responsavel pela interface
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText helloText = new BitmapText(guiFont,false);
        helloText.setSize(guiFont.getCharSet().getRenderedSize());
        helloText.setText("Controle do cubo:");
        helloText.setColor(ColorRGBA.Red);
        helloText.setLocalTranslation(0, settings.getHeight(), 0);
        
        guiNode.attachChild(helloText);
        
         BitmapText textTutorial = new BitmapText(guiFont,false);
        textTutorial.setSize(guiFont.getCharSet().getRenderedSize());
        textTutorial.setText("Tecla I-para cima\n"
                            +  "Tecla K- para baixo\n" +
                               "Tecla L- para direita\n" +
                                "Tecla J- para esquerda\n");
        textTutorial.setColor(ColorRGBA.White);
        textTutorial.setLocalTranslation(0, settings.getHeight() - helloText.getHeight(), 0);
        
        guiNode.attachChild(textTutorial);
         
    
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
        float[][] lista = m.getListaInfo();
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
        float[][] lista = m.getListaInfo();
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
    
    private void renicia(Boolean vitoria)
    {
        auxAnimacao = 0;
        auxCamera = 1;
        
        if (vitoria) //Reinicia todos valores
        {
            rootNode.detachAllChildren();
            simpleInitApp();
        }
        else //Reinicia somente os valores iniciais (Usado principalmente em Geração de mapa)
        {
            c.getNodeCubo().removeFromParent();
            
            c = new Cubo(assetManager,rootNode);
            c.gerarCubo();
            c.getNodeCubo().setLocalTranslation(m.getListaInfo()[0][0],m.getListaInfo()[0][1],m.getListaInfo()[0][2]);
            camera(auxCamera);
        }
        
        System.out.println("\n*** Jogo reiniciado ***");
    }

    private void trocaDeMapa() {
        
        mapaAtual++;
        
        if (!(mapaAtual < 5))
        {
            mapaAtual = 4;
        }
        System.out.println("Mapa trocado para: "+mapaAtual);
    }

    private void audio() {
        audioAmbiente = new AudioNode(assetManager, "Sound/AudioAmbiente.wav");
        audioAmbiente.setVolume(0.1f);
        audioAmbiente.setPositional(false);
        audioAmbiente.setLooping(true);
        audioAmbiente.playInstance();
        rootNode.attachChild(audioAmbiente);
        audioAmbiente.play();
    }
    
}
