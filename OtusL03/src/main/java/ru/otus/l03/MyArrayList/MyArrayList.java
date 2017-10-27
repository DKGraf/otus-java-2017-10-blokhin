package ru.otus.l03.MyArrayList;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private Object[] elements;
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;

    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        elements = new Object[capacity];
    }

    private MyArrayList(E[] elements) {
        this.elements = elements;
        size = elements.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) { //Данную реализацию нагло слизал из оригинального ArrayList
        if (a.length < size) {
            return (T[]) Arrays.copyOf(elements, size, a.getClass());
        }
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(E e) {
        if (size + 1 <= elements.length) {
            elements[size++] = e;
            return true;
        } else {
            expand();
            elements[size++] = e;
            return true;
        }
    }

    @Override
    public void add(int index, E element) {
        int numToMove = size - index;
        if (index + 1 > elements.length) expand();
        System.arraycopy(elements, index, elements, index + 1, numToMove);
        elements[index] = element;
        size++;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] newArray = new Object[size + c.size()];
        System.arraycopy(elements, 0, newArray, 0, size);
        System.arraycopy(c.toArray(), 0, newArray, size, c.size());
        elements = newArray;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndex(index);
        if (index == size) return addAll(c);
        if (index > 0) {
            Object[] newArray = new Object[size + c.size()];
            System.arraycopy(elements, 0, newArray, 0, index);
            System.arraycopy(c.toArray(), 0, newArray, index, c.size());
            System.arraycopy(elements, index, newArray, (index + c.size()), size - index);
            elements = newArray;
            size = elements.length;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    removeByIndex(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(o)) {
                    removeByIndex(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E value = get(index);
        removeByIndex(index);
        return value;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o :
                c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o :
                c) {
            for (int i = 0; i < elements.length; ) {
                if (o.equals(elements[i])) {
                    remove(i);
                } else i++;
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        Object[] retain = new Object[c.size()];
        int retained = 0;
        for (Object o :
                c) {
            if (contains(o)) {
                retain[retained] = o;
                retained++;
            }
        }
        clear();
        if (retained > 0) {
            elements = new Object[retained];
            System.arraycopy(retain, 0, elements, 0, retained);
        }
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        checkIndex(index);
        E currentValue = (E) elements[index];
        elements[index] = element;
        return currentValue;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex == toIndex) return new MyArrayList<>(0);
        checkIndex(fromIndex);
        checkIndex(toIndex);
        int toCopy = toIndex - fromIndex;
        Object[] array = new Object[toCopy];
        System.arraycopy(elements, fromIndex, array, 0, toCopy);
        return new MyArrayList<>((E[]) array);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>(this);
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyIterator<>(this);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        checkIndex(index);
        return new MyIterator<>(this, index);
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) throw new IndexOutOfBoundsException();
    }

    private void expand() {
        int oldSize = elements.length;
        if (oldSize == Integer.MAX_VALUE)
            throw new ArrayIndexOutOfBoundsException("Превышен допустимый размер списка.");
        int newSize = (oldSize * 3) / 2 + 1;
        if (newSize < 0) newSize = Integer.MAX_VALUE;
        Object[] temp = Arrays.copyOf(elements, oldSize);
        elements = new Object[newSize];
        System.arraycopy(temp, 0, elements, 0, oldSize);
    }

    private void removeByIndex(int index) {
        int forMoving = size - index - 1;
        if (forMoving > 0) {
            System.arraycopy(elements, index + 1, elements, index, forMoving);
            elements[--size] = null;
        }
    }
}
