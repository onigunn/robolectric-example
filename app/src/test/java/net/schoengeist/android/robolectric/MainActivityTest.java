package net.schoengeist.android.robolectric;

import android.app.Activity;
import android.view.View;
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

}