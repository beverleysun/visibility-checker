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
        return getClassNames(_folder.getAbsolutePath() + _osSlash, _folder);
    }

    public List<String> getClassNames(String basePath, File directory) {
        File[] files = directory.listFiles();
        if (files == null) {
            return new ArrayList<>();
        }

        List<String> classNames = new ArrayList<String>();
        for (File file: files) {
            if (file.isDirectory()) {
                classNames.addAll(getClassNames(basePath, file));
            }
            else if (file.getName().endsWith(".java")) {
                String filePath = file.getAbsolutePath().replace(basePath, "").replace(".java","");
                classNames.add(filePath.replace(_osSlash, "."));
            }
        }

        return classNames;
    }
}