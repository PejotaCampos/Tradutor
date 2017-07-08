package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tradutor {
    
    private BufferedReader lerArq;
    private FileReader fr;
    
    /* Inicializa um tradutor com seus atributos
    *  prontos para realizarem a leitura do arquivo.
    */
    public Tradutor(String path)
    {
        try {
            this.fr = new FileReader(path);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tradutor.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.lerArq = new BufferedReader(fr);
    }
    
    /* Dependendo do idioma selecionado o Tradutor
    *  sabe qual método de tradução vai invocar.
    */
    public String traduzir(String texto, String idioma) throws IOException
    {
        if(idioma.equals("ptForEn"))
            return this.traduzirPt(texto);
        
        return this.traduzirEn(texto);
    }
    
    /*Traduz do Pt para o En*/
    private String traduzirPt(String texto) throws IOException
    {
        String firstLine = this.lerArq.readLine();
        String[] s = firstLine.split(" = ");
        if(s[0].equalsIgnoreCase(texto))
            return s[1];
       
        while(firstLine != null)
        {
            firstLine = this.lerArq.readLine();
            if(firstLine != null)
                s = firstLine.split(" = ");
            if(s[0].equalsIgnoreCase(texto))
                return s[1];
        }
        
        return texto;
    }
    
    /*Traduz do En para o Pt*/
    private String traduzirEn(String texto) throws IOException
    {
        String firstLine = this.lerArq.readLine();
        String[] s = firstLine.split(" = ");
        if(s[1].equalsIgnoreCase(texto))
            return s[0];
       
        while(firstLine != null)
        {
            firstLine = this.lerArq.readLine();
            if(firstLine != null)
                s = firstLine.split(" = ");
            if(s[1].equalsIgnoreCase(texto))
                return s[0];
        }
        
        return texto;
    }   
    
}
