package com.massive.bakingapp.views.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.massive.bakingapp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.PosterImage), withContentDescription("poster image"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardView),
                                        0),
                                0),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.stepName), withText("Recipe Introduction"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.stepsRecyclerView),
                                        0),
                                1)));
        appCompatTextView.perform(scrollTo(), click());

        ViewInteraction view = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.exo_content_frame),
                                childAtPosition(
                                        withId(R.id.videoView),
                                        1)),
                        0),
                        isDisplayed()));
        view.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.Next),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.RecipeDetailsContainer),
                                        0),
                                2),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.Next), withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.RecipeDetailsContainer),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.Next), withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.RecipeDetailsContainer),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.Next), withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.RecipeDetailsContainer),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

    }
}
