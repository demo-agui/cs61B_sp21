package deque;

import java.util.Iterator;


public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private IntNode sentinel;
    private int size ;

    public  class IntNode{
        public T item ;
        public IntNode next ;
        public IntNode prev ;
        public IntNode (IntNode n, T i ,IntNode m) {
            prev = n ;
            item = i ;
            next = m ;
        }
    }

    public LinkedListDeque() {
        sentinel =new IntNode(sentinel, null, sentinel) ;
        size = 0 ;
    }
    public LinkedListDeque(T item) {
        sentinel =new IntNode(sentinel, null, sentinel) ;
        sentinel.next =new IntNode(sentinel, item, sentinel)  ;
        sentinel.prev = sentinel.next ;
        size =1 ;
    }
    @Override
    public void addFirst(T item) {
        sentinel.next =new IntNode(sentinel, item, sentinel.next);
        if (sentinel.next.next == null) {
            sentinel.prev = sentinel.next ;
        }else {
            sentinel.next.next.prev =sentinel.next ;
        }
        size+=1 ;
    }

    @Override
    public void addLast(T item) {
        sentinel.prev = new IntNode(sentinel.prev , item, sentinel.next);
        if (sentinel.prev.prev == null) {
            sentinel.next = sentinel.prev ;
        }else {
            sentinel.prev.prev.next =sentinel.prev ;
        }
        size +=1 ;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int index =0  ;
        while (index < size) {
            if (sentinel.next.item == null) break;
            System.out.println(sentinel.next.item + " ");
            sentinel = sentinel.next ;
            index++ ;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty())
            return null ;
        T result = sentinel.next.item ;
        sentinel.next = sentinel.next.next ;
        if (sentinel.next == null) {
            sentinel.prev = sentinel.next ;
        }else {
            sentinel.next.prev = sentinel ;
        }
        size = removeSize(size) ;
        return result ;
    }

    @Override
    public T removeLast() {
        if (isEmpty())
            return null;
        T result = sentinel.prev.item ;
        sentinel.prev = sentinel.prev.prev ;
        if (sentinel.prev == null) {
            sentinel.next = sentinel.prev ;
        }else {
            sentinel.prev.next = sentinel ;
        }
        size = removeSize(size) ;
        return result ;

    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size-1) {
            return null ;
        }
        IntNode curr = sentinel.next ;
        for (int i =0; i < index; i++) {
            if (i == index){
                return curr.item;
            }
            curr = curr.next ;
        }
        throw new AssertionError() ;
    }
    public int removeSize(int size) {
        if (size >=1) size-=1 ;
        return size ;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        LinkedListDeque<T> lld = (LinkedListDeque<T>) o;
        if (lld.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (lld.get(i) != get(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * The Deque objects we’ll make are iterable (i.e. Iterable<T>)
     * so we must provide this method to return an iterator.
     */
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private IntNode ptr;
        LinkedListDequeIterator() {
            ptr = sentinel.next;
        }
        public boolean hasNext() {
            return (ptr != sentinel);
        }
        public T next() {
            T item = (T) ptr.item;
            ptr = ptr.next;
            return item;
        }
    }
}
