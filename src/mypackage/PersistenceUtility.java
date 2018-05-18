package mypackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PersistenceUtility {

	public static void store(String path, ArrayList<Map<String, Map<String, String>>> collection) {
//		System.out.println(collection);
		
		String out = "";
		
		for (Map<String, Map<String, String>> m: collection) {
			Map.Entry<String, Map<String, String>> entry = m.entrySet().iterator().next();
			 
			final String className = entry.getKey();
			
			out += (className + " {\n");
			
			Map<String, String> attributes = entry.getValue();
			
			for (Map.Entry<String, String> e : attributes.entrySet()) {
				 final String key = e.getKey();
				 final String value = e.getValue();
				 
				 out += ("\t" + key + ": " + value + ";\n");
			}
			
			out += "}\n\n";
		}
		
		BufferedWriter bufferedWriter = null;
        
		try {
            File file = new File(path);
            // make sure the file is there
            if (!file.exists()) file.createNewFile();
            Writer writer = new FileWriter(file);
            bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) bufferedWriter.close();
            } catch(Exception e) {
                 e.printStackTrace();
            }
        }
	}
	
	public static ArrayList<Map<String, Map<String, String>>> load(String path) {
		
		// such an insane declaration
		ArrayList<Map<String, Map<String, String>>> collection = new ArrayList<Map<String, Map<String, String>>>();
		
		try {
			Reader freader = new FileReader(path);
			
			StreamTokenizer tokenizer = new StreamTokenizer(freader);
			
			tokenizer.resetSyntax();
			// recreate standard syntax table
			tokenizer.resetSyntax();
			tokenizer.whitespaceChars('\u0000', '\u0020');
			tokenizer.wordChars('a', 'z');
			tokenizer.wordChars('A', 'Z');
			tokenizer.wordChars('\u00A0', '\u00FF');
			tokenizer.commentChar('/');
			tokenizer.quoteChar('\'');
			tokenizer.quoteChar('"');
			tokenizer.eolIsSignificant(false);
			tokenizer.slashSlashComments(false);
			tokenizer.slashStarComments(false);
			// this WOULD be part of the standard syntax
//			tokenizer.parseNumbers();  
			
			// syntax additions
			tokenizer.wordChars('0', '9');
			tokenizer.wordChars('.', '.');
			tokenizer.wordChars('_', '_');
			tokenizer.wordChars('-', '-');
			
			// are they really constants? 
			final int TT_OPENING_BRACKET = 123;
			final int TT_CLOSING_BRACKET = 125;
			final int TT_COLUMN = 58;
			final int TT_SEMICOLON = 59;
			
			String identifier = "";
			
			final int MAX_COUNT = 100;
			int count = 0;
			
			// next till it hits the end of the file
			while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
				
//				System.out.println(tokenizer.toString() + " " + tokenizer.ttype);
				
				Map<String, Map<String, String>> foo = new HashMap<String, Map<String, String>>();
				Map<String, String> bar = new HashMap<String, String>();

				// if it hits a class name
				if (tokenizer.ttype == StreamTokenizer.TT_WORD) {

					// class name
//					System.out.println("Class name: " + tokenizer.sval);
					
					identifier = tokenizer.sval;
					
					// it must hit an opening bracket next
					if (tokenizer.nextToken() != TT_OPENING_BRACKET)
						throw new IOException("[Error] invalid data file format");
					
					while (tokenizer.nextToken() != TT_CLOSING_BRACKET && (++count) < MAX_COUNT) {
						String key = tokenizer.sval;
//						System.out.println("Key: " + tokenizer.sval);
						
						// make sure : is there
						tokenizer.nextToken();
						if (tokenizer.ttype != TT_COLUMN) 
							throw new IOException("[Error] invalid data file format");
						// value can be both a number or a string
						tokenizer.nextToken();
						
						String sval = tokenizer.sval;
//						System.out.println("Value: " + sval);
						
						// make sure ; is there
						tokenizer.nextToken();
						if (tokenizer.ttype != TT_SEMICOLON) 
							throw new IOException("[Error] invalid data file format");
						
						bar.put(key, sval);
					}
					
					foo.put(identifier, bar);
					
				} 
				
				collection.add(foo);
				
			}
			
			freader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		return collection;
		
	}
	
}
