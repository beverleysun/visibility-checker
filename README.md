## Visibility Checker
#### A program to list out fields and methods in all classes in a package

### How Do I Use This?
1. [Download](https://github.com/beverleysun/visibility-checker/archive/master.zip) this repository
2. Move the `visibilitychecker` folder into the package that requires checking
   - Refactor the 1st line of all .java files to conform with your package structure
   - Note that this code assumes that your root package is not in a sources root folder. If it is, you may change this in the `Directory` class at the field `_sourcesRoot` to what your source folder is named. Also note that this also assumes you are using Windows - for Mac users, change the `_osSlash` in `Directory` to a forward-slash.
3. Run the `main` method in `VisibilityChecker`
