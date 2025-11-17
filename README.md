# epam_final_task
WebDriver final task in Selenium Framework

### Test cases

All test cases has been implemented. While firefox tests passes fine, the chrome driver meets some kind of sync issues and it doesn't clear input fields. Even explicit wait from WebDriverWait doesn't help.

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

