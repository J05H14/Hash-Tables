package hashTable;
import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class HashTableTest {

	public static void main(String[] args){
		File file = null;
		JFileChooser fc= new JFileChooser();
		int returnVal = fc.showOpenDialog(null);

		if(returnVal == JFileChooser.APPROVE_OPTION){
			file = fc.getSelectedFile();
		}

		Instant start = Instant.now();
		generateSimpleList(file);
		Instant end = Instant.now();
		System.out.println(Duration.between(start, end));

		Instant start2 = Instant.now();
		generateHashTable(file);
		Instant end2 = Instant.now();
		System.out.println(Duration.between(start2, end2));
		
	}

	public static SimpleList generateSimpleList(File file){
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
	public static HashTable generateHashTable(File file){
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
