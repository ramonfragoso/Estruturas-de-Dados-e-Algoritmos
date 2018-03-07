package adt.skipList.fromBST;

import adt.bst.BST;
import adt.bst.BSTNode;
import adt.skipList.SkipListImpl;
import adt.skipList.SkipListNode;

public class SkipListFromBSTImpl extends SkipListImpl<Integer> implements SkipListFromBST<Integer> {

   public SkipListFromBSTImpl(int maxHeight) {
      super(maxHeight);
   }

   public void importFromBST(BST<Integer> bst) {

      BSTNode<Integer> aux = (BSTNode<Integer>) bst.getRoot();
      int bstHeight = bst.height() + 1;
      this.maxHeight = bstHeight + 1;
      this.reset();
      if (aux.isEmpty())
         return;
      insertFromBST(aux, bstHeight);
   }

   private void reset() {
      this.root = new SkipListNode<Integer>(Integer.MIN_VALUE, maxHeight, null);
      this.NIL = new SkipListNode<Integer>(Integer.MAX_VALUE, maxHeight, null);
      for (int i = 0; i < this.maxHeight; i++) {
         this.root.getForward()[i] = NIL;
      }
   }

   private void insertFromBST(BSTNode<Integer> node, int height) {
      if (node.isEmpty() && height <= 0)
         return;
      this._insert_(node.getData(), node.getData(), height);
      height--;
      insertFromBST((BSTNode<Integer>) node.getLeft(), height);
      insertFromBST((BSTNode<Integer>) node.getRight(), height);
   }

   private void _insert_(int value, Integer actual, int height) {

      SkipListNode<Integer>[] auxNode = generatePath(value);
      SkipListNode<Integer> node = auxNode[0].getForward(0);
      if (node.getKey() == value && node.getValue() != null)
         node.setValue(actual);
      else {
         SkipListNode<Integer> newNode = new SkipListNode<Integer>(value, height, actual);
         SkipListNode<Integer>[] newNodeForward = newNode.getForward();
         for (int i = 0; i < newNode.getHeight(); i++) {
            SkipListNode<Integer>[] leftForward = auxNode[i].getForward();
            newNodeForward[i] = leftForward[i];
            leftForward[i] = newNode;
         }
      }
   }

   @SuppressWarnings("unchecked")
   private SkipListNode<Integer>[] generatePath(int value) {

      SkipListNode<Integer>[] path = (SkipListNode<Integer>[]) new SkipListNode[this.maxHeight];
      SkipListNode<Integer> node = this.root;
      for (int i = this.maxHeight - 1; i >= 0; i--) {
         while (node.getForward(i).getKey() < value) {
            node = node.getForward(i);
         }
         path[i] = node;
      }
      return path;
   }

}
