import java.util.ArrayList;
import java.util.*;

/**
 * A class to hold details of audio files.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class MusicOrganizer
{
    // An ArrayList for storing the file names of music files.
    private ArrayList<String> files;

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        files = new ArrayList<>();
    }

    /**
     * Add a file to the collection.
     * @param filename The file to be added.
     */
    public void addFile(String filename)
    {
        files.add(filename);
    }

    public void checkIndex(int para)
    {
         if (files.isEmpty()){
            System.out.println("files is empty.");
        }else{
            if (para<0 || para>=files.size()){
                System.out.println("invalid index. range >= 0 < "
                + files.size());
            }
        }
    }

    /**
     * Return the number of files in the collection.
     * @return The number of files in the collection.
     */
    public int getNumberOfFiles()
    {
        return files.size();
    }

    public boolean validIndex(int para)
    {
        if (files.isEmpty()){
            System.out.println("files is empty.");
        }else{
            if (para>=0 && para<files.size()){
                return true;
            }
        }
        return false;
    }

    /**
     * List a file from the collection.
     * @param index The index of the file to be listed.
     */
    public void listFile(int index)
    {
        if(validIndex(index)) 
        {
            String filename = files.get(index);
            System.out.println(filename);
        }
    }

    /**
     * Remove a file from the collection.
     * @param index The index of the file to be removed.
     */
    public void removeFile(int index)
    {
        if(validIndex(index)) 
        {
            files.remove(index);
        }
    }

    public void listAllFiles()
    {
        Iterator itr = files.iterator();
        while (itr.hasNext())
        {
            System.out.println(itr.next());
        }
    }
}
