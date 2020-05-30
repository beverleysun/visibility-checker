package visibilitychecker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Directory {
    private File _folder;
    private String _osSlash = "\\"; // For Windows: "\\" For Mac: "/"
    private String _sourcesRoot; // If your root package is in a sources root folder, then initialise this to the name of that folder

    public Directory(String folderName) {
        if (_sourcesRoot != null) {
            _folder = new File(_sourcesRoot + _osSlash + folderName.replace(".", _osSlash));
        } else {
            _folder = new File(folderName.replace(".", _osSlash));
        }

    }

    public List<String> getClassNames() {
        String[] fileNames = _folder.list();
        List<String> classNames = new ArrayList<String>();

        for (String fileName: fileNames) {

            if (fileName.endsWith(".java")){
                classNames.add(fileName.replace(".java",""));
            }
        }

        return classNames;
    }
}