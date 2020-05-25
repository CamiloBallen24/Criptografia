/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

/**
 *
 * @author andre
 */
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteFile {
    public String path;
    public boolean append_to_file;
    
    
    public WriteFile(String file_path , boolean append_value ) {
        this.path = file_path;
        this.append_to_file = append_value;
    }
    
    public void writeToFile( String textLine ) throws IOException {
        FileWriter write = new FileWriter( this.path , this.append_to_file);
        PrintWriter print_line = new PrintWriter( write );
        print_line.printf( "%s" , textLine);
        print_line.close();
    }
}
