## Iteration 2

# Paying off technical debt
- Instance 1 
    
[Instance 1 link](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/commit/7b88ac76c7efee55e2a6fcf814fcb7d745c87504)
    
Deliberate and Prudent

During the initial designing of the Book Detail View, Kevin created the layout dynamically, instead of creating a visual layout. 
It was done this way because it was quick and we needed to finish it for iteration 1.
For iteration 2, we redesigned to the layout in the visual editor, which looks better as it gives us more control.
- Instance 2
    
[Instance 2 link](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/commit/5b27de18e1731c6d933b148d74fef99551bb03e2#b68cd193f074d0c92aa985ee1dc258134dbadcf6_50_55)
    
Inadvertent and Prudent

Initially when working on the data layer, we created BookData.java which had a list of type Book since we wanted to have a list of the available books.
At that point, we didn't know about the concept of tightly coupled classes, so for later iterations we changed the type to IBook.
So instead of depending on the class, it's dependent on the interface now.

# SOLID 
Group 10 from A01
- It looks like they mixed the function from the logic layer with the UI layer. 
  For example, the initItem() function should be placed in the logic layer. Except for the first line, 
  the remaining lines are dealing with logic. It is much better if they can have another function that 
  receives the input as the parameter, have all the logics working on that passed-in parameter 
  and return the result. In conclusion, they violate the S principle. Since this could be fixed in future,
  we are also uploading the screenshot

[Link to the file](https://code.cs.umanitoba.ca/3350-winter-2021-a01/refrigator-tracker-group-10/-/blob/master/app/src/main/java/com/smartkitchen/presentation/AddGroceryItemActivity.java)

[Link to the commit](https://code.cs.umanitoba.ca/3350-winter-2021-a01/refrigator-tracker-group-10/-/commit/39c8fb54349725b2a3beb0dc1681394791850187)

[Link to the screenshot](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/blob/master/docs/Capture.JPG)

# Restrospective 

- During our iteration 1 retrospective we discussed ways to better manage our time and stay organized.
  We also split the workload more evenly, with two people working in each layer.

[Link to discord channels](/docs/Capture_4_.JPG)

- In iteration 1 we didn't get much work done in the first week. We decided to start work earlier this time around.

[Link 1](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/blob/master/docs/Capture_5_.JPG)

[Link 2](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/blob/master/docs/Capture_6_.JPG)

# Design Patterns 

- We used the observer pattern. Since our app is a virtual bookshelf, we needed all the books to be clickable. So every book cover image has an onClickListener that waits for a click.
  This opens the Book Details Activity, which show more details about the selected book. The onClickListeners are added in code, as the whole shelf is populated dynamically through database queries.

[Observer pattern link](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/blob/Daniel/app/src/main/java/com/comp3350_group10/bookstore/business/UI_Handler/TrendingPageFunctions.java#L99)

# Iteration 1 feedback fixes 

- Issue: BUG - Search is showing books out of scope

  The search feature was working incorrectly. It was spliting the the search term on spaces and search for books that matches any of the terms. This caused more specific searches to yield more books that were seemingly not related to the search.

[Link to the issue](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/issues/45)

- We rewrote most of the search code to fix this problem. It now searches for books that match all the terms.

[Link to commit](https://code.cs.umanitoba.ca/3350-winter-2021-a03/winter-2021-a03-group-10/-/commit/81e9a722510870cb40a1794c164b2105c54062da)
