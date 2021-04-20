## Iteration 3 Worksheet

- What technical debt has been cleaned up?

    In iteration 2, we were unable to use the hsqldb as listed in the marking 
rubrics. We had a lot of problems when it came to setting up a proper environment 
for this kind of database. It is classified as a prudent deliberate debt because 
we were aware that failure to implement the database could lose us a lot of marks. 
However, we had no other choice but to deliver a working product with a fake 
database to meet a strict deadline.

    - [Link for Question 1](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/commit/b1dee02c8500d1346e2f24e4055976bcc56aa7b1)


- What technical debt did you leave?
 

    Currently, the findBooks method retrieves all books from the database. The 
sorting is done on the returned list, in logic. This was deliberate and prudent
technical debt. This technique works for us because our database is small. In 
the future, this would need to be changed to use SQL queries to return only the
required books.

- Discuss a Feature or User Story that was cut/re-prioritized

    The user story where a customer would be able to navigate to the “contact us” 
page was initially set to a low priority feature. It was implemented in iteration
3 and ended up being a high priority feature as it was in our vision statement 
and one of the main features of our app that would help make it more user 
friendly. Also, it gave our app more functionality to the customer of the bookstore.  



- Acceptance test/end-to-end

    We have one system test that tests adding a new user to the database. We had 
some flakiness due to the Android window animations complicating the process. 
This caused the test to fail sometimes. We fixed this by disabling window 
transitions in the Android emulator. 

- Acceptance test, untestable

    It was difficult testing user stories that simply “shows information”. That is 
because there is no suitable condition to assert. We decided to just navigate to
the desired activity and pass the test if nothing crashed, just as how we would 
write the acceptance test. We also encountered technical difficulty when 
attempting to switch activities via dropdown menu button. The dropdown 
menu isn’t exactly a component in the activity, but it is a component in the appbar 
layout, that's why the regular way of “onView perform click” didn’t work.

- Velocity/teamwork

    Through the course, our estimates gradually became better. In our first 
iteration, we estimated 3 features and achieved only 1. However, in this 
iteration, we were able to estimate 5 features and achieve all our estimated 
features plus a couple more features.

    - Links: 

        - [Link 1](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/milestones)

        - [Link 2](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/graphs/master/charts)