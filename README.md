## Visibility Checker
#### A program to list out fields and methods in all classes in a package

### How Do I Use This?
1. [Download](https://github.com/beverleysun/visibility-checker/archive/master.zip) this repository
2. Move the `visibilitychecker` folder into the package that requires checking. For example, if the files you want checking are in the package `mypackage`, then move `visibilitychecker` into the `mypackage` folder
3. Refactor the 1st line of all .java files to conform with your package structure. So if you moved it to `mypackage`, change `package visibilitychecker;` to `package mypackage.visibilitychecker;` on the first line. If you copy and paste straight into IntelliJ, then you may get an option to automatically refactor the `visibilitychecker` package.
3. Run the `main` method in `VisibilityChecker`

*Note that this code assumes that your root package is not in a sources root folder (folder highlighted blue in IntelliJ). If it is, you may change this in the `Directory` class at the field `_sourcesRoot` to what your source folder is named.*
