package Main;

import Objects.Template;

import java.io.*;

/**
 * Created by Gebruiker on 7-2-2015.
 */
public class Files {
    public static boolean saveTemplate(Template o)
    {
        try{
            File file = new File(Main.folder + "/"  + o.getTemplateName() + ".template");
            if (!file.exists())
            {
                file.createNewFile();
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
                output.writeObject(o);
                output.flush();
                output.close();
                return true;
            }
            System.out.println("File already exists");
            return false;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public static Template loadTemplate(String name)
    {
        try {
            File file = new File(Main.folder + "/" + name + ".template");
            if (!file.exists())
            {
                System.out.println("This file doesn't exist");
                return null;
            }
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
            Object result = input.readObject();
            input.close();
            if (result instanceof Template)
            {
                return (Template)result;
            }
            return null;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean deleteTemplate(String name)
    {
        try {
            File file = new File(Main.folder + "/" + name + ".template");
            if (!file.exists())
            {
                return false;
            }
            file.delete();
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
