package hashTable;
import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class HashTableTest {

	static File file = null;

	public static void main(String[] args){
		JFileChooser fc= new JFileChooser();
		int returnVal = fc.showOpenDialog(null);
		
		SimpleList list;
		HashTable table;
		String exportList = "";
		String exportTable = "";
		


		if(returnVal == JFileChooser.APPROVE_OPTION){
			file = fc.getSelectedFile();
		}

		Instant start = Instant.now();
		list = generateSimpleList();
		Instant end = Instant.now();
		System.out.println(Duration.between(start, end));

		Instant start2 = Instant.now();
		table = generateHashTable();
		Instant end2 = Instant.now();
		System.out.println(Duration.between(start2, end2));
		
		table = sortHashTable(table);
		list = sortSimpleList(list);
		
		for(int i = 0; i < table.getCapacity(); i++){
			exportTable = exportTable + table.getHashTable()[i].getWord() + "\n";
		}
		for(int i = 0; i < list.size(); i++){
			exportList = exportList + list.getEntry(i).getWord() + "\n";
		}
		
		saveSimpleList(list);

	}
	
	public static void saveSimpleList(SimpleList list){
		Writer writer = null;

		JFileChooser fc = new JFileChooser();
		int retVal = fc.showOpenDialog(null);
		
		if(retVal == JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
		}
		
		try{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static SimpleList sortSimpleList(SimpleList list){
		SimpleList sortedList = new SimpleList();
		for(int i = 0; i < list.size(); i++){
			Entry first = list.getEntry(i);
			for(int j = i + 1; i < list.size(); j++){
				if(list.getEntry(j).getWord().compareTo(first.getWord()) < 0){
					first = list.getEntry(j);
				}
			}
			sortedList.add(first);
		}
		return sortedList;
	}
	
	public static HashTable sortHashTable(HashTable table){
		HashTable newTable = new HashTable(table);
		for(int i = 0; i < newTable.getCapacity(); i++){
			Entry first = newTable.getHashTable()[i];
			for(int j = i + 1; j < newTable.getCapacity(); j++){
				if(newTable.getHashTable()[j].getWord().compareTo(first.getWord()) < 0){
					first = newTable.getHashTable()[j];
				}
			}
			Entry temp = newTable.getHashTable()[i];
			newTable.getHashTable()[i] = first;
			first = temp;
		}
		return newTable;
		
	}

	public static SimpleList generateSimpleList(){
		SimpleList list = new SimpleList();

		try{
			Scanner sc = new Scanner(file);

			while(sc.hasNext()){
				String word = sc.next();
				Entry entry = new Entry(word);

				if(list.find(word) != -1){
					list.getEntry(list.find(word)).incrementCount();
				}
				else{
					list.add(entry);
				}
			}
			sc.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;

	}
	public static HashTable generateHashTable(){
		HashTable table = new HashTable();

		//System.out.println(table.getHashTable()[0]);
		try{
			Scanner sc = new Scanner(file);

			while(sc.hasNext()){
				String word = sc.next();
				//Entry entry = new Entry(word);

				table.add(word);

				
			}
			sc.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

		//		for(int i = 0; i < table.getHashTable().length; i++){
		//			System.out.println(table.getHashTable()[i]);
		//		}

		return table;
	}
}
