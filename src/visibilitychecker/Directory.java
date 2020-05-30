package visibilitychecker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Directory {
    private File _folder;
    private String _osSlash = "\\"; // For Windows: "\\" For Mac: "/"
    private String _sourcesRoot = "" + _osSlash; // If your root package is in a sources root folder, then change this the name of that folder

    public Directory(String folderName) {
        _folder = new File(_sourcesRoot + folderName.replace(".", _osSlash));
    }

    public List<String> getClassNames() {
        String[] fileNames = _folder.list();
        List<String> classNames = new ArrayList<String>();

        for (String fileName: fileNames) {
            int nameLength = fileName.length() - 1;

            if (fileName.substring(nameLength-4).equals(".java")){
                classNames.add(fileName.replace(".java",""));
            }
        }

        return classNames;
    }
}