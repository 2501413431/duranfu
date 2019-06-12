//package com.example.demo.study.jdk8.dataStructure;
//
//import java.util.Comparator;
//
///**
// * @Describe
// * @Auth duranfu
// * @Date 2018/9/27
// */
//public class BinarySearchTree<T extends Comparator<? super T>> {
//	private static class BinaryNode<T> {
//
//	}
//
//	private BinaryNode<T> root;
//
//	public BinarySearchTree() {
//		root = null;
//	}
//
//	public void makeEmpty() {
//		root = null;
//	}
//
//	public boolean isEmpty() {
//		return root == null;
//	}
//
//	public boolean contains(T x) {
//		return contails(x, root);
//	}
//
//	public T findMin() {
//
//		if (isEmpty()) {
//			throw new NullPointerException();
//		}
//		return findMin(root).
//	}
//
//	public T findMax() {
//		if (isEmpty()) {
//			throw new NullPointerException();
//		}
//		BinaryNode<T> max = findMax(root);
//
//		return findMax(root).element;
//	}
//
//	public void insert(T x) {
//		root = insert(x, root);
//	}
//
//	public void remove(T x) {
//		root = remove(x, root);
//	}
//
//	public void printTree() {
//
//	}
//
//
//	private boolean contails(T x, BinaryNode<T> t) {
//		if (t == null) {
//			return false;
//		}
//		x.compare(t.element);
//
//		return true;
//	}
//
//	private BinaryNode<T> findMin(BinaryNode<T> t) {
//		return new BinaryNode<T>();
//	}
//
//	private BinaryNode<T> findMax(BinaryNode<T> t) {
//		return new BinaryNode<T>();
//	}
//
//	private BinaryNode<T> insert(T x, BinaryNode<T> t) {
//		return new BinaryNode<T>();
//	}
//
//	private BinaryNode<T> remove(T x, BinaryNode<T> t) {
//		return new BinaryNode<T>();
//	}
//
//	private void printTree(BinaryNode<T> t) {
//
//	}
//
//}
//
//
//
//
//class BinaryNode<T> {
//
//	public T element;
//	public BinaryNode<T> left;
//	public BinaryNode<T> right;
//
//	BinaryNode(T e, BinaryNode left, BinaryNode right) {
//		this.element = e;
//		this.left = left;
//		this.right = right;
//	}
//
//	BinaryNode(T e) {
//		this(e, null, null);
//	}
//
//	public T getElement() {
//		return element;
//	}
//
//	public BinaryNode<T> getLeft() {
//		return left;
//	}
//
//	public BinaryNode<T> getRight() {
//		return right;
//	}
//}
