package assigOne;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;



public class Driver{	
	public static void main(String[] args) throws IOException {		
		
		dateGenerator(100);
		SinglyList<Numerology> singlyList = singlyListCreator();
		DoublyList<Numerology> doublyList = doublyListCreator();
		
			
		System.out.println("Numerology report in SLL:");
		singlyList.printElement();
		System.out.println("\n\nNumerology report in DLL transversing backwards:\n");
		doublyList.reverse();
		doublyList.printlist(doublyList.head);
		
		
		for(int i = 0; i< singlyList.size(); i++) { 
			String prediction = singlyList.getElement(i).getPrediction();
			writeFile(prediction,"prediction.txt");
		}
		
			
		
	}
	
	// ----------- DOUBLY LINKED LIST CLASS ------------ 
	
	private static class DoublyList<E> {
		private Node<E> head;
		static class Node<E> {
			private E element;
			private Node<E> prev;
			private Node<E> next;
			
			public Node(E e) {
				element = e;
				next=prev=null;
			}			
		}
		
		public void addLast(E newElement) {
			Node<E> newNode = new Node<E>(newElement);
			Node<E> last = head;
			
			newNode.next = null;
			if(head == null) {
				newNode.prev = null;
				head = newNode;
				return;
			}
			
			while(last.next!= null) {
				last = last.next;
			}
			
			last.next = newNode;
			newNode.prev= last;
		}
		
		public void printlist(Node<E> node) {
			while (node != null) {
	            System.out.println(node.element);
	            node = node.next;
	        }
		}
		
		public void reverse() {
			Node<E> temp = null;
			Node<E> current = head;
			
			while (current != null) {
	            temp = current.prev;
	            current.prev = current.next;
	            current.next = temp;
	            current = current.prev;
	        }
			if (temp != null) {
	            head = temp.prev;
	        }
		}
			
		
	}
	
	
	
	
	// ----------- SINGLY LINKED LIST CLASS ------------
	
	private static class SinglyList<E> {
		private static class Node<E> {
			private E element;
			private Node<E> next;
			public Node(E e, Node<E> n) {
				element = e;
				next = n;
			}
			public E getElement() {
				return element; 
			}
			public Node<E> getNext() {
				return next;
			}
			public void setNext(Node<E> n) {
				next= n;
			}
		}
		private Node<E> head = null;
		private Node<E> tail = null;
		private int totalElements = 0;
		public SinglyList() {}
				
		
		public void add(E element) {
			Node<E> newElement = new Node<>(element,null);
			if(totalElements == 0) {
				head= newElement;
			} else 
				tail.setNext(newElement);
			tail=newElement;
			this.totalElements ++;
			}
			
		public int size() {
			return totalElements;
		}

		public Node<E> getNode(int position) {
			if(!(position >= 0 && position < totalElements)) {
				throw new IllegalArgumentException("non-existent position");
			}
			
			Node<E> current = head;
			for(int i = 0; i < position; i++) {
				current = current.getNext();
		    }
		    return current;
			
		}
		public E getElement(int position) {
			return this.getNode(position).getElement();
		} 		
		
		
		public void printElement() {
			for(int i = 0; i < totalElements; i++) {
				System.out.println(getNode(i).getElement());
			}
		}	
		
	}
	
	
	
	
	// ----------- WRITE FILES ------------
		
	public static void writeFile (String content, String fileName) throws IOException {
		PrintWriter out = null;
		
		out= new PrintWriter(new FileWriter(fileName, true));
		out.println(content);
		out.close();
		
	}	
	
	
	
	// ----------- GENERATE N DATES ------------
	
	public static void dateGenerator (int numberOfDates) throws IOException {
		Random random = new Random();
		
		
		int minYear = 1500;
		int maxYear = 2020;
		
		int minMonth = 1;
		int maxMonth = 12;
		
		for (int i = 0; i < numberOfDates; i++) {
			
			int yyyy = random.nextInt(maxYear - minYear) + minYear;
			int mm = random.nextInt(maxMonth - minMonth) + minMonth;
			
			int maxDay;
			int minDay = 1;
			
			if(mm == 4 || mm == 6 || mm == 9 || mm == 11) {
				maxDay = 30;
			} else if (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12){
				maxDay = 31;
			} else {
				maxDay=28;
			}
			
			int dd = random.nextInt(maxDay - minDay) + minDay;
			
			String date = mm + " " + dd + " "+yyyy;
			writeFile(date, "dates.txt");
		}
		
	}
	
	
	// ----------- RETURN PREDICTION BASED ON RESULT ------------
	
	public static String predictionCreator(int result) {
		
		if (result == 1) return "Error 404: I couldn't predict anything";
		if (result == 2) return "Lucky you! You will get an A in Data Structures!"; 
		//Btw I was born in 11/22/1994, which is number 2. Hopefully that prediction is true lol
		if (result == 3) return "Maybe try to run the code again so you get a different number.";
		if (result == 4) return "You will get a full scholarship!";
		if (result == 5) return "You really don't wanna know";
		if (result == 6) return "Pick another number. I do not like odd numbers.";
		if (result == 7) return "Nothing good will happen to you, because 7 is cliche!";
		if (result == 8) return "Don't press your luck.";
		if (result == 9) return "Write your own prediction here ____.";
		
		return null;
		
	}
	
	
	
	// ----------- CALCULATE INT TO A SINGLE DIGIT ------------
	
	public static int singleDigitCalculator (int month, int day, int year) {
		int firstAddition = month+day+year;
		int result = 0;
	
		while(firstAddition > 0 || result > 9) { 
			
			if(firstAddition == 0) {
				firstAddition = result;
				result = 0;
			}
			
			
			result = result + firstAddition % 10;
			firstAddition = firstAddition / 10;
		}
		return result;
	}
	
	
	// ----------- READ FILE AND RETURN A NUMEROLOGY OBJECT ------------
	
	public static Numerology readFile(Scanner reader) {
		Scanner date = new Scanner(reader.nextLine()); 
		
		int month = date.nextInt(); 
		int day = date.nextInt();
		int year = date.nextInt();
			
		
		int result= singleDigitCalculator(month,day,year);
		
		String prediction = predictionCreator(result);
					
		Numerology object = new Numerology(month,day,year,result,prediction);	
		date.close();
		return object;
		
	}
	
	
	// ----------- CREATING A DOUBLY LINKED LIST ------------
	
	
	public static DoublyList<Numerology> doublyListCreator() throws FileNotFoundException {
		File obj = new File("dates.txt"); 
		
		Scanner reader = new Scanner(obj);
		
		DoublyList<Numerology> numerologyList= new DoublyList<Numerology>();
		while(reader.hasNextLine()) {
			Numerology object = readFile(reader);
			numerologyList.addLast(object);
		}
		reader.close();
		return numerologyList;	
		
	}
	
	
	// ----------- CREATING A SINGLY LINKED LIST ------------
	
	public static SinglyList<Numerology> singlyListCreator() throws FileNotFoundException {
		File obj = new File("dates.txt"); 
			
		Scanner reader = new Scanner(obj);
		
		SinglyList<Numerology> numerologyList= new SinglyList<Numerology>();
		
		while(reader.hasNextLine()) {
			Numerology object = readFile(reader);							
			numerologyList.add(object);
						
					
		}
		
		reader.close();
		return numerologyList;			
		
	}		
	
	
}
