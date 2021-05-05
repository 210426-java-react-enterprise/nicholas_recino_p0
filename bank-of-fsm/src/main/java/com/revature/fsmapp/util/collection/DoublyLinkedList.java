package com.revature.fsmapp.util.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* TODO Maybe push through the type ahead of time and use generics? might be to close to copying the
    oem solution
 */
public class DoublyLinkedList<E> implements Collection<E> {
    private Node<E> head;
    private Node<E> tail;

    public DoublyLinkedList(){
        head = null;
        tail = null;
    }
// TODO Implement add Object at specific index, if index is greater than size append to end
// Adds data to end of Linked List
    public boolean add(E data){
        Node iterate = head;
        if (head == null && tail == null) {
            head = tail = new Node(data,null, null);
            return true;
        }

        if (head == tail){
            head.nextNode= (new Node(data,null,head));
            tail = head.nextNode;
            return true;
        }
        ArrayList<String> list;

        while(iterate.nextNode!=null){
            iterate = advance(iterate);
        }
        iterate.nextNode = (new Node(data,null, iterate));
        tail = iterate.nextNode;
        return true;
    }
//  Returns true if head is null.
    public boolean isEmpty(){
        return head==null;
    }
// Gets a specific Object at the index specified
    public E get(int index){
        if (head.data==null){
            return null;
        }
        Node iterate = head;
        for(int i = 0; i<index-1;i++){
            iterate = advance(iterate);
        }
        return (E) iterate.data;
    }
// Modifies the List into a static array the size of the LinkedList
    public E[] toArray(){
        Object[] returnArr = new Object[size()];
        Node iterate = head;
        for(int i = 0;i<returnArr.length;i++){
            returnArr[i] = iterate.data;
            iterate = advance(iterate);
        }
        return (E[])returnArr;
    }
    // Returns a SubList LinkedList from fromIndex to toIndex
    public DoublyLinkedList subList(int fromIndex, int toIndex){
        DoublyLinkedList subList = new DoublyLinkedList();
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
        while(iterate.nextNode!=null){
            iterate = advance(iterate);
            size++;
        }
        return size;
    }
// Advances through the LinkedList and returns the Next Node in line, if null returns the current node
    private Node advance(Node temp){
        if(temp.nextNode!=null) {
            return new Node(temp.nextNode.nextNode,
                    temp, temp.nextNode.prevNode);
        }else{
            return temp;
        }
    }
// Removes a Generic Object from the LinkedList returns true if successful
    public boolean remove(Object T){

        if (head == null && tail == null){
            return false;
        }else if(head==tail){
            if(!head.data.equals(T)){
                return false;
            }else{
                // As both are equal to each other and the contents are equivalent to T, reuse code instead;
                clear();
            }
        }else{
            Node iterate = head;
            while(iterate.nextNode!=null){
                if(iterate.data.equals(T)){
                    // Iterate holds a previous node as well as the next due to being doubly linked;
                    iterate.prevNode = iterate.nextNode;
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

    private static class Node<T>{
        T data;
        Node<T> nextNode;
        Node<T> prevNode;

        Node(T data){
            this.data = data;
        }
        Node(T data, Node<T> nextNode, Node<T> prevNode){
            this.data = data;
            this.nextNode = nextNode;
            this.prevNode = prevNode;
        }
    }
}
