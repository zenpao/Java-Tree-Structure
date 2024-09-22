/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BS.java
 *
 * Created on Sep 25, 2013, 12:05:16 PM
 */

//package XmasTree;

/**
 *
 * @author javier_j
 */
import java.io.*;
import java.util.*;
import javax.swing.*;

public class BS extends javax.swing.JFrame {
    
    /** Creates new form BS */
    public BS() {
        initComponents();
    }

 
class Node
{
    public int item;
    public Node leftChild;
    public Node rightChild;
    public void displayNode()
    {
        System.out.print("[");
        System.out.print(item);
        System.out.print("]");
    }
}
class StackNode
{
    public Node item;
    public StackNode next;
    public StackNode(Node val)
    {
        item = val;
    }
 
}
class LinkedListStack
{
    private StackNode first;
    public LinkedListStack()
    {
        first = null;
    }
    public boolean isEmpty()
    {
        return (first==null);
    }
    public void insert(Node key)//inserts at beginning of list
    {
        StackNode newLLNode = new StackNode(key);
        newLLNode.next = first;
        first = newLLNode;
    }
    public Node delete()//deletes at beginning of list
    {
        StackNode temp = first;
        first = first.next;
        return temp.item;
    }
}
class tack
{
    private LinkedListStack listObj;
    public tack()
    {
        listObj = new LinkedListStack();
    }
    public void push(Node num)
    {
        listObj.insert(num);
    }
    public Node pop()
    {
        return listObj.delete();
    }
    public boolean isEmpty()
    {
        return listObj.isEmpty();
    }
}
 
class Tree
{
    private Node root;
    public Tree()
    { root = null; }
    public Node find(int val)
    {
        Node current = root;
        while(current.item != val)
        {
            if(val < current.item)
                current = current.leftChild;
            else
                current = current.rightChild;
            if(current == null)
                return null;
        }
        return current;
    } 
    public void insert(int id)
    {
        Node newNode = new Node();
        newNode.item = id;
        if(root==null)
            root = newNode;
        else
        {
            Node current = root;
            Node parent;
            while(true)
            {
                parent = current;
                if(id < current.item)
                {
                    current = current.leftChild;
                    if(current == null)
                    {
                        parent.leftChild = newNode;
                        return;
                    }
                } 
                else
                {
                    current = current.rightChild;
                    if(current == null) 
                    {
                        parent.rightChild = newNode;
                        return;
                    }
                } 
            } 
        } 
    } 
    public boolean delete(int val) 
    {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;
        while(current.item != val)
        {
            parent = current;
            if(val < current.item)
            {   
                isLeftChild = true;
                current = current.leftChild;
            }
            else
            {
                isLeftChild = false;
                current = current.rightChild;
            }
            if(current == null)
                return false;
 
        } 
        if(current.leftChild==null && current.rightChild==null)
        {
            if(current == root)
                root = null;
            else if(isLeftChild)
                parent.leftChild = null;
            else
                parent.rightChild = null;
        }
        else if(current.rightChild==null)
        {
            if(current == root)
                root = current.leftChild;
            else if(isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;
        }
        else if(current.leftChild==null)
        {
            if(current == root)
                root = current.rightChild;
            else if(isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;
        }
        else
        {
            Node successor = findSuccessor(current);
            if(current == root)
                root = successor;
            else if(isLeftChild)
                parent.leftChild = successor;
            else
                parent.rightChild = successor;
            successor.leftChild = current.leftChild;
        } 
        return true;
    } 
    private Node findSuccessor(Node delNode)
    {
        Node successorParent = delNode; 
        Node successor = delNode;
        Node current = delNode.rightChild;
        while(current != null)
        {
            successorParent = successor;    
            successor = current;
            current = current.leftChild;
        }
        if(successor != delNode.rightChild)
        {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }
    public void displayTree()
    {
        Stack globalStack = new Stack();
        globalStack.push(root); 
        int emptyLeaf = 32;
        boolean isRowEmpty = false;
        System.out.println("****......................................................****");
        while(isRowEmpty==false)
        {
 
            Stack localStack = new Stack();
            isRowEmpty = true;
            for(int j=0; j<emptyLeaf; j++)
                System.out.print(' ');
            while(globalStack.isEmpty()==false)
            {
                Node temp = (Node) globalStack.pop();
                if(temp != null)
                {
                    System.out.print(temp.item);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);
                    if(temp.leftChild != null ||temp.rightChild != null)
                        isRowEmpty = false;
                }
                else
                {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for(int j=0; j<emptyLeaf*2-2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            emptyLeaf /= 2;
            while(localStack.isEmpty()==false)
                globalStack.push( localStack.pop() );
        }
    System.out.println("****......................................................****");
    } 
} 
Tree theTree=new Tree();
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Create = new javax.swing.JMenuItem();
        Insert = new javax.swing.JMenuItem();
        Delete = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Tree");

        Create.setText("Create");
        Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateActionPerformed(evt);
            }
        });
        jMenu1.add(Create);

        Insert.setText("Insert");
        Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertActionPerformed(evt);
            }
        });
        jMenu1.add(Insert);

        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });
        jMenu1.add(Delete);

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 76, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertActionPerformed
   
    
    int x=Integer.parseInt(JOptionPane.showInputDialog(null, "Enter a value"));
    theTree.insert(x);
    theTree.displayTree();
}//GEN-LAST:event_InsertActionPerformed

private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
int y=Integer.parseInt(JOptionPane.showInputDialog(null, "Enter a value you want to delete"));
        theTree.delete(y);
        theTree.displayTree();
}//GEN-LAST:event_DeleteActionPerformed

private void CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateActionPerformed
JOptionPane.showMessageDialog(null, "Tree is created");
}//GEN-LAST:event_CreateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new BS().setVisible(true);
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Create;
    private javax.swing.JMenuItem Delete;
    private javax.swing.JMenuItem Insert;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
