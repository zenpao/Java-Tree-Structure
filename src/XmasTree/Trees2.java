/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Trees2.java
 *
 * Created on Sep 25, 2013, 11:57:55 AM
 */
//package XmasTree;

/**
 *
 * @author javier_je
 */

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.lang.StringBuilder;
import javax.swing.JOptionPane;
import javax.swing.event.*;
import javax.swing.text.*;

public class Trees2 extends javax.swing.JFrame {

    /** Creates new form Trees2 */
    public Trees2() {
        initComponents();
    }
    
class Node
{
    public int item;
    public Node leftChild;
    public Node rightChild;
    public void displayNode()
    {
        StringBuilder sbinitial2= new StringBuilder(""+item);
        jTextArea1.append("[");
        jTextArea1.append(""+sbinitial2);
        jTextArea1.append("]");
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
    public Node returnRoot()
    {
        return root;
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
                else if(id == current.item){
                    JOptionPane.showMessageDialog(null, "Error: Duplicated value.");
                    return;
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
    
    public void preOrder(Node Root)
    {
        if(Root != null)
        {  
            jTextArea1.append(Root.item + " ");
            System.out.print(Root.item + " ");
            preOrder(Root.leftChild);
            preOrder(Root.rightChild);
        }
    }
    public void inOrder(Node Root)
    {
        if(Root != null)
        {
            inOrder(Root.leftChild);
            jTextArea1.append(Root.item + " ");
            System.out.print(Root.item + " ");
            inOrder(Root.rightChild);
        }
    }
    public void postOrder(Node Root)
    {
        if(Root != null)
        {
            postOrder(Root.leftChild);
            postOrder(Root.rightChild);
            jTextArea1.append(Root.item + " ");
            System.out.print(Root.item + " ");
        }
    }
    
    public void displayTree()
    {
        Stack globalStack = new Stack();
        globalStack.push(root); 
        int emptyLeaf = 32;
        boolean isRowEmpty = false;
        //StringBuilder sb = new StringBuilder("****......................................................****");
        //jTextArea1.append(""+sb);
        //jTextArea1.setText("****......................................................****");
        System.out.println("****......................................................****");
        while(isRowEmpty==false)
        {
 
            Stack localStack = new Stack();
            isRowEmpty = true;
            char a = ' ';
            StringBuilder sba = new StringBuilder(""+a);
                jTextArea1.append("\n");
            for(int j=0; j<emptyLeaf; j++)
                jTextArea1.append(""+sba);
                System.out.print(' ');
            while(globalStack.isEmpty()==false)
            {
                Node temp = (Node) globalStack.pop();
                if(temp != null)
                {
                    StringBuilder sb2 = new StringBuilder(""+temp.item);
                    jTextArea1.append(""+sb2);
                    System.out.print(temp.item);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);
                    if(temp.leftChild != null ||temp.rightChild != null)
                        isRowEmpty = false;
                }
                else
                {
                    StringBuilder sb3 = new StringBuilder("--");
                    jTextArea1.append(""+sb3);
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                char b = ' ';
                StringBuilder sbb = new StringBuilder(""+b);
                for(int j=0; j<emptyLeaf*2-2; j++)
                    jTextArea1.append(""+sbb);
                    System.out.print(' ');
            }
            StringBuilder sbc = new StringBuilder("\n");
            jTextArea1.append(""+sbc);
            System.out.println();
            emptyLeaf /= 2;
            while(localStack.isEmpty()==false)
                globalStack.push( localStack.pop() );
        }
        //StringBuilder sb4 = new StringBuilder("****......................................................****");
        jTextArea1.append("****......................................................****");
    System.out.println("****......................................................****");
    } 
} 
Tree theTree=new Tree();

class TreeTraversal
{
    public void main(String[] args) throws IOException
    {
        StringBuilder order1 = new StringBuilder("Inorder traversal: ");
        jTextArea1.append("\n"+order1);
        System.out.println("Inorder traversal: ");
        theTree.inOrder(theTree.returnRoot());
        System.out.println(" ");
 
        StringBuilder order2 = new StringBuilder("Preorder traversal: ");
        jTextArea1.append("\n"+order2);
        System.out.println("Preorder traversal: ");
        theTree.preOrder(theTree.returnRoot());
        System.out.println(" ");
            
        StringBuilder order3 = new StringBuilder("Postorder traversal: ");
        jTextArea1.append("\n"+order3);
        System.out.println("Postorder traversal: ");
        theTree.postOrder(theTree.returnRoot());
        System.out.println(" ");
    }
}

class AVLNode{
	AVLNode lChild, rChild, parent; //pointers to the children and the parent of the node
	Comparable key; //The sortable key
	Object data; //the node data
	int height; //The height of the node is kept to avoid getting recalculation on every insert
	AVLTree tree; //A pointer to the AVLTree is needed for changing the tree root
	
	//constructs a node with no parent (the tree root)
	AVLNode( Comparable newKey, Object newData, AVLTree t ){
		key = newKey;
		data = newData;
		lChild = null;
		rChild = null;
		parent = null;
		height = 0;
		tree = t;
	}
	
	//Constructs a node with a parent
	AVLNode( Comparable newKey, Object newData, AVLNode p, AVLTree t ){
		key = newKey;
		data = newData;
		lChild = null;
		rChild = null;
		parent = p;
		tree = t;
	}
	
	//Inserts a new mapping from key to data, keeping the AVL structure
	void insert( Comparable newKey, Object newData ){
		if( key.compareTo( newKey ) > 0 ){
			if( lChild == null )
				lChild = new AVLNode( newKey, newData, this, tree );
			else
				lChild.insert( newKey, newData );
		}
		else{
			if( rChild == null )
				rChild = new AVLNode( newKey, newData, this, tree );
			else
				rChild.insert( newKey, newData );
		}
		
		//The new node has been inserted - now check that the AVL property has not been violated
		checkAVL();
	}
	
	//Searches for a data given a key
	Object search( Comparable searchKey ){
		int res = key.compareTo( searchKey );
		if( res == 0 )
			return data;
		if( ( res > 0 ) && ( lChild != null ) )
			return lChild.search( searchKey );
		if( ( res < 0 ) && ( rChild != null ) )
			return rChild.search( searchKey );
		return null;
	}
	
	//Checks whether the AVL property is maintained
	void checkAVL(){
		int lHeight = -1, rHeight = -1;
		if( lChild != null )
			lHeight = lChild.height;
		if( rChild != null )
			rHeight = rChild.height;
		
		if( Math.abs( lHeight - rHeight ) <= 1 ) //The AVL property holds
			height = Math.max( lHeight, rHeight ) + 1;
		else //The AVL property has been violated - a fix is needed
			fixAVL( lHeight, rHeight );
	}
	
	/*Fixes the AVL node by rotating it as needed
	  The code handles four cases outlined below:
	    /            /           \            \
	   /             \            \           /
	   1             2           3             4
	   where 1 and 3 require a single rotation while 2 and 4 require a double rotation.
	   case 1 and 3, and case 2 and 4 are mirroring of each other, so the code for fixing them
	   is the same switching between lChild and rChild.
	*/
	void fixAVL( int lHeight, int rHeight ){
                StringBuilder ss=new StringBuilder("fixAVL found an unbalanced node: " + toString());
		jTextArea2.append(""+ss );
		
		if( lHeight > rHeight ){ //The balance tips to the left
			if( getHeight( lChild.lChild ) > getHeight( lChild.rChild ) ){ //case 1
				AVLNode nTmp = null;
				nTmp = lChild.rChild;
				
				//rotate the needed nodes
				if( parent != null )
					parent.replaceChild( this, lChild );
				else{
					tree.root = lChild;
					lChild.parent = null;
				}
				lChild.replaceChild( lChild.rChild, this );
				replaceChild( lChild, nTmp );
				
				//fix the height
				height = rHeight + 1;
			}
			else if( getHeight( lChild.lChild ) < getHeight( lChild.rChild ) ){ //case 2
				AVLNode nLeftTmp = null, nRightTmp = null;
				AVLNode nMedian = lChild.rChild;
				nLeftTmp = nMedian.lChild;
				nRightTmp = nMedian.rChild;
				
				//rotate the needed nodes
				if( parent != null )
					parent.replaceChild( this, nMedian );
				else{
					tree.root = nMedian;
					nMedian.parent = null;
				}
				nMedian.replaceChild( nMedian.lChild, lChild );
				nMedian.replaceChild( nMedian.rChild, this );
				
				lChild.replaceChild( nMedian, nLeftTmp );
				replaceChild( lChild, nRightTmp );
				
				//fix the height
				height = rHeight + 1;
				nMedian.height = height + 1;
				nMedian.lChild.height -= 1;
			}
		}
		if( rHeight > lHeight ){
			if( getHeight( rChild.rChild ) > getHeight( rChild.lChild ) ){ //case 3
				AVLNode nTmp = null;
				nTmp = rChild.lChild;
				
				//rotate the needed nodes
				if( parent != null )
					parent.replaceChild( this, rChild );
				else{
					tree.root = rChild;
					rChild.parent = null;
				}
				rChild.replaceChild( rChild.lChild, this );
				replaceChild( rChild, nTmp );
				
				//fix the height
				height = lHeight + 1;
			}
			else if( getHeight( rChild.rChild ) < getHeight( rChild.lChild ) ){ //case 4
				AVLNode nLeftTmp = null, nRightTmp = null;
				AVLNode nMedian = rChild.lChild;
				nLeftTmp = nMedian.lChild;
				nRightTmp = nMedian.rChild;
				
				//rotate the needed nodes
				if( parent != null )
					parent.replaceChild( this, nMedian );
				else{
					tree.root = nMedian;
					nMedian.parent = null;
				}
				nMedian.replaceChild( nMedian.lChild, this );
				nMedian.replaceChild( nMedian.rChild, rChild );
				
				rChild.replaceChild( nMedian, nLeftTmp );
				replaceChild( rChild, nRightTmp );
				
				//fix the height
				height = lHeight + 1;
				nMedian.height = height + 1;
				nMedian.rChild.height -= 1;
			}
		}
	}	
	
	//We use this function to avoid checking for null parameters repeatedly
	int getHeight( AVLNode n ){
		if( n == null )
			return -1;
		return n.height;
	}
	
	//The copy function copies the content (key and data) of the node n into the current node
	void copy( AVLNode n ){
		key = n.key;
		data = n.data;
	}
	
	//Replaces the child curChild with newChild
	void replaceChild( AVLNode curChild, AVLNode newChild ){
            StringBuilder s= new StringBuilder("In node: " + toString() + " replacing " + curChild + " with " + newChild);
		jTextArea2.append(""+s);
		if( lChild == curChild )
			lChild = newChild;
		else if( rChild == curChild )
			rChild = newChild;
		if( newChild != null )
			newChild.parent = this;
	}
	
	//prints a snapshot of the tree
	void printTree( int indent ){
		if( rChild != null )
			rChild.printTree( indent + 1 );
		String sOutput = "";
		for( int i = 0 ; i < indent ; i++ )
			sOutput += "\t";
		sOutput += toString();
		System.out.println( sOutput );
		if( lChild != null )
			lChild.printTree( indent + 1 );
	}
	
	//the string representation of the node
	public String toString(){
		return key + "->" + data;
	}	
}

class AVLTree{
	AVLNode root;
	
	AVLTree(){
		root = null;
	}
	
	//Inserts a new mapping key to data into the tree
	void insert( Comparable key, Object data ){
		if( root == null )
			root = new AVLNode( key, data, this );
		else
			root.insert( key, data );
	}
	
	//Searchs for data associated with a certain key 
	Object search( Comparable key ){
		if( root == null )
			return null;
		else
			return root.search( key );
	}
	
	//prints a snapshot of the tree
	void printTree(){
		if( root == null )
			JOptionPane.showMessageDialog(null,"The tree is empty" );
		else
			root.printTree( 0 );
                StringBuilder sss=new StringBuilder("\n===================================================\n");
		jTextArea2.append(""+sss);
	}	
}
    
public void about() {
        JOptionPane.showMessageDialog(null, "Jentzen Paolo A. Javier\n\nTrees #1 & 2\n\nDATSTC1-IDF 2013");
    }

 public void thanks() {
        JOptionPane.showMessageDialog(null, "Thanks!");
    }
     
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButtonBSTinsert = new javax.swing.JButton();
        jButtonBSTcreate = new javax.swing.JButton();
        jButtonBSTdelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButtonBSTdisplay = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButtonAVLcreate = new javax.swing.JButton();
        jButtonAVLinsert = new javax.swing.JButton();
        jButtonAVLdelete = new javax.swing.JButton();
        jButtonAVLdisplay = new javax.swing.JButton();
        jButtonClear2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuBSTcreate = new javax.swing.JMenuItem();
        jMenuBSTinsert = new javax.swing.JMenuItem();
        jMenuBSTdelete = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuAVLcreate = new javax.swing.JMenuItem();
        jMenuAVLinsert = new javax.swing.JMenuItem();
        jMenuAVLdelete = new javax.swing.JMenuItem();
        jMenuBSTdisplay = new javax.swing.JMenuItem();
        jMenuClearAll = new javax.swing.JMenuItem();
        jMenuItemAbout = new javax.swing.JMenuItem();
        jMenuRate = new javax.swing.JMenuItem();
        jMenuExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trees");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(jTextArea1);

        jButtonBSTinsert.setText("Insert");
        jButtonBSTinsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBSTinsertActionPerformed(evt);
            }
        });

        jButtonBSTcreate.setText("Create");
        jButtonBSTcreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBSTcreateActionPerformed(evt);
            }
        });

        jButtonBSTdelete.setText("Delete");
        jButtonBSTdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBSTdeleteActionPerformed(evt);
            }
        });

        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonBSTdisplay.setText("Display");
        jButtonBSTdisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBSTdisplayActionPerformed(evt);
            }
        });

        jButtonClear.setText("Clear");
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(jTextArea2);

        jButtonAVLcreate.setText("Create");
        jButtonAVLcreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAVLcreateActionPerformed(evt);
            }
        });

        jButtonAVLinsert.setText("Insert");
        jButtonAVLinsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAVLinsertActionPerformed(evt);
            }
        });

        jButtonAVLdelete.setText("Delete");
        jButtonAVLdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAVLdeleteActionPerformed(evt);
            }
        });

        jButtonAVLdisplay.setText("Display");
        jButtonAVLdisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAVLdisplayActionPerformed(evt);
            }
        });

        jButtonClear2.setText("Clear");
        jButtonClear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClear2ActionPerformed(evt);
            }
        });

        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("B.S.T");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("A.V.L");

        jMenu1.setText("Menu");

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

        jMenu1.add(jMenu2);

        jMenu4.setText("A.V.L");

        jMenuAVLcreate.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuAVLcreate.setText("Create");
        jMenuAVLcreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAVLcreateActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuAVLcreate);

        jMenuAVLinsert.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuAVLinsert.setText("Insert");
        jMenuAVLinsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAVLinsertActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuAVLinsert);

        jMenuAVLdelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuAVLdelete.setText("Delete");
        jMenuAVLdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAVLdeleteActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuAVLdelete);

        jMenu1.add(jMenu4);

        jMenuBSTdisplay.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.ALT_MASK));
        jMenuBSTdisplay.setText("Display");
        jMenuBSTdisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuBSTdisplayActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuBSTdisplay);

        jMenuClearAll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, java.awt.event.InputEvent.ALT_MASK));
        jMenuClearAll.setText("Clear All");
        jMenuClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuClearAllActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuClearAll);

        jMenuItemAbout.setText("About");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemAbout);

        jMenuRate.setText("Rate");
        jMenuRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuRateActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuRate);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel3)
                            .addGap(88, 88, 88)
                            .addComponent(jButtonBSTcreate)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonBSTinsert)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonBSTdelete)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonBSTdisplay)
                            .addGap(8, 8, 8))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(365, Short.MAX_VALUE)
                        .addComponent(jButtonClear))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(365, Short.MAX_VALUE)
                        .addComponent(jButtonClear2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4)
                                .addGap(86, 86, 86)
                                .addComponent(jButtonAVLcreate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAVLinsert)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAVLdelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAVLdisplay))
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonBSTinsert)
                        .addComponent(jButtonBSTdelete)
                        .addComponent(jButtonBSTdisplay)
                        .addComponent(jButtonBSTcreate))
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonClear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAVLcreate)
                    .addComponent(jButtonAVLinsert)
                    .addComponent(jButtonAVLdelete)
                    .addComponent(jButtonAVLdisplay)
                    .addComponent(jLabel4))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonClear2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jMenuBSTcreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuBSTcreateActionPerformed
// TODO add your handling code here:
    JOptionPane.showMessageDialog(null, "New Tree created.");
}//GEN-LAST:event_jMenuBSTcreateActionPerformed

private void jMenuBSTinsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuBSTinsertActionPerformed
// TODO add your handling code here:
    final double startTime = System.nanoTime();
    int value;
    int[] elements = new int[10];
    int x = Integer.parseInt(JOptionPane.showInputDialog(null, "Input value: "));
    theTree.insert(x);
    theTree.displayTree();
 
        StringBuilder order1 = new StringBuilder("Inorder traversal: ");
        jTextArea1.append("\n"+order1);
        System.out.println("Inorder traversal: ");
        theTree.inOrder(theTree.returnRoot());
        System.out.println(" ");
 
        StringBuilder order2 = new StringBuilder("Preorder traversal: ");
        jTextArea1.append("\n"+order2);
        System.out.println("Preorder traversal: ");
        theTree.preOrder(theTree.returnRoot());
        System.out.println(" ");
            
        StringBuilder order3 = new StringBuilder("Postorder traversal: ");
        jTextArea1.append("\n"+order3);
        System.out.println("Postorder traversal: ");
        theTree.postOrder(theTree.returnRoot());
        System.out.println(" ");
        
        final double duration = System.nanoTime() - startTime;
        double ans = Double.parseDouble(""+duration);
         
         jLabel1.setText("System.nanoTime: " + ans);
        
        //JOptionPane.showMessageDialog(null, "Inorder traversal" + theTree.inOrder(theTree.returnRoot()));
}//GEN-LAST:event_jMenuBSTinsertActionPerformed

private void jMenuBSTdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuBSTdeleteActionPerformed
// TODO add your handling code here:
    final double startTime = System.nanoTime();
    int y = Integer.parseInt(JOptionPane.showInputDialog(null, "Input # to delete: "));
        theTree.delete(y);
        theTree.displayTree();
        
        StringBuilder order1 = new StringBuilder("Inorder traversal: ");
        jTextArea1.append("\n"+order1);
        System.out.println("Inorder traversal: ");
        theTree.inOrder(theTree.returnRoot());
        System.out.println(" ");
 
        StringBuilder order2 = new StringBuilder("Preorder traversal: ");
        jTextArea1.append("\n"+order2);
        System.out.println("Preorder traversal: ");
        theTree.preOrder(theTree.returnRoot());
        System.out.println(" ");
            
        StringBuilder order3 = new StringBuilder("Postorder traversal: ");
        jTextArea1.append("\n"+order3);
        System.out.println("Postorder traversal: ");
        theTree.postOrder(theTree.returnRoot());
        System.out.println(" ");
        
        final double duration = System.nanoTime() - startTime;
        double ans = Double.parseDouble(""+duration);
         
         jLabel1.setText("System.nanoTime: " + ans);
}//GEN-LAST:event_jMenuBSTdeleteActionPerformed

private void jMenuAVLcreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAVLcreateActionPerformed
// TODO add your handling code here:
    JOptionPane.showMessageDialog(null, "New Tree created.");
}//GEN-LAST:event_jMenuAVLcreateActionPerformed

private void jMenuAVLinsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAVLinsertActionPerformed
// TODO add your handling code here:
     final double startTime = System.nanoTime();
    Integer key;
		Object data;
		int i=0;
		char[] buffer = new char[2];
		buffer[1] = '\0';
                
		
		AVLTree bst = new AVLTree();
		LinkedList llKeys = new LinkedList();
                while(i!= 9){
                do{
                            int w;
                            
                            w= Integer.parseInt(JOptionPane.showInputDialog(null,"Input value: "));
                            
				key = new Integer( w );
				data = bst.search( key );
			}while( data != null );
			buffer[0] = (char)( 992 + i );
			data = new String( buffer );
                        StringBuilder p=new StringBuilder("Inserting " + key + " , " + data);
			jTextArea2.append(""+p);
			bst.insert( key, data );
			bst.printTree();
			llKeys.add( key );
                        i++;
                        
                final double duration = System.nanoTime() - startTime;
                double ans = Double.parseDouble(""+duration);
         
         jLabel2.setText("System.nanoTime: " + ans);
                }
                
}//GEN-LAST:event_jMenuAVLinsertActionPerformed

private void jMenuAVLdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAVLdeleteActionPerformed
// TODO add your handling code here:
    //EXTRA!
}//GEN-LAST:event_jMenuAVLdeleteActionPerformed

private void jMenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuExitActionPerformed
// TODO add your handling code here:
    String[] choices = {"Yes", "No"};
    int ex = JOptionPane.showOptionDialog(null, "Do you want to quit?", null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, choices, choices);
    if (ex == 0) {
        System.exit(0);
    }
}//GEN-LAST:event_jMenuExitActionPerformed

private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
// TODO add your handling code here:
    about();
}//GEN-LAST:event_jMenuItemAboutActionPerformed

private void jMenuRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuRateActionPerformed
// TODO add your handling code here:
    String[] choices = {"Yes", "No"};
    int ex = JOptionPane.showOptionDialog(null, "Do you like the program?", null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, choices, choices);
    if (ex == 0) {
        thanks();
        System.exit(0);
    } else {
        System.exit(0);
    }
}//GEN-LAST:event_jMenuRateActionPerformed

private void jMenuBSTdisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuBSTdisplayActionPerformed
// TODO add your handling code here:
    final double startTime = System.nanoTime();

    jTextArea1.setText(null);
    theTree.displayTree();
    
    StringBuilder order1 = new StringBuilder("Inorder traversal: ");
        jTextArea1.append("\n"+order1);
        System.out.println("Inorder traversal: ");
        theTree.inOrder(theTree.returnRoot());
        System.out.println(" ");
 
        StringBuilder order2 = new StringBuilder("Preorder traversal: ");
        jTextArea1.append("\n"+order2);
        System.out.println("Preorder traversal: ");
        theTree.preOrder(theTree.returnRoot());
        System.out.println(" ");
            
        StringBuilder order3 = new StringBuilder("Postorder traversal: ");
        jTextArea1.append("\n"+order3);
        System.out.println("Postorder traversal: ");
        theTree.postOrder(theTree.returnRoot());
        System.out.println(" ");
        
        final double duration = System.nanoTime() - startTime;
        double ans = Double.parseDouble(""+duration);
         
         jLabel1.setText("System.nanoTime: " + ans);
}//GEN-LAST:event_jMenuBSTdisplayActionPerformed

private void jButtonBSTinsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBSTinsertActionPerformed
// TODO add your handling code here:
    final double startTime = System.nanoTime();
    int value;
    int[] elements = new int[10];
    int x = Integer.parseInt(JOptionPane.showInputDialog(null, "Input value: "));
    theTree.insert(x);
    theTree.displayTree();
 
        StringBuilder order1 = new StringBuilder("Inorder traversal: ");
        jTextArea1.append("\n"+order1);
        System.out.println("Inorder traversal: ");
        theTree.inOrder(theTree.returnRoot());
        System.out.println(" ");
 
        StringBuilder order2 = new StringBuilder("Preorder traversal: ");
        jTextArea1.append("\n"+order2);
        System.out.println("Preorder traversal: ");
        theTree.preOrder(theTree.returnRoot());
        System.out.println(" ");
            
        StringBuilder order3 = new StringBuilder("Postorder traversal: ");
        jTextArea1.append("\n"+order3);
        System.out.println("Postorder traversal: ");
        theTree.postOrder(theTree.returnRoot());
        System.out.println(" ");
        
        final double duration = System.nanoTime() - startTime;
        double ans = Double.parseDouble(""+duration);
         
         jLabel1.setText("System.nanoTime: " + ans);
}//GEN-LAST:event_jButtonBSTinsertActionPerformed

private void jButtonBSTcreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBSTcreateActionPerformed
// TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "New Tree created.");
}//GEN-LAST:event_jButtonBSTcreateActionPerformed

private void jButtonBSTdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBSTdeleteActionPerformed
// TODO add your handling code here:
        final double startTime = System.nanoTime();
        int y = Integer.parseInt(JOptionPane.showInputDialog(null, "Input # to delete: "));
        theTree.delete(y);
        theTree.displayTree();
        
        StringBuilder order1 = new StringBuilder("Inorder traversal: ");
        jTextArea1.append("\n"+order1);
        System.out.println("Inorder traversal: ");
        theTree.inOrder(theTree.returnRoot());
        System.out.println(" ");
 
        StringBuilder order2 = new StringBuilder("Preorder traversal: ");
        jTextArea1.append("\n"+order2);
        System.out.println("Preorder traversal: ");
        theTree.preOrder(theTree.returnRoot());
        System.out.println(" ");
            
        StringBuilder order3 = new StringBuilder("Postorder traversal: ");
        jTextArea1.append("\n"+order3);
        System.out.println("Postorder traversal: ");
        theTree.postOrder(theTree.returnRoot());
        System.out.println(" ");
        
        final double duration = System.nanoTime() - startTime;
        double ans = Double.parseDouble(""+duration);
         
         jLabel1.setText("System.nanoTime: " + ans);
}//GEN-LAST:event_jButtonBSTdeleteActionPerformed

private void jButtonBSTdisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBSTdisplayActionPerformed
    // TODO add your handling code here:
          final double startTime = System.nanoTime();
          
    jTextArea1.setText(null);
    theTree.displayTree();
    
    StringBuilder order1 = new StringBuilder("Inorder traversal: ");
        jTextArea1.append("\n"+order1);
        System.out.println("Inorder traversal: ");
        theTree.inOrder(theTree.returnRoot());
        System.out.println(" ");
 
        StringBuilder order2 = new StringBuilder("Preorder traversal: ");
        jTextArea1.append("\n"+order2);
        System.out.println("Preorder traversal: ");
        theTree.preOrder(theTree.returnRoot());
        System.out.println(" ");
            
        StringBuilder order3 = new StringBuilder("Postorder traversal: ");
        jTextArea1.append("\n"+order3);
        System.out.println("Postorder traversal: ");
        theTree.postOrder(theTree.returnRoot());
        System.out.println(" ");
 
        final double duration = System.nanoTime() - startTime;
        double ans = Double.parseDouble(""+duration);
         
         jLabel1.setText("System.nanoTime: " + ans);
}//GEN-LAST:event_jButtonBSTdisplayActionPerformed

private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
    // TODO add your handling code here:
    jTextArea1.setText(null);
    jLabel1.setText(null);
}//GEN-LAST:event_jButtonClearActionPerformed

private void jMenuClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuClearAllActionPerformed
    // TODO add your handling code here:
    jTextArea1.setText(null);
    jLabel1.setText(null);
    jTextArea2.setText(null);
    jLabel2.setText(null);
}//GEN-LAST:event_jMenuClearAllActionPerformed

private void jButtonAVLcreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAVLcreateActionPerformed
// TODO add your handling code here:
    JOptionPane.showMessageDialog(null, "New Tree created.");
}//GEN-LAST:event_jButtonAVLcreateActionPerformed

private void jButtonAVLinsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAVLinsertActionPerformed
// TODO add your handling code here:
     final double startTime = System.nanoTime();
    Integer key;
		Object data;
		int i=0;
		char[] buffer = new char[2];
		buffer[1] = '\0';
                
		
		AVLTree bst = new AVLTree();
		LinkedList llKeys = new LinkedList();
                while(i!= 9){
                do{
                            int w;
                            
                            w= Integer.parseInt(JOptionPane.showInputDialog(null,"Input value: "));
                            
				key = new Integer( w );
				data = bst.search( key );
			}while( data != null );
			buffer[0] = (char)( 992 + i );
			data = new String( buffer );
                        StringBuilder p=new StringBuilder("Inserting " + key + " , " + data);
			jTextArea2.append(""+p);
			bst.insert( key, data );
			bst.printTree();
			llKeys.add( key );
                        i++;
                        
                final double duration = System.nanoTime() - startTime;
                double ans = Double.parseDouble(""+duration);
         
         jLabel2.setText("System.nanoTime: " + ans);
                }
}//GEN-LAST:event_jButtonAVLinsertActionPerformed

private void jButtonAVLdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAVLdeleteActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jButtonAVLdeleteActionPerformed

private void jButtonAVLdisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAVLdisplayActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jButtonAVLdisplayActionPerformed

private void jButtonClear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClear2ActionPerformed
// TODO add your handling code here:
    jTextArea2.setText(null);
    jLabel2.setText(null);
}//GEN-LAST:event_jButtonClear2ActionPerformed

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
            java.util.logging.Logger.getLogger(Trees2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Trees2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Trees2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Trees2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Trees2().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAVLcreate;
    private javax.swing.JButton jButtonAVLdelete;
    private javax.swing.JButton jButtonAVLdisplay;
    private javax.swing.JButton jButtonAVLinsert;
    private javax.swing.JButton jButtonBSTcreate;
    private javax.swing.JButton jButtonBSTdelete;
    private javax.swing.JButton jButtonBSTdisplay;
    private javax.swing.JButton jButtonBSTinsert;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonClear2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuAVLcreate;
    private javax.swing.JMenuItem jMenuAVLdelete;
    private javax.swing.JMenuItem jMenuAVLinsert;
    private javax.swing.JMenuItem jMenuBSTcreate;
    private javax.swing.JMenuItem jMenuBSTdelete;
    private javax.swing.JMenuItem jMenuBSTdisplay;
    private javax.swing.JMenuItem jMenuBSTinsert;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuClearAll;
    private javax.swing.JMenuItem jMenuExit;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuRate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
