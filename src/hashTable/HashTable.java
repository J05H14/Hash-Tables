package hashTable;
import java.util.Arrays;

public class HashTable {

	final private Double LOAD_FACTOR = 0.50;
	final private int INITIAL_CAP = 20;
	final private Entry EMPTY = new Entry("");
	private int capacity;
	private Entry[] hashTable;
	private int size;

	public HashTable(){
		this.capacity = INITIAL_CAP;
		this.size = 0;
		this.hashTable = new Entry[capacity];
		Arrays.fill(this.hashTable, EMPTY);
	}
	
	public HashTable(HashTable copy){
		this.capacity = copy.capacity;
		this.size = copy.size;
		this.hashTable = copy.hashTable;
	}

	public Entry getEMPTY() {
		return EMPTY;
	}

	public void add(String word){
		Entry entry = new Entry(word);

		int elementIndex = word.hashCode() % (this.capacity - 1);
		elementIndex = Math.abs(elementIndex);




		//linear probing
		while(this.hashTable[elementIndex] != EMPTY){
			elementIndex++;
			//System.out.println("COLLISION! TRY INDEX: " + elementIndex);
			elementIndex %= this.capacity;
		}

		if(this.hashTable[elementIndex].equals(entry)){
			this.hashTable[elementIndex].incrementCount();
		}
		else{
			this.hashTable[elementIndex] = entry;
			this.size++;
		}

		Double ratio = (double) (this.size / this.capacity);

		if(ratio.compareTo(LOAD_FACTOR) > 0){
			//System.out.println("rehash");
			this.hashTable = rehash();
		}

	}

	public int find(Entry word){
		int index = word.hashCode() % capacity;

		while(this.hashTable[index] != word){
			index++;
			index %= this.size;

		}
		if(this.hashTable[index] != word){
			return -1;
		}

		return index;
	}


	public Entry[] rehash(){
		this.capacity *= 2;
		Entry[] newTable = new Entry[this.capacity];
		Arrays.fill(newTable, EMPTY);

		Entry[] noEmpty = removeEmpty();

		for(int i = 0; i < noEmpty.length; i++){

			int elementIndex = noEmpty[i].hashCode() % this.capacity;
			elementIndex = Math.abs(elementIndex);

			while(newTable[elementIndex] != EMPTY){
				elementIndex++;
				elementIndex %= this.size;
			}

			newTable[elementIndex] = noEmpty[i];

		}
		return newTable;

	}

	private Entry[] removeEmpty(){
		Entry[] newTable = new Entry[this.size];
		int size = 0;

		for(int i = 0; i < this.hashTable.length; i++){
			if(this.hashTable[i] != EMPTY){
				newTable[size] = this.hashTable[i];
				size++;
			}
		}
		return newTable;
	}

	public double getLOAD_FACTOR() {
		return LOAD_FACTOR;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Entry[] getHashTable() {
		return hashTable;
	}

	public void setHashTable(Entry[] hashTable) {
		this.hashTable = hashTable;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getINITIAL_CAP() {
		return INITIAL_CAP;
	}
}
