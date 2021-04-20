package com.comp3350_group10.bookstore;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(
        Suite.class
)
@Suite.SuiteClasses(
        {
                LogInTest.class,
                BookSearchTest.class,
                ChangePasswordTest.class,
                CreateUserTest.class,
                SortTest.class,
                StockChangeTest.class,
                PriceChangeTest.class,
                RemoveUserTest.class,
                ForgotPasswordTest.class,
                ContactUsTest.class,
                CheckInfoTest.class,
                AlertTest.class
        }

)
public class AllAcceptanceTests {}
