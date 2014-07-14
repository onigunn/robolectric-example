package net.schoengeist.android.robolectric;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.fest.assertions.api.ANDROID.assertThat;
import static org.junit.Assert.assertEquals;
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
    public void shouldHaveButton() throws Exception {
        Button button = (Button) sut.findViewById(R.id.button1);
        assertThat(button).isVisible();
    }

    @Test
    public void shouldHaveButtonInLayout() throws Exception {
        LinearLayout layout = (LinearLayout) sut.findViewById(R.id.linearLayout);
        assertThat(layout).hasChildCount(2);
    }

    @Test
    public void shouldStartNexActivity() throws Exception {
        sut.findViewById(R.id.button1).performClick();

        ShadowActivity shadowActivity = shadowOf(sut);
        Intent nextStartedActivity = shadowActivity.getNextStartedActivity();

        Intent expected = new Intent(sut, NextActivitiy.class);
        assertThat(nextStartedActivity).isEqualTo(expected);
    }

    @Test
    public void shouldHaveLauncherIcon() throws Exception {
        ImageView imageView = (ImageView) sut.findViewById(R.id.imageView);
        int imageResourceId = shadowOf(imageView).getImageResourceId();

        assertEquals(imageResourceId, R.drawable.ic_launcher);

    }


    // TEST LANDSCAPE LAYOUT
    @Test
    @Config(qualifiers = "land")
    public void shouldTestLandscapeLayout() throws Exception {
        LinearLayout layout = (LinearLayout) sut.findViewById(R.id.linearLayout);
        assertThat(layout).hasChildCount(3);
    }

}