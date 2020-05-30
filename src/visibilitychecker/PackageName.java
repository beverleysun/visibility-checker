package visibilitychecker;

public class PackageName {
    private String _packageForChecking;
    public PackageName(){
        String packageName = VisibilityChecker.class.getPackage().toString();
        int packageNameLength = packageName.length();
        _packageForChecking = packageName.substring(8,packageNameLength - 18);
    }

    public String getPackageForChecking(){
        return _packageForChecking;
    }
}
