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
public class CircularDoublyLinkedList implements List {

    private Node first = null;
    private Node last;

    @Override
    public int size() throws ListException {
        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList is empty");
        }
        Node aux = first;
        int count = 0;
        while (aux != last) {
            count++;
            aux = aux.next;
        }
        return count + 1;

    }

    @Override
    public void clear() {
        this.first = this.last = null;
    }

    @Override
    public boolean isEmpty() {
        return this.first == null;
    }

    @Override
    public boolean contains(Object element) throws ListException {

        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList is empty");
        }
        Node aux = first;
        while (aux != last) {
            if (util.Utility.equals(aux.data, element)) {
                return true;
            }
            aux = aux.next;
        }

        return util.Utility.equals(aux.data, element); //indica q el elemento no existe

    }

    public boolean contains1(Object element) throws ListException {

        if (isEmpty()) {
            return false;
        }
        Node aux = first;
        while (aux != last) {
            if (util.Utility.equals(aux.data, element)) {
                return true;
            }
            aux = aux.next;
        }
        return util.Utility.equals(aux.data, element); 

    }
     public boolean contains2(Object element) throws ListException {

        if (isEmpty()) {
            return false;
        }
        Node aux = first;
        while (aux != last) {
            if (util.Utility.equals2(aux.data, element)) {
                return true;
            }
            aux = aux.next;
        }
        return util.Utility.equals2(aux.data, element); 

    }
    

    @Override
    public void add(Object element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            this.first = this.last = newNode;
        } else {
            last.next = newNode;

            newNode.prev = last;
            last = newNode;
            last.next = first;
            first.prev = last;

        }

    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            this.first = this.last = newNode;
        }
        newNode.next = first;
        first.prev = newNode;
        first = newNode;
        last.next = first;
        first.prev = last;
    }

    @Override
    public void addLast(Object element) {
        add(element);
    }

    @Override
    public void addInSortedList(Object element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            this.first = this.last = newNode;
        } else {
            if (util.Utility.greaterT(first.data, element)) {
                newNode.next = first;
                first.prev = newNode;
                first = newNode;
            } else {
                Node prev = first;
                Node aux = first.next;
                boolean added = false;
                while (aux != last && !added) {
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
                if (util.Utility.lessT(element, aux.data) && !added) {
                    prev.next = newNode;
                    newNode.prev = prev;

                    newNode.next = aux;
                    aux.prev = newNode;
                } else {
                    if (!added) {
                        aux.next = newNode;
                        newNode.prev = aux;
                        last = newNode;
                    }
                }
            }

        }

        last.next = first;
        first.prev = last;
    }

    @Override
    public void remove(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList is empty");
        }
        if (util.Utility.equals(first.data, element)) {
            first = first.next;
        } else {
            Node prev = first;
            Node aux = first.next;
            while (aux != last && !util.Utility.equals(aux.data, element)) {
                prev = aux;
                aux = aux.next;
            }

            if (util.Utility.equals(aux.data, element)) {
                prev.next = aux.next;
                aux.next.prev = prev;
            }
            if (aux == last && util.Utility.equals(aux.data, element)) {
                last = prev;
            }
        }
        last.next = first;
        first.prev = last;

        if (first == last && util.Utility.equals(first.data, element)) {
            clear();
        }

    }
    public boolean remove1(Object element) throws ListException {
        if (isEmpty()) {
            return false;
        }
        if (util.Utility.equals(first.data, element)) {
            first = first.next;
        } else {
            Node prev = first;
            Node aux = first.next;
            while (aux != last && !util.Utility.equals(aux.data, element)) {
                prev = aux;
                aux = aux.next;
            }

            if (util.Utility.equals(aux.data, element)) {
                prev.next = aux.next;
                aux.next.prev = prev;
                return true;
            }
            if (aux == last && util.Utility.equals(aux.data, element)) {
                last = prev;
                return true;
            }
        }
        last.next = first;
        first.prev = last;

        if (first == last && util.Utility.equals(first.data, element)) {
            clear();
            return true;
        }
        return false;

    }

    @Override
    public Object removeFirst() throws ListException {
        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList is empty");
        }
        Object element = first.data;
        first = first.next;
        first.prev = last;

        last.next = first;
        return element;
    }

    @Override
    public Object removeLast() throws ListException {
        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList is empty");
        }
        Node aux = first;
        Node prev = first;
        while (aux.next != last) {
            prev = aux;
            aux = aux.next;
        }
        Object element = aux.data;
        last = prev;
        last.next = first;
        first.prev = last;
        return element;
    }

    @Override
    public void sort() throws ListException {

        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList is empty");
        }
        Node aux = first;
        Node prev = first;
        Object temp;
        while (aux.next != last.next) {
            prev = aux.next;
            while (prev != last.next) {
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
            throw new ListException("CircularDoublyLinkedList is empty");
        }
        Node aux = first;
        int index = 1;
        while (aux != last) {
            if (util.Utility.equals(aux.data, element)) {
                return index;
            }
            index++;
            aux = aux.next;
        }
        if (util.Utility.equals(aux.data, element)) {
            return index;
        }
        return -1;
    }

    @Override
    public Object getFirst() throws ListException {
        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList is empty");
        }
        return first.data;
    }

    @Override
    public Object getLast() throws ListException {
        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList is empty");
        }

        return last.data;

    }

    @Override
    public Object getPrev(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList is empty");
        }

        Node prev = first;
        Node aux = first.next;

        while (aux != last) {
            if (aux.data == element) {
                return prev.data;

            }
            prev = aux;
            aux = aux.next;
        }
        if (aux.data == element) {
            return prev.data;

        }

        return null;

    }

    @Override
    public Object getNext(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList is empty");
        }
        Node aux = first;
        while (aux.next != last) {
            if (aux.data == element) {
                return aux.next.data;
            }
            aux = aux.next;

        }
        if (aux.data == element) {
            return aux.next.data;
        }
        return null;
    }
   
    public Node getNodeLast(){
        return last;
    }

    @Override
    public Node getNode(int index) throws ListException {
        if (isEmpty()) {
            throw new ListException("CircularDoublyLinkedList is empty");
        }
        Node aux = first;
        int i = 1; //el indice del primer elemento de la lista
        while (aux != last) {
            if (util.Utility.equals(i, index)) {
                return aux; //ya lo encontro
            }
            i++;
            aux = aux.next;
        }
        if (util.Utility.equals(i, index)) {
            return aux;
        }
        return null; //si llega aqui, no encontro el nodo
    }

    @Override
    public String toString() {

        String result = "CIRCULAR DOUBLY LINKED LIST\n";
        Node aux = first;
        while (aux != last) {
            result += aux.data + " ";
            aux = aux.next;
        } // While

        return result + aux.data;

    }

}
