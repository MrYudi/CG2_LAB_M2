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
public class Cubo 
{
    private AssetManager assetManager;
    private Node nodeCubo = new Node();

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
    
    private double x = 0;
    private double y = 0;
    private final double z = 0.55; // este determina a distancia do cubo em relacao ao mapa
    
    private double i; // usado como axiliar para animação
    
    private boolean animando = false; //é usado como controle de transição entre animar e realmente mover
    private boolean permitirMovimentacao = false; //auxiliar para avisa a animação foi terminada
    
    public void gerarCubo(int lado)
    {        
        //gl.glTranslated(x, y, z);

        if (lado != 0) 
        {  
            anima(lado);
        }
        

        defineCor(cima,cima());

        defineCor(direita,direita());
        
        defineCor(baixo, baixo());
        
        defineCor(frente,frente());

        defineCor(atras,tras());

        defineCor(esquerda,esquerda());

        //nodeCubo.move(0,0,0.01f); //apenas para nao encosta
            
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
    
    public boolean moverDireita(){
        if (animando) // animação acabo?
        {
            if (permitirMovimentacao) 
            {
                animando = false;
                x += 1;
                mudancaDeFace(1);
                return true;
            }
        }
        else
        {
            animando = true;
        }
        
        return false;
    }
    public boolean moverEsquerda(){
        
        if (animando) // animação acabo?
        {
            if (permitirMovimentacao) 
            {
                animando = false;
                x -= 1;
                mudancaDeFace(3);
                return true;
            }
        }
        else
        {
            animando = true;
        }
        
        return false;
    }
    public boolean moverFrente(){
        
        if (animando) // animação acabo?
        {
            if (permitirMovimentacao) 
            {
                animando = false;
                y += 1;
                mudancaDeFace(4);
                return true;
            }
        }
        else
        {
            animando = true;
        }
        
        return false;
    }
    public boolean moverTras(){
        //Primeira vez: permitir animicao e retorna false
        //Durante animação return false
        //Fim: realizar a movimentacao
        
        if (animando) // animação acabo?
        {
            if (permitirMovimentacao) 
            {
                animando = false;
                y -= 1;
                mudancaDeFace(2);
                return true;
            }
        }
        else
        {
            animando = true;
        }
        
        return false;
    }
    
    //----------------------------------------------------------------
    private void defineCor(boolean b, Geometry g){
        if (b) 
        {
            g.getMaterial().setColor("Color", ColorRGBA.Red);
        } 
        else 
        {
            g.getMaterial().setColor("Color", ColorRGBA.Green);
        }
    }
    
    //1-direita, 2-tras, 3-esquerda, 4-frente
    private void mudancaDeFace(int lado)
    {
        System.out.println("Antes da alteração: "+this);
        
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
        
        System.out.println("Depois da alteração: "+this);
    }
    
    public void reniciaValores()
    {
        cima = true;
        baixo = false;
        direita = false;
        esquerda = false;
        atras = false;
        frente = false;

        x = 0;
        y = 0;

        i = 0;

        animando = false;
        permitirMovimentacao = false; 
    }
    

    //Animação 
    //----------------------------------------------------------------
    
    private void anima(int lado) 
    {
        i++; //Este valor define a velocidade da animação
        
        switch(lado)
        {
            case 1:
                animaDireita();
                break;
            case 2:
                animaTras();
                break;
            case 3:
                animaEsquerda();
                break;
            case 4:
                animaFrente();
                break;
            
        }
                
        if (i < 90) 
        {
            permitirMovimentacao = false;
        }
        else
        {
            i = 0;
            permitirMovimentacao = true;
        }
    }
    
    private void animaDireita() 
    {
        /*gl.glTranslated(0.5, 0, -0.5);
        gl.glRotated(i, 0, 1, 0);
        gl.glTranslated(-0.5, 0, 0.5);*/
    }

    private void animaEsquerda() 
    {
        /*gl.glTranslated(-0.5, 0, -0.5);
        gl.glRotated(-i, 0, 1, 0);
         gl.glTranslated(0.5, 0, 0.5);*/
    }

    private void animaFrente() 
    {
        /*gl.glTranslated(0, 0.5, -0.5); 
        gl.glRotated(-i, 1, 0, 0);
        gl.glTranslated(0, -0.5, 0.5); */
    }

    private void animaTras() 
    {
        /*gl.glTranslated(0, -0.5, -0.5); 
        gl.glRotated(i, 1, 0, 0);
        gl.glTranslated(0, 0.5, 0.5);*/ 
    }
    
    //Get e Set
    //----------------------------------------------------------------
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getZ() {
        return z;
    }
    public boolean isBaixo() {
        return baixo;
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
        
        s += ", x=" + x + ", y=" + y + ", z=" + z + '}';
        
        return s;
    }    

}
