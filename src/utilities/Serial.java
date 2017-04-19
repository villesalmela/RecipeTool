package utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class contains methods for serialization and deserialization.<br>
 * Following methods are available:<br>
 * -from file to object<br>
 * -from stream to object<br>
 * -from bytearray to object<br>
 * <br>
 * -from object to file<br>
 * -from object to stream
 * @author Ville Salmela
 *
 */
public class Serial {

	/**
	 * This constructor is private, thus preventing the instantiation of this class.
	 */
	private Serial() {}
	
	/**
	 * This method will deserialize an object, which is loaded from a file.
	 * 
	 * @param path A String path to the file, which contains the serialized object.
	 * @return A deserialized object.
	 * @throws IOException if deserialization fails.
	 */
	public static Object fromFile(String path) throws IOException{
		
		// Read file to stream
		try (	FileInputStream fileIn = new FileInputStream(path);
				
				// Transform file stream into object stream
				ObjectInputStream in = new ObjectInputStream(fileIn);) {
			
			// Read object from stream and return it
			return in.readObject();
			
		} catch (Exception exception) {
			
			// If process fails, throw exception
			throw new IOException();
		} 
	}// end method fromFile
	
	/**
	 * This method will deserialize an object, which is in stream format.
	 * 
	 * @param stream A stream, which contains the serialized object.
	 * @return A deserialized object.
	 * @throws IOException if deserialization fails.
	 */
	public static Object fromStream(InputStream stream) throws IOException{
			
		// Transform inputstream into object stream	
		try (ObjectInputStream in = new ObjectInputStream(stream)) {
			
			// Read object from stream and return it
			return in.readObject();
			
		} catch(Exception exception){
			
			// If process fails, throw exception
			throw new IOException();
		}
	}// end method fromStream
	
	/**
	 * This method will deserialize an object, which is in a form of a byte array.
	 * 
	 * @param byteArr A byte array, which contains the serialized object.
	 * @return A deserialized object.
	 * @throws IOException if deserialization fails.
	 */
	public static Object fromByteArray(byte[] byteArr) throws IOException{
		
		// Read byte array into stream
		try (	ByteArrayInputStream stream = new ByteArrayInputStream(byteArr);
				
				// Transform bytearray stream into object stream
				ObjectInputStream in = new ObjectInputStream(stream);) {
			
				// Read object from stream and return it
				return in.readObject();
				
		} catch(Exception exception){
			
			// If process fails, throw exception
			throw new IOException();
		}
	}// end method fromByteArray
	
	/**
	 * This method will serialize an object, and save it to file.
	 * 
	 * @param object The serializable object.
	 * @param path Where the file should be saved.
	 * @throws IOException if serialization fails.
	 */
	public static void toFile(Object object, String path) throws IOException{
		
		// Prepare writing to file from stream
		try (	FileOutputStream fileOut = new FileOutputStream(path);
				
				// Create object stream that will write to file stream
				ObjectOutputStream out = new ObjectOutputStream(fileOut);){

			// Write object to object stream,
			// which writes it to file stream,
			// which writes it to file system
			out.writeObject(object);
			
		} catch (Exception exception) {
			
			// If process fails, throw exception
			throw new IOException();
		} 
	}// end method toFile
	
	/**
	 * This method will serialize an object, and transform it into a stream.
	 * 
	 * @param object The serializable object.
	 * @return A stream, which contains the serialized object.
	 * @throws IOException if serialization fails.
	 */
	public static ByteArrayInputStream toStream(Object object) throws IOException{
		
		// Prepare writing an bytearray stream
		try (	ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
				
				// Create object stream that will write to bytearray stream
				ObjectOutputStream out = new ObjectOutputStream(byteOut);){

			// Write object to object stream,
			// which writes it to bytearray stream
			out.writeObject(object);
			
			// Transform output into input and return it
			return new ByteArrayInputStream(byteOut.toByteArray());

		} catch (Exception exception) {
			
			// If process fails, throw exception
			throw new IOException();
		} 
	}// end method toStream
}// end class Serial
