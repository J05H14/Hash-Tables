package hashTable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.JFileChooser;

public class Results {

	public static void main(String[] args) {
		SimpleList list;
		HashTable table;
		String exportList = "";
		String exportTable = "";
		
		File file = null;
		JFileChooser fc= new JFileChooser();
		int returnVal = fc.showOpenDialog(null);

		if(returnVal == JFileChooser.APPROVE_OPTION){
			file = fc.getSelectedFile();
		}
		
//		table = HashTableTest.generateHashTable();
		list = HashTableTest.generateSimpleList(file);
		
//		table = sortHashTable(table);
		list = sortSimpleList(list);
		
//		for(int i = 0; i < table.getCapacity(); i++){
//			exportTable = exportTable + table.getHashTable()[i].getWord() + "\n";
//		}
		for(int i = 0; i < list.size(); i++){
			exportList = exportList + list.getEntry(i).getWord() + "\n";
		}
		
		saveSimpleList(list, exportList);

	}
	
	public static void saveSimpleList(SimpleList list, String words){
		File file = null;
		Writer writer = null;

		JFileChooser fc = new JFileChooser();
		int retVal = fc.showOpenDialog(null);
		
		if(retVal == JFileChooser.APPROVE_OPTION){
			file = fc.getSelectedFile();
		}
		
		try{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			writer.write(words);
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
}
