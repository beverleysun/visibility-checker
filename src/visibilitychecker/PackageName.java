package visibilitychecker;

public class PackageName {
    private final String _packageForChecking;

    public PackageName(){
        String packageName = VisibilityChecker.class.getPackage().toString();
        int packageNameLength = packageName.length();
        int _visibilitycheckerLength = 18;
        _packageForChecking = packageName.substring(8,packageNameLength - _visibilitycheckerLength);
    }

    public String getPackageForChecking(){
        return _packageForChecking;
    }
}
