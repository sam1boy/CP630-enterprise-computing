package ec.lab;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileIO {

	public static void main(String[] args) {

		/*
		 * i/o, BufferedWriter, Scanner reader
		 */
		try {
			FileWriter fw = new FileWriter("textdata.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			String content = "text file i/o";
			bw.write(content);
			bw.close();
			
			System.out.println("read line by line from file");
			FileReader fr = new FileReader("textdata.txt");			
			Scanner sin = new Scanner(fr);
			while (sin.hasNext()) {
				System.out.println(sin.nextLine());
			}
			System.out.print("\n");
			sin.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		/*
		 * stream data (byte array) i/o
		 */
		try {
			File out = new File("stringdata.byte"); 
			OutputStream fout = new FileOutputStream(out);
			String stringdata = "byte array i/o";
			fout.write(stringdata.getBytes());
			fout.close();
			
			File in = new File("stringdata.byte");
			InputStream fin = new FileInputStream(in);
			int i;
			char c;

			System.out.println("read bytes from file");
			while ((i = fin.read()) != -1) {
				c = (char) i;
				System.out.print(c);
			}			
			fin.close();
			System.out.print("\n\n");
			
			
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		/*
		 * String object i/o
		 */
		try {
			String stringdata = "String object i/o";
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("stringdata.obj"));
			os.writeObject(stringdata);
			os.close();

			System.out.println("read String object from file");
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("stringdata.obj"));
			String stringdata1 = (String) is.readObject();
			is.close();
			System.out.println(stringdata1);
			System.out.print("\n");

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		/*
		 * array object i/o
		 */
		double[] arraydata = {0, 1, 2, 3};
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("arraydata.obj"));
			os.writeObject(arraydata);
			os.close();
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("arraydata.obj"));
			double[] arraydata1 = (double[]) is.readObject();
			is.close();
			
			System.out.println("read array object from file");
			for (int i=0; i<arraydata1.length; i++ ) {
				System.out.println(i + " " + arraydata1[i]);
			}
			System.out.print("\n");

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		/*
		 * list object i/o
		 */
		ArrayList<Double> listdata = new ArrayList<Double>();
		for (int i=0; i<4 ; i++ ) {
			listdata.add( (double) i);
		}
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("listdata.obj"));
			os.writeObject(listdata);
			os.close();
			
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("listdata.obj"));
			ArrayList<Double> listdata1 = (ArrayList<Double>) is.readObject();
			is.close();
			
			System.out.println("read arraylist object from file");
			for (int i=0; i<listdata1.size(); i++ ) {
				System.out.println(i + " " + listdata1.get(i));
			}
			System.out.print("\n");

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		 * map object i/o
		 */
		HashMap<Integer, Double> mapdata = new HashMap<Integer, Double>();
		for (int i=0; i<4 ; i++ ) {
			mapdata.put(i, (double) i);
		}
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("mapdata.obj"));
			os.writeObject(mapdata);
			os.close();
			
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("mapdata.obj"));
			HashMap<Integer, Double> mapdata1 = (HashMap<Integer, Double>) is.readObject();
			is.close();
			
			System.out.println("read map object from file");
			for (int key : mapdata1.keySet()) {
				System.out.println(key + " " + mapdata1.get(key));
			}
			System.out.print("\ns");

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		 * map object i/o with specific folder
		 */
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("c:/tmp/mapdata.obj"));
			os.writeObject(mapdata);
			os.close();
			
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("c:/tmp/mapdata.obj"));
			HashMap<Integer, Double> mapdata1 = (HashMap<Integer, Double>) is.readObject();
			is.close();
			

			System.out.println("read map object from file in specific folder");
			for (int key : mapdata1.keySet()) {
				System.out.println(key + " " + mapdata1.get(key));
			}
			System.out.print("\n");
			
		
			File f = new File("c:/tmp/mapdata.obj");
			if (f.exists()) { 
				f.delete();
				System.out.print("file is deleted \n\n");
			}
			

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		 * serializable object i/o
		 */
		try {
			Person agent = new Person();
			agent.setId("007");
			agent.setFistName("James");
			agent.setLastName("Bond");
			agent.setEmail("jb007@mi6.uk");
			agent.getCharacteristic().put("status", "on vocation");
			
		
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("persondata.obj"));
			os.writeObject(agent);
			os.close();
			
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("persondata.obj"));
			Person agent1 = (Person) is.readObject();
			is.close();
			
			System.out.println("read serializable object from file");
			System.out.println(agent1.toString());
			System.out.print("\n");
					

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
