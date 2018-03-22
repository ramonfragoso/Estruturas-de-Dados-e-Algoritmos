package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

   protected DoubleLinkedListNode<T> last;

   @Override
   public void insert(T element) {

      if (element == null)
         return;

      DoubleLinkedListNode<T> aux = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<>(), last);
      if (this.head.isNIL()) {
         super.head = last = aux;
      } else {
         this.last.next = aux;
         this.last = aux;
      }
   }

   @Override
   public void remove(T element) {

      if (head.getData().equals(element)) {
         head = getHead().getNext();
         return;
      } else {
         SingleLinkedListNode<T> aux = getHead().getNext();
         while (!aux.isNIL() && !aux.getData().equals(element)) {
            aux = aux.next;
         }
         if (aux.getData().equals(element)) {
            DoubleLinkedListNode<T> removido = (DoubleLinkedListNode<T>) aux;
            DoubleLinkedListNode<T> anterior = removido.getPrevious();
            DoubleLinkedListNode<T> proximo = (DoubleLinkedListNode<T>) removido.getNext();

            if (!proximo.isNIL())
               proximo.setPrevious(anterior);
            anterior.setNext(proximo);
         }
      }
   }

   @Override
   public void insertFirst(T element) {
      if (element == null)
         return;

      if (this.head.isNIL()) {
         this.insert(element);
      } else {
         DoubleLinkedListNode<T> newHead = (DoubleLinkedListNode<T>) this.getHead();
         this.head = new DoubleLinkedListNode<T>(element, newHead, new DoubleLinkedListNode<T>());
         newHead.setPrevious((DoubleLinkedListNode<T>) this.head);
      }
   }

   @Override
   public void removeFirst() {
      if (this.head.isNIL())
         return;
      if (!this.head.next.isNIL()) {
         DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.getHead().getNext();
         this.setHead(aux);
         return;
      }
      this.setHead(new DoubleLinkedListNode<>());
   }

   @Override
   public void removeLast() {
      if (this.head.isNIL())
         return;
      if (this.size() == 1) {
         super.head = this.last = (DoubleLinkedListNode<T>) super.getHead().getNext();
      } else {
         this.last.getPrevious().setNext(new DoubleLinkedListNode<T>());
         this.last = this.last.getPrevious();
         return;
      }
   }

   public DoubleLinkedListNode<T> getLast() {
      return last;
   }

   public void setLast(DoubleLinkedListNode<T> last) {
      this.last = last;
   }
}
