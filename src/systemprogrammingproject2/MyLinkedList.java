/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package systemprogrammingproject2;

/**
 *
 * @author hagam
 */
public class MyLinkedList {
    
    Node head = new Node();
    
    
    public int size(){
      Node temp = head;
      int count = 0;
      
      while(temp.next != null){
          count++;
          temp = temp.next;
      }
       return count; 
    }
    
    public void display(){
        Node temp = head;
        while(temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
    
    public void insert(String data){
        Node temp = head;
        Node newNode = new Node();
        newNode.data = data;
        newNode.next = null;
        
        if(head == null){
            head.next = newNode;
        }
        else{
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = newNode;
        }        
    }
    
    public void insertAtBeg(String data){
        Node newNode = new Node();
        newNode.data = data;
        newNode.next = head;
        head = newNode;
    }
    
    public void insertAtPos(String data, int pos){
        
        if(pos == 1){
            insertAtBeg(data);
        }
        else if(pos == this.size()){
            insert(data);
        }
        else if( pos <1 || pos > this.size()){
            System.out.println("Invalid Position");
        }
        else{
            Node temp = head;
            Node newNode = new Node();
            newNode.data = data;
            for(int i = 1; i< pos-1; i++){
                temp = temp.next;
            }
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }
    
    public void remove(){
        Node temp = head;
        while(temp.next.next != null){
            temp = temp.next;
        }
        temp.next = null;
    }
    
    public void removeAtBeg(){
        Node temp = head;
        head = head.next;
        temp.next = null;
    }
    
    public void removeAtPos(int pos){
        if(pos == 1){
            removeAtBeg();
        }
        else if(pos == this.size()){
            remove();
        }
        else if(pos < 1 || pos > this.size()){
            System.out.println("Invalid Position");
        }
        else{
            Node temp = head;
            for(int i = 1; i< pos-1 ; i++){
                temp = temp.next;
            }
            temp.next = temp.next.next;
        }
    }
    
    public String get(int pos){
        Node temp = head;
        if(pos < 1 || pos > this.size()){
            System.out.println("Invalid Index");
            return null;
        }
        else{
            
            for(int i = 0; i < pos-1; i++){
                temp = temp.next;
            }
            return temp.data;
        }
        
    }
    public String getNextNodeData(String item){
        Node temp = head;
        while(temp != null){
            if(temp.data.equals(item)){
                return temp.next.data;
            }
            temp = temp.next;
        }
        return null;
    }
}
