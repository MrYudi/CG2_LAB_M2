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
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Yudi-PC
 */
public class Cubo 
{
    private final AssetManager assetManager;
    private final Node nodeCubo = new Node();

    public Cubo() {
        this.assetManager = null;
    }
    
    public Cubo(AssetManager assetManager, Node rootNode) {
        this.assetManager = assetManager;
        rootNode.attachChild(nodeCubo);
    }
    
    private boolean cima = true;
    private boolean baixo = false;
    private boolean direita = false;
    private boolean esquerda = false;
    private boolean atras = false;
    private boolean frente = false;
    
    /*private float x = 0;
    private float y = 0;
    private final float z = 0.55f; // este determina a distancia do cubo em relacao ao mapa*/
    
    private float contadoAnima; // Contador auxiliar para animação
    
    private boolean animando = false; //é usado como controle de transição entre animar e realmente mover
    //private boolean permitirMovimentacao = false; //auxiliar para avisa a animação foi terminada
    
    public void gerarCubo()
    {                   
        defineCor(cima,cima());

        defineCor(direita,direita());
        
        defineCor(baixo,baixo());
        
        defineCor(frente,frente());

        defineCor(atras,tras());

        defineCor(esquerda,esquerda());           
    }
   
    //Criação das faces do cubo
    //----------------------------------------------------------------
    
    private Geometry cima(){
        
        Box b = new Box(0.5f, 0.5f, 0);
        Geometry geom = new Geometry("cima", b);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        geom.setMaterial(mat);
        geom.move(0, 0, 1);
        nodeCubo.attachChild(geom);
        
        return geom;
    }  
    private Geometry direita(){
        Box b = new Box(0, 0.5f, 0.5f);
        Geometry geom = new Geometry("direita", b);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        geom.setMaterial(mat);
        geom.move(0.5f, 0, 0.5f);
        nodeCubo.attachChild(geom);
        
        return geom;
    }   
    private Geometry baixo(){
        Box b = new Box(0.5f, 0.5f, 0);
        Geometry geom = new Geometry("baixo", b);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        geom.setMaterial(mat);
        nodeCubo.attachChild(geom);
        
        return geom;
    }   
    private Geometry esquerda(){
        Box b = new Box(0, 0.5f, 0.5f);
        Geometry geom = new Geometry("esquerda", b);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        geom.setMaterial(mat);
        geom.move(-0.5f, 0, 0.5f);
        nodeCubo.attachChild(geom);
        
        return geom;
    }   
    private Geometry frente(){
        Box b = new Box(0.5f, 0, 0.5f);
        Geometry geom = new Geometry("frente", b);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        geom.setMaterial(mat);
        geom.move(0, 0.5f, 0.5f);
        nodeCubo.attachChild(geom);
        
        return geom;
    }   
    private Geometry tras(){
        Box b = new Box(0.5f, 0, 0.5f);
        Geometry geom = new Geometry("frente", b);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        geom.setMaterial(mat);
        geom.move(0, -0.5f, 0.5f);
        nodeCubo.attachChild(geom);
        
        return geom;
    }
        
    //Mover cubos
    //----------------------------------------------------------------
    
    public void moveCubo(int auxAnimacao) {
        
        System.out.println("Antes da alteração: "+this);
        
        switch(auxAnimacao)
        {
            case 1:
                moverDireita();
                break;
            case 2:
                moverTras();
                break;
            case 3:
                moverEsquerda();
                break;
            case 4:
                moverFrente();
                break;
            default:    
                throw new UnsupportedOperationException("Move cubo incorreto: "+ auxAnimacao);
        }
        System.out.println("Depois da alteração: "+this);
    }
    
    private void moverDireita(){
        nodeCubo.move(1, 0, 0);
        mudancaDeFace(1);
    }
    private void moverEsquerda(){
        
        nodeCubo.move(-1, 0, 0);
        mudancaDeFace(3);
    }
    private void moverFrente(){
        nodeCubo.move(0, 1, 0);
        mudancaDeFace(4);
    }
    private void moverTras(){
        nodeCubo.move(0, -1, 0);
        mudancaDeFace(2);
    }
    
    //1-direita, 2-tras, 3-esquerda, 4-frente
    private void mudancaDeFace(int lado){
        
        if (cima) 
        {
            cima = false;
            switch(lado)
            {
                case 1:
                    direita = true;
                    break;
                case 2:
                    atras = true;
                    break;
                case 3:
                    esquerda = true;
                    break;
                case 4:
                    frente = true;
                    break;
            }
        }
        else if(baixo)
        {
            baixo = false;
            
            switch(lado)
            {
                case 1:
                    esquerda = true;
                    break;
                case 2:
                    frente = true;
                    break;
                case 3:
                    direita = true;
                    break;
                case 4:
                    atras = true;
                    break;
            }
        }
        else if (direita)
        {
            direita = false;
            switch(lado)
            {
                case 1:
                     baixo = true;
                    break;
                case 3:
                     cima = true;
                    break;
                default:
                    direita = true;
                    break;
            }
        }
        else if(esquerda)
        {
            esquerda = false;
            switch(lado)
            {
                case 1:
                    cima = true;
                    break;
                case 3:
                    baixo = true;
                    break;
                default:
                    esquerda = true;
                    break;
            }
        }
        else if(atras)
        {
            atras = false;
            switch(lado)
            {
                case 2:
                    baixo = true;
                    break;
                case 4:
                    cima = true;
                    break;
                default:
                    atras = true;
                    break;
            }
        }
        else if(frente)
        {
            frente = false;
            switch(lado)
            {
                case 2:
                    cima = true;
                    break;
                case 4:
                    baixo = true;
                    break;
                default:
                    frente = true;
                    break;
            }
        }
        else
        {
            throw new UnsupportedOperationException("ERRO - Todas variaveis boolean esta falsa, qual face é a correta?");
        }
        
    }
    
    //Outras Funções...
    //----------------------------------------------------------------
    private void defineCor(boolean b, Geometry g){
        
        
        if (b) 
        {
            g.getMaterial().setColor("Color", ColorRGBA.Red);
            g.getMaterial().setTexture("ColorMap", assetManager.loadTexture("Textures/box.jpg"));
        } 
        else 
        {
            g.getMaterial().setColor("Color", ColorRGBA.Gray);
            g.getMaterial().setTexture("ColorMap", assetManager.loadTexture("Textures/box.jpg"));
            
        }
    }
    public void reniciaValores() {
        cima = true;
        baixo = false;
        direita = false;
        esquerda = false;
        atras = false;
        frente = false;

        contadoAnima = 0;

        animando = false;
    }
    
    //Animação 
    //----------------------------------------------------------------
    
    //Anima direciona qual tipo de animação e faz o controle
    //Anima**** realiza animação
    //Desanima**** remove os efeitos da animação (é necessario por causa da logica)
    
    public void anima(int lado, float tpf) {
        contadoAnima += tpf;
        switch (lado) {
            case 1:
                animaDireita(tpf);
                break;
            case 2:
                animaTras(tpf);
                break;
            case 3:
                animaEsquerda(tpf);
                break;
            case 4:
                animaFrente(tpf);
                break;

        }

        if (contadoAnima < Math.PI / 2) {
            animando = true;
        } else {
            contadoAnima = 0;
            animando = false;

            switch (lado) {
                case 1:
                    desanimaDireita();
                    break;
                case 2:
                    desanimaTras();
                    break;
                case 3:
                    desanimaEsquerda();
                    break;
                case 4:
                    desanimaFrente();
                    break;

            }

        }
    }
    
    private void animaDireita(float tpf) {

        if (!animando) {
            nodeCubo.move(0.5f, 0, 0);

            for (Spatial s : nodeCubo.getChildren()) {
                s.move(-0.5f, 0, 0);
            }
        }

        nodeCubo.rotate(0, tpf, 0);
    }
    private void animaEsquerda(float tpf) {

        if (!animando) {
            nodeCubo.move(-0.5f, 0, 0);

            for (Spatial s : nodeCubo.getChildren()) {
                s.move(0.5f, 0, 0);
            }
        }

        nodeCubo.rotate(0, -tpf, 0);
    }
    private void animaFrente(float tpf) {

        if (!animando) {
            nodeCubo.move(0, 0.5f, 0);

            for (Spatial s : nodeCubo.getChildren()) {
                s.move(0, -0.5f, 0);
            }
        }

        nodeCubo.rotate(-tpf, 0, 0);
    }
    private void animaTras(float tpf) {

        if (!animando) {
            nodeCubo.move(0, -0.5f, 0);

            for (Spatial s : nodeCubo.getChildren()) {
                s.move(0, 0.5f, 0);
            }
        }

        nodeCubo.rotate(tpf, 0, 0);
    }

    private void desanimaDireita() {

        for (Spatial s : nodeCubo.getChildren()) {
            s.move(0.5f, 0, 0);
        }
        nodeCubo.move(-0.5f, 0, 0);

        nodeCubo.rotate(0, (float) (-Math.PI / 2), 0);

    }
    private void desanimaEsquerda() {

        for (Spatial s : nodeCubo.getChildren()) {
            s.move(-0.5f, 0, 0);
        }
        nodeCubo.move(0.5f, 0, 0);

        nodeCubo.rotate(0, (float) (Math.PI / 2), 0);
    }
    private void desanimaFrente() {

        for (Spatial s : nodeCubo.getChildren()) {
            s.move(0, 0.5f, 0);
        }
        nodeCubo.move(0, -0.5f, 0);

        nodeCubo.rotate((float) (Math.PI / 2), 0, 0);
    }
    private void desanimaTras() {

        for (Spatial s : nodeCubo.getChildren()) {
            s.move(0, -0.5f, 0);
        }
        nodeCubo.move(0, 0.5f, 0);

        nodeCubo.rotate((float) (-Math.PI / 2), 0, 0);
    }

    //Get e Set
    //----------------------------------------------------------------
    public boolean isBaixo() {
        return baixo;
    } 
    public boolean isAnimando() {
        return animando;
    }   
    public float getX() {
        return nodeCubo.getLocalTranslation().x;
    }
    public float getY() {
        return nodeCubo.getLocalTranslation().y;
    }
    public Node getNodeCubo() {
        return nodeCubo;
    }
    
    
    //String
    //---------------------------------------------------------------
    @Override
    public String toString() {
        String s = "Cubo{";
        if (cima) 
        {
            s += "cima=" + cima;
        }
        else if(baixo)
        {
            s += "baixo=" + baixo;
        }
        else if (direita)
        {
            s += "direita="+direita;
        }
        else if(esquerda)
        {
            s += "esquerda="+esquerda;
        }
        else if(atras)
        {
            s += "atras="+atras;
        }
        else if(frente)
        {
            s += "frente="+frente;
        }
        
        s += ", x=" + nodeCubo.getLocalTranslation().x + ", y=" + nodeCubo.getLocalTranslation().y + ", z=" + nodeCubo.getLocalTranslation().z + '}';
        
        return s;
    }    

}
