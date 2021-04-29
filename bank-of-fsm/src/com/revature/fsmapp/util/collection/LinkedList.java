package com.revature.fsmapp.util.collection;

import java.util.Collection;
import java.util.Iterator;

/* TODO Maybe push through the type ahead of time and use generics? might be to close to copying the
    oem solution
 */
public class LinkedList implements Collection {
    private Node head;
    private Node tail;

    public LinkedList(){
        head = null;
        tail = null;
    }
// TODO Implement add Object at specific index, if index is greater than size append to end
// Adds Object T to end of Linked List
    public boolean add(Object T){
        Node iterate = head;
        if (head == null && tail == null) {
            head = tail = new Node(null, null, T);
            return true;
        }

        if (head == tail){
            head.setNextNode(new Node(null,head,T));
            tail = head.getNextNode();
            return true;
        }

        while(iterate.getNextNode()!=null){
            iterate = advance(iterate);
        }
        iterate.setNextNode(new Node(null, iterate, T));
        tail = iterate.getNextNode();
        return true;
    }
//  Returns true if head is null.
    public boolean isEmpty(){
        return head==null;
    }
// Gets a specific Object at the index specified
    public Object get(int index){
        if (head.getContents()==null){
            return null;
        }
        Node iterate = head;
        for(int i = 0; i<index-1;i++){
            iterate = advance(iterate);
        }
        return iterate.getContents();
    }
// Modifies the List into a static array the size of the LinkedList
    public Object[] toArray(){
        Object[] returnArr = new Object[size()];
        Node iterate = head;
        for(int i = 0;i<returnArr.length;i++){
            returnArr[i] = iterate.getContents();
            iterate = advance(iterate);
        }
        return returnArr;
    }
    // Returns a SubList LinkedList from fromIndex to toIndex
    public LinkedList subList(int fromIndex, int toIndex){
        LinkedList subList = new LinkedList();
        if(toIndex>size())
            toIndex=size();
        if(fromIndex<0)
            fromIndex = 0;
        else if(fromIndex>toIndex)
            fromIndex = toIndex;
        for(int i= fromIndex; i< toIndex; i++){
            subList.add(get(i));
        }
        return subList;
    }
    // Generates the size of the list
    public int size(){
        int size = 0;
        Node iterate = head;
        while(iterate.getNextNode()!=null){
            iterate = advance(iterate);
            size++;
        }
        return size;
    }
// Advances through the LinkedList and returns the Next Node in line, if null returns the current node
    private Node advance(Node temp){
        if(temp.getNextNode()!=null) {
            return new Node(temp.getNextNode().getNextNode(),
                    temp, temp.getNextNode().getContents());
        }else{
            return temp;
        }
    }
// Removes a Generic Object from the LinkedList returns true if successful
    public boolean remove(Object T){

        if (head == null && tail == null){
            return false;
        }else if(head==tail){
            if(!head.getContents().equals(T)){
                return false;
            }else{
                // As both are equal to each other and the contents are equivalent to T, reuse code instead;
                clear();
            }
        }else{
            Node iterate = head;
            while(iterate.getNextNode()!=null){
                if(iterate.getContents().equals(T)){
                    // Iterate holds a previous node as well as the next due to being doubly linked;
                    iterate.getPrevNode().setNextNode(iterate.getNextNode());
                    return true;
                }else{
                    iterate = advance(iterate);
                }
            }
        }
        // If falling through to this then it is assumed that Object T is not present in the LinkedList
        // And therefore the remove operation failed
        return false;
    }
//  Clears out the LinkedList
    public void clear(){
        head = tail = null;
    }
//  TODO Search for object in a LinkedList
    public boolean contains(Object T){
        return true;
    }


//  Not Really Needed Deprecate
    @Override
    public boolean addAll(Collection c) {
        return false;
    }
//  Not Really Needed Deprecate
    @Override
    public boolean retainAll(Collection c) {
        return false;
    }
// Not Really Needed Deprecate
    @Override
    public boolean removeAll(Collection c) {
        return false;
    }
//  Not Really Needed Deprecate
    @Override
    public boolean containsAll(Collection c) {
        return false;
    }
//  Not Really Needed Deprecate
    @Override
    public Iterator iterator() {
        return null;
    }
//  Not Really Needed Deprecate
    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
