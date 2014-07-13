package net.schoengeist.android.robolectric;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.fest.assertions.api.ANDROID.assertThat;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Robolectric.shadowOf;


@Config(manifest = "./src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private Activity sut;

    @Before
    public void setup() {
        sut = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void shouldBeNotNull() throws Exception {
        assertTrue(sut != null);
    }

    @Test
    public void shouldHoldTextView() throws Exception {
        View textView = sut.findViewById(R.id.textView);
        assertThat(textView).isNotNull();
    }

    @Test
    public void shouldDisplayHelloWorld() throws Exception {
        TextView textView = (TextView) sut.findViewById(R.id.textView);
        assertThat(textView).containsText(R.string.hello_world);
    }

    @Test
    public void shouldHaveThreeButtons() throws Exception {
        LinearLayout layout = (LinearLayout) sut.findViewById(R.id.linearLayout);
        assertThat(layout)
                .hasChildCount(3)
                .isVisible();
    }

    @Test
    public void shouldHideButton2OnButton1Click() throws Exception {
        sut.findViewById(R.id.button1).performClick();

        View button2 = sut.findViewById(R.id.button2);
        assertThat(button2).isInvisible();
    }

    @Test
    public void shouldStartIntent() throws Exception {
        Intent expectedIntent = new Intent(sut, MainActivity.class);

        sut.findViewById(R.id.button2).performClick();
        Intent startedActivity = shadowOf(sut).getNextStartedActivity();

        assertThat(startedActivity).isEqualTo(expectedIntent);
    }
}