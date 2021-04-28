package com.revature.util;

public class LinkedList {
    private Node head;
    private Node tail;

    public boolean add(Object T){
        Node iterate = head;
        while(iterate.getNextNode()!=null){
            iterate.setNextNode(iterate.getNextNode());
        }
        iterate.setNextNode(new Node(null, iterate, T));
        tail = iterate.getNextNode();
        return true;
    }

    public boolean isEmpty(){
        return head.getContents()==null;
    }

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

    public Object[] toArray(){
        Object[] returnArr = new Object[size()];
        Node iterate = head;
        for(int i = 0;i<returnArr.length;i++){
            returnArr[i] = iterate.getContents();
            iterate = advance(iterate);
        }
        return returnArr;
    }

    public int size(){
        int size = 0;
        Node iterate = head;
        while(iterate.getNextNode()!=null){
            iterate = advance(iterate);
            size++;
        }
        return size;
    }

    private Node advance(Node temp){
        if(temp.getNextNode()!=null) {
            return new Node(temp.getNextNode().getNextNode(),
                    temp, temp.getNextNode().getContents());
        }else{
            return temp;
        }
    }

//    public boolean remove(Object T){
//
//    }

    public void clear(){
        head = null;
        tail = null;
    }

//    public boolean contains(Object T){
//
//    }

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


}
