/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Trees.java
 *
 * Created on Sep 24, 2013, 10:54:59 PM
 */
package XmasTree;

/**
 *
 * @author javier_j1
 */

import java.awt.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.lang.StringBuilder;
import javax.swing.JOptionPane;

public class Trees extends javax.swing.JFrame {

    /** Creates new form Trees */
    public Trees() {
        initComponents();
        //canvas1.setBackground(Color.getHSBColor(0, 200.1f, 10.5f));
    }
    
public class Node{
        Object cont;
        Node left, right;
        
        public Node(){
            
        }
        
        public Node(Object c){
            cont = c;
        }
        
        public Node(Object c, Node l, Node r){
            cont = c;
            left = l;
            right = r;
        }
    }
    
    boolean active = false;  
    
    public class BST{
        Node root;
        String item;
        String trvs = "";
        //int count = 0;
        
        
        public void create(){
            root = null;
            active = true;
            canvas1.repaint();
            JOptionPane.showMessageDialog(null, "New BST created.");
        }
        
        public void insert(){
            if(active){
            Node prnt = root;
            Node chld = root;             
            boolean left = true;
            
            item = JOptionPane.showInputDialog(null, "Input a value: ");//.toUpperCase();
            Matcher m = Pattern.compile("-?\\d+").matcher(item);
            try{
            if(m.find()){
                if(item.length() == 1){
                    item = "0" + item;
                }
                if(Integer.valueOf(item) >= 99){
                    item = "99";
                }
                if(Integer.valueOf(item) <= 0){
                    item = "0";
                }
            }
            
            if(item.length() > 1 && !m.matches()){                
                item = item.substring(0, 1);                
            }
            
            if(root == null){
                root = new Node(item, null, null);    
            }
            else{
                while(chld != null){
                    if(!m.find()){
                    if(String.valueOf(chld.cont).compareTo(String.valueOf(item)) > 0){
                        prnt = chld;
                        chld = chld.left;
                        left = true;
                    }else{
                        prnt = chld;
                        chld = chld.right;
                        left = false;
                    }
                }else{                        
                    if(Integer.valueOf((chld.cont).toString()).compareTo(Integer.valueOf(item)) > 0){
                        prnt = chld;
                        chld = chld.left;
                        left = true;
                    }else{
                        prnt = chld;
                        chld = chld.right;
                        left = false;
                    }                        
                    }
                }
                Node n = new Node(item, null, null);
                if(left){
                    prnt.left = n;                  
                }else{
                    prnt.right = n;                    
                }
            }
            }catch(NumberFormatException a){
                JOptionPane.showMessageDialog(null, "Error: Digits together w/ letters are not allowed."); 
            }
            display();
            }else{
                JOptionPane.showMessageDialog(null, "No BST created.");
            } 
        }
                
        public void delete(){
            Node prnt = null;
            Node dNode = root;
            Node scsr = null;
            Node prdr = null;
            
            item = JOptionPane.showInputDialog(null, "Enter value to delete: ");
            Matcher m = Pattern.compile("-?\\d+").matcher(item); 
            try{
            if(m.find()){
                if(item.length() == 1){
                    item = "0" + item;
                }
            }
            
            while(dNode != null && item.compareTo(String.valueOf(dNode.cont)) < 0){
                prnt = dNode;
                if(item.compareToIgnoreCase(String.valueOf(dNode.cont)) < 0){
                    dNode = dNode.left;                
                }else{
                    dNode = dNode.right;
                }
            }            
            if(dNode == null){
                JOptionPane.showMessageDialog(null, "Error: Value not found!");
            }else{
                if(dNode.left == null && dNode.right == null){
                    if(root == dNode){
                        root = null;
                        dNode = null;
                    }else{
                        if(String.valueOf(dNode.cont).compareTo(String.valueOf(prnt.cont))<0){
                            prnt.left = null;
                        }else{
                            prnt.right = null;
                        }
                    }
                }else{
                    if(dNode.left != null && dNode.right != null){
                        scsr = dNode.right;
                        prdr = dNode;
                        while(scsr.left != null){
                            prdr = scsr;
                            scsr = scsr.left;
                        }
                        if(dNode.right == scsr){
                            prdr.right = scsr.right;
                            dNode.cont = scsr.cont;
                            scsr = null;
                        }else{
                            prdr.left = scsr.right;
                            dNode.cont = scsr.cont;
                            scsr = null;
                        }
                    }else{
                        if(dNode.left == null){
                            if(Integer.parseInt(String.valueOf(dNode.cont)) < Integer.parseInt(String.valueOf(prnt.cont))){
                                prnt.left = dNode.right;
                                dNode = null;
                            }else{
                                prnt.right = dNode.right;
                                dNode = null;
                            }
                        }else{
                            if(Integer.parseInt(String.valueOf(dNode.cont)) < Integer.parseInt(String.valueOf(prnt.cont))){
                                prnt.left = dNode.left;
                                dNode = null;
                            }else{
                                prnt.left = dNode.left;
                                dNode = null;
                            }
                        }
                    }
                }
            }}catch(NumberFormatException a){
                            
                        }
            //canvas1.repaint();
            display();
        }
        

        
        public void display(){
            
            Graphics g = canvas1.getGraphics();
            Graphics2D g2 = (Graphics2D) g;
            canvas1.update(g);
            
//            if(root.left == null && root.right == null){
//                g.setColor(Color.red);
//            g.drawOval(canvas1.getWidth()/2, canvas1.getHeight()/10, 30, 30);
//            g.drawString(item, canvas1.getWidth()/2, canvas1.getHeight()/10);
//            }
//            if(root.left != null && root != null){
//            g.drawOval(canvas1.getWidth()/3, canvas1.getHeight()/8, 30, 30);
//            g.drawString(root.left.cont.toString(), canvas1.getWidth()/3, canvas1.getHeight()/8);
//            }
//            if(root.right != null && root != null){
//            g.drawOval(canvas1.getWidth()/2 +100, canvas1.getHeight()/8, 30, 30);
//            g.drawString(root.right.cont.toString(), canvas1.getWidth()/2 +100, canvas1.getHeight()/8);
//            }
            
            canvas1.print(g);
            canvas1.update(g);
            for(int count = 0; count < pre().size(); count++){
                if(count == 0){
                g.setColor(Color.red);
                g.drawOval(canvas1.getWidth()/2, canvas1.getHeight()/10, 30, 30);
                g.drawString(pre().get(0).toString(), canvas1.getWidth()/2+9, canvas1.getHeight()/10+20);
                }else if (count == 1 || count == 2){
                    if(String.valueOf(pre().get(count)).compareTo(String.valueOf(pre().get(0))) < 0){
                        g.drawOval(canvas1.getWidth()/3, canvas1.getHeight()/10+12, 30, 30);
                        g.drawString(pre().get(count).toString(), canvas1.getWidth()/3+10, canvas1.getHeight()/10+30);
                    }else if(String.valueOf(pre().get(count)).compareTo(String.valueOf(pre().get(0))) > 0){
                        g.drawOval(canvas1.getWidth()/3 + 200 , canvas1.getHeight()/10 + 15, 30, 30);
                        g.drawString(pre().get(count).toString(), canvas1.getWidth()/3 + 212 , canvas1.getHeight()/10 + 35);
                    }
                }else{
                    if(String.valueOf(pre().get(count)).compareTo(String.valueOf(pre().get(0))) > 0 && String.valueOf(pre().get(count)).compareTo(String.valueOf(pre().get(count - 1))) > 0){
                        g.drawOval(canvas1.getWidth()/3 + 260 , canvas1.getHeight()/10 + 35, 30, 30);
                        g.drawString(pre().get(count).toString(), canvas1.getWidth()/3 + 273 , canvas1.getHeight()/10 + 55);
                    }

                }
            }
            
        }
        
                        
private void pre0rder(Node node, ArrayList goal) {
    if (node != null) {
        goal.add(String.valueOf(node.cont));
        pre0rder(node.left, goal);
        pre0rder(node.right, goal);
    }
}

public ArrayList pre() {
    ArrayList result = new ArrayList();
    pre0rder(root, result);
    return result;
}

private void in0rder(Node node, ArrayList goal) {
    if (node != null) {
        in0rder(node.left, goal);
        goal.add(String.valueOf(node.cont));
        in0rder(node.right, goal);
    }
}

public ArrayList in() {
    ArrayList result = new ArrayList();
    in0rder(root, result);
    return result;
}

private void post0rder(Node node, ArrayList goal) {
    if (node != null) {
        post0rder(node.left, goal);
        post0rder(node.right, goal);
        goal.add(String.valueOf(node.cont));
    }
}

public ArrayList p0st() {
    ArrayList result = new ArrayList();
    post0rder(root, result);
    return result;
}

    }

    public class AVL{
        Node root;
        String item;
        
        public void create(){
            root = null;
            canvas1.repaint();
            JOptionPane.showMessageDialog(null, "New A.V.L tree created.");
        }
        
        public void insert(){
            
        }
                
        public void delete(){
            
        }
        
    }
    
    BST BSTtree = new BST();
    AVL AVLtree = new AVL();
            

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvas1 = new java.awt.Canvas();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuBSTcreate = new javax.swing.JMenuItem();
        jMenuBSTinsert = new javax.swing.JMenuItem();
        jMenuBSTdelete = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuBSTpre0rder = new javax.swing.JMenuItem();
        jMenuBSTin0rder = new javax.swing.JMenuItem();
        jMenuBSTpost0rder = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuAVLcreate = new javax.swing.JMenuItem();
        jMenuAVLinsert = new javax.swing.JMenuItem();
        jMenuAVLdelete = new javax.swing.JMenuItem();
        jMenuExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        canvas1.setBackground(new java.awt.Color(153, 255, 255));

        jLabel1.setText("Binary Search Tree:");

        jLabel2.setText("Create - Alt + C");

        jLabel3.setText("Insert -   Alt + I");

        jLabel4.setText("Delete - Alt + D");

        jLabel5.setText("Traversal:");

        jLabel6.setText("Preorder -   Alt + 1");

        jLabel7.setText("Inorder -      Alt + 2");

        jLabel8.setText("Postorder - Alt + 3");

        jMenu1.setText("File");

        jMenu2.setText("Binary Search Tree");

        jMenuBSTcreate.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jMenuBSTcreate.setText("Create");
        jMenuBSTcreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuBSTcreateActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuBSTcreate);

        jMenuBSTinsert.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        jMenuBSTinsert.setText("Insert");
        jMenuBSTinsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuBSTinsertActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuBSTinsert);

        jMenuBSTdelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        jMenuBSTdelete.setText("Delete");
        jMenuBSTdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuBSTdeleteActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuBSTdelete);

        jMenu4.setText("Traverse");

        jMenuBSTpre0rder.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.ALT_MASK));
        jMenuBSTpre0rder.setText("Preorder");
        jMenuBSTpre0rder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuBSTpre0rderActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuBSTpre0rder);

        jMenuBSTin0rder.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.ALT_MASK));
        jMenuBSTin0rder.setText("Inorder");
        jMenuBSTin0rder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuBSTin0rderActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuBSTin0rder);

        jMenuBSTpost0rder.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.ALT_MASK));
        jMenuBSTpost0rder.setText("Postorder");
        jMenuBSTpost0rder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuBSTpost0rderActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuBSTpost0rder);

        jMenu2.add(jMenu4);

        jMenu1.add(jMenu2);

        jMenu3.setText("A.V.L");

        jMenuAVLcreate.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuAVLcreate.setText("Create");
        jMenuAVLcreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAVLcreateActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuAVLcreate);

        jMenuAVLinsert.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuAVLinsert.setText("Insert");
        jMenuAVLinsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAVLinsertActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuAVLinsert);

        jMenuAVLdelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuAVLdelete.setText("Delete");
        jMenuAVLdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAVLdeleteActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuAVLdelete);

        jMenu1.add(jMenu3);

        jMenuExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuExit.setText("Exit");
        jMenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuExitActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuExit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(canvas1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuExitActionPerformed
        // TODO add your handling code here:
    String[] choices = {"Yes", "No"};
    int ex = JOptionPane.showOptionDialog(null, "Do you want to quit?", null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, choices, choices);
    if (ex == 0) {
        System.exit(0);
    }
    }//GEN-LAST:event_jMenuExitActionPerformed

    private void jMenuBSTcreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuBSTcreateActionPerformed
        // TODO add your handling code here:
        BSTtree.create();
    }//GEN-LAST:event_jMenuBSTcreateActionPerformed

    private void jMenuBSTinsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuBSTinsertActionPerformed
        // TODO add your handling code here:
 try{
        BSTtree.insert();
    }catch(NullPointerException a){ 
}
    }//GEN-LAST:event_jMenuBSTinsertActionPerformed

    private void jMenuBSTdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuBSTdeleteActionPerformed
        // TODO add your handling code here:
  try{
    BSTtree.delete();
    }catch(NullPointerException a){ 
    }
    }//GEN-LAST:event_jMenuBSTdeleteActionPerformed

    private void jMenuBSTpre0rderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuBSTpre0rderActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Preorder: " +BSTtree.pre());
    }//GEN-LAST:event_jMenuBSTpre0rderActionPerformed

    private void jMenuBSTin0rderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuBSTin0rderActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Inorder: " +BSTtree.in());
    }//GEN-LAST:event_jMenuBSTin0rderActionPerformed

    private void jMenuBSTpost0rderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuBSTpost0rderActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Postorder: " +BSTtree.p0st());
    }//GEN-LAST:event_jMenuBSTpost0rderActionPerformed

    private void jMenuAVLcreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAVLcreateActionPerformed
        // TODO add your handling code here:
        AVLtree.create();
    }//GEN-LAST:event_jMenuAVLcreateActionPerformed

    private void jMenuAVLinsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAVLinsertActionPerformed
        // TODO add your handling code here:
        AVLtree.insert();
    }//GEN-LAST:event_jMenuAVLinsertActionPerformed

    private void jMenuAVLdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAVLdeleteActionPerformed
        // TODO add your handling code here:
        AVLtree.delete();
    }//GEN-LAST:event_jMenuAVLdeleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Trees().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Canvas canvas1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuAVLcreate;
    private javax.swing.JMenuItem jMenuAVLdelete;
    private javax.swing.JMenuItem jMenuAVLinsert;
    private javax.swing.JMenuItem jMenuBSTcreate;
    private javax.swing.JMenuItem jMenuBSTdelete;
    private javax.swing.JMenuItem jMenuBSTin0rder;
    private javax.swing.JMenuItem jMenuBSTinsert;
    private javax.swing.JMenuItem jMenuBSTpost0rder;
    private javax.swing.JMenuItem jMenuBSTpre0rder;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuExit;
    // End of variables declaration//GEN-END:variables
}
