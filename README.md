# epam_final_task
WebDriver final task in Selenium Framework

### Test cases

All test cases has been implemented. The first version of the task is available on the first_release branch. The review_fixes branch, which is merged to main branch, contains updated project with fixed bugs and project issues.

### Test run

Application contains single application property with attribute 'browser' to set web driver to run tests.
```
browser=firefox
```

How to run project:

1. Download project from repository 
```
git clone git@github.com:PiotrDuma/epam_final_task.git
```
2. Move to project folder
```
cd epam_final_task
```
3. Invoke tests with Maven setting up browser attribute to 'firefox' or 'chrome'. 
```
mvn -Dbrowser=firefox clean test
```

### Update changes:

- change singleton WebDriver factory: replace static singleton WebDriver instance with ThreadLocal webdriver provider for parallel testing,
- fix input field clear() method for chrome browser,
- update and clean test cases implementation,
- restore application.properties load class and extend it with maven parameter loader,
- exclude URL class property to application.property variable,
- refactor StringGenerator, simplifty User and DataProvider classes,
- update project structure;
