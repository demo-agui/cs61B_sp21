package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    protected T[] items ;
    protected int size ;
    protected int nextFirst ;
    protected int nextLast ;

    public ArrayDeque() {
        items =(T[])new Object[8] ;
        size = 0 ;
        nextFirst = 3 ;
        nextLast = 4 ;
    }
    @Override
    public void addFirst(T item) {
        checkCapacity();
        items[nextFirst] = item ;
        nextFirst = minusIndex(nextFirst) ;
        size+=1 ;
    }

    @Override
    public void addLast(T item) {
        checkCapacity();
        items[nextLast] = item ;
        nextLast = addIndex(nextLast) ;
        size+=1 ;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int index =addIndex(nextFirst) ;
        for (int i =0 ; i <size; i++) {
            System.out.println(items[index] + " ");
            index =addIndex(index) ;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0)
            return null ;
        checkCapacity();
        nextFirst =addIndex(nextFirst) ;
        T item =items[nextFirst] ;
        items[nextFirst] = null ;
        size -=1 ;
        return item ;
    }

    @Override
    public T removeLast() {
       if (size == 0)
           return null ;
       checkCapacity();
       nextLast = minusIndex(nextLast) ;
       T item= items[nextLast] ;
       items[nextLast] = null ;
       size-=1 ;
       return item ;
    }


    @Override
    public T get(int index) {
        return items[(nextFirst+1 + index) % items.length];
    }


    public void resize(int capacity) {
        T[] resized = (T[]) new Object[capacity];

        int index = addIndex(nextFirst);
        for (int i = 0; i < size; i++) {
            resized[i] = items[index];
            index = addIndex(index);
        }

        nextFirst = capacity - 1;
        nextLast = size;
        items = resized;
    }
    public void checkCapacity(){
        if (size == items.length) {
            resize(size *2) ;
        }
        if (size > 16 && items.length < size/4) {
            resize(size/4) ;
        }
    }

    public  int minusIndex(int index) {
        return(index + items.length -1) % items.length ;
    }

    public int addIndex(int index) {
        return (index + 1) % items.length ;
    }
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false ;
        }
        Deque other = (Deque) o ;
        if (size != other.size())
            return false ;
        int index = addIndex(nextFirst) ;
        for (int i = 0; i< size; i++) {
            if(!(items[index].equals(other.get(i))))
                return false ;
            index = addIndex(index) ;
        }
        return true ;
    }

    /**
     * The Deque objects we’ll make are iterable (i.e. Iterable<T>)
     * so we must provide this method to return an iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    protected class ArrayDequeIterator implements Iterator<T> {
        private int ptr;

        ArrayDequeIterator() {
            ptr = addIndex(nextFirst);
        }
        public boolean hasNext() {
            return ptr != nextLast;
        }
        public T next() {
            T item =  items[ptr];
            ptr = addIndex(ptr);
            return item;
        }
    }
}
