## INTRODUCTION
This is the start of a simple weather app that currently:
- Shows weather information for the current day in London
- Shows weather information for the future in London

## WHAT IS NEEDED
- Android studio or any other viable IDE that supports building android apps.
- Viable Andorid device or Emulators with a minimum of Android API 21 (5.0 Kitkat)

## HOW TO RUN
In the terminal:
```terminal
$ git clone https://github.com/APBrown/weatherApp.git
```
You may also use programs like SourceTree which uses a GUI rather than the terminal.
Once the project has been downloaded you may open the projetc with your IDE.

You can run both the app and tests form the IDE.

## SCREENSHOTS
London Weather Loaded

![Weather Loaded](https://github.com/APBrown/weatherApp/blob/master/Screenshots/Weather%20Loaded.png "Weather Loaded")

Weather Loading

![Weather Loading](https://github.com/APBrown/weatherApp/blob/master/Screenshots/Weather%20Loading.png "Weather Loaded")

Error message

![Error Message](https://github.com/APBrown/weatherApp/blob/master/Screenshots/Error%20Message.png "Error Message")
https://github.com/APBrown/weatherApp/blob/master/Screenshots/Weather%20Loading.png

## IMPROVEMENTS / TECH DEBT / FEATURES / FUTURE UPDATES
I am my own worst critic and very critical of my own work, so i believe there is a lot I could improve in the app given some more time.

- Updating from rxjava to rxjava2: The main reason for using rxjava was that, 
given the time constraints i prefered to use something i have already used, rather than something that would need more time.
- Dependancy Injection using Dagger2: Rather than creating the instances of certian objects on the fly, i would have much
prefered to inject them using Dagger2. Due to the small size of the app and the time constraints i chose not to
set this up due to the very little benifit it would giove to a project of this size. This would mainly be used for injecting the RestClient
and AppSchedulerManager rather than creating instances of these during runtime
- Resources: Moving all the strings, dimensions into resource files. This again was mainly due to the time constraint 
(I am aware that it is bad practice to use String literals in xml files, bad Android developer).
- Mocks that return Mocks: This is also bad practive, but for time reasons i thought this would
be the quickest way to get some tests running
- More Tests and run propper TDD: More tests are always good, using the MVP pattern i have seperated out the android code 
(in the Array Adapter and Activity) form the Java code (in the Presenter and Provider). This allows me to run quick Java tests on java code.
- Espresso: Even more tests are even better. This would test the user journey of the app.
- DTOs: In this instance, since this is a simple app, I did not convert the DTO into a "Business Object". I would normally do this 
to avoid any testable code such as the `WeatherForecastDto.WeatherDto.getIcon()` method. This ideal would be in its own Data object 
after being converted from the DTO.
- Caching the data: Storing the data locally so thata fter the user has recieved the information, they may access the previous 
weather report while offline.
- Improved Error Handling: Give the user spacific and useful error messages, with the option to retry rather than the simple error message that currently exists


- Design: I am by no means an expert in UX, but listed here are some of the minor improvements I would like to make
    
    - Weather for each day: With the API i noticed that it returned the weather for the future days in 3 hour increments. 
    I believe a better user experiance would be to show them the average weather for each day, and create a new activity 
    that would show the breakdown for the day. This would limit the front page to only 5 Items and reduce the clutter 
    and information overload.
    - With some more time and investigation, it would be possible to get what infomration a user would prefer to see 
    when looking up the weather. This could be used to give the user more useful information at a glance.
    - Adding in an EditText field to let the user search for a place in the UK to get the weather would be reasonably easy to implement,
    This would also need corrisponding error (for unrecognised places for example)
    - Refresh button/Pull down to refresh: TTo reload and update the data.
    - Dates: Rather than having the dates in a format supplied by the API, it would be easer for the user to view the date in a more 
    standard format for their country, also using "Tomorrow" rather than the date for the next day would read better.
