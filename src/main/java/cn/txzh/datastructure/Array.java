package cn.txzh.datastructure;

import java.util.zip.ZipEntry;

/**
 * Created by leizhao_3 on 2019/5/22.
 */
public class Array<E> {
    private int size;
    private E[] elementData;

    public Array() {
        int capacity = 10;
        elementData = (E[])new Object[capacity];
    }

    public Array(int capacity) {
        if (capacity <= 0) {
            capacity = 10;
        }
        elementData = (E[])new Object[capacity];
    }

    public void add(int index, E e) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }

        if (elementData.length == size) {
            grow(elementData.length*2);
        }

        for (int i = size - 1; i >= index; i--) {
            elementData[i+1] = elementData[i];
        }
        elementData[index] = e;
        size++;
    }

    private void grow(int newCapacity) {
        E[] newElementData = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElementData[i] = elementData[i];
        }
        elementData = newElementData;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public boolean contains(E e) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index overflow");
        }
        return elementData[index];
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index overflow");
        }
        E oldValue = elementData[index];

        for (int i = index + 1; i < size; i++) {
            elementData[i-1] = elementData[i];
        }
        size--;
        elementData[size] = null;
        if (size == elementData.length/2) {
            grow(elementData.length/2);
        }
        return oldValue;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public int find(E e) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public E removeElement(E e) {
        int i = find(e);
        return remove(i);
    }

    public int size() {
        return size;
    }

    public E getFirst() {
        return elementData[0];
    }

    public E getLast() {
        return elementData[size - 1];
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, elementData.length));
        res.append('[');
        for(int i = 0 ; i < size ; i ++){
            res.append(elementData[i]);
            if(i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    public static void main(String[] args) {

        Array<Integer> arr = new Array<>();
        for(int i = 0 ; i < 10 ; i ++)
            arr.addLast(i);
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(2);
        System.out.println(arr);

        arr.removeElement(4);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);
    }
}
