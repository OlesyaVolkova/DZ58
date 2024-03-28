/*  Создать свой интерфейс List. В его основу его имплементации положить параметризованный объект Node, который будет принимать параметром объект любого класса. Реализовать одну имплементацию (можно без итератора)
Методы, которые нужно реализовать:
- int size()
- Boolean isEmpty()
- Boolean contains(E e)
- void add(E e)
- void remove(E e)
- void remove(int id)
- void addAll(List list)
- void clear()
- E get(int index)
- int indexOf(E e)
 */

public class Main {
    public static void main(String[] args) {
        CustomList<String> customList = new CustomList<>();
        customList.add("Элемент 1");
        customList.add("Элемент 2");

        System.out.println("Размер: " + customList.size());
        System.out.println("Пусто: " + customList.isEmpty());

        System.out.println("Содержит первый элемент: " + customList.contains("Element 1"));

        System.out.println("Элемент с индексом 1: " + customList.get(1));

        System.out.println("Содержит второй элемент: " + customList.indexOf("Element 2"));

        customList.remove("Элемент 1");
        System.out.println("Размер после удаления Элемента 1: " + customList.size());

        customList.clear();
        System.out.println("Размер после очистки: " + customList.size());
    }

    public interface List<E> {
        int size();
        boolean isEmpty();
        boolean contains(E e);
        void add(E e);
        void remove(E e);
        void remove(int index);
        void addAll(List<E> list);
        void clear();
        E get(int index);
        int indexOf(E e);
    }

    public static class CustomList<E> implements List<E> {
        private Node<E> head;
        private int size;

        public CustomList() {
            this.head = null;
            this.size = 0;
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
        public boolean contains(E e) {
            Node<E> current = head;
            while (current != null) {
                if (current.getData().equals(e)) {
                    return true;
                }
                current = current.getNext();
            }
            return false;
        }

        @Override
        public void add(E e) {
            Node<E> newNode = new Node<>(e);
            if (head == null) {
                head = newNode;
            } else {
                Node<E> current = head;
                while (current.getNext() != null) {
                    current = current.getNext();
                }
                current.setNext(newNode);
            }
            size++;
        }

        @Override
        public void remove(E e) {
            Node<E> current = head;
            Node<E> prev = null;
            while (current != null) {
                if (current.getData().equals(e)) {
                    if (prev == null) {
                        head = current.getNext();
                    } else {
                        prev.setNext(current.getNext());
                    }
                    size--;
                    return;
                }
                prev = current;
                current = current.getNext();
            }
        }

        public void remove(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Индекс выходит за рамки");
            }
            if (index == 0) {
                head = head.getNext();
            } else {
                Node<E> current = head;
                for (int i = 0; i < index - 1; i++) {
                    current = current.getNext();
                }
                current.setNext(current.getNext().getNext());
            }
            size--;
        }

        @Override
        public void addAll(List<E> list) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }

        @Override
        public void clear() {
            head = null;
            size = 0;
        }

        public E get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Индекс выходит за рамки");
            }
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current.getData();
        }

        public int indexOf(E e) {
            Node<E> current = head;
            int index = 0;
            while (current != null) {
                if (current.getData().equals(e)) {
                    return index;
                }
                current = current.getNext();
                index++;
            }
            return -1; // Возвращаем -1, если элемент не найден
        }
    }

    public static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}