package visibilitychecker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Directory {
    private File _folder;
    private String _folderName;

    public Directory(String folderName) {
        _folder = new File(folderName);
        _folderName = folderName;
    }

    public List<String> getFileNames() {

        String[] fileNames = _folder.list();
        List<String> javaFileNames = new ArrayList<String>();

        for (String fileName: fileNames) {
            int nameLength = fileName.length() - 1;

            if (fileName.substring(nameLength-4).equals(".java")){
                javaFileNames.add(_folderName + "\\" + fileName);
            }
        }

        return javaFileNames;
    }
}
