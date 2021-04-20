## Iteration 1

# Adding a feature

Our project simulates a bookstore system,
- Feature: Worked on searching the book by ISBN, title and author name. 
    - We divided the work amongst ourselves by assigning tasks per layer to atmost 2 people in our group.
    - Kevin made all of the presentation layer for the feature by adding search page, bar and buttons, 
      Harshal and Dawarit worked on the data layer and added a 'fake' database of the books plus book
      database management system. Matt and Animesh worked on the logic layer, which included handling 
      the data passed like book name, ISBN or author between the presentation and data layer. 
- Links
    - [Feature](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/issues/19)
    - [User Stories link](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/issues/17)
    - [Test](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/tree/master/app/src/test/java/com/comp3350_group10/bookstore)
    - [Merge commit](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/commit/091bd90748517e9550814dabf8613bacb1c71c3c)


# Exceptional code

- The "null book tests" in the code was supposed to generate NullPointerExceptions, but the exceptions are  
  caught in each of the methods and will print out message accordingly.
- It is not expected for users to cause the exception with the provided interface, but considering future  
  development, the exceptions were handled gracefully and will not crash the program.  
- [Exception code link](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/blob/master/app/src/test/java/com/comp3350_group10/bookstore/data/model/DataHandlerTest.java)


# Branching

- Our [Branching Strategy](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/blob/master/docs/BranchingStrategy.md)
- [Branching Strategy Picture](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/blob/master/docs/branchingstrategy.png)

# SOLID
Group 11
- How? 
    - Course class can be broken down into smaller classes. Like for example, Course.java should have getters and setters within it.
      This coding strategy firstly does not perform object oriented programming
    - Secondly, This violates Single-responsibility principle as that class should only have one single responsibility of getter and setter for these local variables.
      These getters and setters should be used to perform other functions like add in other classes. Instead of having them all cramped in one class

- [Screen shot 1](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/blob/master/docs/screenshot1.PNG) for Group 11's Course.java

- [Screen shot 2](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/blob/master/docs/screenshot2.PNG) for Group 11's Course.java

- [SOLID's violation link to Group 11's Course.java](https://code.cs.umanitoba.ca/3350-winter-2021-a03/listmycourses-comp3350-a03-group11/-/blob/develop/server/ListMyCoursesServer/src/main/java/group11/listmycourses/server/logic/Course.java)

- [Link to Group 11's SOLID issue](https://code.cs.umanitoba.ca/3350-winter-2021-a03/listmycourses-comp3350-a03-group11/-/issues/49)


# Agile Planning

Did you 'push' any features to iteration 2? 
Yes, we have pushed the below features to iteration 2
- [Sort by any info](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/issues/31)
- [Stock management](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/issues/14)
  
  
  

Did you change the description of any Features or User Stories?  
Yes, we have changed the description for the following features and user stories.  

- User Stories (Added related features and changed previous titles to detailed description):
    - [All](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/issues?label_name%5B%5D=User+stories)
- Features: (Splitted search and sort, and removed technical terms)
    - [Search by any info](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/issues/19)
    - [Sort by any info](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/issues/31)
    - [Login system by management and staffs](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/issues/8)
    - [Export feature](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/issues/6)