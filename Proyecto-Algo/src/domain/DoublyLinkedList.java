/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author User
 */
public class DoublyLinkedList implements List {

    private Node first;

    public DoublyLinkedList() {
        this.first = null;
    }

    @Override
    public int size() throws ListException {
        if (isEmpty()) {
            throw new ListException("SingleLinkedList is empty");
        }
        Node aux = first;
        int count = 0;
        while (aux != null) {
            count++;
            aux = aux.next;
        }
        return count;

    }

    @Override
    public void clear() {
        this.first = null;
    }

    @Override
    public boolean isEmpty() {
        return this.first == null;
    }

    @Override
    public boolean contains(Object element) throws ListException {

         if(isEmpty()){
            throw new ListException("SinglyLinkedList is empty");
        }
        Node aux = first;
        while(aux!=null){
            if(util.Utility.equals(aux.data, element)){
                return true;
            }
            aux = aux.next;
        }
        return false; //indica q el elemento no existe

    }
     public boolean contains1(Object element) throws ListException {

        if(isEmpty()){
            return false;
        }
        Node aux = first;
        while(aux!=null){
            if(util.Utility.equals(aux.data, element)){
                return true;
            }
            aux = aux.next;
        }
        return false; //indica q el elemento no existe

    }

    @Override
    public void add(Object element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            this.first = newNode;
        } else {
            Node aux = first;
            while (aux.next != null) {
                aux = aux.next;
            }
            aux.next = newNode;
            newNode.prev=aux;

        }

    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            this.first = newNode;
        }
        newNode.next = first;
        first.prev = newNode;
        first = newNode;
    }

    @Override
    public void addLast(Object element) {
        add(element);
    }

    @Override
    public void addInSortedList(Object element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            this.first = newNode;
        } else {
            if (util.Utility.greaterT(first.data, element)) {
                newNode.next = first;
                first.prev = newNode;
                first = newNode;
            } else {
                Node prev = first;
                Node aux = first.next;
                boolean added = false;
                while (aux != null && !added) {
                    if (util.Utility.lessT(element, aux.data)) {
                        prev.next = newNode;
                        newNode.prev = prev;
                        newNode.next = aux;
                        aux.prev = newNode;
                        added = true;
                    }
                    prev = aux;
                    aux = aux.next;
                }
                if (!added) {
                    prev.next = newNode;
                    newNode.prev = prev;
                }
            }

        }
    }

    @Override
    public void remove(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("DoublyLinkedList is empty");
        }
        if (util.Utility.equals(first.data, element)) {
            first = first.next;
        } else {
            Node prev = first;
            Node aux = first.next;
            while (aux != null && !util.Utility.equals(aux.data, element)) {
                prev = aux;
                aux = aux.next;
            }
            if (aux != null && util.Utility.equals(aux.data, element)) {

                prev.next = aux.next;
            }
        }

    }

    @Override
    public Object removeFirst() throws ListException {
        if (isEmpty()) {
            throw new ListException("DoublyLinkedList is empty");
        }
        Object element = first.data;
        first = first.next;
        return element;
    }

    @Override
    public Object removeLast() throws ListException {
        if (isEmpty()) {
            throw new ListException("DoublyLinkedList is empty");
        }
        Node aux = first;
        Node prev = first;
        while (aux.next != null) {
            prev = aux;
            aux = aux.next;
        }
        Object element = aux.data;
        prev.next = null;
        return element;
    }

    @Override
    public void sort() throws ListException {
       if (isEmpty()) {
            throw new ListException("DoublyLinkedList is empty");
        }
        Node aux = first;
        Node prev = first;
        Object temp;
        while (aux.next != null) {
            prev = aux.next;
            while (prev != null) {
                if (util.Utility.lessT(prev.data, aux.data)) {
                    temp = aux.data;
                    aux.data = prev.data;
                    prev.data = temp;
                }
                prev = prev.next;
            }
            aux = aux.next;
        }
    }

    @Override
    public int indexOf(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("DoublyLinkedList is empty");
        }
        Node aux = first;
        int index = 1;
        while (aux != null) {
            if (util.Utility.equals(aux.data, element)) {
                return index;
            }
            index++;
            aux = aux.next;
        }
        return -1;
    }

    @Override
    public Object getFirst() throws ListException {
        if (isEmpty()) {
            throw new ListException("DoublyLinkedList is empty");
        }
        return first.data;
    }

    @Override
    public Object getLast() throws ListException {
        if (isEmpty()) {
            throw new ListException("DoublyLinkedList is empty");
        }
        Node aux = first;
        while (aux.next != null) {
            aux = aux.next;
        }
        return aux.data;

    }

    @Override
    public Object getPrev(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("DoublyLinkedList is empty");
        }

        Node prev = first;
        Node aux = first.next;

        while (aux != null) {
            if (aux.data == element) {
                return prev.data;

            }
            prev = aux;
            aux = aux.next;
        }

        return null;
    }

    @Override
    public Object getNext(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("DoublyLinkedList is empty");
        }
        Node aux = first;
        while (aux.next != null) {
            if (aux.data == element) {
                return aux.next.data;
            }
            aux = aux.next;

        }
        return null;
    }

    @Override
    public Node getNode(int index) throws ListException {
        if(isEmpty()){
            throw new ListException("SinglyLinkedList is empty");
        }
        Node aux = first;
        int i = 1; //el indice del primer elemento de la lista
        while(aux!=null){
            if(util.Utility.equals(i, index)){
                return aux; //ya lo encontro
            }
            i++;
            aux = aux.next; 
        }
        return null; //si llega aqui, no encontro el nodo
    }

    @Override
    public String toString() {

        String result = "DOUBLY LINKED LIST\n";
        Node aux = first;
        while (aux != null) {
            result += aux.data + " ";
            aux = aux.next;
        } // While
        return result;

    }

}
