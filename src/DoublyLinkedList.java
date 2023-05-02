import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.security.InvalidParameterException;

public class DoublyLinkedList <T>{
	public int size;
	public Node<T> head;
	public Node<T> tail;
	
	public DoublyLinkedList() {
		head = new Node<T>();
		tail = new Node<T>();
		head.next = tail;
		tail.prev = head;
		size = 0;
	}
	
	public void addFirst(T x) {
		Node<T> n = new Node<T>(x);
		n.next = head.next;
		head.next = n;
		n.next.prev = n;
		n.prev = head;
		
		size++;
	}
	
	public void addLast(T x) {
		Node<T> n = new Node<T>(x);
		n.prev = tail.prev;
		tail.prev = n;
		n.prev.next = n;
		n.next = tail;
		size++;
	}
	
	public void removeAt(int index) {
		
		if(index < 0 || index >= size) {
			throw new InvalidParameterException("index out of range : "+index);
		}
		
		int i=0;
		Node<T> p = head;
		while(p.next != tail && i < index) {
			p = p.next;
			i++;
		}
		p.next = p.next.next;
		p.next.prev = p;
		size--;
	}
	
	public void insert(T x, int i) {
		int k=0;
		Node<T> n;
		if(i > size)return;
		if(i > size/2) {
			n = tail;
			k=size;
			for(;k>i-1;k--) {
				n=n.prev;
			}
		}
		else {
			n=head;
			for(k=0; k<i; k++) {
				n=n.next;
			}
		}
		Node<T> m = n.next;
		n.next = new Node<T>();
		n.next.data = x;
		n.next.next = m;
		n.prev = n;
		m.prev = n.next;
	}
	
	public T get(int index) {
		if (index <0 || index >=size)
		{
			throw new InvalidParameterException("index out of range :"+index);
		}
		if(index < size / 2)
		{
			int i = 0;
			Node<T> p = head;
			while(p.next != null && i <= index)
			{
				p = p.next;
				i++;
			}
			
			return p.data;
		}
		int i = size;
		Node<T> p = tail;
		while(p.prev != null && i != index)
		{
			p = p.prev;
			i--;
		}
		
		return p.data;
	}
	
	public void draw(Graphics g) {
		int i = 0;
		Node<T> p = head;
		Font fn = new Font ("Serif", Font.BOLD, 20);
		while(p != null) {
			//Draw Oval
			i++;
			g.setColor(Color.BLACK);
			g.fillOval(50+60*i, 50, 50+1, 50+1);
			g.setColor(Color.GREEN);
			g.fillOval(50+60*i, 50, 50, 50);
		
			//Draw Data
			g.setColor(Color.BLACK);
			g.setFont(fn);
			g.drawString(""+p.data, 40+60*i+15, 80);
			
			//Draw straight line
			g.drawLine(40+60*i+50, 55, 40+60*i+50, 95);
		
			//Draw arrow line
			g.drawLine(40 + 60 *i + 50, 65, 50+60*(i+1), 65);
			g.drawLine(50 + 60 * (i+1), 75-10, 50+60 * (i+1) - 5, 65-10);
			g.drawLine(50 + 60 * (i + 1), 75-10, 50+60*(i+1)-5, 85-10);
			
			g.drawLine(40 + 60 *i + 50, 85, 50+60*(i+1), 85);
			g.drawLine(50+60 * (i+1) - 20, 75+10, 40 + 60 * (i+1), 65+10);
			g.drawLine(50+60 * (i+1) - 20, 75+10, 40 + 60 * (i+1), 65+10);
			g.drawLine(30 + 60 * (i + 1), 75+10, 50+60*(i+1)-10, 85-10+20);
			p=p.next;
		}
	}
	
}
